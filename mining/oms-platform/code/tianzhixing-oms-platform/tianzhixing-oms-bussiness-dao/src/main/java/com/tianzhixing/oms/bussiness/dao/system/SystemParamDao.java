package com.tianzhixing.oms.bussiness.dao.system;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.system.SystemParamModel;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/6/25.
 */
@Repository("systemParamDao")
public class SystemParamDao extends GenericBaseDao<SystemParamModel, Long> {

    /**
     * 根据类型获取
     *
     * @param systemParamType
     * @return
     */
    public SystemParamModel getType(final SystemParamType systemParamType) {
        return super.get(new String[]{"system_param_type"}, new Object[]{systemParamType.name()});
    }
}
