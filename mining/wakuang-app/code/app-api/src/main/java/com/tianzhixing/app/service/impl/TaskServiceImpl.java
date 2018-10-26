package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.component.nats.NatsComponent;
import com.tianzhixing.app.dao.mapper.ChargeMapper;
import com.tianzhixing.app.dao.mapper.TaskMapper;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.ChargeInfo;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.Task;
import com.tianzhixing.app.pojo.UnCollectionRecord;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.TaskService;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.service.impl.grpc.TaskOperation;
import com.tianzhixing.app.util.RedisUtil;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.task.operation.Result;

@Service
public class TaskServiceImpl implements TaskService {
	private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Resource
	private GrpcConnection grpcConnection;
	@Resource
	private UserService userService;
	@Resource
	private TaskMapper taskMapper;
	@Resource
	private ChargeMapper chargeMapper;
	@Resource
	public NatsComponent natsComponent;
	@Resource
	public TaskOperation taskOperation;

	@Override
	public void addUserTaskPoint(String order, Integer userId) throws SQLException {
		if (Integer.parseInt(order) <= 0) {
			return;
		}
		String key = userId + "_cacheUncollection";
		if (!RedisUtil.isExitKey(key)) {
			return;
		}
		String info = RedisUtil.getString(key);// 从缓存里面拿
		if (info == null || info.length() <= 0) {
			return;
		}
		List<UnCollectionRecord> records = JSONArray.parseArray(info, UnCollectionRecord.class);
		String starPoint = null;
		for (UnCollectionRecord record : records) {
			if (record.getTaskId() == Integer.parseInt(order)) {
				starPoint = record.getOperStarPoint();
			}
		}
		Integer task_id = taskMapper.getTaskId(order);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("task_id", task_id);
		map.put("user_id", userId);
		String task_user_starPoint = taskMapper.getStarPoint(map);
		if (task_user_starPoint == null || task_user_starPoint.length() <= 0) {
			task_user_starPoint = "0";
		}
		Map<String, Object> map01 = new HashMap<String, Object>();
		map01.put("task_id", task_id);
		map01.put("user_id", userId);
		float taskstarPoint = Float.parseFloat(task_user_starPoint);
		float starPoint0 = Float.parseFloat(starPoint);
		map01.put("starPoint", taskstarPoint + starPoint0);
		Integer result = taskMapper.saveStarPoint(map01);
	}

