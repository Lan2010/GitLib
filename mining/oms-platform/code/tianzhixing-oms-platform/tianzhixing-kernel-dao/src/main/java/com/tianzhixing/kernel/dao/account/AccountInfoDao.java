package com.tianzhixing.kernel.dao.account;

import com.tianzhixing.kernel.dao.generic.GenericBaseDao;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/6/19.
 */
@Repository("accountInfoDao")
public class AccountInfoDao extends GenericBaseDao<AccountInfoModel, Long> {


    /**
     * 添加
     *
     * @param accountInfoModel
     */
    public AccountInfoModel add(final AccountInfoModel accountInfoModel) {
        Long id = super.insert(accountInfoModel);
        accountInfoModel.setId(id);
        return accountInfoModel;
    }

    /**
     * 通过手机号获取
     *
     * @param mobile
     * @param org
     * @return
     */
    public AccountInfoModel getByMobile(final String mobile, final String org) {
        return super.get(new String[]{"mobile", "org"}, new Object[]{mobile, org});
    }

    /**
     * 通过第三方Token获取
     *
     * @param thirdToken
     * @param org
     * @return
     */
    public AccountInfoModel getByThirdToken(final String thirdToken, final String org) {
        return super.get(new String[]{"third_token", "org"}, new Object[]{thirdToken, org});
    }

    /**
     * 通过account token获取账户
     *
     * @param accountToken
     * @return
     */
    public AccountInfoModel getByAccountToken(final String accountToken) {
        return super.get(new String[]{"account_token"}, new Object[]{accountToken});
    }

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    public AccountInfoModel getById(final Long id) {
        return super.get(id);
    }

    /**
     * 获取数量
     *
     * @param org
     * @return
     */
    public long countAccount(final String org) {
        return super.count(new String[]{"org"}, new Object[]{org});
    }
}
