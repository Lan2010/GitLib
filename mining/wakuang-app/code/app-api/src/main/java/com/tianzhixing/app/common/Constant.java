package com.tianzhixing.app.common;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.tianzhixing.app.util.CommonUtils;

/**
 * 全局静态变量
 * 
 * @author dev-teng
 * @date 2018年7月30日
 */
public class Constant {

	/**
	 * 日期格式
	 */
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/** 登录密码加密的盐 */
	public static final String LOGIN_SECRETKEY = "darren";

	/**
	 * 3DES秘钥
	 */
	public static final String API_3DES_KEY = "AGAO53D4E5FY27H8I9J0G1I3";

	public static final int SESSION_EXPIRES_TIME = 30 * 24 * 60 * 60;// 一个月的秒数

	/**
	 * RSA私钥文件路径
	 */
	public static String API_RSA_PRIKEY_PATH;
	/**
	 * RSA公钥文件路径
	 */
	public static String API_RSA_PUBKEY_PATH;
	/**
	 * 配置文件路径
	 */
	public static String APPLICATION_CONFIG_PATH = "/conf/properties/application.properties";
	/**
	 * 加密开关（请求接口的返回的数据）,false不加密，true加密
	 */
	public static String API_ENABLED_ENCRYPT;

	/**
	 * 是否进行签名（请求接口的返回的数据）
	 */
	public static String API_ENABLED_SIGN;
	/**
	 * grpc服务端口
	 */
	public static String GRPC_OTHER_PORT;
	/**
	 * grpc服务端口，短信 身份证 服务专用
	 */
	public static String GRPC_PORT;
	/**
	 * grpc服务url/ip
	 */
	public static String GRPC_HOST;
	/**
	 * grpc接口调用的token
	 */
	public static  String ID_AUTH_TOKEN;

	/**
	 * 图片服务器
	 */
	public static String UPLOAD;

	public static Map<String, String> BANK = new HashMap<String, String>();
	public static Map<String, String> APP_ADDR = new HashMap<String, String>();
	public static Map<String, String> IOSAPP_ADDR = new HashMap<String, String>();

