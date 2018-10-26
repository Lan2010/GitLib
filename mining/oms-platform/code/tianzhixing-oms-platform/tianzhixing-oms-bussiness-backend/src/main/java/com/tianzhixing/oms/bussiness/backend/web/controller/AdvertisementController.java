package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.AdvertisementAttribute;
import com.tianzhixing.bussiness.commons.em.AdvertisementStatus;
import com.tianzhixing.bussiness.commons.em.AdvertisementType;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.AdvertisementService;
import com.tianzhixing.oms.bussiness.backend.web.vo.AdvertisementVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.advertisement.AdvertisementMapper;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/advert")
public class AdvertisementController extends BaseController {

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("advertisement/index");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @param beginTimeB
     * @param endTimeB
     * @param beginTimeE
     * @param endTimeE
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AdvertisementVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize,
                                                @RequestParam(value = "beginTimeB", required = false) String beginTimeB,
                                                @RequestParam(value = "endTimeB", required = false) String endTimeB,
                                                @RequestParam(value = "beginTimeE", required = false) String beginTimeE,
                                                @RequestParam(value = "endTimeE", required = false) String endTimeE) {

        String timeFormat = "yyyy-MM-dd";
        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        Date sBeginTime = null;
        if (StringUtil.check(beginTimeB)) {
            sBeginTime = CalendarUtil.calBeginTimeForThisTime(CalendarUtil.stringToDate(beginTimeB, timeFormat));
        }
        Date sEndTime = null;
        if (StringUtil.check(endTimeB)) {
            sEndTime = CalendarUtil.calEndTimeForThisTime(CalendarUtil.stringToDate(endTimeB, timeFormat));
        }
        Date aBeginTime = null;
        if (StringUtil.check(beginTimeE)) {
            aBeginTime = CalendarUtil.calBeginTimeForThisTime(CalendarUtil.stringToDate(beginTimeE, timeFormat));
        }
        Date aEndTime = null;
        if (StringUtil.check(endTimeE)) {
            aEndTime = CalendarUtil.calEndTimeForThisTime(CalendarUtil.stringToDate(endTimeE, timeFormat));
        }
        long count = advertisementService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
        List<AdvertisementVo> advertisementVos = new ArrayList<>();
        if (count > 0) {
            List<AdvertisementMapper> advertisementMappers = advertisementService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, pagerMapping);
            for (AdvertisementMapper advertisementMapper : advertisementMappers) {
                advertisementVos.add(_toVo(advertisementMapper));
            }
        }
        return responseEntity(200, "request.suc", count, advertisementVos);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AdvertisementVo> detail(@RequestParam("id") long id) {
        AdvertisementMapper advertisementMapper = advertisementService.getById(id);
        if (advertisementMapper == null) {
            return responseEntity(301, "advertisement.not.found");
        }
        AdvertisementVo vo = _toVo(advertisementMapper);
        vo.setCity(BaiduMapConfig.code(advertisementMapper.getCity()));
        for (AdvertisementType advertisementType : AdvertisementType.values()) {
            if (advertisementType.getValue().equals(vo.getAdvertisementType())) {
                vo.setAdvertisementType(advertisementType.name());
            }
        }
        for (AdvertisementAttribute advertisementAttribute : AdvertisementAttribute.values()) {
            if (advertisementAttribute.getValue().equals(vo.getAdvertisementAttribute())) {
                vo.setAdvertisementAttribute(advertisementAttribute.name());
            }
        }
        return responseEntity(200, vo, "request.suc");
    }

    /**
     * 推送
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AdvertisementVo> push(@RequestParam("id") long id) {
        try {
            advertisementService.push(id, SecurityUtil.currentLogin().getAccount());
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return responseEntity(301, ex.getMessage());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, ex.getMessage());
        }
        return responseEntity(200, "request.suc");
    }


    /**
     * 添加
     *
     * @param advertisementVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AdvertisementVo> add(@ModelAttribute("advertisementVo") AdvertisementVo advertisementVo) {
        if (StringUtils.isEmpty(advertisementVo.getAdvertName())) {
            return responseEntity(301, "advert.name.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertIcon())) {
            return responseEntity(301, "advert.icon.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertPic())) {
            return responseEntity(301, "advert.pic.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getCity())) {
            return responseEntity(301, "advert.city.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertisementDescribe())) {
            return responseEntity(301, "advert.describe.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertRemark())) {
            return responseEntity(301, "advert.remark.empty");
        }
        if (advertisementVo.getTotalCount() <= 0) {
            return responseEntity(301, "advert.totalcount.error");
        }
        if (advertisementVo.getTotalAccessStarPoint() < 0) {
            return responseEntity(301, "advert.starpoint.error");
        }
        if (advertisementVo.getTotalClickStarPoint() < 0) {
            return responseEntity(301, "advert.starpoint.error");
        }
        if (advertisementVo.getBeginTime() == null) {
            return responseEntity(301, "advert.begin.time.empty");
        }
        if (advertisementVo.getTotalAccessStarPoint() < 0) {
            return responseEntity(301, "advert.starpoint.error");
        }
        if (advertisementVo.getBeginTime() == null) {
            return responseEntity(301, "advert.end.time.empty");
        }
        if (CalendarUtil.isAfter(advertisementVo.getBeginTime(), advertisementVo.getEndTime())) {
            return responseEntity(301, "advert.begin.end.time.error");
        }
        try {
            advertisementVo.setIsSend(false);
            advertisementVo.setAdvertStatus(AdvertisementStatus.ENABLE.name());
            advertisementVo.setCreateTime(new Date());
            AdvertisementMapper advertisementMapper = advertisementService.add(_toMapper(advertisementVo));
            advertisementVo.setId(advertisementMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, advertisementVo, ex.getMessage());
        }
        return responseEntity(200, advertisementVo, "request.suc");
    }

    /**
     * update
     *
     * @param advertisementVo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AdvertisementVo> update(@ModelAttribute("advertisementVo") AdvertisementVo advertisementVo) {
        if (advertisementVo.getId() == null || advertisementVo.getId() == 0) {
            return responseEntity(301, "advert.not.found");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertIcon())) {
            return responseEntity(301, "advert.icon.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertPic())) {
            return responseEntity(301, "advert.pic.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertName())) {
            return responseEntity(301, "advert.name.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getCity())) {
            return responseEntity(301, "advert.city.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertisementDescribe())) {
            return responseEntity(301, "advert.describe.empty");
        }
        if (StringUtils.isEmpty(advertisementVo.getAdvertRemark())) {
            return responseEntity(301, "advert.remark.empty");
        }
        if (advertisementVo.getTotalCount() <= 0) {
            return responseEntity(301, "advert.totalcount.error");
        }
        if (advertisementVo.getTotalAccessStarPoint() < 0) {
            return responseEntity(301, "advert.starpoint.error");
        }
        if (advertisementVo.getTotalClickStarPoint() < 0) {
            return responseEntity(301, "advert.starpoint.error");
        }
        if (advertisementVo.getBeginTime() == null) {
            return responseEntity(301, "advert.begin.time.empty");
        }
        if (advertisementVo.getBeginTime() == null) {
            return responseEntity(301, "advert.end.time.empty");
        }
        if (CalendarUtil.isAfter(advertisementVo.getBeginTime(), advertisementVo.getEndTime())) {
            return responseEntity(301, "advert.begin.end.time.error");
        }
        try {
            advertisementService.update(_toMapper(advertisementVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, advertisementVo, ex.getMessage());
        }
        return responseEntity(200, advertisementVo, "request.suc");
    }

    private AdvertisementMapper _toMapper(AdvertisementVo advertisementVo) {
        return new AdvertisementMapper(advertisementVo.getId(), advertisementVo.getCreateTime(), null, SecurityUtil.currentLogin().getAccount(), SecurityUtil.currentLogin().getAccount(), advertisementVo.getAdvertName(), advertisementVo.getCity(), advertisementVo.getArea(), advertisementVo.getAdvertRemark(), advertisementVo.getAdvertIcon(), advertisementVo.getAdvertPic(), advertisementVo.getBeginTime(), advertisementVo.getEndTime(), org.apache.commons.lang3.StringUtils.isEmpty(advertisementVo.getAdvertStatus()) ? null : AdvertisementStatus.valueOf(advertisementVo.getAdvertStatus()), AdvertisementType.valueOf(advertisementVo.getAdvertisementType()), advertisementVo.getTotalCount(), advertisementVo.getTotalAccessStarPoint(), advertisementVo.getOnceAccessStarPoint(), advertisementVo.getIsSend(), advertisementVo.getAdvertisementLink(), advertisementVo.getAdvertisementDescribe(), AdvertisementAttribute.valueOf(advertisementVo.getAdvertisementAttribute()), advertisementVo.getOnceClickStarPoint(), advertisementVo.getTotalClickStarPoint());
    }

    private AdvertisementVo _toVo(AdvertisementMapper advertisementMapper) {
        return new AdvertisementVo(advertisementMapper.getId(), advertisementMapper.getCreateTime(), advertisementMapper.getAdvertName(), advertisementMapper.getCity(), advertisementMapper.getArea(), advertisementMapper.getAdvertRemark(), advertisementMapper.getAdvertIcon(), advertisementMapper.getAdvertPic(), advertisementMapper.getBeginTime(), advertisementMapper.getEndTime(), advertisementMapper.getAdvertStatus().getValue(), advertisementMapper.getAdvertisementType().getValue(), advertisementMapper.getTotalCount(), advertisementMapper.getTotalAccessStarPoint(), advertisementMapper.getOnceAccessStarPoint(), advertisementMapper.getIsSend(), advertisementMapper.getAdvertisementLink(), advertisementMapper.getAdvertisementDescribe(), advertisementMapper.getAdvertisementAttribute().getValue(), advertisementMapper.getOnceClickStarPoint(), advertisementMapper.getTotalClickStarPoint());
    }
}
