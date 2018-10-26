package com.tianzhixing.common.auth.verification.yuanjian;

import com.tianzhixing.common.auth.utils.HttpClientUtil;
import com.tianzhixing.common.auth.utils.JSONUtil;
import com.tianzhixing.common.auth.utils.rsa.RsaCodingUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianClient {

    private static Log logger = LogFactory.getLog(YuanJianClient.class);

    public YuanJianResultEntity executeAuth(YuanJianRequestEntity yuanJianRequestEntity) {

        //加密datacontent
        try {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("member_id", yuanJianRequestEntity.getMember_id());
            paramMap.put("terminal_id", yuanJianRequestEntity.getTerminal_id());
            paramMap.put("data_type", yuanJianRequestEntity.getData_type());
            String dataContentBase64 = Base64.encodeBase64String(yuanJianRequestEntity.getData_content().getBytes("UTF-8"));
            InputStream inputStream = YuanJianClient.class.getClassLoader().getResourceAsStream(YuanJianConfig.getPrivatePFXFile());
            byte[] bytes = _inputStreamToByte(inputStream);
            String dataContentRSA = RsaCodingUtil.encryptByPriPfxStream(dataContentBase64, bytes, YuanJianConfig.getPrivatePFXPassword());
            paramMap.put("data_content", dataContentRSA);
            Map<String, String> headers = new HashMap<String, String>();
            String result = HttpClientUtil.doPost(YuanJianConfig.getURI(), headers, paramMap);
            if (result == null || "".equals(result)) {
                logger.error("request finished, but result is null");
                throw new YuanJianIdCardAuthException("request finished, but result is null");
            }
            YuanJianResultEntity yuanJianResultEntity = JSONUtil.jsonToBean(result, YuanJianResultEntity.class);
            if (yuanJianResultEntity.getSuccess() == null) {
                logger.error("request finished, but result not found request status, result[" + result + "]");
                throw new YuanJianIdCardAuthException("request finished, but result not found request status");
            }
//            if (!yuanJianResultEntity.getSuccess()) {
//                logger.error("request finished, request status not success, result[" + result + "]");
//                throw new YuanJianIdCardAuthException("request finished, request status not success, msg[" + yuanJianResultEntity.getErrorMsg() + "]");
//            }
            if (yuanJianResultEntity.getSuccess() && yuanJianResultEntity.getData() == null) {
                logger.error("request finished, result request status is true ,but not found result data, result[" + result + "]");
                throw new YuanJianIdCardAuthException("request finished, result request status is true ,but not found result data");
            }
            return yuanJianResultEntity;
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new YuanJianIdCardAuthException(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            throw new YuanJianIdCardAuthException(e.getMessage());
        }
    }

    private static byte[] _inputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int bytesWritten = 0;
        int byteCount = 0;
        while ((byteCount = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, bytesWritten, byteCount);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
}
