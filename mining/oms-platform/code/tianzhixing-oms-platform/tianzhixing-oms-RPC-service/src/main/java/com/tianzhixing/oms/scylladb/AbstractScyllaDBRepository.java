package com.tianzhixing.oms.scylladb;

import com.datastax.driver.core.*;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.RetryPolicy;
import com.tianzhixing.oms.model.annotation.Column;
import com.tianzhixing.oms.model.annotation.PrimaryKey;
import com.tianzhixing.oms.model.annotation.Table;
import com.tianzhixing.oms.model.annotation.Transient;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.ResourceBundleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/27.
 */
public abstract class AbstractScyllaDBRepository<T extends Serializable> {

    private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AbstractScyllaDBRepository.class);

    private final static String CONFIGFILE = "datasource.scylladb-config";
    /**
     * 服务器IP
     */
    private static String IP = "localhost";
    /**
     * 端口号
     */
    private static Integer PORT = 9042;
    /**
     * 数据库名称
     */
    private static String KEYSPACE = null;

    private static Session session = null;

    static {
        IP = ResourceBundleUtil.getStringValue("scylladb.cassandra.host", CONFIGFILE);
        PORT = ResourceBundleUtil.getIntegerValue("scylladb.cassandra.port", CONFIGFILE, false);
        KEYSPACE = ResourceBundleUtil.getStringValue("scylladb.cassandra.keyspace", CONFIGFILE);
        //认证配置  ,如果有用户名或密码
//      AuthProvider authProvider = new PlainTextAuthProvider("ershixiong", "123456");
//      LoadBalancingPolicy lbp = new TokenAwarePolicy(
//              DCAwareRoundRobinPolicy.builder().withLocalDc("myDC").build()
//      );
        //读超时或连接超时设置
        int readTimeout = ResourceBundleUtil.getIntegerValue("scylladb.read.time.out", CONFIGFILE, false);
        int connectionTimeout = ResourceBundleUtil.getIntegerValue("scylladb.connection.time.out", CONFIGFILE, false);
        SocketOptions so = new SocketOptions().setReadTimeoutMillis(readTimeout).setConnectTimeoutMillis(connectionTimeout);

        //连接池配置
        //PoolingOptions poolingOptions = new PoolingOptions().setConnectionsPerHost(HostDistance.LOCAL, 2, 3);
        //集群在同一个机房用HostDistance.LOCAL   不同的机房用HostDistance.REMOTE   忽略用HostDistance.IGNORED
        int maxRequestPerConnection = ResourceBundleUtil.getIntegerValue("scylladb.max.request.connection", CONFIGFILE, false);
        int coreConnectionsPerHost = ResourceBundleUtil.getIntegerValue("scylladb.core.min.connection.per", CONFIGFILE, false);
        int maxConnectionsPerHost = ResourceBundleUtil.getIntegerValue("scylladb.core.max.connection.per", CONFIGFILE, false);
        PoolingOptions poolingOptions = new PoolingOptions()
                .setMaxRequestsPerConnection(HostDistance.LOCAL, maxRequestPerConnection)//每个连接最多允许64个并发请求
                .setCoreConnectionsPerHost(HostDistance.LOCAL, coreConnectionsPerHost)//和集群里的每个机器都至少有2个连接
                .setMaxConnectionsPerHost(HostDistance.LOCAL, maxConnectionsPerHost);//和集群里的每个机器都最多有6个连接

        //查询配置
        //设置一致性级别ANY(0),ONE(1),TWO(2),THREE(3),QUORUM(4),ALL(5),LOCAL_QUORUM(6),EACH_QUORUM(7),SERIAL(8),LOCAL_SERIAL(9),LOCAL_ONE(10);
        //可以在每次生成查询statement的时候设置，也可以像这样全局设置
        QueryOptions queryOptions = new QueryOptions().setConsistencyLevel(ConsistencyLevel.ONE);

        //重试策略
        RetryPolicy retryPolicy = DowngradingConsistencyRetryPolicy.INSTANCE;

        Cluster cluster = Cluster.builder()
                .addContactPoints(IP)
                        //.withAuthProvider(authProvider)
                        //.withLoadBalancingPolicy(lbp)
                .withSocketOptions(so)
                .withPoolingOptions(poolingOptions)
                .withQueryOptions(queryOptions)
                .withRetryPolicy(retryPolicy)
                .withPort(PORT)
                .build();
        session = cluster.connect(KEYSPACE);
    }

    private Class<T> clazz;

    private String clazzName;

    private String tableName;

    private int fieldCount;

    public AbstractScyllaDBRepository() {
        // 通过范型反射，取得在子类中定义的class.
        clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
        clazzName = clazz.getName();// clazz.getSimpleName();
        Table table = clazz.getAnnotation(Table.class);
        Assert.notNull(table, "Class[" + clazzName + "] not found @Table annotation...");
        this.tableName = table.name();
        Assert.isTrue(StringUtils.isNotEmpty(tableName), "Class[" + clazzName + "] @Table annotation not has name value ...");
        //检查字段
        fieldCount = _checkField() - 1;
    }

    private int _checkField() {
        Field[] fields = clazz.getDeclaredFields();
        boolean primaryKeyFlag = false;
        int i = 0;
        for (Field field : fields) {
            Transient transientAnnotation = field.getAnnotation(Transient.class);
            if (transientAnnotation == null) {
                Type type = field.getGenericType();
                Class<?> cls = (Class<?>) type;
                Column columnAnnotation = field.getAnnotation(Column.class);
                String fieldName = field.getName();
                Assert.notNull(columnAnnotation, "Class[" + clazzName + "] , field[" + fieldName + "] not found column annotation");
                PrimaryKey primaryAnnotation = field.getAnnotation(PrimaryKey.class);
                if (primaryAnnotation != null) {
                    Assert.isTrue("id".equals(fieldName), "Class[" + clazzName + "] , field[" + fieldName + "] with primary key must be named id");
                    primaryKeyFlag = true;
                }
                //字段必须属于包装类型不能够使用基础类型
                Assert.isTrue(
                        Integer.class.isAssignableFrom(cls)
                                || Long.class.isAssignableFrom(cls)
                                || String.class.isAssignableFrom(cls)
                                || Date.class.isAssignableFrom(cls)
                                || Enum.class.isAssignableFrom(cls)
                                || Boolean.class.isAssignableFrom(cls)
                                || Double.class.isAssignableFrom(cls)
                                || BigDecimal.class.isAssignableFrom(cls),
                        "Class[" + clazzName + "], field[" + fieldName + "] must be object type");
                i++;
            }
        }
        Assert.isTrue(primaryKeyFlag, "Class[" + clazzName + "] , not found primary key");
        return i;
    }

    /**
     * 插入一条数据
     *
     * @param object
     */
    public void insert(final Object object) {
        try {
            if (object.getClass() != clazz) {
                throw new SQLExecutorException("insert error, obj class type[" + object.getClass() + "] not equals clazz[" + clazz + "]");
            }
            ClazzPropertiesHelper helper = new ClazzPropertiesHelper(object);
            helper.handle();
            insert(helper.getProperties(), helper.getPropertyValues());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new SQLExecutorException(e.getMessage());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new SQLExecutorException(e.getMessage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new SQLExecutorException(e.getMessage());
        }
    }

    private void insert(final String[] properties,
                        final Object[] propertyValues) {
        final String sql = _SQLInsert(properties, propertyValues);
        session().execute(sql);
    }

    private String _SQLInsert(final String[] propertyNames,
                              final Object[] propertyValues) {
        Assert.notEmpty(propertyNames, "Class[" + clazzName + "]  property names empty ...");
        Assert.notEmpty(propertyValues, "Class[" + clazzName + "]  property values empty ...");
        Assert.noNullElements(propertyNames, "Class[" + clazzName + "]  property names has null element ...");
//        Assert.noNullElements(propertyValues, "Class[" + clazzName + "]  property values has null element ...");
        Assert.isTrue(propertyNames.length == propertyValues.length, "Class[" + clazzName + "]  property names and values length not equals ...");
        StringBuffer stringBuffer = new StringBuffer("INSERT INTO ").append(tableName).append("(id,");
        StringBuffer valuesBuffer = new StringBuffer(" VALUES(uuid(),");
        for (int i = 0; i < propertyNames.length; i++) {
            stringBuffer.append(propertyNames[i]).append(i + 1 == propertyNames.length ? ")" : ", ");
            Object valueObject = propertyValues[i];
            boolean isStr = valueObject instanceof String;
            if (isStr)
                valuesBuffer.append("'");
            valuesBuffer.append(propertyValues[i] == null ? null : propertyValues[i].toString());
            if (isStr)
                valuesBuffer.append("'");
            valuesBuffer.append(i + 1 == propertyNames.length ? ")" : ", ");
        }
        return stringBuffer.append(valuesBuffer).toString();
    }


    /**
     * 自定义sql查询
     *
     * @param sql
     * @return
     */
    public List<T> list(final String sql) {
        Assert.notNull(sql, "list sql failed, sql is null");
        ResultSet rs = session.execute(sql);
        List<Row> dataList = rs.all();
        List<T> list = new ArrayList<>();
        if (dataList != null) {
            for (Row row : dataList) {
                try {
                    list.add((T) _resultSet(row));
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return list;
    }

    private <T> T _resultSet(final Row row) throws SQLException {
        if (row != null) {
            try {
                T t = (T) Class.forName(clazz.getName()).newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Transient transientAnnotation = field.getAnnotation(Transient.class);
                    if (transientAnnotation == null) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        PrimaryKey primaryKeyAnnotation = field.getAnnotation(PrimaryKey.class);
                        String fieldName = field.getName();
                        if (columnAnnotation == null) {
                            throw new SQLExecutorException("sql execute error, field[" + fieldName + "] not found column annotation");
                        }
                        String columnName = columnAnnotation.name();
                        Type type = field.getGenericType();
                        Class cls = (Class<?>) type;
                        String methodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1, fieldName.length())).toString();
                        Method method = clazz.getDeclaredMethod(methodName, new Class[]{cls});
                        if (primaryKeyAnnotation != null) {
                            //单独处理主键
                            method.invoke(t, row.getUUID(columnName).toString());
                        } else if (Integer.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getInt(columnName));
                        } else if (Long.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getLong(columnName));
                        } else if (String.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getString(columnName));
                        } else if (Date.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getTimestamp(columnName));
                        } else if (Enum.class.isAssignableFrom(cls)) {
                            method.invoke(t, Enum.valueOf(cls, row.getString(columnName)));
                        } else if (Boolean.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getBool(columnName));
                        } else if (Double.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getDouble(columnName));
                        } else if (BigDecimal.class.isAssignableFrom(cls)) {
                            method.invoke(t, row.getDecimal(columnName));
                        } else {
                            throw new SQLExecutorException("sql execute error, field[" + fieldName + "] unknown type");
                        }
                    }
                }
                return t;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            throw new SQLExecutorException("instance of class[" + clazz + "] failed");
        }
        return null;
    }

    /**
     * 关闭连接
     */
    void close() {
        session.close();
    }

    Session session() {
        return session;
    }


    /**
     * 类属性工具
     */
    class ClazzPropertiesHelper {

        /**
         * 处理的对象
         */
        private Object obj;

        /**
         * 属性集合
         */
        private String[] properties;

        /**
         * 属性值集合
         */
        private Object[] propertyValues;

        ClazzPropertiesHelper(Object _obj) {
            this.obj = _obj;
            properties = new String[fieldCount];
            propertyValues = new Object[fieldCount];
        }

        public void handle() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Field[] fields = clazz.getDeclaredFields();
            int i = 0;
            for (Field field : fields) {
                Transient transientAnnotation = field.getAnnotation(Transient.class);
                PrimaryKey primaryAnnotation = field.getAnnotation(PrimaryKey.class);
                if (transientAnnotation == null && primaryAnnotation == null) {
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    String fieldName = field.getName();
                    if (columnAnnotation == null) {
                        throw new SQLExecutorException("sql execute error, field[" + fieldName + "] not found column annotation");
                    }
                    String methodName = new StringBuffer("get").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1, fieldName.length())).toString();
                    Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
                    Object result = method.invoke(obj, new Object[0]);
                    properties[i] = columnAnnotation.name();
                    propertyValues[i] = result instanceof Enum ? ((Enum) result).name() : (result instanceof Date ? CalendarUtil.dateTime2String((Date) result) : result);
                    i++;
                }
            }
        }

        public String[] getProperties() {
            return properties;
        }

        public Object[] getPropertyValues() {
            return propertyValues;
        }
    }
}

/**
 * sql exception
 *
 * @author jinkai.xie
 * @date 下午12:32:12
 */
class SQLExecutorException extends RuntimeException {

    private static final long serialVersionUID = -6587342075666878380L;

    public SQLExecutorException() {
    }

    public SQLExecutorException(String message) {
        super(message);
    }

    public SQLExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SQLExecutorException(Throwable cause) {
        super(cause);
    }

    public SQLExecutorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
