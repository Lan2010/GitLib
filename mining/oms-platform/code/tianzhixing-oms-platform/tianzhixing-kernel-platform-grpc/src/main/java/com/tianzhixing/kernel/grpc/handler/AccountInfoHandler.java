package com.tianzhixing.kernel.grpc.handler;

import com.tianzhixing.kernel.commons.ex.AccountAlreadyExitsException;
import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.utils.Assert;
import com.tianzhixing.kernel.grpc.exception.AccountRegException;
import com.tianzhixing.kernel.grpc.util.AccountTokenGeneratUtil;
import com.tianzhixing.kernel.rpc.mapper.account.*;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by routine.k on 2018/6/21.
 */
@Service
public class AccountInfoHandler {

    @Autowired
    private RPCAccountInfoService rpcAccountInfoService;

    /**
     * 添加
     *
     * @param mobile
     * @param thirdToken
     * @param regTime
     * @param org
     * @param referrerToken
     */
    public String add(String mobile, String thirdToken, Date regTime, String org, String referrerToken) {
        //check mobile exists
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByMobile(mobile, org);
        Assert.isNull(accountInfoMapper, new AccountAlreadyExitsException(accountInfoMapper != null ? accountInfoMapper.getAccountToken() : null));
        //check thirdtoken exists
        accountInfoMapper = rpcAccountInfoService.getByThirdToken(thirdToken, org);
        Assert.isNull(accountInfoMapper, new AccountAlreadyExitsException("thirdToken.already.exists"));
        //如果推荐人token不为空，检查推荐人信息
        AccountInfoMapper referrerAccount = null;
        if (StringUtils.isNotEmpty(referrerToken)) {
            referrerAccount = rpcAccountInfoService.getByAccountToken(referrerToken);
            Assert.notNull(referrerAccount, new AccountRegException("referrer.account.not.found"));
        }
        accountInfoMapper = rpcAccountInfoService.add(new AccountInfoMapper(null, 0, new Date(), new Date(), regTime, mobile, AccountTokenGeneratUtil.generat(org), org, thirdToken), referrerAccount == null ? null : referrerAccount.getId());
        return accountInfoMapper.getAccountToken();
    }

    /**
     * 通过account token查询账户星点
     *
     * @param accountToken
     * @return
     */
    public AccountStarPointInfoMapper getAccountStartPoint(String accountToken) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        AccountStarPointInfoMapper accountStarPointInfoMapper = rpcAccountInfoService.getAccountStarPointByAccountId(accountInfoMapper.getId());
        Assert.notNull(accountStarPointInfoMapper, new AccountStarPointNotFoundException("... get account starpoint info by account token[" + accountToken + "] failed, account starpoint not found ..."));
        return accountStarPointInfoMapper;
    }

    /**
     * 排行信息
     *
     * @param org
     * @param from
     * @param size
     * @return
     */
    public AccountWithStarPointInfoWithPagerMapper ranking(String org, int from, int size) {
        return rpcAccountInfoService.listWithRanking(org, from, size);
    }

    /**
     * 记录信息 - 以天为单位
     *
     * @param accountToken
     * @param endTime
     * @param days
     * @return
     */
    public List<AccountStarPointWithDayRecordsMapper> recordsWithDay(String accountToken, Date endTime, int days) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        return rpcAccountInfoService.recordsWithDay(accountInfoMapper, endTime, days);
    }

    /**
     * 根据手机号和机构编号获取
     *
     * @param mobile
     * @param org
     * @return
     */
    public AccountInfoMapper getByMobileAndOrg(String mobile, String org) {
        return rpcAccountInfoService.getByMobile(mobile, org);
    }

    /**
     * 根据任务获取星点
     *
     * @param accountToken
     * @param taskIds
     * @return
     */
    public List<AccountStarPointWithTaskMapper> starPointWithTask(String accountToken, List<String> taskIds) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        return rpcAccountInfoService.starPointWithTask(accountInfoMapper, taskIds);
    }
}
