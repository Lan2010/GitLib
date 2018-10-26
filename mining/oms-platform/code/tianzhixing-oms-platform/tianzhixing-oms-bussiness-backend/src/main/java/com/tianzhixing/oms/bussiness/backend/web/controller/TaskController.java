package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.MapType;
import com.tianzhixing.bussiness.commons.em.TaskStatus;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.TaskInfoService;
import com.tianzhixing.oms.bussiness.backend.web.vo.TaskInfoVo;
import com.tianzhixing.oms.bussiness.backend.web.vo.TaskLocationInfoVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskInfoMappper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskLocationInfoMapper;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Index
 * Created by routine.k on 16/8/12.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/task")
public class TaskController extends BaseController {

    @Autowired
    private TaskInfoService taskInfoService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("task/index");
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
    public ResponseEntity<TaskInfoVo> list(@RequestParam("pageNo") int pageNo,
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
        long count = taskInfoService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
        List<TaskInfoVo> taskInfoVoList = new ArrayList<>();
        if (count > 0) {
            List<TaskInfoMappper> taskInfoMapppers = taskInfoService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, pagerMapping);
            for (TaskInfoMappper taskInfoMappper : taskInfoMapppers) {
                taskInfoVoList.add(_toVo(taskInfoMappper));
            }
        }
        return responseEntity(200, "task.list.suc", count, taskInfoVoList);
    }

    /**
     * 列表
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TaskInfoVo> all() {

        List<TaskInfoVo> taskInfoVoList = new ArrayList<>();
        List<TaskInfoMappper> taskInfoMapppers = taskInfoService.list();
        for (TaskInfoMappper taskInfoMappper : taskInfoMapppers) {
            taskInfoVoList.add(_toVo(taskInfoMappper));
        }
        return responseEntity(200, "task.list.suc", taskInfoVoList.size(), taskInfoVoList);
    }


    /**
     * 任务地图
     *
     * @return
     */
    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public ModelAndView location() {
        return configModelAndView("task/location");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/location/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TaskLocationInfoVo> locationList(@RequestParam("pageNo") int pageNo,
                                                           @RequestParam("pageSize") int pageSize,
                                                           @RequestParam(value = "taskId", required = false) String taskId) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = taskInfoService.countTaskLocationByTaskId(StringUtils.isEmpty(taskId) ? null : Long.valueOf(taskId));
        List<TaskLocationInfoVo> taskInfoVoList = new ArrayList<>();
        if (count > 0) {
            List<TaskLocationInfoMapper> taskLocationInfoMappers = taskInfoService.listTaskLocationByTaskId(StringUtils.isEmpty(taskId) ? null : Long.valueOf(taskId), pagerMapping);
            for (TaskLocationInfoMapper locationInfoMapper : taskLocationInfoMappers) {
                TaskInfoMappper taskInfoMappper = taskInfoService.getById(locationInfoMapper.getTaskId());
                taskInfoVoList.add(_toVo(locationInfoMapper, taskInfoMappper == null ? null : taskInfoMappper.getTaskName()));
            }
        }
        return responseEntity(200, "task.location.list.suc", count, taskInfoVoList);
    }

    /**
     * 添加任务
     *
     * @param taskInfoVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TaskInfoVo> addTask(@ModelAttribute("taskInfoVo") TaskInfoVo taskInfoVo) {
        if (StringUtils.isEmpty(taskInfoVo.getTaskName())) {
            return responseEntity(301, "task.name.empty");
        }
        if (StringUtils.isEmpty(taskInfoVo.getTaskIcon())) {
            return responseEntity(301, "task.icon.empty");
        }
        if (StringUtils.isEmpty(taskInfoVo.getCity())) {
            return responseEntity(301, "task.city.empty");
        }
        if (taskInfoVo.getTaskRadius() == null || taskInfoVo.getTaskRadius() < 0) {
            return responseEntity(301, "task.radius.error");
        }
        if (StringUtils.isEmpty(taskInfoVo.getKeyword())) {
            return responseEntity(301, "task.keyword.empty");
        }
        if (taskInfoVo.getRate() < 0) {
            return responseEntity(301, "task.rate.error");
        }
        if (taskInfoVo.getBeginTime() == null) {
            return responseEntity(301, "task.begin.time.empty");
        }
        if (taskInfoVo.getBeginTime() == null) {
            return responseEntity(301, "task.begin.time.empty");
        }
        if (taskInfoVo.getTaskLevel() == null) {
            return responseEntity(301, "task.level.empty");
        }
        if (StringUtils.isEmpty(taskInfoVo.getTaskRemark())) {
            return responseEntity(301, "task.remark.empty");
        }
        if (taskInfoVo.getTaskAward() == null) {
            return responseEntity(301, "task.award.empty");
        }
        if (CalendarUtil.isAfter(taskInfoVo.getBeginTime(), taskInfoVo.getEndTime())) {
            return responseEntity(301, "task.begin.end.time.error");
        }
        try {
            taskInfoVo.setCreateTime(new Date());
            taskInfoVo.setIsSend(false);
            taskInfoVo.setTaskStatus(TaskStatus.ENABLE.name());
            TaskInfoMappper taskInfoMappper = taskInfoService.add(_toMapper(taskInfoVo), taskInfoVo.getIsAutoMap() == null ? false : taskInfoVo.getIsAutoMap());
            taskInfoVo.setId(taskInfoMappper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, taskInfoVo, ex.getMessage());
        }
        return responseEntity(200, taskInfoVo, "request.suc");
    }

    /**
     * 添加任务地址
     *
     * @param taskLocationInfoVo
     * @return
     */
    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TaskLocationInfoVo> addTaskLocation(@ModelAttribute("taskLocationInfoVo") TaskLocationInfoVo taskLocationInfoVo) {
        if (taskLocationInfoVo.getTaskId() == null || taskLocationInfoVo.getTaskId() == 0) {
            return responseEntity(301, "task.not.found");
        }
        if (StringUtils.isEmpty(taskLocationInfoVo.getName())) {
            return responseEntity(301, "task.location.name.empty");
        }
        if (StringUtils.isEmpty(taskLocationInfoVo.getLat())) {
            return responseEntity(301, "task.location.lat.empty");
        }
        if (StringUtils.isEmpty(taskLocationInfoVo.getLng())) {
            return responseEntity(301, "task.location.lng.empty");
        }
        if (StringUtils.isEmpty(taskLocationInfoVo.getAddress())) {
            return responseEntity(301, "task.location.address.empty");
        }
        if (StringUtils.isEmpty(taskLocationInfoVo.getMapType())) {
            return responseEntity(301, "task.location.maptype.empty");
        }
        TaskInfoMappper taskInfoMappper = taskInfoService.getById(taskLocationInfoVo.getTaskId());
        if (taskInfoMappper == null) {
            return responseEntity(301, "task.not.found");
        }
        try {
            TaskLocationInfoMapper taskLocationInfoMapper = _toLocationMapper(taskLocationInfoVo, BaiduMapConfig.BAIDU_MAP_COORDTYPE);
            taskLocationInfoMapper.setEnable(true);
            taskLocationInfoMapper.setProvince(null);
            taskLocationInfoMapper.setCity(taskInfoMappper.getCity());
            taskInfoService.addLocation(taskLocationInfoMapper);
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, taskLocationInfoVo, ex.getMessage());
        }
        return responseEntity(200, taskLocationInfoVo, "request.suc");
    }

    private TaskLocationInfoMapper _toLocationMapper(TaskLocationInfoVo taskLocationInfoVo, String coordType) {
        return new TaskLocationInfoMapper(taskLocationInfoVo.getId(), taskLocationInfoVo.getTaskId(), taskLocationInfoVo.getEnable(), taskLocationInfoVo.getName(), "lat:" + taskLocationInfoVo.getLat() + "|lng:" + taskLocationInfoVo.getLng(), taskLocationInfoVo.getAddress(), taskLocationInfoVo.getProvince(), taskLocationInfoVo.getCity(), taskLocationInfoVo.getArea(), coordType, MapType.valueOf(taskLocationInfoVo.getMapType()), null);
    }

    /**
     * 更新任务
     *
     * @param taskInfoVo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TaskInfoVo> update(@ModelAttribute("taskInfoVo") TaskInfoVo taskInfoVo) {
        if (StringUtils.isEmpty(taskInfoVo.getTaskName())) {
            return responseEntity(301, "task.name.empty");
        }
        if (StringUtils.isEmpty(taskInfoVo.getTaskIcon())) {
            return responseEntity(301, "task.icon.empty");
        }
        if (taskInfoVo.getTaskRadius() == null || taskInfoVo.getTaskRadius() < 0) {
            return responseEntity(301, "task.radius.error");
        }
        if (taskInfoVo.getTaskLevel() == null) {
            return responseEntity(301, "task.level.empty");
        }
        if (StringUtils.isEmpty(taskInfoVo.getTaskRemark())) {
            return responseEntity(301, "task.remark.empty");
        }
        if (taskInfoVo.getTaskAward() == null) {
            return responseEntity(301, "task.award.empty");
        }
        if (taskInfoVo.getRate() < 0) {
            return responseEntity(301, "task.rate.error");
        }
        if (taskInfoVo.getBeginTime() == null) {
            return responseEntity(301, "task.begin.time.empty");
        }
        if (taskInfoVo.getBeginTime() == null) {
            return responseEntity(301, "task.end.time.empty");
        }
        if (CalendarUtil.isAfter(taskInfoVo.getBeginTime(), taskInfoVo.getEndTime())) {
            return responseEntity(301, "task.begin.end.time.error");
        }
        try {
            taskInfoService.update(_toMapper(taskInfoVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, taskInfoVo, ex.getMessage());
        }
        return responseEntity(200, taskInfoVo, "request.suc");
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TaskInfoVo> detail(@RequestParam("id") long id) {
        TaskInfoMappper taskInfoMappper = taskInfoService.getById(id);
        if (taskInfoMappper == null) {
            return responseEntity(301, "task.not.found");
        }
        TaskInfoVo vo = _toVo(taskInfoMappper);
        return responseEntity(200, vo, "request.suc");
    }

    /**
     * 推送任务
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/push", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<TaskInfoVo> push(@RequestParam(value = "taskId") Long taskId) {
        if (taskId == null || taskId == 0) {
            return responseEntity(301, "task.id.empty");
        }
        try {
            taskInfoService.push(taskId, SecurityUtil.currentLogin().getAccount());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, null, ex.getMessage());
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return responseEntity(301, null, ex.getMessage());
        }
        return responseEntity(200, null, "request.suc");
    }

    private TaskInfoMappper _toMapper(TaskInfoVo taskInfoVo) {
        return new TaskInfoMappper(taskInfoVo.getId(), taskInfoVo.getCreateTime(), null, SecurityUtil.currentLogin().getAccount(), SecurityUtil.currentLogin().getAccount(), taskInfoVo.getTaskName().trim(), taskInfoVo.getCity(), taskInfoVo.getArea(), taskInfoVo.getKeyword().trim(), taskInfoVo.getRate(), taskInfoVo.getTaskStatus() == null ? null : TaskStatus.valueOf(taskInfoVo.getTaskStatus()), taskInfoVo.getBeginTime(), taskInfoVo.getEndTime(), taskInfoVo.getIsSend(), taskInfoVo.getTaskRadius(), taskInfoVo.getTaskIcon(), taskInfoVo.getTaskAward(), taskInfoVo.getTaskLevel(), taskInfoVo.getTaskRemark());
    }


    private TaskLocationInfoVo _toVo(TaskLocationInfoMapper locationInfoMapper, String taskName) {
        return new TaskLocationInfoVo(locationInfoMapper.getId(), locationInfoMapper.getTaskId(), taskName, locationInfoMapper.getEnable(), locationInfoMapper.getEnable() ? "ENABLE" : "DISABLE", locationInfoMapper.getName(), locationInfoMapper.getLocation(), locationInfoMapper.getAddress(), locationInfoMapper.getProvince(), locationInfoMapper.getCity(), locationInfoMapper.getArea());
    }


    private TaskInfoVo _toVo(TaskInfoMappper taskInfoMappper) {
        return new TaskInfoVo(taskInfoMappper.getId(), taskInfoMappper.getBeginTime(), taskInfoMappper.getEndTime(), taskInfoMappper.getCreateTime(), taskInfoMappper.getTaskName(), taskInfoMappper.getCity(), taskInfoMappper.getArea(), taskInfoMappper.getKeyword(), taskInfoMappper.getRate(), taskInfoMappper.getTaskStatus().getValue(), taskInfoMappper.getIsSend(), taskInfoMappper.getTaskRadius(), taskInfoMappper.getTaskIcon(), taskInfoMappper.getTaskAward(), taskInfoMappper.getTaskLevel(), taskInfoMappper.getTaskRemark());
    }


}
