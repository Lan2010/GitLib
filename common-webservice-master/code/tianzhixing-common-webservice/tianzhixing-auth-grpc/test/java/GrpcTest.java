import com.tianzhixing.auth.WebApplication;
import com.tianzhixing.auth.grpc.proto.idcard.AuthResult;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardAuthSerivceGrpc;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo;
import com.tianzhixing.auth.grpc.proto.mobile.*;
import com.tianzhixing.common.auth.utils.JSONUtil;
import com.tianzhixing.common.auth.verification.yuanjian.YuanJianIdCardAuthTools;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/9.
 */
public class GrpcTest {

    protected static Logger LOGGER = LoggerFactory.getLogger(GrpcTest.class);

        protected final static String address = "192.168.11.23";
//    protected final static String address = "127.0.0.1";
        protected final static int port = 5005;
//    protected final static int port = 9899;
    protected final static String token = "111111";

    public static void main(String[] args) throws Exception {

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        IDCardAuthSerivceGrpc.IDCardAuthSerivceBlockingStub idCardAuthSerivceBlockingStub = IDCardAuthSerivceGrpc.newBlockingStub(channel);
        AuthResult authResult = idCardAuthSerivceBlockingStub.authIDCard(IDCardInfo.newBuilder().setIdcard("450802201802023417").setName("这些什么").setToken(token).build());
//        MobileValidationSerivceGrpc.MobileValidationSerivceBlockingStub mobileValidationSerivceBlockingStub = MobileValidationSerivceGrpc.newBlockingStub(channel);
//        Map<String, String> map = new HashMap<>();
//        map.put("code", "000000");
//        SendContentResult sendContentResult = mobileValidationSerivceBlockingStub.sendWithContent(SendContentInfo.newBuilder().setMobile("13621285297").setToken(token).setModKey("yunpian.security.mod").putAllReplace(map).build());
        LOGGER.info("authResult");
//
//        SendCodeResult sendCodeResult = mobileValidationSerivceBlockingStub.send(SendCodeInfo.newBuilder().setCodeLength(4).setMobile("13621285297").setSendCodeType(SendCodeInfo.SendCodeType.SECURITY_CODE).setToken(token).build());
//        LOGGER.info("sendCodeResult");
//
//        SearchResultInfo searchResultInfo = mobileValidationSerivceBlockingStub.search(SearchCodeInfo.newBuilder().setToken(token).setMobile("13621285297").build());
//        LOGGER.info("searchResultInfo");
//
//        ValidationResult validationResult = mobileValidationSerivceBlockingStub.validate(ValidationCodeInfo.newBuilder().setMobile("13621285297").setCodeToken("f0527b5b-9f3a-47e3-95d0-6cc5d4a158a9").setInputCode("8868").setToken(token).build());
//        LOGGER.info("validationResult");

        // YuanJianIdCardAuthTools.auth("", "k");

    }
}
