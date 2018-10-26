package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.component.nats.NatsComponent;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.AdvertisementModel;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.app.pojo.Ranking;
import com.tianzhixing.app.pojo.Records;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.UnCollectionRecord;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.AdvertisementLogic;
import com.tianzhixing.app.service.StarService;
import com.tianzhixing.app.service.TaskService;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.service.impl.grpc.AccountStarPoint;
import com.tianzhixing.app.service.impl.grpc.AccountStarPointOperation;
import com.tianzhixing.app.util.CommonUtils;
import com.tianzhixing.app.util.FunctionUtil;
import com.tianzhixing.app.util.RedisUtil;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingRecordsInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult;

@Service
public class StarServiceImpl implements StarService {
	private static Logger log = LoggerFactory.getLogger(StarServiceImpl.class);

	@Resource
	private GrpcConnection grpcConnection;
	@Resource
	private UserService userService;
	@Resource
	private TaskService taskService;
	@Resource
	private AdvertisementLogic advertisementLogic;
	@Resource
	public NatsComponent natsComponent;
	@Resource
	public AccountStarPoint accountStarPoint;
	@Resource
	public AccountStarPointOperation accountStarPointOperation;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 获取排行榜
	@Override
	public ResultMessage ranking() throws SQLException, GrpcException {
		Integer code = null;
		String message = null;
		RankingResult rankingResult = accountStarPoint.ranking();
		ResponseEntity responseEntity = rankingResult.getResponseEntity();
		code = responseEntity.getCode();
		message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
			resultMessage.put("info", null);
			return resultMessage;
		}
		Integer total = (int) rankingResult.getTotal();
		List<RankingRecordsInfo> recordList = rankingResult.getRecordsList();
		if (recordList.isEmpty()) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, "数据为空！");
			resultMessage.put("info", null);
			return resultMessage;
		}
		List<Ranking> infos = new ArrayList<Ranking>();
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, message);
		for (int i = 1; i <= recordList.size(); i++) {
			User user = userService.getUserByToken(recordList.get(i - 1).getAccountToken());
			String phone = null;
			if (user != null && user.getPhone() != null) {
				phone = FunctionUtil.hidPhone(user.getPhone());
			} else {
				phone = FunctionUtil.hidPhone("13100004545");
			}
			Ranking info = new Ranking(String.valueOf(i), phone, recordList.get(i - 1).getStarPoint());
			infos.add(info);
		}
		resultMessage.put("info", infos);
		return resultMessage;

	}

	// 星星流水信息
	@Override
	public ResultMessage records(Integer userId, Page page) throws SQLException, GrpcException {
		User user = userService.getUserById(userId);
		String userToken = user.getUserToken();
		if (userToken == null || userToken.length() <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "获取用户信息异常！");
		}
		RecordsResult recordsResult = accountStarPoint.records(userToken, page);
		ResponseEntity responseEntity = recordsResult.getResponseEntity();
		Integer code = responseEntity.getCode();
		String message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(code, message);
			resultMessage.put("info", null);
			return resultMessage;
		}
		List<RecordsInfo> recordsList = recordsResult.getRecordsList();
		if (recordsList.isEmpty()) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, "数据为空！");
			resultMessage.put("info", null);
			return resultMessage;
		}
		List<Records> records = new ArrayList<Records>();
		for (RecordsInfo recordsInfo : recordsList) {
			String operationType = switchOpType(recordsInfo.getOperationType());
			String recordsType = switchRecordsType(recordsInfo.getRecordsType(), recordsInfo.getRemark());
			Records record = new Records(recordsInfo.getOperStarPoint(), sdf.format(recordsInfo.getCreateTime()),
					operationType, recordsType, recordsInfo.getRemark());
			records.add(record);
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, message);
		resultMessage.setTotal("totalPage", (int) recordsResult.getTotal());
		resultMessage.put("info", records);
		return resultMessage;
	}

	private String switchOpType(String operationType) {
		switch (operationType) {
		case "CONSUME":
			return "消费";
		case "INCREMENT":
			return "增量";
		case "FROZEN":
			return "冻结";
		default:
			return "未知";
		}
	}

	private String switchRecordsType(String recordsType, String remark) {
		if (recordsType.equals("AWARD")) {
			switch (remark) {
			case "REGAWARD":
				return "注册";
			case "AUTHIDCARD":
				return "实名认证";
			case "GENE":
				return "生物数据";
			case "INVITATION":
				return "邀请好友";
			case "ADDRESS":
				return "送货地址";
			case "VOICEDISCERN":
				return "声音识别";
			case "FACEDEISCEERN":
				return "人脸识别";
			case "BINDBANK":
				return "绑定银行卡";
			case "HARDWAREBIND":
				return "绑定矿机";
			case "BINDADDRESSLIST":
				return "通讯录邀请好友";
			default:
				return "未知";
			}
		} else {
			switch (recordsType) {
			case "TASK":
				return "领取星星";
			case "ADVERTISEMENT":
				return "领取星星";
			case "BASIC":
				return "基本";
			case "RANDOM":
				return "领取星星";
			case "CONSUME":
				return "消费";
			default:
				return "未知";
			}
		}

	}

	// 待收取星星
	@Override
	public ResultMessage unCollection(Integer userId) throws SQLException, GrpcException {
		User user = userService.getUserById(userId);
		String userToken = user.getUserToken();
		if (userToken == null || userToken.length() <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "获取用户信息异常！");
		}
		UnCollectionRecordsResult result = accountStarPoint.unCollectionRecords(userToken);
		ResponseEntity responseEntity = result.getResponseEntity();
		Integer code = responseEntity.getCode();
		String message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(code, message);
			resultMessage.put("info", null);
			return resultMessage;
		}
		List<UnCollectionRecordsInfo> list = result.getRecordsList();
		if (list.isEmpty()) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, "获取到星星为空");
			resultMessage.put("info", null);
			return resultMessage;
		}
		List<UnCollectionRecord> taskStars = new ArrayList<UnCollectionRecord>();
		List<UnCollectionRecord> randomStars = new ArrayList<UnCollectionRecord>();
		for (UnCollectionRecordsInfo recordsInfo : list) {
			Integer taskID = 0;
			Integer adId = 0;
			if (!StringUtils.isEmpty(recordsInfo.getTaskId())) {
				taskID = Integer.valueOf(recordsInfo.getTaskId());
			}
			if (!StringUtils.isEmpty(recordsInfo.getAdvertisementId())) {
				adId = Integer.valueOf(recordsInfo.getAdvertisementId());
			}
			UnCollectionRecord record = new UnCollectionRecord(recordsInfo.getOperStarPoint(),
					recordsInfo.getLongitudeAndLatitude(), recordsInfo.getRecordsType(), taskID, adId,
					recordsInfo.getRemark(), sdf.format(recordsInfo.getCreateTime()), recordsInfo.getRecordToken());
			if (recordsInfo.getRecordsType() == "TASK") {
				taskStars.add(record);
			} else {
				randomStars.add(record);
			}
		}
		List<UnCollectionRecord> records = mergeStars(taskStars, randomStars, userId); // 合并星星
		List<AdvertisementModel> ad_data = advertisementLogic.getEffectiveAd(Constant.cityCode); // 获取到全部的广告信息
		if (ad_data == null) { // 广告位空
			ad_data = advertisementLogic.getDefault(Constant.cityCode); // 获取到默认的
		}
		JSONArray info = new JSONArray();
		for (UnCollectionRecord record : records) { // 合并
			AdvertisementModel randad = advertisementLogic.getRandAd(ad_data);
			if (randad == null) {
				randad = new AdvertisementModel();
				randad.setAdvertIcon(Constant.UPLOAD + "/upload/logo.png");
				randad.setAdvertPic(Constant.UPLOAD + "/upload/logo.png");
				randad.setAd_url("http://www.baidu.com");
				randad.setAd_id(0);
				randad.setOnceStarPoint("0");
				randad.setAdvertName("天智星默认");
				randad.setAdvertRemark("天智星");
				randad.setAdvertisement_describe("天智星");
				randad.setOnceClickStarPoint("0");
				randad.setUser_browse_starpoint("0");
				randad.setTotalStarPoint("0");
			}
			JSONObject recordJson = (JSONObject) JSONObject.toJSON(record);
			JSONObject adJson = (JSONObject) JSONObject.toJSON(randad);
			recordJson.putAll(adJson);
			info.add(recordJson);
		}
		int returnCode = 1;
		if (info.size() > 0) {
			returnCode = 0;
		}
		ResultMessage resultMessage = new ResultMessage(returnCode, message);
		resultMessage.put("info", info);
		return resultMessage;
	}

	/**
	 * 合并星星
	 * 
	 * @param taskStars
	 * @param randomStars
	 * @param userId
	 */
	private List<UnCollectionRecord> mergeStars(List<UnCollectionRecord> taskStars,
			List<UnCollectionRecord> randomStars, Integer userId) {
		if (taskStars.isEmpty() && randomStars.isEmpty()) {
			return null;
		}
		Integer taskCount = taskStars.size(); // 任务星星的总数
		Integer randomCount = randomStars.size(); // 随机星星的总数
		if (randomCount > 5) {
			randomStars = mergeStarsByGroup(randomStars, 5); // 合并随机星星
		}
		if (taskCount > 1) {
			taskStars = mergeStarsByTaskId(taskStars); // 合并任务星星
		}
		if (!taskStars.isEmpty()) {
			cacheUnCollection(userId, taskStars);
		}
		taskStars.addAll(randomStars);
		return taskStars;
	}

	/**
	 * 缓存用户待收集的星星
	 * 
	 * @param userId
	 * @param taskStars
	 */
	private void cacheUnCollection(Integer userId, List<UnCollectionRecord> data) {
		String key = userId + "_cacheUncollection";
		if (RedisUtil.isExitKey(key)) {
			RedisUtil.del(key);
		}
		String str = JSON.toJSONString(data);
		RedisUtil.set(key, str, 1000);
	}

	/**
	 * 分组合并星星
	 * 
	 * @param randomStars
	 * @param i
	 * @return
	 */
	private List<UnCollectionRecord> mergeStarsByGroup(List<UnCollectionRecord> starsArr, Integer num) {
		if (num == null) {
			num = 1;
		}
		if (num * 1 < 1) {
			return starsArr; // 分组的标识
		}
		Integer count = starsArr.size(); // 全部星星
		List<UnCollectionRecord> newStars = new ArrayList<UnCollectionRecord>(); // 新的星星数量
		List<UnCollectionRecord> starArr = new ArrayList<UnCollectionRecord>(); // 新的星星数组
		int j = count / num;

		for (int i = 0; i < count; i++) {
			starArr.add(starsArr.get(i));
			if ((0 == ((i + 1) % j) && (num > 1)) || count == (i + 1)) {
				List<String> recordTokens = new ArrayList<String>();
				Double operStarPoints = 0.0;
				for (UnCollectionRecord record : starArr) {
					recordTokens.add(record.getRecordToken());
					operStarPoints += Double.parseDouble(record.getOperStarPoint());
				}
				String recordToken = StringUtils.collectionToDelimitedString(recordTokens, ",");
				starArr.get(0).setRecordToken(recordToken);
				starArr.get(0).setOperStarPoint(operStarPoints.toString());
				newStars.add(starArr.get(0));
				starArr.clear();
				num--;
			}
		}
		return newStars;
	}

	/**
	 * 按任务ID合并星星
	 * 
	 * @param taskStars
	 * @return
	 */
	private List<UnCollectionRecord> mergeStarsByTaskId(List<UnCollectionRecord> taskStars) {
		List<UnCollectionRecord> newTaskStars = new ArrayList<UnCollectionRecord>();
		Set<Integer> taskIdArr = new TreeSet<Integer>();
		for (UnCollectionRecord record : taskStars) {
			taskIdArr.add(record.getTaskId());
		}
		for (Integer taskId : taskIdArr) {
			List<UnCollectionRecord> starArr = new ArrayList<UnCollectionRecord>();
			for (UnCollectionRecord recodes : taskStars) {
				if (recodes.getTaskId() == taskId) {
					starArr.add(recodes);
				}
			}
			List<String> recordTokens = new ArrayList<String>();
			Double operStarPoints = 0.0;
			for (UnCollectionRecord record : starArr) {
				recordTokens.add(record.getRecordToken());
				operStarPoints += Double.parseDouble(record.getOperStarPoint());
			}
			String recordToken = StringUtils.collectionToDelimitedString(recordTokens, ",");
			starArr.get(0).setRecordToken(recordToken);
			starArr.get(0).setOperStarPoint(operStarPoints.toString());
			newTaskStars.add(starArr.get(0));
		}
		return newTaskStars;
	}

	@Override
	public ResultMessage collection(Integer userId, String advertId, String recordToken, String taskId)
			throws GrpcException, SQLException {
		User user = userService.getUserById(userId);
		if (user == null) {
			return new ResultMessage(ReturnCode.FAILED, "用户信息异常！");
		}
		String userToken = user.getUserToken();
		if (userToken == null || userToken.length() <= 0 || recordToken == null || recordToken.length() <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "获取用户信息异常！");
		}
		String[] tokenArr = recordToken.split(",");
		List<StarPointRecordsInfo> records = new ArrayList<StarPointRecordsInfo>();
		for (String item : tokenArr) {
			StarPointRecordsInfo record = new StarPointRecordsInfo().newBuilder().setRecordToken(item).build();
			records.add(record);
		}
		CollectionResult result = accountStarPointOperation.collection(userToken, records, advertId);
		ResponseEntity responseEntity = result.getResponseEntity();
		Integer code = responseEntity.getCode();
		String message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
			resultMessage.put("info", null);
			return resultMessage;
		}

		if (advertId == null || advertId.length() <= 0) {
			AdvertisementModel ad_info = advertisementLogic.getAd(advertId);
			if (ad_info.getAd_url() == null || ad_info.getAd_url().length() <= 0) {
				advertisementLogic.setDec(advertId);// 是否为纯粹的浏览星星 -1
			}
			advertisementLogic.addNum(advertId, ad_info.getOnceStarPoint());// 加上
		}
		if (taskId != null) {
			taskService.addUserTaskPoint(taskId, userId);
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, message);
		JSONObject json = new JSONObject();
		json.put("starpoint", result.getStarpoint());
		json.put("availableStarPoint", result.getAvailableStarPoint());
		json.put("frozenStarPoint", result.getFrozenStarPoint());
		resultMessage.put("info", json);
		return resultMessage;
	}

	@Override
	public ResultMessage clickAd(HttpServletRequest request, Integer userId, String advertId, String starPoints,
			Integer adStarPointType) throws SQLException, GrpcException {
		float starPoint = Float.parseFloat(starPoints);
		Boolean mon_res = monitor(userId); // 是否过频繁访问
		if (!mon_res) {
			return new ResultMessage(ReturnCode.FAILED, "访问过频繁！");
		}
		AdvertisementModel ad_info = advertisementLogic.getAd(advertId);
		if (ad_info == null) {
			return new ResultMessage(ReturnCode.FAILED, "操作失败！");
		}
		if (Integer.valueOf(ad_info.getTotalCount()) <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "星星被收取了！");
		}
		if (adStarPointType == 1) { // 当时点击星星的时候
			if (ad_info.getAd_url() == null || ad_info.getAd_url().length() <= 0) {
				return new ResultMessage(ReturnCode.FAILED, "星星的类型不对！");
			}
			starPoint = Float.parseFloat(ad_info.getOnceClickStarPoint()); // 由前端传递 改成 后台自己读取
		} else {
			if (ad_info.getAd_url() == null || ad_info.getAd_url().length() <= 0) { // 当为纯粹的浏览星星时

			} else {
				// 当是点击星星且是浏览星值的时候 限制下
				Boolean res = getlock(userId, advertId);
				if (!res) {
					return new ResultMessage(ReturnCode.FAILED, "浏览星数用尽！");
				}
				// 限制下
				if (Float.parseFloat(ad_info.getUser_browse_starpoint()) >= Float
						.parseFloat(ad_info.getTotalStarPoint())) {
					return new ResultMessage(ReturnCode.FAILED, "浏览星数用尽！");
				}
			}
			starPoint = Float.parseFloat(ad_info.getOnceStarPoint());
		}
		User user = userService.getUserById(userId);
		if (user == null) {
			return new ResultMessage(ReturnCode.FAILED, "用户信息异常！");
		}
		String userToken = user.getUserToken();
		if (userToken == null || userToken.length() <= 0 || starPoint * 1 <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "用户信息异常！");
		}
		ClickResult result = accountStarPointOperation.clickOrAccess(advertId, String.valueOf(starPoint),
				adStarPointType, userToken);
		ResponseEntity responseEntity = result.getResponseEntity();
		Integer code = responseEntity.getCode();
		String message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
			resultMessage.put("info", null);
			return resultMessage;
		}
		JSONObject publish = new JSONObject();
		String mobile = user.getPhone();
		if (mobile == null || mobile.length() <= 0) {
			mobile = "";
		}
		publish.put("mobile", mobile);
		publish.put("advertId", advertId);
		publish.put("advertLink", ad_info.getAd_url());
		publish.put("advertType", ad_info.getAdvertisementType());
		publish.put("advertName", ad_info.getAdvertName());
		publish.put("advertInfo", ad_info.getAdvertRemark());
		// nat推送
		clickAdvertisement(request, publish);

		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, message);
		JSONObject json = new JSONObject();
		json.put("starpoint", result.getStarPoint());
		json.put("availableStarPoint", result.getAvailableStarPoint());
		json.put("frozenStarPoint", result.getFrozenStarPoint());
		resultMessage.put("info", json);
		return resultMessage;
	}

	// 点击广告 nat推送
	private void clickAdvertisement(HttpServletRequest request, JSONObject json) {
		json.put("operationTime", System.currentTimeMillis());
		json.put("operationType", 1);
		json.put("id", CommonUtils.randomID(8));
		try {
			json.put("ip", CommonUtils.getIpAddr(request));
		} finally {
			natsComponent.publish4oms("oms.subject.user.access-click.advertisement", json.toJSONString());
		}
	}

	// 获取到锁
	private Boolean getlock(Integer userId, String adId) {
		String key = "key" + userId + "_" + adId + "_view";
		String d = RedisUtil.getString(key);
		if (d == null || d.length() <= 0) {
			return true;
		}
		JSONObject json = JSONObject.parseObject(d);
		if (json.getInteger("num") >= 400) {
			return false;
		}
		return true;
	}

	/**
	 * 监控
	 * 
	 * 用户10秒之内只能访问8次 8次之后为过频
	 */
	@Override
	public Boolean monitor(Integer userId) {
		String key = "key_monitor_time_" + userId;
		String lock_key = userId + "_clickAd_lock";
		String info = RedisUtil.getString(key);// 从缓存里面拿
		JSONObject json = new JSONObject();
		if (info == null || info.length() <= 0) {
			json.put("opTime", System.currentTimeMillis()); // 毫秒
			json.put("num", 1);
			RedisUtil.set(key, json.toString(), 10);
			return true;
		}
		long time = System.currentTimeMillis(); // 当前的时间
		JSONObject json0 = JSONObject.parseObject(info);
		long time0 = json0.getLongValue("opTime"); // 上次记录的时间
		if (((int) ((time - time0) / 1000)) <= 10) { // 10秒之内 5次
			if (json0.getInteger("num") >= 5) { // 过频 则为非法用户 直接锁定一个小时
				JSONObject lock = new JSONObject();
				lock.put("lock", 22);
				RedisUtil.set(lock_key, lock.toString(), 3600);
				return false;
			} else {
				int time1 = 10 - ((int) ((time - time0) / 1000)); // 剩余的秒数
				if (time1 > 0) {
					json.put("opTime", json0.getLongValue("opTime")); // 毫秒
					json.put("num", 1 + json0.getInteger("num"));
					RedisUtil.set(key, json.toString(), time1);
				}
			}
		} else {
			RedisUtil.del(key);
		}
		String data = RedisUtil.getString(lock_key);// 检测是否上锁
		if (data != null && data.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public ResultMessage getLocationStar(String code, String lats, String lngs, String m) throws SQLException {
		DecimalFormat decimalFormat = new DecimalFormat(".000000");
		float lat = Float.parseFloat(decimalFormat.format(Float.parseFloat(lats)));
		float lng = Float.parseFloat(decimalFormat.format(Float.parseFloat(lngs)));
		float raidus = Float.parseFloat(m);
		JSONObject range_lan_long = FunctionUtil.getAround(lat, lng, raidus);
		float lat_rand_num = range_lan_long.getFloatValue("maxLat") - range_lan_long.getFloatValue("minLat");
		float lng_rand_num = range_lan_long.getFloatValue("maxLong") - range_lan_long.getFloatValue("minLong");
		AdvertisementModel model = new AdvertisementModel();
		model.setCityCode(code);
		List<AdvertisementModel> models = advertisementLogic.getAds(model);
		if (models.isEmpty()) {
			return new ResultMessage(ReturnCode.FAILED, "任务信息为空");
		}
		for (AdvertisementModel adModel : models) {
			Random rand = new Random();
			float Lat0 = rand.nextFloat() * lat_rand_num + range_lan_long.getFloatValue("minLat");
			float Lng0 = rand.nextFloat() * lng_rand_num + range_lan_long.getFloatValue("minLong");
			adModel.setLat(decimalFormat.format(Lat0));
			adModel.setLng(decimalFormat.format(Lng0));
			adModel.setAdvertPic(hidIMG(adModel.getAdvertPic()));
			if (adModel.getAdvertisementType().toLowerCase() == "alert") {
				adModel.setAdvertisementType(String.valueOf(0));
			} else {
				adModel.setAdvertisementType(String.valueOf(1));
			}
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "请求成功");
		resultMessage.put("info", models);
		return resultMessage;
	}

	/**
	 * 处理图片
	 * 
	 * @param data
	 * @return
	 */
	private String hidIMG(String data) {
		JSONArray ja = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("url", data);
		ja.add(json);
		return ja.toString();
	}

	// 奖励
	@Override
	public ResultMessage award(String userToken, String type) throws GrpcException {
		if (userToken != null && userToken.length() > 0) {
			AwardResult result = accountStarPointOperation.award(userToken, type);
			ResponseEntity responseEntity = result.getResponseEntity();
			Integer code = responseEntity.getCode();
			String message = responseEntity.getMessage();
			log.info("-------奖励GRPC返回 code:" + code);
			log.info("-------奖励GRPC返回 message:" + message);

			if (code != 200) {
				ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
				resultMessage.put("info", null);
				return resultMessage;
			}
			ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, message);
			JSONObject json = new JSONObject();
			json.put("starpoint", result.getStarPoint());
			json.put("availableStarPoint", result.getAvailableStarPoint());
			json.put("frozenStarPoint", result.getFrozenStarPoint());
			resultMessage.put("info", json);
			return resultMessage;
		}
		return new ResultMessage(ReturnCode.FAILED, "userToken是空的");
	}

}
