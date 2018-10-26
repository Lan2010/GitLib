package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.PagesDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.PagesDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
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
@RequestMapping(value = "/dimension/pagesDimension")
public class PagesDimensionController extends BaseController {

    @Autowired
    private PagesDimensionService pagesDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/pagesDimension");
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
    public ResponseEntity<PagesDimensionVo> list(@RequestParam("pageNo") int pageNo,
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
        long count = pagesDimensionService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
        List<PagesDimensionVo> pagesDimensionVos = new ArrayList<>();
        if (count > 0) {
            List<PagesDimensionMapper> pagesDimensionMappers = pagesDimensionService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, pagerMapping);
            for (PagesDimensionMapper pagesDimensionMapper : pagesDimensionMappers) {
                pagesDimensionVos.add(_toVo(pagesDimensionMapper));
            }
        }
        return responseEntity(200, "request.suc", count, pagesDimensionVos);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PagesDimensionVo> detail(@RequestParam("id") long id) {
        PagesDimensionMapper pagesDimensionMapper = pagesDimensionService.getById(id);
        if (pagesDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        PagesDimensionVo vo = _toVo(pagesDimensionMapper);
        return responseEntity(200, vo, "request.suc");
    }

    /**
     * 添加
     *
     * @param pagesDimensionVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PagesDimensionVo> add(@ModelAttribute("pagesDimensionVo") PagesDimensionVo pagesDimensionVo) {
        if (StringUtils.isEmpty(pagesDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(pagesDimensionVo.getUrl())) {
            return responseEntity(301, "dimension.url.empty");
        }
        if (pagesDimensionVo.getBeginTime() == null) {
            return responseEntity(301, "dimension.begin.time.empty");
        }
        if (pagesDimensionVo.getEndTime() == null) {
            return responseEntity(301, "dimension.end.time.empty");
        }
        if (CalendarUtil.isAfter(pagesDimensionVo.getBeginTime(), pagesDimensionVo.getEndTime())) {
            return responseEntity(301, "dimension.begin.end.time.error");
        }
        try {
            pagesDimensionVo.setVersion(0);
            pagesDimensionVo.setCreateTime(new Date());
            pagesDimensionVo.setEnable(true);
            PagesDimensionMapper pagesDimensionMapper = pagesDimensionService.add(_toMapper(pagesDimensionVo));
            pagesDimensionVo.setId(pagesDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, pagesDimensionVo, ex.getMessage());
        }
        return responseEntity(200, pagesDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param pagesDimensionVo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<PagesDimensionVo> update(@ModelAttribute("pagesDimensionVo") PagesDimensionVo pagesDimensionVo) {
        if (pagesDimensionVo.getId() == null || pagesDimensionVo.getId() == 0) {
            return responseEntity(301, "dimension.not.found");
        }
        if (StringUtils.isEmpty(pagesDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(pagesDimensionVo.getUrl())) {
            return responseEntity(301, "dimension.url.empty");
        }
        if (StringUtils.isEmpty(pagesDimensionVo.getEnable())) {
            return responseEntity(301, "dimension.enable.empty");
        }
        if (pagesDimensionVo.getBeginTime() == null) {
            return responseEntity(301, "dimension.begin.time.empty");
        }
        if (pagesDimensionVo.getEndTime() == null) {
            return responseEntity(301, "dimension.end.time.empty");
        }
        if (CalendarUtil.isAfter(pagesDimensionVo.getBeginTime(), pagesDimensionVo.getEndTime())) {
            return responseEntity(301, "dimension.begin.end.time.error");
        }
        try {
            pagesDimensionService.update(_toMapper(pagesDimensionVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, pagesDimensionVo, ex.getMessage());
        }
        return responseEntity(200, pagesDimensionVo, "request.suc");
    }

    private PagesDimensionMapper _toMapper(PagesDimensionVo pagesDimensionVo) {
        return new PagesDimensionMapper(pagesDimensionVo.getId(),pagesDimensionVo.getVersion(),pagesDimensionVo.getCreateTime(),pagesDimensionVo.getName(),pagesDimensionVo.getUrl(),pagesDimensionVo.getEnable(),pagesDimensionVo.getBeginTime(),pagesDimensionVo.getEndTime());
    }

    private PagesDimensionVo _toVo(PagesDimensionMapper pagesDimensionMapper) {
        return new PagesDimensionVo(pagesDimensionMapper.getId(),pagesDimensionMapper.getVersion(),pagesDimensionMapper.getCreateTime(),pagesDimensionMapper.getName(),pagesDimensionMapper.getUrl(),pagesDimensionMapper.getBeginTime(),pagesDimensionMapper.getEndTime(),pagesDimensionMapper.getEnable());
    }
}
