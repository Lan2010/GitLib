package core.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import core.mapper.GreencardMapper;
import core.mapper.PrizeMapper;
import core.mapper.UserMapper;
import core.pojo.GreenCard;
import core.pojo.Prize;
import core.pojo.User;
import core.service.PrizeService;

@Service
public class PrizeServiceImpl implements PrizeService {
	@Autowired
	private PrizeMapper prizeMapper;
	@Autowired
	private GreencardMapper greencardMapper;
	@Autowired
	private UserMapper userMapper;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public int selectPrizesCount() throws SQLException {
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("week", getWeek());//获取当前的周数
		return prizeMapper.selectPrizesCount();
	}

	@Override
	public JSONArray selectPrizesByPage(Map<String, Object> map) throws SQLException {
		Integer pageSize = (Integer) map.get("pageSize");
		if (pageSize == null) {
			pageSize = 3;
		}
		Integer page = (Integer) map.get("page");
		if (page == null || page <= 0) {
			page = 1;
		}
		JSONArray jsonArr = new JSONArray();
		JSONArray temp = new JSONArray();
		JSONObject json = null;
		int start = getWeek() - page + 1;
		List<Prize> prizes = null;
		for (int i = 0; i < pageSize; i++) {
			map.put("week", start - i);
			prizes = prizeMapper.selectPrizesByPage(map);
			if (prizes.isEmpty()) {
				continue;
			}
			temp = prizesJSONArr(prizes);
			if(temp.isEmpty()) {
				continue;
			}
			json = new JSONObject();
			json.put("date", sdf.format(prizes.get(0).getPrizeTime()));
			json.put("data", temp);
			jsonArr.add(json);
		}
		return jsonArr;
	}

	private static int getWeek() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置周一为一周的第一天
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week;
	}

	@Override
	public JSONArray individualPrizes(Integer userId) throws SQLException {
		List<Prize> prizes = prizeMapper.individualPrizes(userId);
		JSONArray temp = new JSONArray();
		Prize p = null;
		for (Prize prize : prizes) {
			p = new Prize();
			p.setId(prize.getId());
			p.setPrizeTime(prize.getPrizeTime());
			p.setDesc(prize.getDesc());
			temp.add(p);
		}
		return temp;
	}

	@Override
	public JSONArray allPrizes() throws SQLException {
		List<Prize> prizes = prizeMapper.allPrizes();
		JSONArray temp = prizesJSONArr(prizes);
		return temp;
	}

	private JSONArray prizesJSONArr(List<Prize> list) throws SQLException {
		GreenCard greenCard = null;
		User user = null;
		Map<String, Object> tempMap = null;
		JSONArray temp = new JSONArray();
		for (Prize prize : list) {
			user = userMapper.selectOneByID(prize.getUserId());
			greenCard = greencardMapper.getGreenCardById(prize.getUserId());
			if (greenCard == null || user==null) {
				continue;
			}
			tempMap = new HashMap<String, Object>();
			tempMap.put("id", prize.getId());// 自增编号
			tempMap.put("gcNumber", greenCard.getGcNumber());// 绿卡编号
			tempMap.put("avatarUrl", user.getAvatarUrl());// 头像url
			tempMap.put("nickName", user.getNickName());// 昵称
			tempMap.put("desc", prize.getDesc());// 自增编号
			temp.add(tempMap);
		}
		return temp;
	}
}
