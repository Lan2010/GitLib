package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.MallUserOrderPayStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallUserOrderPayStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCMallUserOrderPayStatisticsService;
import com.tianzhixing.oms.bussiness.service.MallUserOrderPayStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCMallUserOrderPayStatisticsService")
public class RPCMallUserOrderPayStatisticsServiceImpl implements RPCMallUserOrderPayStatisticsService {

    @Autowired
    private MallUserOrderPayStatisticsService mallUserOrderPayStatisticsService;

    @Override
    public void insert(List<MallUserOrderPayStatisticsMapper> mallUserOrderPayStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (mallUserOrderPayStatisticsMappers != null) {
            List<MallUserOrderPayStatisticsModel> mallUserOrderPayStatisticsModels = new ArrayList<>();
            for(MallUserOrderPayStatisticsMapper mallUserOrderPayStatisticsMapper : mallUserOrderPayStatisticsMappers){
                MallUserOrderPayStatisticsModel mallUserOrderPayStatisticsModel = new MallUserOrderPayStatisticsModel();
                BeanUtils.copyProperties(mallUserOrderPayStatisticsMapper, mallUserOrderPayStatisticsModel);
                mallUserOrderPayStatisticsModels.add(mallUserOrderPayStatisticsModel);
            }
            mallUserOrderPayStatisticsService.insert(mallUserOrderPayStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
	public List<MallUserOrderPayStatisticsMapper> list(int type, Integer hour, Integer day, Integer month, Integer year, String platformName) {
		List<Map<String, Object>> listMap = mallUserOrderPayStatisticsService.listSum(type, hour, day, month, year, platformName);
		List<MallUserOrderPayStatisticsMapper> mallUserOrderPayStatisticsMappers = new ArrayList<>();
		for (Map<String, Object> map : listMap) {
			mallUserOrderPayStatisticsMappers.add(new MallUserOrderPayStatisticsMapper(null, null, null, null, null, null, null, null, null, null, null,
					map.get("alreadyPayCount") == null ? 0 : Integer.valueOf(map.get("alreadyPayCount").toString()),
					map.get("alreadyPayAmount") == null ? 0.0d : Double.valueOf(map.get("alreadyPayAmount").toString()),
					map.get("awaitPayCount") == null ? 0 : Integer.valueOf(map.get("awaitPayCount").toString()),
				    map.get("awaitPayAmount") == null ? 0.0d : Double.valueOf(map.get("awaitPayAmount").toString()),
				    map.get("failedPayCount") == null ? 0 : Integer.valueOf(map.get("failedPayCount").toString()),
				    map.get("failedPayAmount") == null ? 0.0d : Double.valueOf(map.get("failedPayAmount").toString()),
					platformName));
		}
		return mallUserOrderPayStatisticsMappers;
	}
    
    @Override
    public List<MallUserOrderPayStatisticsMapper> listByType(int type,Integer hour, Integer day, Integer month,Integer year,String value) {
        List<Map<String, Object>> listMap = mallUserOrderPayStatisticsService.listSumByType(type,hour,day,month,year,value);
        List<MallUserOrderPayStatisticsMapper>  mallUserOrderPayStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
             mallUserOrderPayStatisticsMappers.add(new MallUserOrderPayStatisticsMapper(null, null, null, null, null, null, null, null, null, null,null,
 					map.get("alreadyPayCount") == null ? 0 : Integer.valueOf(map.get("alreadyPayCount").toString()),
 					map.get("alreadyPayAmount") == null ? 0.0d : Double.valueOf(map.get("alreadyPayAmount").toString()),
 					map.get("awaitPayCount") == null ? 0 : Integer.valueOf(map.get("awaitPayCount").toString()),
 					map.get("awaitPayAmount") == null ? 0.0d : Double.valueOf(map.get("awaitPayAmount").toString()),
 					map.get("failedPayCount") == null ? 0 : Integer.valueOf(map.get("failedPayCount").toString()),
 					map.get("failedPayAmount") == null ? 0.0d : Double.valueOf(map.get("failedPayAmount").toString()),
 					null));
        }
        return  mallUserOrderPayStatisticsMappers;
    }
    
    @Override
    public long count() {
        return mallUserOrderPayStatisticsService.count();
    }
    
    @Override
    public List<MallUserOrderPayStatisticsMapper> listByAppAndType(int type,Integer hour, Integer day, Integer month,Integer year,String platformName,String value) {
        List<Map<String, Object>> listMap = mallUserOrderPayStatisticsService.listByAppAndType(type,hour,day,month,year,platformName,value);
        List<MallUserOrderPayStatisticsMapper>  mallUserOrderPayStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
             mallUserOrderPayStatisticsMappers.add(new MallUserOrderPayStatisticsMapper(null, null, null, null, null, null, null, null, null, null,null,
 					map.get("alreadyPayCount") == null ? 0 : Integer.valueOf(map.get("alreadyPayCount").toString()),
 					map.get("alreadyPayAmount") == null ? 0.0d : Double.valueOf(map.get("alreadyPayAmount").toString()),
 					map.get("awaitPayCount") == null ? 0 : Integer.valueOf(map.get("awaitPayCount").toString()),
 					map.get("awaitPayAmount") == null ? 0.0d : Double.valueOf(map.get("awaitPayAmount").toString()),
 					map.get("failedPayCount") == null ? 0 : Integer.valueOf(map.get("failedPayCount").toString()),
 					map.get("failedPayAmount") == null ? 0.0d : Double.valueOf(map.get("failedPayAmount").toString()),
 					null));
        }
        return  mallUserOrderPayStatisticsMappers;
    }
}
