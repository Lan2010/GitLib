package com.tianzhixing.kernel.dao.generic;

import com.tianzhixing.kernel.model.annotation.Column;
import com.tianzhixing.kernel.model.annotation.PrimaryKey;
import com.tianzhixing.kernel.model.annotation.Table;
import com.tianzhixing.kernel.model.annotation.Transient;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * DAO 层基类
 *
 * @author jinkai.xie
 * @date 下午12:32:25 @param <T>
 * @date 下午12:32:25 @param <PK>
 */
public class GenericBaseDao<T extends Serializable, PK extends Serializable> extends
        JdbcDaoSupport implements GenericInfDao<T, PK> {

    protected final Logger logger = Logger.getLogger(getClass());

    protected DataSourceTransactionManager transactionManager;

    private Class<T> clazz;

    private String clazzName;

    private String tableName;

    private int fieldCount;

    public GenericBaseDao() {
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
                    Assert.isTrue(Long.class.isAssignableFrom(cls), "Class[" + clazzName + "], field[" + fieldName + "] primary key must be Long type");
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

    @Resource
    public void setTransactionManager(DataSourceTransactionManager _transactionManager) {
        this.transactionManager = _transactionManager;
    }

    @Resource
    public void autoWireJdbcTemplate(JdbcTemplate jdbcTemplate) {
        super.setJdbcTemplate(jdbcTemplate);
    }

    /**
     * 查询总条数
     *
     * @return
     */
    @Override
    public long count() {
        Long count = getJdbcTemplate().queryForObject(_SQLCount(), Long.class);
        return count == null ? 0l : count.longValue();
    }

    /**
     * 查询总条数
     *
     * @param sql
     * @return
     */
    @Override
    public long count(final String sql) {
        Long count = getJdbcTemplate().queryForObject(sql, Long.class);
        return count == null ? 0l : count.longValue();
    }

    /**
     * 根据单条件查询条数
     *
     * @param propertyName
     * @param propertyValue
     * @return
     */
    @Override
    public long count(final String propertyName,
                      final Object propertyValue) {
        Long count = count(new String[]{propertyName},
                new Object[]{propertyValue});
        return count == null ? 0l : count.longValue();
    }

    /**
     * 根据多条件查询条数
     *
     * @param propertyNames
     * @param propertyValues
     * @return
     */
    @Override
    public long count(final String[] propertyNames,
                      final Object[] propertyValues) {
        Long count = getJdbcTemplate().queryForObject(_SQL(propertyNames, propertyValues, _SQLCount()), propertyValues, Long.class);
        return count == null ? 0l : count.longValue();
    }

    /**
     * 通过id得到实体对象
     *
     * @param id
     * @return
     */
    @Override
    public T get(final PK id) {
        String sql = _SQL();
        StringBuffer stringBuffer = new StringBuffer(sql).append(" where id = ").append(id);
        return _get(stringBuffer.toString(), null);

    }

    /**
     * 根据条件查询实体对象
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    public T get(final String[] properties,
                 final Object[] propertyValues) {
        return _get(_SQL(properties, propertyValues, _SQL()), propertyValues);
    }

    /**
     * 根据条件对象获取对象
     *
     * @param property
     * @param obj
     * @return
     */
    @Override
    public T get(final String property, final Object obj) {
        return _get(_SQL(new String[]{property}, new Object[]{obj}, _SQL()), new Object[]{obj});
    }

    /**
     * 根据条件对象获取对象
     *
     * @param sql
     * @return
     */
    @Override
    public T get(final String sql) {
        return _get(sql, new Object[]{});
    }

    /**
     * 通过id得到实体对象某项属性
     *
     * @param property
     * @param id
     * @return
     */
    @Override
    public Object getProperty(final String property, final PK id) {
        String sql = new StringBuffer("select ").append(property).append(" from ").append(tableName).append(" where id = ").append(id).toString();
        return _getProperty(sql, null, property);
    }


    /**
     * 根据条件查询实体对象某项属性
     *
     * @param property
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    public Object getProperty(final String property, final String[] properties, final Object[] propertyValues) {
        String sql = new StringBuffer("select ").append(property).append(" from ").append(tableName).toString();
        return _getProperty(_SQL(properties, propertyValues, sql), propertyValues, property);
    }

    /**
     * 自定义sql查询属性
     *
     * @param sql
     * @return
     */
    @Override
    public Object getProperty(final String sql) {
        return getJdbcTemplate().queryForObject(sql, Object.class);
    }

    /**
     * 根据条件对象获取对象某项属性
     *
     * @param property
     * @param propertyName
     * @param obj
     * @return
     */
    @Override
    public Object getProperty(final String property, final String propertyName, final Object obj) {
        String sql = new StringBuffer("select ").append(property).append(" from ").append(tableName).toString();
        return _getProperty(_SQL(new String[]{propertyName}, new Object[]{obj}, sql), new Object[]{obj}, property);
    }


    @Override
    public List<Object> listProperty(String sql, String property) {
        return _listProperty(sql, null, property);
    }

    @Override
    public List<Object> listProperty(String property, PK id) {
        String sql = new StringBuffer("select ").append(property).append(" from ").append(tableName).append(" where id = ").append(id).toString();
        return _listProperty(sql, null, property);
    }

    @Override
    public List<Object> listProperty(String property, String[] properties, Object[] propertyValues) {
        String sql = new StringBuffer("select ").append(property).append(" from ").append(tableName).toString();
        return _listProperty(_SQL(properties, propertyValues, sql), propertyValues, property);
    }

    @Override
    public List<Object> listProperty(String property, String propertyName, Object obj) {
        String sql = new StringBuffer("select ").append(property).append(" from ").append(tableName).toString();
        return _listProperty(_SQL(new String[]{propertyName}, new Object[]{obj}, sql), new Object[]{obj}, property);
    }

    /**
     * 通过id得到实体某几项属性
     *
     * @param propertiesMap
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getProperties(String[] propertiesMap, PK id) {
        StringBuffer sql = _configSQL(propertiesMap);
        sql.append(" where id = ").append(id);
        return _getProperties(sql.toString(), null, propertiesMap);
    }

    /**
     * 根据条件查询实体对象某几项属性
     *
     * @param propertiesMap
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    public Map<String, Object> getProperties(String[] propertiesMap, String[] properties, Object[] propertyValues) {
        StringBuffer sql = _configSQL(propertiesMap);
        return _getProperties(_SQL(properties, propertyValues, sql.toString()), propertyValues, propertiesMap);
    }

    /**
     * 根据条件对象获取对象某几项属性
     *
     * @param propertiesMap
     * @param propertyName
     * @param obj
     * @return
     */
    @Override
    public Map<String, Object> getProperties(String[] propertiesMap, String propertyName, Object obj) {
        StringBuffer sql = _configSQL(propertiesMap);
        return _getProperties(_SQL(new String[]{propertyName}, new Object[]{obj}, sql.toString()), new Object[]{obj}, propertiesMap);
    }

    @Override
    public List<Map<String, Object>> listProperties(String[] propertiesMap, PK id) {
        StringBuffer sql = _configSQL(propertiesMap);
        sql.append(" where id = ").append(id);
        return _listProperties(sql.toString(), null, propertiesMap);
    }

    @Override
    public List<Map<String, Object>> listProperties(String[] propertiesMap, String[] properties, Object[] propertyValues) {
        StringBuffer sql = _configSQL(propertiesMap);
        return _listProperties(_SQL(properties, propertyValues, sql.toString()), propertyValues, propertiesMap);
    }

    @Override
    public List<Map<String, Object>> listProperties(String[] propertiesMap, String propertyName, Object obj) {
        StringBuffer sql = _configSQL(propertiesMap);
        return _listProperties(_SQL(new String[]{propertyName}, new Object[]{obj}, sql.toString()), new Object[]{obj}, propertiesMap);
    }

    /**
     * 查询实体列表
     *
     * @return
     */
    @Override
    public List<T> list() {
        String sql = _SQL();
        return getJdbcTemplate().query(sql, new RowMapperHelper<T>());
    }

    /**
     * 根据多条件查询实体列表（排序）
     *
     * @param from
     * @param size
     * @return
     */
    @Override
    public List<T> list(final long from, final long size) {
        String sql = _SQL(from, size);
        return getJdbcTemplate().query(sql, new RowMapperHelper<T>());
    }

    /**
     * 查询实体列表（排序）
     *
     * @param orderBy
     * @param order
     * @return
     */
    @Override
    public List<T> list(final String orderBy,
                        final String order) {
        Assert.isTrue(StringUtils.isNotEmpty(order) && StringUtils.isNotEmpty(orderBy), "list sql failed, order of order by is empty");
        String sql = _SQL(order, orderBy);
        return getJdbcTemplate().query(sql, new RowMapperHelper<T>());
    }

    /**
     * 查询实体列表（排序+分页）
     *
     * @param orderBy
     * @param order
     * @return
     */
    @Override
    public List<T> list(final String orderBy,
                        final String order, final long from, final long size) {
        Assert.isTrue(StringUtils.isNotEmpty(order) && StringUtils.isNotEmpty(orderBy), "list sql failed, order of order by is empty");
        String sql = _SQL(order, orderBy);
        return getJdbcTemplate().query(sql, new RowMapperHelper<T>());
    }

    /**
     * 根据多条件查询实体列表
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    public List<T> list(final String[] properties,
                        final Object[] propertyValues) {
        String sql = _SQL(properties, propertyValues, _SQL());
        return getJdbcTemplate().query(sql, propertyValues, new RowMapperHelper<T>());
    }

    /**
     * 根据多条件查询实体列表（排序）
     *
     * @param properties
     * @param propertyValues
     * @param orderBy
     * @param order
     * @return
     */
    @Override
    public List<T> list(final String[] properties,
                        final Object[] propertyValues, final String orderBy,
                        final String order) {
        Assert.isTrue(StringUtils.isNotEmpty(order) && StringUtils.isNotEmpty(orderBy), "list sql failed, order of order by is empty");
        String sql = _SQL(properties, propertyValues, _SQL(), order, orderBy);
        return getJdbcTemplate().query(sql, propertyValues, new RowMapperHelper<T>());
    }

    /**
     * 根据多条件查询实体列表（分页）
     *
     * @param properties
     * @param propertyValues
     * @param from
     * @param size
     * @return
     */
    @Override
    public List<T> list(final String[] properties,
                        final Object[] propertyValues, final long from, final long size) {
        String sql = _SQL(properties, propertyValues, _SQL(), from, size);
        return getJdbcTemplate().query(sql, propertyValues, new RowMapperHelper<T>());
    }

    /**
     * 根据多条件查询实体列表（排序+分页）
     *
     * @param properties
     * @param propertyValues
     * @param orderBy
     * @param order
     * @param from
     * @param size
     * @return
     */
    @Override
    public List<T> list(final String[] properties,
                        final Object[] propertyValues, final String orderBy,
                        final String order, final long from, final long size) {
        Assert.isTrue(StringUtils.isNotEmpty(order) && StringUtils.isNotEmpty(orderBy), "list sql failed, order of order by is empty");
        String sql = _SQL(properties, propertyValues, _SQL(), order, orderBy, from, size);
        return getJdbcTemplate().query(sql, propertyValues, new RowMapperHelper<T>());
    }

    /**
     * 自定义sql查询
     *
     * @param sql
     * @return
     */
    @Override
    public List<T> list(final String sql) {
        Assert.notNull(sql, "list sql failed, sql is null");
        return getJdbcTemplate().query(sql, new RowMapperHelper<T>());
    }

    /**
     * 根据多条件获取主键id
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    public PK getId(final String[] properties,
                    final Object[] propertyValues) {
        String sql = _SQL(properties, propertyValues, new StringBuffer("select id from ").append(tableName).toString());
        Object obj = _getProperty(_SQL(properties, propertyValues, sql), propertyValues, "id");
        return obj == null ? null : (PK) obj;
    }

    /**
     * 根据多条件获取主键列表
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<PK> listIds(final String[] properties,
                            final Object[] propertyValues) {
        String sql = _SQL(properties, propertyValues, new StringBuffer("select id from ").append(tableName).toString());
        return getJdbcTemplate().query(sql, propertyValues, new RowMapper<PK>() {
            @Override
            public PK mapRow(ResultSet rs, int rowNum) throws SQLException {
                return (PK) rs.getObject("id");
            }
        });
    }

    /**
     * 获取最大ID
     *
     * @return
     */
    @Override
    public Long maxId() {
        String sql = new StringBuffer("select max(id)").append(" from ").append(tableName).toString();
        Long id = getJdbcTemplate().queryForObject(sql, Long.class);
        return id == null ? 0l : id.longValue();
    }

    /**
     * 添加对象
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    @Override
    public PK insert(final String[] properties,
                     final Object[] propertyValues) {
        final String sql = _SQLInsert(properties, propertyValues);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < propertyValues.length; i++) {
                    ps.setObject(i + 1, propertyValues[i]);
                }
                return ps;
            }
        }, keyHolder);
        return (PK) keyHolder.getKey();
    }

    /**
     * 添加对象
     *
     * @param obj
     * @return
     */
    @Override
    public PK insert(final Object obj) {
        try {
            if (obj.getClass() != clazz) {
                throw new SQLExecutorException("insert error, obj class type[" + obj.getClass() + "] not equals clazz[" + clazz + "]");
            }
            ClazzPropertiesHelper helper = new ClazzPropertiesHelper(obj);
            helper.handle();
            return insert(helper.getProperties(), helper.getPropertyValues());
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

    /**
     * 根据主键更新对象部分属性
     *
     * @param id
     * @param properties
     * @param propertyValues
     * @param version
     * @return
     */
    @Override
    public void update(final PK id, final String[] properties, final Object[] propertyValues, final int version) {
        String sql = _SQLUpdate(properties, propertyValues, id, version);
        int flag = getJdbcTemplate().update(sql, propertyValues);
        if (flag == 0) {
            logger.error("update sql executor failed, sql[" + sql + "] return record zero");
            throw new SQLExecutorException(
                    "sql.executor.error.update.return.zero.record");
        }
    }

    /**
     * 更新对象
     *
     * @param id
     * @param version
     * @param obj
     */
    @Override
    public void update(final PK id, final int version, final Object obj) {
        try {
            if (obj.getClass() != clazz) {
                throw new SQLExecutorException("update error, obj class type[" + obj.getClass() + "] not equals clazz[" + clazz + "]");
            }
            ClazzPropertiesHelper helper = new ClazzPropertiesHelper(obj);
            helper.handle();
            update(id, helper.getProperties(), helper.getPropertyValues(), version);
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

    /**
     * 根据主键删除对象
     *
     * @param id
     * @param version
     * @throws Exception
     */
    @Override
    public int deleteById(final PK id, final int version) {
        throw new SQLExecutorException("sql.executor.error.delete.not.allow");
    }

    /**
     * 根据主键列表删除对象
     *
     * @param ids
     * @param versions
     * @throws Exception
     */
    @Override
    public int deleteByIds(final List<PK> ids, final List<Integer> versions) {
        throw new SQLExecutorException("sql.executor.error.delete.not.allow");
    }

    /**
     * 根据条件删除对象
     *
     * @param properties
     * @param propertyValues
     * @param versions
     * @throws Exception
     */
    @Override
    public int deleteByMap(final String[] properties, final Object[] propertyValues, final List<Integer> versions) {
        throw new SQLExecutorException("sql.executor.error.delete.not.allow");
    }

    /**
     * 处理SQL Count
     *
     * @return
     */
    private String _SQLCount() {
        return new StringBuffer("SELECT COUNT(*) FROM ").append(tableName).toString();
    }

    /**
     * 处理SQL
     *
     * @return
     */
    private String _SQL() {
        return new StringBuffer("SELECT * FROM ").append(tableName).toString();
    }

    /**
     * 处理SQL
     *
     * @param from
     * @param size
     * @return
     */
    private String _SQL(final long from, final long size) {
        return new StringBuffer(_SQL()).append(" LIMIT ").append(from).append(",").append(size).toString();
    }

    /**
     * 处理SQL
     *
     * @param order
     * @param orderBy
     * @param from
     * @param size
     * @return
     */
    private String _SQL(final String order, final String orderBy, final long from, final long size) {
        return new StringBuffer(_SQL(order, orderBy)).append(" LIMIT ").append(from).append(",").append(size).toString();
    }

    /**
     * 处理SQL
     *
     * @param order
     * @param orderBy
     * @return
     */
    private String _SQL(final String order, final String orderBy) {
        return new StringBuffer(_SQL()).append(" ORDER BY ").append(orderBy).append(" ").append(order).toString();
    }

    /**
     * 处理SQL
     *
     * @param propertyNames
     * @param propertyValues
     * @param sql
     * @return
     */
    private String _SQL(final String[] propertyNames,
                        final Object[] propertyValues, String sql) {
        Assert.notEmpty(propertyNames, "Class[" + clazzName + "]  property names empty ...");
        Assert.notEmpty(propertyValues, "Class[" + clazzName + "]  property values empty ...");
        Assert.noNullElements(propertyNames, "Class[" + clazzName + "]  property names has null element ...");
//        Assert.noNullElements(propertyValues, "Class[" + clazzName + "]  property values has null element ...");
        Assert.isTrue(propertyNames.length == propertyValues.length, "Class[" + clazzName + "]  property names and values length not equals ...");
        StringBuffer stringBuffer = new StringBuffer(sql).append(" WHERE ");
        for (int i = 0; i < propertyNames.length; i++) {
            stringBuffer.append(propertyNames[i]).append(" = ?").append(i + 1 == propertyNames.length ? " " : " AND ");
        }
        return stringBuffer.toString();
    }

    private StringBuffer _configSQL(String[] propertiesMap) {
        StringBuffer stringBuffer = new StringBuffer("select ");
        for (int i = 0; i < propertiesMap.length; i++) {
            String property = propertiesMap[i];
            stringBuffer.append(property);
            if (i + 1 != propertiesMap.length) {
                stringBuffer.append(", ");
            }
        }
        return stringBuffer.append(" from ").append(tableName);
    }

    /**
     * 处理SQL
     *
     * @param propertyNames
     * @param propertyValues
     * @param sql
     * @param order
     * @param orderBy
     * @return
     */
    private String _SQL(final String[] propertyNames,
                        final Object[] propertyValues, final String sql, final String order, final String orderBy) {
        return new StringBuffer(_SQL(propertyNames, propertyValues, sql)).append(" ORDER BY ").append(orderBy).append(" ").append(order).toString();
    }

    /**
     * 处理SQL
     *
     * @param propertyNames
     * @param propertyValues
     * @param sql
     * @param from
     * @param size
     * @return
     */
    private String _SQL(final String[] propertyNames,
                        final Object[] propertyValues, final String sql, final long from, final long size) {
        return new StringBuffer(_SQL(propertyNames, propertyValues, sql)).append(" LIMIT ").append(from).append(",").append(size).toString();
    }

    /**
     * 处理SQL
     *
     * @param propertyNames
     * @param propertyValues
     * @param sql
     * @param order
     * @param orderBy
     * @param from
     * @param size
     * @return
     */
    private String _SQL(final String[] propertyNames,
                        final Object[] propertyValues, final String sql, final String order, final String orderBy, final long from, final long size) {
        return new StringBuffer(_SQL(propertyNames, propertyValues, sql, order, orderBy)).append(" LIMIT ").append(from).append(",").append(size).toString();
    }

    /**
     * 处理SQL
     *
     * @param propertyNames
     * @param propertyValues
     * @return
     */
    private String _SQLInsert(final String[] propertyNames,
                              final Object[] propertyValues) {
        Assert.notEmpty(propertyNames, "Class[" + clazzName + "]  property names empty ...");
        Assert.notEmpty(propertyValues, "Class[" + clazzName + "]  property values empty ...");
        Assert.noNullElements(propertyNames, "Class[" + clazzName + "]  property names has null element ...");
//        Assert.noNullElements(propertyValues, "Class[" + clazzName + "]  property values has null element ...");
        Assert.isTrue(propertyNames.length == propertyValues.length, "Class[" + clazzName + "]  property names and values length not equals ...");
        StringBuffer stringBuffer = new StringBuffer("INSERT INTO ").append(tableName).append("(");
        StringBuffer valuesBuffer = new StringBuffer(" VALUES(");
        for (int i = 0; i < propertyNames.length; i++) {
            stringBuffer.append(propertyNames[i]).append(i + 1 == propertyNames.length ? ")" : ", ");
            valuesBuffer.append("?").append(i + 1 == propertyNames.length ? ")" : ", ");
        }
        return stringBuffer.append(valuesBuffer).toString();
    }

    /**
     * 处理SQL
     *
     * @param propertyNames
     * @param propertyValues
     * @return
     */
    private String _SQLUpdate(final String[] propertyNames,
                              final Object[] propertyValues, final PK id, final int version) {
        Assert.notEmpty(propertyNames, "Class[" + clazzName + "]  property names empty ...");
        Assert.notEmpty(propertyValues, "Class[" + clazzName + "]  property values empty ...");
        Assert.noNullElements(propertyNames, "Class[" + clazzName + "]  property names has null element ...");
//        Assert.noNullElements(propertyValues, "Class[" + clazzName + "]  property values has null element ...");
        Assert.isTrue(propertyNames.length == propertyValues.length, "Class[" + clazzName + "]  property names and values length not equals ...");
        StringBuffer stringBuffer = new StringBuffer("UPDATE ").append(tableName).append(" SET ");
        for (int i = 0; i < propertyNames.length; i++) {
            stringBuffer.append(propertyNames[i]).append(" = ?").append(i + 1 == propertyNames.length ? "" : ", ");
        }
        stringBuffer.append(", version = version + 1").append(" WHERE id = ").append(id).append(" and version = ").append(version);
        return stringBuffer.toString();
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
                    propertyValues[i] = result instanceof Enum ? ((Enum) result).name() : result;
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

    /**
     * 类实例化工具
     *
     * @param <T>
     */
    class RowMapperHelper<T> implements RowMapper<T> {

        @Override
        public T mapRow(ResultSet resultSet, int i) throws SQLException {
            return _resultSet(resultSet);
        }
    }

    /**
     * 类实例化工具
     *
     * @param <T>
     */
    class ResultSetExtractorHelper<T> implements ResultSetExtractor<T> {

        @Override
        public T extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next())
                return _resultSet(rs);
            else
                return null;
        }
    }

    /**
     * 字段映射工具
     */
    class PropertyResultSetExtractorHelper implements ResultSetExtractor<Object> {

        private String property;

        public PropertyResultSetExtractorHelper(String property) {
            this.property = property;
        }

        @Override
        public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
            return _propertyResultSet(rs, property);
        }
    }

    class PropertyListResultSetExtractorHelper implements ResultSetExtractor<Object> {

        private String property;

        public PropertyListResultSetExtractorHelper(String property) {
            this.property = property;
        }

        @Override
        public List<Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
            return _propertyListResultSet(rs, property);
        }
    }

    class PropertiesResultSetExtractorHelper implements ResultSetExtractor<Object> {

        private String[] properties;

        public PropertiesResultSetExtractorHelper(String[] properties) {
            this.properties = properties;
        }

        @Override
        public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
            return _propertiesResultSet(rs, properties);
        }
    }

    class PropertiesListResultSetExtractorHelper implements ResultSetExtractor<Object> {

        private String[] properties;

        public PropertiesListResultSetExtractorHelper(String[] properties) {
            this.properties = properties;
        }

        @Override
        public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
            return _propertiesListResultSet(rs, properties);
        }
    }

    private <T> T _resultSet(final ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            try {
                T t = (T) Class.forName(clazz.getName()).newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    Transient transientAnnotation = field.getAnnotation(Transient.class);
                    if (transientAnnotation == null) {
                        Column columnAnnotation = field.getAnnotation(Column.class);
                        String fieldName = field.getName();
                        if (columnAnnotation == null) {
                            throw new SQLExecutorException("sql execute error, field[" + fieldName + "] not found column annotation");
                        }
                        String columnName = columnAnnotation.name();
                        Type type = field.getGenericType();
                        Class cls = (Class<?>) type;
                        String methodName = new StringBuffer("set").append(fieldName.substring(0, 1).toUpperCase()).append(fieldName.substring(1, fieldName.length())).toString();
                        Method method = clazz.getDeclaredMethod(methodName, new Class[]{cls});
                        if (Integer.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getInt(columnName));
                        } else if (Long.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getLong(columnName));
                        } else if (String.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getString(columnName));
                        } else if (Date.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getTimestamp(columnName));
                        } else if (Enum.class.isAssignableFrom(cls)) {
                            method.invoke(t, Enum.valueOf(cls, resultSet.getString(columnName)));
                        } else if (Boolean.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getBoolean(columnName));
                        } else if (Double.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getDouble(columnName));
                        } else if (BigDecimal.class.isAssignableFrom(cls)) {
                            method.invoke(t, resultSet.getBigDecimal(columnName));
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

    private Object _propertyResultSet(final ResultSet resultSet, final String property) throws SQLException {
        if (resultSet != null) {
            return resultSet.getObject(property);
        }
        return null;
    }

    private List<Object> _propertyListResultSet(final ResultSet resultSet, final String property) throws SQLException {
        if (resultSet != null) {
            List<Object> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getObject(property));
            }
            return list;
        }
        return null;
    }

    private Map<String, Object> _propertiesResultSet(final ResultSet resultSet, final String[] properties) throws SQLException {
        if (resultSet != null) {
            Map<String, Object> map = new HashMap<>();
            for (String property : properties) {
                Object object = resultSet.getObject(property);
                map.put(property, object);
                return map;
            }
        }
        return null;
    }

    private List<Map<String, Object>> _propertiesListResultSet(final ResultSet resultSet, final String[] properties) throws SQLException {
        if (resultSet != null) {
            List<Map<String, Object>> list = new ArrayList<>();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                for (String property : properties) {
                    Object object = resultSet.getObject(property);
                    map.put(property, object);               
                }
                list.add(map);
            }
            return list;
        }
        return null;
    }


    private Object _getProperty(final String sql, final Object[] args, final String property) {
        return getJdbcTemplate().query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                if (args != null && args.length != 0) {
                    for (int i = 0; i < args.length; i++) {
                        ps.setObject(i + 1, args[i]);
                    }
                }
            }
        }, new PropertyResultSetExtractorHelper(property));
    }

    private List<Object> _listProperty(final String sql, final Object[] args, final String property) {
        return (List<Object>) getJdbcTemplate().query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                if (args != null && args.length != 0) {
                    for (int i = 0; i < args.length; i++) {
                        ps.setObject(i + 1, args[i]);
                    }
                }
            }
        }, new PropertyListResultSetExtractorHelper(property));
    }

    private Map<String, Object> _getProperties(final String sql, final Object[] args, final String[] properties) {
        return (Map<String, Object>) getJdbcTemplate().query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                if (args != null && args.length != 0) {
                    for (int i = 0; i < args.length; i++) {
                        ps.setObject(i + 1, args[i]);
                    }
                }
            }
        }, new PropertiesResultSetExtractorHelper(properties));
    }

    private List<Map<String, Object>> _listProperties(final String sql, final Object[] args, final String[] properties) {
        return (List<Map<String, Object>>) getJdbcTemplate().query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                if (args != null && args.length != 0) {
                    for (int i = 0; i < args.length; i++) {
                        ps.setObject(i + 1, args[i]);
                    }
                }
            }
        }, new PropertiesListResultSetExtractorHelper(properties));
    }

    private T _get(final String sql, final Object[] args) {
        return getJdbcTemplate().query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                if (args != null && args.length != 0) {
                    for (int i = 0; i < args.length; i++) {
                        ps.setObject(i + 1, args[i]);
                    }
                }
            }
        }, new ResultSetExtractorHelper<T>());
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

