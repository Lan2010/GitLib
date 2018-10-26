package com.tianzhixing.kernel.grpc.test;

import com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo;
import com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.PageMapper;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskOperationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/11.
 */

public class TestGRPC {

//            protected final static String address = "192.168.11.23";
//            protected final static String address = "47.106.21.73";
    protected final static String address = "127.0.0.1";
//    protected final static String address = "192.168.11.32";
    protected final static int port = 10344;
//        protected final static int port = 5006;
    protected final static String token = "111111";
//    protected final static String token = "ca077449e30c775def0c95eec8eed0eb";

    protected static Logger LOGGER = LoggerFactory.getLogger(TestGRPC.class);

    public static void main(String[] args) throws ParseException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        AccountRegServiceGrpc.AccountRegServiceBlockingStub accountRegServiceBlockingStub = AccountRegServiceGrpc.newBlockingStub(channel);

//        com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult checkMobileResult = accountRegServiceBlockingStub.check(CheckMobileInfo.newBuilder()
//            .setToken(token).setMobile("13169601345").setOrg("WKAPP").build()
//        );
//        Assert.isTrue(checkMobileResult.getResponseEntity().getCode() == 200, checkMobileResult.getResponseEntity().getMessage());
        //注册
//        com.tianzhixing.kernel.grpc.proto.account.reg.Result accountRegResult = accountRegServiceBlockingStub.reg(AccountInfo.newBuilder()
//                .setMobile("1380013816")
//                .setThirdToken("10009")
//                .setRegTime(new Date().getTime())
//                .setToken(token)//.setReferrerToken("WAKUANG-1530791847236-9451552963297674")
//                .build());
//        Assert.isTrue(accountRegResult.getResponseEntity().getCode() == 200, accountRegResult.getResponseEntity().getMessage());
//        System.out.print(accountRegResult.getAccountToken());
//        查询余额
        AccountStarPointServiceGrpc.AccountStarPointServiceBlockingStub accountStarPointServiceBlockingStub = AccountStarPointServiceGrpc.newBlockingStub(channel);
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.Result starPointResult = accountStarPointServiceBlockingStub.starPoint(com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo.newBuilder()
//                .setToken(token).setAccountToken("WAAPP-1530842786859-0939848072751701").build());
//        Assert.isTrue(starPointResult.getResponseEntity().getCode() == 200, starPointResult.getResponseEntity().getMessage());

//        根据日期查询账户记录-day
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult recordsWithDayResult = accountStarPointServiceBlockingStub.recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530959598431-1395030473451626").setDays(100).setEndTime(new Date().getTime()).build());
//        Assert.isTrue(recordsWithDayResult.getResponseEntity().getCode() == 200, recordsWithDayResult.getResponseEntity().getMessage());

//        com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult starPointWithTaskResult = accountStarPointServiceBlockingStub.starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo.newBuilder()
//                .setToken(token).setAccountToken("WAAPP-1531208908787-3790561242309265").addTaskIds("32").addTaskIds("12").build());
//        Assert.isTrue(starPointWithTaskResult.getResponseEntity().getCode() == 200, starPointWithTaskResult.getResponseEntity().getMessage());

//查询排行
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult rankingResult = accountStarPointServiceBlockingStub.ranking(
//                RankingInfo.newBuilder().setToken(token).setPageMapper(PageMapper.newBuilder().setFrom(0).setSize(3).build()).build()
//        );
//        Assert.isTrue(rankingResult.getResponseEntity().getCode() == 200, rankingResult.getResponseEntity().getMessage());
//流水
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult recordsResult = accountStarPointServiceBlockingStub.records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition.newBuilder()
//                .setToken(token).setAccountToken("WAAPP-1530599656690-3335222706495195").setBeginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-06-28 15:39:37").getTime()).setEndTime(new Date().getTime()).setPageMapper(PageMapper.newBuilder().setSize(20).setFrom(0).build()).setRecordsType("ALL").build());
//        Assert.isTrue(recordsResult.getResponseEntity().getCode() == 200, recordsResult.getResponseEntity().getMessage());
//
        //未采集记录
        com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult unCollectionRecordsResult = accountStarPointServiceBlockingStub.unCollectionRecords(
                UnCollectionRecordsCondition.newBuilder().setToken(token).setAccountToken("WAAPP-1532489107842-5056674094979230").setBeginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2018-06-28 15:39:37").getTime()).setEndTime(new Date().getTime()).build()
        );
        Assert.isTrue(unCollectionRecordsResult.getResponseEntity().getCode() == 200, unCollectionRecordsResult.getResponseEntity().getMessage());
//
//采集
//        AccountStarPointOperationServiceGrpc.AccountStarPointOperationServiceBlockingStub accountStarPointOperationServiceBlockingStub = AccountStarPointOperationServiceGrpc.newBlockingStub(channel);
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult collectionResult = accountStarPointOperationServiceBlockingStub.collection(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530369899002-5134604734457005").setCollectionTime(new Date().getTime()).addRecords(StarPointRecordsInfo.newBuilder().setRecordToken("1ade403a-a2ac-42f0-9c3a-9d6365a6af8c-7").build()).addRecords(StarPointRecordsInfo.newBuilder().setRecordToken("0cc51cbd-926d-45f5-aa9d-a73e973974df-7").build()).build());
//        Assert.isTrue(collectionResult.getResponseEntity().getCode() == 200, collectionResult.getResponseEntity().getMessage());

//        奖励
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult awardResult = accountStarPointOperationServiceBlockingStub.award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530369899002-5134604734457005").setAdwardTime(new Date().getTime()).setAdwardType("AUTHIDCARD").build());
//        Assert.isTrue(awardResult.getResponseEntity().getCode() == 200, awardResult.getResponseEntity().getMessage());
//绑定通讯录
//        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult awardResult = accountStarPointOperationServiceBlockingStub.bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530792473944-5495454864537203").setAdwardTime(new Date().getTime()).setContactCount(10).build());
//        Assert.isTrue(awardResult.getResponseEntity().getCode() == 200, awardResult.getResponseEntity().getMessage());

//点击广告
//        AdvertisementOperationServiceGrpc.AdvertisementOperationServiceBlockingStub advertisementOperationServiceBlockingStub = AdvertisementOperationServiceGrpc.newBlockingStub(channel);
//        com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult clickResult = advertisementOperationServiceBlockingStub.clickOrAccess(com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530959598431-1395030473451626").setClickTime(new Date().getTime()).setStarPoint("0.0001").setAdvertId("26").setOperationType(0).build());
//        Assert.isTrue(clickResult.getResponseEntity().getCode() == 200, clickResult.getResponseEntity().getMessage());
//绑定设备
//        DeviceOperationServiceGrpc.DeviceOperationServiceBlockingStub deviceOperationServiceBlockingStub = DeviceOperationServiceGrpc.newBlockingStub(channel);
//        com.tianzhixing.kernel.grpc.proto.device.operation.Result result = deviceOperationServiceBlockingStub.bind(DeviceInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530369899002-5134604734457005").setDeviceId("TZX1_AABBCC22E8877").setDeviceType("type").setDeviceMAC("00:E0:4C:3B:7D:2F").setDeviceModel("model").setOperationTime(new Date().getTime()).build());
//        Assert.isTrue(result.getResponseEntity().getCode() == 200, result.getResponseEntity().getMessage());
//
//        com.tianzhixing.kernel.grpc.proto.device.operation.Result uresult = deviceOperationServiceBlockingStub.unBind(DeviceInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1529999745534-2007210522011931").setDeviceId("1111").setDeviceType("type").setDeviceMAC("mac").setDeviceModel("model").setOperationTime(new Date().getTime()).build());
//
//        Assert.isTrue(uresult.getResponseEntity().getCode() == 200, uresult.getResponseEntity().getMessage());
//接受任务
//        TaskOperationServiceGrpc.TaskOperationServiceBlockingStub taskOperationServiceBlockingStub = TaskOperationServiceGrpc.newBlockingStub(channel);
//        com.tianzhixing.kernel.grpc.proto.task.operation.Result tresult = taskOperationServiceBlockingStub.accept(TaskInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1530369899002-5134604734457005").setTaskId("9").setOperationTime(new Date().getTime()).build());
//
//        Assert.isTrue(tresult.getResponseEntity().getCode() == 200, tresult.getResponseEntity().getMessage());
//取消任务
//        com.tianzhixing.kernel.grpc.proto.task.operation.Result tcresult = taskOperationServiceBlockingStub.cancel(TaskInfo.newBuilder()
//                .setToken(token).setAccountToken("WAKUANG-1529999745534-2007210522011931").setTaskId("9").setOperationTime(new Date().getTime()).build());
//
//        Assert.isTrue(tcresult.getResponseEntity().getCode() == 200, tcresult.getResponseEntity().getMessage());
    }

}