	static {
		API_RSA_PRIKEY_PATH = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "apiRsaPrikeyPath");
		API_RSA_PUBKEY_PATH = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "apiRsaPubkeyPath");
		API_ENABLED_ENCRYPT = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "api.apiEnabledEncrypt");
		API_ENABLED_SIGN = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "api.apiEnabledSign");
		UPLOAD = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "uploadPath");

		GRPC_HOST = CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "grpc.host");
		GRPC_OTHER_PORT = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "grpc.other.port");
		GRPC_PORT = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "grpc.port");
		ID_AUTH_TOKEN = CommonUtils.getPropertiesValue(APPLICATION_CONFIG_PATH, "id.auth.token");
		//
		BANK.put("SRCB", "深圳农村商业银行");
		BANK.put("BGB", "广西北部湾银行");
		BANK.put("SHRCB", "上海农村商业银行");
		BANK.put("BJBANK", "北京银行");
		BANK.put("WHCCB", "威海市商业银行");
		BANK.put("BOZK", "周口银行");
		BANK.put("KORLABANK", "库尔勒市商业银行");
		BANK.put("SPABANK", "平安银行");
		BANK.put("SDEB", "顺德农商银行");
		BANK.put("HURCB", "湖北省农村信用社");
		BANK.put("WRCB", "无锡农村商业银行");
		BANK.put("BOCY", "朝阳银行");
		BANK.put("CZBANK", "浙商银行");
		BANK.put("HDBANK", "邯郸银行");
		BANK.put("BOC", "中国银行");
		BANK.put("BOD", "东莞银行");
		BANK.put("CCB", "中国建设银行");
		BANK.put("ZYCBANK", "遵义市商业银行");
		BANK.put("SXCB", "绍兴银行");
		BANK.put("GZRCU", "贵州省农村信用社");
		BANK.put("ZJKCCB", "张家口市商业银行");
		BANK.put("BOJZ", "锦州银行");
		BANK.put("BOP", "平顶山银行");
		BANK.put("HKB", "汉口银行");
		BANK.put("SPDB", "上海浦东发展银行");
		BANK.put("NXRCU", "宁夏黄河农村商业银行");
		BANK.put("NYBANK", "广东南粤银行");
		BANK.put("GRCB", "广州农商银行");
		BANK.put("BOSZ", "苏州银行");
		BANK.put("HZCB", "杭州银行");
		BANK.put("HSBK", "衡水银行");
		BANK.put("HBC", "湖北银行");
		BANK.put("JXBANK", "嘉兴银行");
		BANK.put("HRXJB", "华融湘江银行");
		BANK.put("BODD", "丹东银行");
		BANK.put("AYCB", "安阳银行");
		BANK.put("EGBANK", "恒丰银行");
		BANK.put("CDB", "国家开发银行");
		BANK.put("TCRCB", "江苏太仓农村商业银行");
		BANK.put("NJCB", "南京银行");
		BANK.put("ZZBANK", "郑州银行");
		BANK.put("DYCB", "德阳商业银行");
		BANK.put("YBCCB", "宜宾市商业银行");
		BANK.put("SCRCU", "四川省农村信用");
		BANK.put("KLB", "昆仑银行");
		BANK.put("LSBANK", "莱商银行");
		BANK.put("YDRCB", "尧都农商行");
		BANK.put("CCQTGB", "重庆三峡银行");
		BANK.put("FDB", "富滇银行");
		BANK.put("JSRCU", "江苏省农村信用联合社");
		BANK.put("JNBANK", "济宁银行");
		BANK.put("CMB", "招商银行");
		BANK.put("JINCHB", "晋城银行JCBANK");
		BANK.put("FXCB", "阜新银行");
		BANK.put("WHRCB", "武汉农村商业银行");
		BANK.put("HBYCBANK", "湖北银行宜昌分行");
		BANK.put("TZCB", "台州银行");
		BANK.put("TACCB", "泰安市商业银行");
		BANK.put("XCYH", "许昌银行");
		BANK.put("CEB", "中国光大银行");
		BANK.put("NXBANK", "宁夏银行");
		BANK.put("HSBANK", "徽商银行");
		BANK.put("JJBANK", "九江银行");
		BANK.put("NHQS", "农信银清算中心");
		BANK.put("MTBANK", "浙江民泰商业银行");
		BANK.put("LANGFB", "廊坊银行");
		BANK.put("ASCB", "鞍山银行");
		BANK.put("KSRB", "昆山农村商业银行");
		BANK.put("YXCCB", "玉溪市商业银行");
		BANK.put("DLB", "大连银行");
		BANK.put("DRCBCL", "东莞农村商业银行");
		BANK.put("GCB", "广州银行");
		BANK.put("NBBANK", "宁波银行");
		BANK.put("BOYK", "营口银行");
		BANK.put("SXRCCU", "陕西信合");
		BANK.put("GLBANK", "桂林银行");
		BANK.put("BOQH", "青海银行");
		BANK.put("CDRCB", "成都农商银行");
		BANK.put("QDCCB", "青岛银行");
		BANK.put("HKBEA", "东亚银行");
		BANK.put("HBHSBANK", "湖北银行黄石分行");
		BANK.put("WZCB", "温州银行");
		BANK.put("TRCB", "天津农商银行");
		BANK.put("QLBANK", "齐鲁银行");
		BANK.put("GDRCC", "广东省农村信用社联合社");
		BANK.put("ZJTLCB", "浙江泰隆商业银行");
		BANK.put("GZB", "赣州银行");
		BANK.put("GYCB", "贵阳市商业银行");
		BANK.put("CQBANK", "重庆银行");
		BANK.put("DAQINGB", "龙江银行");
		BANK.put("CGNB", "南充市商业银行");
		BANK.put("SCCB", "三门峡银行");
		BANK.put("CSRCB", "常熟农村商业银行");
		BANK.put("SHBANK", "上海银行");
		BANK.put("JLBANK", "吉林银行");
		BANK.put("CZRCB", "常州农村信用联社");
		BANK.put("BANKWF", "潍坊银行");
		BANK.put("ZRCBANK", "张家港农村商业银行");
		BANK.put("FJHXBC", "福建海峡银行");
		BANK.put("FJNX", "福建省农村信用社联合社");
		BANK.put("ZJNX", "浙江省农村信用社联合社");
		BANK.put("LZYH", "兰州银行");
		BANK.put("JSB", "晋商银行");
		BANK.put("BOHAIB", "渤海银行");
		BANK.put("CZCB", "浙江稠州商业银行");
		BANK.put("YQCCB", "阳泉银行");
		BANK.put("SJBANK", "盛京银行");
		BANK.put("XABANK", "西安银行");
		BANK.put("BSB", "包商银行");
		BANK.put("JSBANK", "江苏银行");
		BANK.put("FSCB", "抚顺银行");
		BANK.put("HNRCU", "河南省农村信用");
		BANK.put("COMM", "交通银行");
		BANK.put("XTB", "邢台银行");
		BANK.put("CITIC", "中信银行");
		BANK.put("HXBANK", "华夏银行");
		BANK.put("HNRCC", "湖南省农村信用社");
		BANK.put("DYCCB", "东营市商业银行");
		BANK.put("ORBANK", "鄂尔多斯银行");
		BANK.put("BJRCB", "北京农村商业银行");
		BANK.put("XYBANK", "信阳银行");
		BANK.put("ZGCCB", "自贡市商业银行");
		BANK.put("CDCB", "成都银行");
		BANK.put("HANABANK", "韩亚银行");
		BANK.put("CMBC", "中国民生银行");
		BANK.put("LYBANK", "洛阳银行");
		BANK.put("GDB", "广东发展银行");
		BANK.put("ZBCB", "齐商银行");
		BANK.put("CBKF", "开封市商业银行");
		BANK.put("H3CB", "内蒙古银行");
		BANK.put("CIB", "兴业银行");
		BANK.put("CRCBANK", "重庆农村商业银行");
		BANK.put("SZSBK", "石嘴山银行");
		BANK.put("DZBANK", "德州银行");
		BANK.put("SRBANK", "上饶银行");
		BANK.put("LSCCB", "乐山市商业银行");
		BANK.put("JXRCU", "江西省农村信用");
		BANK.put("ICBC", "中国工商银行");
		BANK.put("JZBANK", "晋中市商业银行");
		BANK.put("HZCCB", "湖州市商业银行");
		BANK.put("NHB", "南海农村信用联社");
		BANK.put("XXBANK", "新乡银行");
		BANK.put("JRCB", "江苏江阴农村商业银行");
		BANK.put("YNRCC", "云南省农村信用社");
		BANK.put("ABC", "中国农业银行");
		BANK.put("GXRCU", "广西省农村信用");
		BANK.put("PSBC", "中国邮政储蓄银行");
		BANK.put("BZMD", "驻马店银行");
		BANK.put("ARCU", "安徽省农村信用社");
		BANK.put("GSRCU", "甘肃省农村信用");
		BANK.put("LYCB", "辽阳市商业银行");
		BANK.put("JLRCU", "吉林农信");
		BANK.put("URMQCCB", "乌鲁木齐市商业银行");
		BANK.put("XLBANK", "中山小榄村镇银行");
		BANK.put("CSCB", "长沙银行");
		BANK.put("JHBANK", "金华银行");
		BANK.put("BHB", "河北银行");
		BANK.put("NBYZ", "鄞州银行");
		BANK.put("LSBC", "临商银行");
		BANK.put("BOCD", "承德银行");
		BANK.put("SDRCU", "山东农信");
		BANK.put("NCB", "南昌银行");
		BANK.put("TCCB", "天津银行");
		BANK.put("WJRCB", "吴江农商银行");
		BANK.put("CBBQS", "城市商业银行资金清算中心");
		BANK.put("HBRCU", "河北省农村信用社");

		APP_ADDR.put("qhz", "主包");
		APP_ADDR.put("qq", "应用宝");
		APP_ADDR.put("baidu", "百度手机助手");
		APP_ADDR.put("360", "360手机助手");
		APP_ADDR.put("huawei", "华为应用市场");
		APP_ADDR.put("xiaomi", "小米应用商店");
		APP_ADDR.put("oppo", "OPPO软件商店");
		APP_ADDR.put("vivo", "VIVO应用商店");
		APP_ADDR.put("meizu", "魅族应用商店");
		APP_ADDR.put("lianxiang", "联想乐商店");
		APP_ADDR.put("ali", "阿里应用");
		APP_ADDR.put("jifeng", "机锋网");
		APP_ADDR.put("anzhi", "安智网");
		IOSAPP_ADDR.put("zhongChen", "深圳中辰科技有限公司");
	}

	/**
	 * 默认城市代号
	 */
	public static final String cityCode = "340";

}
