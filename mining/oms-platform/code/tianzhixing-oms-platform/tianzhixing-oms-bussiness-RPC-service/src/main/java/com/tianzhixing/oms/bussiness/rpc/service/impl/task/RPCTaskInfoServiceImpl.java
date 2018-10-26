package com.tianzhixing.oms.bussiness.rpc.service.impl.task;

import com.tianzhixing.bussiness.commons.em.TaskStatus;
import com.tianzhixing.oms.bussiness.model.task.TaskInfoModel;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;
import com.tianzhixing.oms.bussiness.redis.service.RedisTaskService;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskInfoMappper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskLocationInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.task.RPCTaskInfoService;
import com.tianzhixing.oms.bussiness.service.TaskInfoService;
import com.tianzhixing.oms.bussiness.service.TaskLocationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("RPCTaskInfoService")
public class RPCTaskInfoServiceImpl implements RPCTaskInfoService {

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private TaskLocationInfoService taskLocationInfoService;

    @Autowired
    private RedisTaskService redisTaskService;

    @Override
    public TaskInfoMappper getByTaskId(Long taskId) {
        TaskInfoModel taskInfoModel = taskInfoService.getById(taskId);
        return taskInfoModel == null ? null : _toMapper(taskInfoModel);
    }

    @Override
    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return taskInfoService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    @Override
    public List<TaskInfoMappper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize) {
        List<TaskInfoModel> taskInfoModelList = taskInfoService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, from, pageSize);
        List<TaskInfoMappper> taskInfoMappperList = new ArrayList<>();
        if (taskInfoModelList != null) {
            for (TaskInfoModel taskInfoModel : taskInfoModelList) {
                taskInfoMappperList.add(_toMapper(taskInfoModel));
            }
        }
        return taskInfoMappperList;
    }

    @Override
    public long countTaskLocationByTaskId(Long taskId) {
        return taskId == null ? taskLocationInfoService.count() : taskLocationInfoService.count(taskId);
    }

    @Override
    public List<TaskLocationInfoMapper> listTaskLocationByTaskId(Long taskId, int from, int pageSize) {
        List<TaskLocationInfoModel> taskLocationInfoModels = taskId == null ? taskLocationInfoService.list(from, pageSize) : taskLocationInfoService.list(taskId, from, pageSize);
        List<TaskLocationInfoMapper> taskLocationInfoMappers = new ArrayList<>();
        if (taskLocationInfoModels != null) {
            for (TaskLocationInfoModel taskLocationInfoModel : taskLocationInfoModels) {
                taskLocationInfoMappers.add(_toMapper(taskLocationInfoModel));
            }
        }
        return taskLocationInfoMappers;
    }

    @Override
    public List<TaskInfoMappper> list() {
        List<TaskInfoModel> taskInfoModelList = taskInfoService.list();
        List<TaskInfoMappper> taskInfoMappperList = new ArrayList<>();
        if (taskInfoModelList != null) {
            for (TaskInfoModel taskInfoModel : taskInfoModelList) {
                taskInfoMappperList.add(_toMapper(taskInfoModel));
            }
        }
        return taskInfoMappperList;
    }

    @Override
    public TaskInfoMappper add(TaskInfoMappper taskInfoMappper, List<TaskLocationInfoMapper> list) {
        List<TaskLocationInfoModel> taskLocationInfoModels = new ArrayList<>();
        for (TaskLocationInfoMapper taskLocationInfoMapper : list) {
            taskLocationInfoModels.add(_toModel(taskLocationInfoMapper));
        }
        TaskInfoModel taskInfoModel = taskInfoService.add(_toModel(taskInfoMappper), taskLocationInfoModels);
        //缓存至redis
        _cache(taskInfoModel.getId());
        return _toMapper(taskInfoModel);
    }

    @Override
    public void push(boolean isSend, Long id) {
        TaskInfoModel taskInfoModel = taskInfoService.getById(id);
        Assert.notNull(taskInfoModel, "task.info.not.found");
        taskInfoService.updateIsSend(isSend, taskInfoModel);
    }

    @Override
    public void update(TaskInfoMappper taskInfoMappper) {
        TaskInfoModel taskInfoModel = taskInfoService.getById(taskInfoMappper.getId());
        Assert.notNull(taskInfoModel, "task.info.not.found");
        taskInfoModel.setUpdateUser(taskInfoMappper.getUpdateUser());
        taskInfoModel.setUpdateTime(new Date());
        taskInfoModel.setTaskName(taskInfoMappper.getTaskName());
        //taskInfoModel.setKeyword(taskInfoMappper.getKeyword());
        taskInfoModel.setRate(taskInfoMappper.getRate());
        taskInfoModel.setBeginTime(taskInfoMappper.getBeginTime());
        taskInfoModel.setEndTime(taskInfoMappper.getEndTime());
        taskInfoModel.setTaskRadius(taskInfoMappper.getTaskRadius());
        taskInfoModel.setTaskIcon(taskInfoMappper.getTaskIcon());
        taskInfoModel.setTaskAward(taskInfoMappper.getTaskAward());
        taskInfoModel.setTaskLevel(taskInfoMappper.getTaskLevel());
        taskInfoModel.setTaskRemark(taskInfoMappper.getTaskRemark());
        taskInfoService.update(taskInfoModel);
        //缓存至redis
        _cache(taskInfoModel.getId());
    }

    @Override
    public List<TaskLocationInfoMapper> listTaskLocationByTaskId(Long taskId) {
        List<TaskLocationInfoModel> taskLocationInfoModels = taskLocationInfoService.list(taskId);
        List<TaskLocationInfoMapper> taskLocationInfoMappers = new ArrayList<>();
        if (taskLocationInfoModels != null) {
            for (TaskLocationInfoModel taskLocationInfoModel : taskLocationInfoModels) {
                taskLocationInfoMappers.add(_toMapper(taskLocationInfoModel));
            }
        }
        return taskLocationInfoMappers;
    }

    @Override
    public void addLocation(TaskLocationInfoMapper taskLocationInfoMapper) {
        TaskLocationInfoModel taskLocationInfoModel = taskLocationInfoService.add(_toModel(taskLocationInfoMapper));
        //to redis cache
        redisTaskService.cacheLocation(taskLocationInfoModel);
    }

    private void _cache(long id) {
        TaskInfoModel taskInfoModel = taskInfoService.getById(id);
        List<TaskLocationInfoModel> taskLocationInfoModels = taskLocationInfoService.list(id);
        redisTaskService.cache(taskInfoModel, taskLocationInfoModels);
    }

    private TaskInfoModel _toModel(TaskInfoMappper taskInfoMappper) {
        TaskInfoModel taskInfoModel = new TaskInfoModel();
        taskInfoModel.setVersion(0);
        taskInfoModel.setCreateTime(new Date());
        taskInfoModel.setUpdateTime(new Date());
        taskInfoModel.setCreateUser(taskInfoMappper.getCreateUser());
        taskInfoModel.setUpdateUser(taskInfoMappper.getUpdateUser());
        taskInfoModel.setTaskName(taskInfoMappper.getTaskName());
        taskInfoModel.setCity(taskInfoMappper.getCity());
        taskInfoModel.setArea(taskInfoMappper.getArea());
        taskInfoModel.setKeyword(taskInfoMappper.getKeyword());
        taskInfoModel.setRate(taskInfoMappper.getRate());
        taskInfoModel.setTaskStatus(TaskStatus.ENABLE);
        taskInfoModel.setBeginTime(taskInfoMappper.getBeginTime());
        taskInfoModel.setEndTime(taskInfoMappper.getEndTime());
        taskInfoModel.setIsSend(false);
        taskInfoModel.setTaskIcon(taskInfoMappper.getTaskIcon());
        taskInfoModel.setTaskRemark(taskInfoMappper.getTaskRemark());
        taskInfoModel.setTaskLevel(taskInfoMappper.getTaskLevel());
        taskInfoModel.setTaskAward(taskInfoMappper.getTaskAward());
        taskInfoModel.setTaskRadius(taskInfoMappper.getTaskRadius());
        return taskInfoModel;
    }

    private TaskLocationInfoModel _toModel(TaskLocationInfoMapper taskLocationInfoMapper) {
        TaskLocationInfoModel taskLocationInfoModel = new TaskLocationInfoModel();
        taskLocationInfoModel.setVersion(0);
        taskLocationInfoModel.setCreateTime(new Date());
        taskLocationInfoModel.setUpdateTime(new Date());
        taskLocationInfoModel.setTaskId(taskLocationInfoMapper.getTaskId());
        taskLocationInfoModel.setEnable(taskLocationInfoMapper.getEnable());
        taskLocationInfoModel.setName(taskLocationInfoMapper.getName());
        taskLocationInfoModel.setLocation(taskLocationInfoMapper.getLocation());
        taskLocationInfoModel.setAddress(taskLocationInfoMapper.getAddress());
        taskLocationInfoModel.setProvince(taskLocationInfoMapper.getProvince());
        taskLocationInfoModel.setCity(taskLocationInfoMapper.getCity());
        taskLocationInfoModel.setArea(taskLocationInfoMapper.getArea());
        taskLocationInfoModel.setStreetId(taskLocationInfoMapper.getStreetId());
        taskLocationInfoModel.setCoordType(taskLocationInfoMapper.getCoordType());
        taskLocationInfoModel.setMapType(taskLocationInfoMapper.getMapType());
        return taskLocationInfoModel;
    }

    private TaskLocationInfoMapper _toMapper(TaskLocationInfoModel taskLocationInfoModel) {
        return new TaskLocationInfoMapper(taskLocationInfoModel.getId(), taskLocationInfoModel.getTaskId(), taskLocationInfoModel.getEnable(), taskLocationInfoModel.getName(), taskLocationInfoModel.getLocation(), taskLocationInfoModel.getAddress(), taskLocationInfoModel.getProvince(), taskLocationInfoModel.getCity(), taskLocationInfoModel.getArea(), taskLocationInfoModel.getCoordType(), taskLocationInfoModel.getMapType(), taskLocationInfoModel.getStreetId());
    }

    private TaskInfoMappper _toMapper(TaskInfoModel taskInfoModel) {
        return new TaskInfoMappper(taskInfoModel.getId(), taskInfoModel.getCreateTime(), taskInfoModel.getUpdateTime(), taskInfoModel.getCreateUser(), taskInfoModel.getUpdateUser(), taskInfoModel.getTaskName(), taskInfoModel.getCity(), taskInfoModel.getArea(), taskInfoModel.getKeyword(), taskInfoModel.getRate(), taskInfoModel.getTaskStatus(), taskInfoModel.getBeginTime(), taskInfoModel.getEndTime(), taskInfoModel.getIsSend(), taskInfoModel.getTaskRadius(), taskInfoModel.getTaskIcon(), taskInfoModel.getTaskAward(), taskInfoModel.getTaskLevel(), taskInfoModel.getTaskRemark());
    }
}
