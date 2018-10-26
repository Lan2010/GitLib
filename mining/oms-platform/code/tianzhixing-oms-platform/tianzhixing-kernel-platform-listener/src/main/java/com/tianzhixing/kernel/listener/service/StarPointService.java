package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.listener.entity.DeviceEntity;
import com.tianzhixing.kernel.listener.entity.RateEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service
public class StarPointService {

    public double calStarPoint(RateEntity rateEntity, DeviceEntity deviceEntity) {
        return _multiply(Double.valueOf(deviceEntity.getCount()), Double.valueOf(rateEntity.getRate())).doubleValue();
    }

    /*
     * 乘法运算，四舍五入
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    private BigDecimal _multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(6, BigDecimal.ROUND_HALF_UP);
    }
}
