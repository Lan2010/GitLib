package com.tianzhixing.oms.test.grpc;

import com.tianzhixing.oms.commons.em.ClientPlatformType;
import com.tianzhixing.oms.commons.em.LoginStatus;
import com.tianzhixing.oms.rpc.mapper.*;
import com.tianzhixing.oms.utils.JSONUtil;
import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;
import io.nats.client.Message;
import nats.client.NatsConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/11.
 */

public class TestNats {

    protected final static String address = "192.168.11.23";
    //    protected final static String address = "127.0.0.1";
    protected final static int port = 5000;
    protected final static String token = "111111";

    protected static Logger LOGGER = LoggerFactory.getLogger(TestNats.class);

    public static void main(String[] args) throws IOException {
        ConnectionFactory cf = new ConnectionFactory("nats://192.168.11.18:4222");
        Connection nc = cf.createConnection();
        nats.client.Nats nats = new NatsConnector().addHost("nats://192.168.11.18:4222").connect();
        //for (int i = 0; i < 500; i++) {
            String json;
            ApplicationOperationInfoMapper applicationOperationInfoMapper = new ApplicationOperationInfoMapper();
            applicationOperationInfoMapper.setId(null);
            applicationOperationInfoMapper.setAppOperationTime(new Date().getTime());
            applicationOperationInfoMapper.setClientPlatformType(ClientPlatformType.WKWXAPP.name());
            applicationOperationInfoMapper.setCreateTime(new Date());
            applicationOperationInfoMapper.setIp("127.0.0.1");
            applicationOperationInfoMapper.setMobile("1380013800" + 0);
            applicationOperationInfoMapper.setOperationType(1);
            applicationOperationInfoMapper.setPlatformFrom(ClientPlatformType.WKWXAPP.name());
            applicationOperationInfoMapper.setWxID("nowx");
            json = JSONUtil.beanWithDateToJson(applicationOperationInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.application.operation", json);
            DeviceCheckinInfoMapper deviceCheckinInfoMapper = new DeviceCheckinInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "d_id01", "d_type", "d_mac", "d_ip", "d_mo", "d_p", new Date().getTime(), 0);
            json = JSONUtil.beanWithDateToJson(deviceCheckinInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.device.checkin", json);
            DeviceOnlineStatusInfoMapper deviceOnOffLineInfoMapper = new DeviceOnlineStatusInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "d_id01", "d_type", "d_mac", "d_ip", "d_mo", "d_p", new Date().getTime(), 0, "113.96085", "22.536276", 1);
            json = JSONUtil.beanWithDateToJson(deviceOnOffLineInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.device.on-off", json);
            DeviceOperationInfoMapper deviceOperationInfoMapper = new DeviceOperationInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "d_id01", "d_type", "d_mac", "d_ip", "d_mo", "d_p", new Date().getTime(), 1);
            json = JSONUtil.beanWithDateToJson(deviceOperationInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.device.bind-unbind", json);
            PagesOperationInfoMapper pagesOperationInfoMapper = new PagesOperationInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "wx_id001", new Date().getTime(), "http://google.com", "首页", "首页", LoginStatus.LOGIN, "127.0.0.1");
            json = JSONUtil.beanWithDateToJson(pagesOperationInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.pages.operation", json);
            UserAdvertisementInfoMapper userAccessAndClickAdvertmentInfoMapper = new UserAdvertisementInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "25", "http://baidu.com", "type", "我是广告", "我是广告", new Date().getTime(), 0, "127.0.0.1");
            json = JSONUtil.beanWithDateToJson(userAccessAndClickAdvertmentInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.access-click.advertisement", json);
            UserBasicInfoMapper userBasicInfoMapper = new UserBasicInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", null, null, null, null, "qd01", null, "reg", null, null, null, new Date().getTime(), "127.0.0.1");
            json = JSONUtil.beanWithDateToJson(userBasicInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.basic.info", json);
            UserAuthInfoMapper userIDCardAuthInfoMapper = new UserAuthInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "IDCARD", 1, new Date().getTime());
            json = JSONUtil.beanWithDateToJson(userIDCardAuthInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.auth", json);
            UserLoginStatusInfoMapper userLoginLogoutInfoMapper = new UserLoginStatusInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", new Date().getTime(), 0, "wx_id", "qq_id", "sina_id", "127.0.0.1");
            json = JSONUtil.beanWithDateToJson(userLoginLogoutInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.login-logout", json);
            UserGreeterCardInfoMapper userCreateShareGreeterCardInfoMapper = new UserGreeterCardInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "1", "im card", "http://baidu.com", "\"nkname\"", "wx_id", new Date().getTime(), 0, "wx", "wx");
            json = JSONUtil.beanWithDateToJson(userCreateShareGreeterCardInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.create-share.greetercard", json);
            UserPostCardInfoMapper userCreateSharePostCardInfoMapper = new UserPostCardInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "1", "im card", "http://baidu.com", "\"nkname\"", "wx_id", new Date().getTime(), 0, "wx", "wx");
            json = JSONUtil.beanWithDateToJson(userCreateSharePostCardInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.create-share.postcard", json);
            UserRecordingInfoMapper userRecordingInfoMapper = new UserRecordingInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", "0", "link", "nickname", "wx_id", new Date().getTime());
            json = JSONUtil.beanWithDateToJson(userRecordingInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.recording", json);
            UserTaskStatusInfoMapper userTaskStatusInfoMapper = new UserTaskStatusInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", new Date().getTime(), 1, "28", "macc", "maccinfo");
            json = JSONUtil.beanWithDateToJson(userTaskStatusInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.task.accept-finished", json);
            UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper = new UserStarPointIncrementInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", new Date().getTime(), "100", 0, "1", "名字");
            json = JSONUtil.beanWithDateToJson(userStarPointIncrementInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.starpoint.increment", json);
            UserStarPointConsumeInfoMapper userStarPointConsumeInfoMapper = new UserStarPointConsumeInfoMapper(null, new Date(), ClientPlatformType.WKWXAPP.name(), ClientPlatformType.WKWXAPP.name(), "1388888888", new Date().getTime(), "100", "WITHDRAW");
            json = JSONUtil.beanWithDateToJson(userStarPointConsumeInfoMapper, true);
            System.out.println("----- " + json + " -----");
            nats.publish("oms.subject.user.starpoint.consume", json);
//        }
    }

}
