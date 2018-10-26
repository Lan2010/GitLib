package core.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import core.common.Constant;
import core.mapper.GreencardMapper;
import core.mapper.PostcardMapper;
import core.pojo.Dest;
import core.pojo.GreenCard;
import core.pojo.PostCard;
import core.pojo.User;
import core.service.CardService;
import core.service.nats.NatsComponent;
import core.util.CommonUtils;

@Service
public class CardServiceImpl implements CardService {
	private static Logger log = LoggerFactory.getLogger(CardServiceImpl.class);

	@Autowired
	private GreencardMapper greencardMapper;
	@Autowired
	private PostcardMapper postcardMapper;
	@Autowired
	private NatsComponent natsComponent;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	// 通过ID查询绿卡信息
	public GreenCard getGreenCardById(String userId) throws NumberFormatException, SQLException {
		return greencardMapper.getGreenCardById(Integer.valueOf(userId));
	}

	// 绿卡总数
	public int selectGCCount() throws SQLException {
		return greencardMapper.selectGCCount();
	}

	// 保存绿卡
	public Integer saveGreenCard(GreenCard gc) throws SQLException {
		return greencardMapper.saveGreenCard(gc);
	}

	// 根据GCId获取绿卡
	public GreenCard getGCByGCId(int gcId) throws SQLException {
		return greencardMapper.getGCByGCId(gcId);
	}

	// 明信片景点总数
	public int selectDestCount() throws SQLException {
		return postcardMapper.selectDestCount();
	}

	// 明信片景点列表
	public List<Dest> selectDestsByPage(Map<String, Object> destPage) throws SQLException {
		return postcardMapper.selectDestsByPage(destPage);
	}

	// 通过ID获取某个景点信息
	public Dest getDestById(int destId) throws SQLException {
		return postcardMapper.getDestById(destId);

	}

	// 用户明信片总数
	public int selectUserPCCount(Integer userId) throws SQLException {
		return postcardMapper.selectUserPCCount(userId);
	}

	// 分页查询用户明信片列表
	public List<PostCard> selectPCsByPage(Map<String, Object> pcPage) throws SQLException {
		return postcardMapper.selectPCsByPage(pcPage);
	}

	// 获取某个用户明信片
	public PostCard getPostcardById(int pcId) throws SQLException {
		return postcardMapper.getPostcardById(pcId);
	}

	// 删除明信片信息
	public int delPostcard(Integer pcId) throws SQLException {
		return postcardMapper.delPostcard(pcId);
	}

	// 修改明信片标题
	public int updatePCTitle(PostCard pc) throws SQLException {
		return postcardMapper.updatePCTitle(pc);

	}

	/**
	 * 推送"创建/分享明信片）"消息到nats
	 */
	@Override
	public void publish4oms(User user, PostCard pc, Integer operationType) {
		try {
			JSONObject json = new JSONObject();
			json.put("id", CommonUtils.randomID(8));
			json.put("createTime", new Date());
			json.put("platformFrom", Constant.PLATFORM_NAME);
			json.put("clientPlatformType", Constant.CLIENT_PLATFORM_TYPE);
			json.put("mobile", user.getCellPhone());
			json.put("postCardId", pc.getPcId());
			json.put("postCardInfo", pc.getPcTitle());
			json.put("postCardLink", pc.getDestImgUrl());
			json.put("nickName", user.getNickName());
			// json.put("wxID","");
			json.put("operationTime", new Date());
			json.put("operationType", operationType);
			// json.put("shareFromPlatform", "wx");
			// json.put("shareToPlatform", "wx");
			natsComponent.publish4oms("oms.subject.user.create-share.postcard", json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception.", e);
		}
	}

	/**
	 * 推送"创建/分享绿卡"消息到nats
	 */
	@Override
	public void publish4oms(User user, GreenCard gc, Integer operationType) {
		try {
			JSONObject json = new JSONObject();
			json.put("id", CommonUtils.randomID(8));
			json.put("createTime", new Date());
			json.put("platformFrom", Constant.PLATFORM_NAME);
			json.put("clientPlatformType", Constant.CLIENT_PLATFORM_TYPE);
			json.put("mobile", user.getCellPhone());
			json.put("greeterCardId", gc.getGcId());
			json.put("greeterCardInfo", gc.getGcNumber());
			// json.put("greeterCardLink","");
			json.put("nickName", user.getNickName());
			// json.put("wxID","");
			json.put("operationTime", new Date());
			json.put("operationType", operationType);
			// json.put("shareFromPlatform", "wx");
			// json.put("shareToPlatform", "wx");
			natsComponent.publish4oms("oms.subject.user.create-share.greetercard", json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception.", e);
		}
	}
}