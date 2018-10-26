package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.MapType;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapEntity;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapResult;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.utils.RemoteServerPushUtil;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppPushRecordsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskInfoMappper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskLocationInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppPushRecordsService;
import com.tianzhixing.oms.bussiness.rpc.service.task.RPCTaskInfoService;
import com.tianzhixing.oms.utils.HttpClientUtil;
import com.tianzhixing.oms.utils.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service
public class TaskInfoService {

    @Autowired
    private RPCTaskInfoService rpcTaskInfoService;

    @Autowired
    private RPCAppPushRecordsService rpcAppPushRecordsService;

    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return rpcTaskInfoService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    public List<TaskInfoMappper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, PagerMapping pagerMapping) {
        return rpcTaskInfoService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public long countTaskLocationByTaskId(Long taskId) {
        return rpcTaskInfoService.countTaskLocationByTaskId(taskId);
    }

    public TaskInfoMappper getById(Long taskId) {
        return rpcTaskInfoService.getByTaskId(taskId);
    }

    public List<TaskLocationInfoMapper> listTaskLocationByTaskId(Long taskId, PagerMapping pagerMapping) {
        return rpcTaskInfoService.listTaskLocationByTaskId(taskId, pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public List<TaskInfoMappper> list() {
        List<TaskInfoMappper> list = rpcTaskInfoService.list();
        return list == null ? new ArrayList<TaskInfoMappper>() : list;
    }

    public TaskInfoMappper add(TaskInfoMappper taskInfoMappper, boolean isAutoMap) {
        List<TaskLocationInfoMapper> list = new ArrayList<>();
        try {
            if (isAutoMap) {
                //获取首页数据
                Map<String, Object> param = new HashMap<>();
                param.put("query", taskInfoMappper.getKeyword());
                param.put("region", taskInfoMappper.getCity());//此处的城市为代号
                param.put("city_limit", "true");
                param.put("output", "json");
                param.put("ak", BaiduMapConfig.BAIDU_MAP_KEY);
                param.put("page_size", BaiduMapConfig.BAIDU_MAP_DATA_PAGESIZE);
                param.put("coord_type", BaiduMapConfig.BAIDU_MAP_COORDTYPE_NUM);
                param.put("page_num", 0);
                String data = HttpClientUtil.doGet(BaiduMapConfig.BAIDU_MAP_API_URI, null, param);
                BaiduMapEntity entity = JSONUtil.jsonToBean(data, BaiduMapEntity.class);
                if (entity == null) {
                    throw new RequestException("baidu.map.request.failed");
                }
                //计算分页数
                int pages = entity.getTotal() % Integer.valueOf(Integer.valueOf(BaiduMapConfig.BAIDU_MAP_DATA_PAGESIZE)) == 0 ? entity.getTotal() / Integer.valueOf(Integer.valueOf(BaiduMapConfig.BAIDU_MAP_DATA_PAGESIZE)) : entity.getTotal() / Integer.valueOf(Integer.valueOf(BaiduMapConfig.BAIDU_MAP_DATA_PAGESIZE)) + 1;
                //添加第一页数据
                _mapDataToTaskLocation(entity.getResults(), list, taskInfoMappper.getArea());
                //获取剩余页数数据
                for (int i = 1; i < pages; i++) {
                    param.put("page_num", i);
                    String mapData = HttpClientUtil.doGet(BaiduMapConfig.BAIDU_MAP_API_URI, null, param);
                    BaiduMapEntity result = JSONUtil.jsonToBean(mapData, BaiduMapEntity.class);
                    if (result == null) {
                        throw new RequestException("baidu.map.request.failed");
                    }
                    _mapDataToTaskLocation(result.getResults(), list, taskInfoMappper.getArea());
                }
            }
            //存储文件
            taskInfoMappper.setCity(BaiduMapConfig.city(taskInfoMappper.getCity()));
            return rpcTaskInfoService.add(taskInfoMappper, list);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RequestException("baidu.map.request.failed");
        }
    }

    private void _mapDataToTaskLocation(List<BaiduMapResult> results, List<TaskLocationInfoMapper> list, String area) {
        if (results != null) {
            for (BaiduMapResult result : results) {
                if (result.getLocation() != null && StringUtils.isNotEmpty(result.getName())) {
                    if (StringUtils.isNotEmpty(area)) {
                        if (result.getArea().contains(area))
                            list.add(new TaskLocationInfoMapper(null, null, true, result.getName(), "lat:" + result.getLocation().getLat() + "|lng:" + result.getLocation().getLng(), result.getAddress(), result.getProvince(), result.getCity(), result.getArea(), BaiduMapConfig.BAIDU_MAP_COORDTYPE, MapType.BAIDU, result.getStreet_id()));
                    } else {
                        list.add(new TaskLocationInfoMapper(null, null, true, result.getName(), "lat:" + result.getLocation().getLat() + "|lng:" + result.getLocation().getLng(), result.getAddress(), result.getProvince(), result.getCity(), result.getArea(), BaiduMapConfig.BAIDU_MAP_COORDTYPE, MapType.BAIDU, result.getStreet_id()));
                    }

                }
            }
        }
    }

    /**
     * 推送任务
     *
     * @param id
     */
    public void push(Long id, String operUser) {
        TaskInfoMappper taskInfoMappper = rpcTaskInfoService.getByTaskId(id);
        Assert.notNull(taskInfoMappper, "task.not.found");
        List<TaskLocationInfoMapper> taskLocationInfoMapperList = rpcTaskInfoService.listTaskLocationByTaskId(taskInfoMappper.getId());
        boolean status = new RemoteServerPushUtil().pushTaskToWkAPP(taskInfoMappper, taskLocationInfoMapperList);
        Assert.isTrue(status, "push.remote.failed");
        //添加推送日志
        AppPushRecordsMapper appPushRecordsMapper = new AppPushRecordsMapper();
        appPushRecordsMapper.setUpdateTime(new Date());
        appPushRecordsMapper.setCreateTime(new Date());
        appPushRecordsMapper.setId(null);
        appPushRecordsMapper.setOperUser(operUser);
        appPushRecordsMapper.setPushType(1);
        appPushRecordsMapper.setThirdId(taskInfoMappper.getId());
        appPushRecordsMapper.setVersion(0);
        rpcAppPushRecordsService.add(appPushRecordsMapper);
        if (!taskInfoMappper.getIsSend()) {
            rpcTaskInfoService.push(true, taskInfoMappper.getId());
        }
    }

    /**
     * 更新
     *
     * @param taskInfoMappper
     */
    public void update(TaskInfoMappper taskInfoMappper) {
        TaskInfoMappper tim = rpcTaskInfoService.getByTaskId(taskInfoMappper.getId());
        Assert.notNull(tim, "task.not.found");
        rpcTaskInfoService.update(taskInfoMappper);
    }

    public void addLocation(TaskLocationInfoMapper taskLocationInfoMapper) {
        rpcTaskInfoService.addLocation(taskLocationInfoMapper);
    }
}