	@Override
	public ResultMessage getMyTask(Integer userId, Page page, Integer type) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		map.put("status", 1);
		map.put("pageSize", page.getPageSize());
		map.put("start", page.getStart());
		map.put("type", type); // 1 已经完成的 ;2 进行中的
		Integer count = taskMapper.getMyTaskCount(map);
		if (count <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "没有数据！");
		}
		List<Task> taskList = taskMapper.getMyTaskList(map);
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "获取成功");
		resultMessage.setTotal("totalPage", count);
		resultMessage.put("info", taskList);
		return resultMessage;

	}

	@Override
	public ResultMessage getRemindAd(Integer userId, String lat, String lng) throws SQLException {
		Task task = new Task(lat, lng);
		task.setUserId(userId);
		List<Task> Remind_task = taskMapper.getRemind(task);
		// 获取到当天已经推送过的任务
		JSONArray userFrame = getFrameLogID(userId);
		if (userFrame != null && userFrame.size() > 0) {
			List<Integer> taskIds = JSONObject.parseArray(userFrame.toJSONString(), Integer.class);
			// 从符合推送到任务中筛选出未接受和未推送的任务
			Iterator<Task> it = Remind_task.iterator();
			while (it.hasNext()) {
				if (taskIds.contains(it.next().getTask_id())) {
					it.remove();
				}
			}
		}
		if (Remind_task.size() == 0) {
			return new ResultMessage(ReturnCode.FAILED, "数据为空");
		}
		Set<Integer> taskIdset = new HashSet<Integer>();
		for (Task info : Remind_task) {
			taskIdset.add(info.getTask_id());
		}
		if (!taskIdset.isEmpty()) {
			// 记录当前用户已经弹窗的任务
			addFrameLog(userId, taskIdset);
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "获取成功");
		resultMessage.put("info", Remind_task);
		return resultMessage;
	}

	// 添加弹窗的记录
	private void addFrameLog(Integer userId, Set<Integer> data) {
		String key = "Frame_log_id_" + userId;
		JSONArray dataja = JSONArray.parseArray(JSON.toJSONString(data));
		String cache = RedisUtil.getString(key);
		if (!StringUtils.isEmpty(cache)) {
			JSONArray Fra = JSONArray.parseArray(cache);
			dataja.addAll(Fra);
		}
		RedisUtil.set(key, dataja.toJSONString(), 86400);
	}

	// 获取到弹窗的ID
	private JSONArray getFrameLogID(Integer userId) {
		String key = "Frame_log_id_" + userId;
		String cache = RedisUtil.getString(key);
		if (StringUtils.isEmpty(cache)) {
			return null;
		}
		JSONArray json = JSONArray.parseArray(cache);
		return json;
	}

	@Override
	public ResultMessage getTask(Integer userId, Page page, String lat, String lng, String cityCode)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("pageSize", page.getPageSize());
		map.put("start", page.getStart());
		map.put("lat", lat);
		map.put("lng", lng);
		map.put("cityCode", cityCode);

		Integer count = taskMapper.getTaskCount(map);
		if (count <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "没有数据！");
		}
		List<Task> taskList = taskMapper.getTaskList(map);
		List<Task> nostatus_info = new ArrayList<Task>();
		List<Task> yesstatus_info = new ArrayList<Task>();
		for (Task task : taskList) {
			if (StringUtils.isEmpty(task.getStatus())) { // 将排序后的结果分成两组 按照是否接受任务进行第二次排序
				task.setStatus("0");
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				if (format.parse(task.getBegin_time()).getTime() > System.currentTimeMillis()) {
					nostatus_info.add(task);
				} else {
					yesstatus_info.add(task);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (!nostatus_info.isEmpty() && !yesstatus_info.isEmpty()) {
			// 当作为组合的数组的为空的时候就直接返回数据库结果
			nostatus_info.addAll(yesstatus_info);
			taskList = nostatus_info;
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "获取成功");
		resultMessage.setTotal("totalPage", count);
		resultMessage.put("info", taskList);
		return resultMessage;
	}

	@Override
	public ResultMessage userAcceptTask(HttpServletRequest request, Integer userId, String orderNO, String tl_id,
			Short mt) throws SQLException, GrpcException {
		Task task = taskMapper.getTask(orderNO);
		List<ChargeInfo> charge_info = chargeMapper.getInfoByUser(userId);
		Set<Short> states = new HashSet<Short>();
		for (ChargeInfo chargeInfo : charge_info) {
			states.add(chargeInfo.getState());
		}
		if (charge_info.isEmpty() || !states.contains((short) 2)) {
			return new ResultMessage(ReturnCode.FAILED, "未绑定矿机！");
		}
		if (task == null) {
			return new ResultMessage(ReturnCode.FAILED, "不存在任务");
		}
		Task where = new Task();
		where.setUserId(userId);
		where.setTask_id(task.getTask_id());
		List<Task> tmp = taskMapper.getUserTaskList(where);
		if (!tmp.isEmpty()) {
			return new ResultMessage(ReturnCode.FAILED, "已经领取过该任务");
		}
		User user = userService.getUserById(userId);
		if (user == null) {
			return new ResultMessage(ReturnCode.FAILED, "用户信息异常！");
		}
		String userToken = user.getUserToken();
		if (userToken == null || userToken.length() <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "获取用户信息异常！");
		}
		Result result = taskOperation.accept(orderNO, userToken);
		ResponseEntity responseEntity = result.getResponseEntity();
		Integer code = responseEntity.getCode();
		String message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
			return resultMessage;
		}
		Map<String, Object> map = setContion(userId, task.getTask_id(), 1, tl_id);
		if (map != null) {
			int ret = taskMapper.addUserTask(map);
			if (ret >= 1) {
				String mobile = user.getPhone();
				if (mobile == null || mobile.length() <= 0) {
					mobile = "";
				}
				JSONObject publish = new JSONObject();
				publish.put("mobile", mobile);
				publish.put("operationTime", System.currentTimeMillis());
				publish.put("operationType", 1);// 必填(0=取消，1=接受)
				publish.put("taskId", orderNO);
				publish.put("taskName", task.getTask_name());
				publish.put("taskInfo", task.getTask_remark());
				// nat推送
				acceptFinished(request, publish, mt);
				return new ResultMessage(ReturnCode.OK, "接受成功");
			} else {
				return new ResultMessage(ReturnCode.FAILED, "接受失败！");
			}
		}
		return new ResultMessage(ReturnCode.FAILED, "接受失败！");
	}

	// 接受任务 推送
	private void acceptFinished(HttpServletRequest request, JSONObject json, Short mt) {
		json.put("id", null);
		json.put("createTime", System.currentTimeMillis());
		json.put("platformFrom", "WKAPP");
		json.put("clientPlatformType", (mt == 3) ? "IOS" : "ANDROID");
		natsComponent.publish4oms("oms.subject.user.auth", json.toJSONString());
	}

	private Map<String, Object> setContion(Integer userId, Integer task_id, int status, String tl_id) {
		if (!StringUtils.isEmpty(userId) && !StringUtils.isEmpty(task_id) && !StringUtils.isEmpty(tl_id)) {
			Map<String, Object> condition = new HashMap<String, Object>();
			condition.put("user_id", userId);
			condition.put("task_id", task_id);
			condition.put("status", status);
			condition.put("receive_date", System.currentTimeMillis() / 1000);
			condition.put("tl_id", tl_id);
			return condition;
		} else {
			return new HashMap<String, Object>();
		}

	}
}
