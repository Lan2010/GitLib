package com.tianzhixing.kernel.service;

import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;

import java.util.List;

/**
 * Created by routine.k on 2018/6/19.
 */
public interface AccountInfoService {

    /**
     * 添加
     *
     * @param accountInfoModel
     * @param startPoint
     * @param awardType
     * @param referrerAccountId
     * @param referrerType
     * @param referrerStarPoint
     */
    AccountInfoModel add(AccountInfoModel accountInfoModel, Double startPoint, String awardType, Long referrerAccountId, String referrerType, Double referrerStarPoint);

    /**
     * 通过手机号获取
     *
     * @param mobile
     * @param org
     * @return
     */
    AccountInfoModel getByMobile(String mobile, String org);

    /**
     * 通过第三方Token获取
     *
     * @param thirdToken
     * @param org
     * @return
     */
    AccountInfoModel getByThirdToken(String thirdToken, String org);

    /**
     * 通过account id 获取 account starpoint
     *
     * @param accountId
     * @return
     */
    AccountStarPointInfoModel getAccountStarPointByAccountId(Long accountId);

    /**
     * 获取排行账户
     *
     * @param org
     * @param from
     * @param size
     * @return
     */
    List<AccountStarPointInfoModel> listRankingAccount(String org, int from, int size);

    /**
     * 通过account token获取账户
     *
     * @param accountToken
     * @return
     */
    AccountInfoModel getByAccountToken(String accountToken);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    AccountInfoModel getById(Long id);

    /**
     * 获取数量
     *
     * @param org
     * @return
     */
    long countAccount(String org);

    /**
     * 获取最大ID
     *
     * @return
     */
    Long maxId();
}
