package core.common;

import java.io.File;
import java.text.SimpleDateFormat;

import core.util.CommonUtils;

/**
 * 全局静态变量
 * 
 * @author dev-teng
 * @date 2018年6月7日
 */
public class Constant {
	
	public static final String APPLICATION_CONFIG_PATH = "/conf/properties/application.properties";

	
	public static String BASE_PATH;
	
	static {		
		BASE_PATH = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "cn.sischain.immig.url");
	}
	/**
	 *  获取文件需要上传到的路径
	 */
	public static final String WISH_AUDIO_PATH = "wish" + File.separator + "audio" + File.separator;
	/**
	 *  获取文件需要上传到的路径
	 */
	public static final String AVATAR_LOCAL_PATH = "user" + File.separator + "avatar" + File.separator;
	// String path = "/data001/data/images/"+wishPath;//linux环境路径

	public static final String SESSION_USER = "User";
	/**
	 * Token名
	 */
	public static final String TOKEN = "token";
	
	/**
	 * 日期格式
	 */
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
	
	
	/**
	 * 平台名称
	 */
	public static final String PLATFORM_NAME = "XCWXAPP";
	
	public static final String CLIENT_PLATFORM_TYPE = "WXAPP";
}
