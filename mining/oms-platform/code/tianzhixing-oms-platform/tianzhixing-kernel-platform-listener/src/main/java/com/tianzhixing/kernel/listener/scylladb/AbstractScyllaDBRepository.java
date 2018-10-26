package com.tianzhixing.kernel.listener.scylladb;

import com.datastax.driver.core.*;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.RetryPolicy;
import com.tianzhixing.oms.utils.ResourceBundleUtil;
import org.slf4j.Logger;

/**
 * Created by routine.k on 2018/6/27.
 */
public abstract class AbstractScyllaDBRepository<T> {

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

    /**
     * 关闭连接
     */
    public void close() {
        session.close();
    }

    Session session() {
        return session;
    }

    abstract String table();

    /*
     *
     *
     CREATE TABLE account_kernel_platform.device_mac_starpoint_records
        (version bigint,
        timeout_hour int,
        create_time timestamp,
        update_time timestamp,
        account_id bigint,
        oper_starpoint varchar,
        records_type int,
        task_id varchar,
        advertisement_id varchar,
        longitude_and_latitude varchar,
        record_token varchar,
        remark varchar,
        status int,
        task_name varchar,
        task_keyword varchar,
        task_location_name varchar,
        dev_id varchar,
        dev_wifi varchar,
        dev_bt varchar,
        dev_time varchar,
        dev_gps varchar,
        devmac_count int,
        PRIMARY KEY (record_token))
     *
     *
     *
     CREATE TABLE account_kernel_platform.collection_starpoint_records
        (create_time timestamp,
        account_id bigint,
        oper_starpoint varchar,
        records_type int,
        task_id varchar,
        advertisement_id varchar,
        record_token varchar,
        PRIMARY KEY (record_token))
     *
     *
     *
     */

}
