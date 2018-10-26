package com.tianzhixing.app.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.AwardStarModule;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.PhoneList;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult;
import com.tianzhixing.kernel.grpc.proto.account.reg.Result;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult;

public interface UserService {
	/**
	 * 用户登录
	 * 
	 * @param user
	 * @return 登录提示信息，失败时返回失败的原因描述
	 */
	public ResultMessage login(User user,BaseRequest baseRequest) throws SQLException;

	/**
	 * 推送登录消息到nats
	 * 
	 * @param user
	 */
	void pushLogin2Nats(User user);

	/**
	 * 发送退出登录消息到nats
	 * 
	 * @param user
	 * @throws SQLException
	 */
	void pushLogout2Nats(HttpServletRequest request);

	Map<String, Object> setUserInfo(User user, String loginType, BaseRequest baseRequest) throws SQLException;

	public Long logout(HttpServletRequest request, BaseRequest baseRequest);

	/**
	 * 根据accountToken查找用户信息
	 * 
	 * @param accountToken
	 * @return
	 */
	User getUserByToken(String accountToken) throws SQLException;

	/**
	 * 根据USERid获取用户信息
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	User getUserById(Integer userId) throws SQLException;

	/**
	 * 短息验证码登录
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 * @throws GrpcException 
	 */
	ResultMessage smsLogin(User user, BaseRequest baseRequest) throws SQLException, GrpcException;

	/**
	 * 根据手机号检索用户信息
	 * 
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	User getByPhone(String phone) throws SQLException;

	/**
	 * 校验用户输入的短信验证码是否正确
	 * @param smsCode
	 * @param phone
	 * @param useType
	 * @return
	 * @throws GrpcException 
	 */
	ResultMessage smsValidate(String smsCode, String phone, String useType) throws GrpcException;

	/**
	 * 用户注册
	 * 
	 * @param user
	 * @throws SQLException
	 * @throws GrpcException 
	 */
	public ResultMessage register(User user, BaseRequest baseRequest) throws SQLException, GrpcException;

	/**
	 * 根据邀请码获取邀请人信息
	 * 
	 * @param inviteCode
	 * @return
	 */
	public User getUserByInviter(String inviteCode) throws SQLException;

	

	/**
	 * 发送注册信息到nats
	 * 
	 * @param ip
	 * @param terminal
	 * @param phone
	 */
	void pushRegister2Nats(String ip, Short terminal, String phone);

	/**
	 * 发送用户认证信息（绑定银行卡，实名认证，绑定通讯录等）到nats
	 * 
	 * @param terminal
	 * @param authType
	 */
	void pushUserAuth2Nats(Short terminal, String authType, short isSuccess);

	/**
	 * 获取某用户的奖励星星情况
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	JSONObject getStarStatus(Integer userId) throws SQLException;

	/**
	 * 获取用户指定星星奖励模块的状态
	 * 
	 * @param moduleCode
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	Integer getOneModule(AwardStarModule awardStarModule, Integer userId) throws SQLException;

	/**
	 * 添加定星星奖励模块信息
	 * 
	 * @param maillist
	 * @throws SQLException 
	 */
	public Integer addModule(Integer userId, AwardStarModule awardStarModule) throws SQLException;

	/**
	 * 查询指定用户已经同步的通讯录信息
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public List<PhoneList> getPhoneLists(Integer userId) throws SQLException;

	/**
	 * 批量添加通讯录
	 * 
	 * @param phones
	 */
	public int addPhoneLists(List<PhoneList> phones) throws SQLException;;

	/**
	 * 获取某用户通讯录信息
	 * 
	 * @param registerType
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	List<PhoneList> getPhoneLists(Integer registerType, Integer userId) throws SQLException;

	/**
	 * 钱包的曲线图
	 * 
	 * @param userId
	 * @param parseInt
	 * @param endTime
	 * @return
	 * @throws ParseException 
	 * @throws SQLException 
	 * @throws GrpcException 
	 */
	public ResultMessage getWithDayList(Integer userId, int days, String endTime) throws ParseException, SQLException, GrpcException;

	/**
	 * 星星领取记录列表
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public ResultMessage getUserCenterStar(Integer userId) throws SQLException;

	/**
	 * 删除用户通讯录
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public int delPhoneLists(Integer userId) throws SQLException;

	/**
	 * 获取某用户已邀请的人数
	 * 
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public Integer getInviteCount(Integer userId) throws SQLException;

	/**
	 * 更新用户邀请码
	 * 
	 * @param userId
	 * @param inviteCode
	 * @throws SQLException
	 */
	public void updateInviteCode(Integer userId, String inviteCode) throws SQLException;

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	Integer updateUser(User user) throws SQLException;

	/**
	 * 校验密码是否正确
	 * 
	 * @param userId
	 * @param oldPwd
	 * @throws SQLException
	 */
	public boolean verifyLoginPassword(Integer userId, String oldPwd) throws SQLException;

	/**
	 * 修改登录密码
	 * 
	 * @param userId
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 * @throws SQLException
	 */
	public String editLoginPassword(Integer userId, String oldPwd, String newPwd) throws SQLException;

	/**
	 * 获取到当前用户实力奖励列表
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	public ResultMessage getStarModuleList(Integer userId) throws SQLException;
	
	/**
	 * 重置密码
	 * @param userId
	 * @param newPwd
	 * @param phone
	 * @return
	 * @throws SQLException 
	 */
	public String restLoginPassword(Integer userId, String newPwd, String phone) throws SQLException;

	/**
	 * 认证银行卡奖励星星
	 * @param userId
	 * @param authType
	 * @param mt
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public ResultMessage award(Integer userId, String authType, Short mt) throws SQLException, GrpcException;

	void pushAuth2Nats(Integer terminal, String authType);

	//上传图片
	public ResultMessage upload(HttpServletRequest request, Integer userId, String type, CommonsMultipartFile file, Short mt) throws SQLException, IOException;
}
