package xinglian;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.util.EncryptUtil;

public class ResquestTest {
	public static void main(String[] args) throws Exception {
		String data1 = "{\"phone\":\"13111111111\",\"password\":\"100msh\"}";
		String data2 = "[{\"name\":\"test2\",\"phone\":\"13111111111\"},{\"name\":\"test8\",\"phone\":\"13388888888\"}]";
		String data3 = "{\"phone\":\"15768118003\",\"agreement\":\"1\",\"password\":\"100msh\",\"phoneCode\":\"888888\",\"inviteCode\":\"888888\"}";
		//System.out.println(EncryptUtil.encrypt3DESECB(data3, Constant.API_3DES_KEY));
		System.out.println(data3);
		BaseRequest req = new BaseRequest();
	}
}
