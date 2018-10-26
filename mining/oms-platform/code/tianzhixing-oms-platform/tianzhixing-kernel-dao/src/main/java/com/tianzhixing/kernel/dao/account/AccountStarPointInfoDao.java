package com.tianzhixing.kernel.dao.account;

import com.tianzhixing.kernel.dao.generic.GenericBaseDao;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/19.
 */
@Repository("accountStarPointInfoDao")
public class AccountStarPointInfoDao extends GenericBaseDao<AccountStarPointInfoModel, Long> {


    /**
     * 添加
     *
     * @param accountStarPointInfoModel
     */
    public AccountStarPointInfoModel add(final AccountStarPointInfoModel accountStarPointInfoModel) {
        Long id = super.insert(accountStarPointInfoModel);
        accountStarPointInfoModel.setId(id);
        return accountStarPointInfoModel;
    }

    /**
     * 通过account id获取
     *
     * @param accountId
     * @return
     */
    public AccountStarPointInfoModel getByAccountId(final Long accountId) {
        return super.get(new String[]{"account_id"}, new Object[]{accountId});
    }

    /**
     * 分页获取排行数据
     *
     * @param org
     * @param from
     * @param size
     * @return
     */
    public List<AccountStarPointInfoModel> listRankingAccount(final String org, final int from, final int size) {
        String sql = "SELECT t_asi.* FROM account_starpoint_info AS t_asi, account_info AS t_ai WHERE t_asi.account_id = t_ai.id AND t_ai.org = '" + org + "'  GROUP BY id ORDER BY SUM(available_starpoint+frozen_starpoint) DESC LIMIT " + from + ", " + size + "";
        return super.list(sql);
    }

    /**
     * 更新可用星星
     *
     * @param id
     * @param availableStarPoint
     * @param updateTime
     * @param version
     * @return
     */
    public void updateAvailableStarPoint(final Long id, final Double availableStarPoint, final Date updateTime, final Integer version) {
        super.update(id, new String[]{"update_time", "available_starpoint"}, new Object[]{updateTime, availableStarPoint}, version);
    }
}