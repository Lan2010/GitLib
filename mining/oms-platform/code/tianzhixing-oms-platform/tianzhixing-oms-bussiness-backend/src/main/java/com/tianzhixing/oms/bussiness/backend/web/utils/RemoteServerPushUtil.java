package com.tianzhixing.oms.bussiness.backend.web.utils;

import com.tianzhixing.oms.bussiness.backend.web.conf.FileUploadPathConfig;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig;
import com.tianzhixing.oms.bussiness.rpc.mapper.advertisement.AdvertisementMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.system.SystemParamMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskInfoMappper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskLocationInfoMapper;
import com.tianzhixing.oms.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.Assert;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by routine.k on 2018/6/25.
 */
public class RemoteServerPushUtil {

    private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(RemoteServerPushUtil.class);

    private static String WKAPPSERVER;
    private static String WKAPPSERVER_TASK;
    private static String WKAPPSERVER_ADVERTISEMENT;
    private static String WKAPPSERVER_ADWARD;
    private static String WKAPPSERVER_SUSPEND;
    private static String WKAPPREQUEST_TYPE;

    static {
        WKAPPSERVER = ResourceBundleUtil.getStringValue("wkapp.remote.server.uri", "push-server-config");
        WKAPPSERVER_TASK = WKAPPSERVER + ResourceBundleUtil.getStringValue("wkapp.remote.server.task", "push-server-config");
        WKAPPSERVER_ADVERTISEMENT = WKAPPSERVER + ResourceBundleUtil.getStringValue("wkapp.remote.server.advertisement", "push-server-config");
        WKAPPREQUEST_TYPE = ResourceBundleUtil.getStringValue("wkapp.remote.server.request.type", "push-server-config");
        WKAPPSERVER_ADWARD = WKAPPSERVER + ResourceBundleUtil.getStringValue("wkapp.remote.server.adward", "push-server-config");
        WKAPPSERVER_SUSPEND = WKAPPSERVER + ResourceBundleUtil.getStringValue("wkapp.remote.server.suspend", "push-server-config");
    }

    /**
     * 推送任务信息至挖矿app
     *
     * @param taskInfoMappper
     * @param taskLocationInfoMapperList
     * @return
     */
    public boolean pushTaskToWkAPP(TaskInfoMappper taskInfoMappper, List<TaskLocationInfoMapper> taskLocationInfoMapperList) {
//        File file = new File(FileUploadPathConfig.IMAGEPATH + taskInfoMappper.getTaskIcon());
//        Assert.isTrue(file.exists(), "task push failed, icon file not found");
        List<TaskLocation> taskLocations = new ArrayList<>();
        for (TaskLocationInfoMapper taskLocationInfoMapper : taskLocationInfoMapperList) {
            taskLocations.add(new TaskLocation(taskLocationInfoMapper.getId().toString(), taskLocationInfoMapper.getTaskId().toString(), taskLocationInfoMapper.getName(), taskLocationInfoMapper.getLocation(), taskLocationInfoMapper.getAddress(), taskLocationInfoMapper.getProvince(), taskLocationInfoMapper.getCity(), taskLocationInfoMapper.getArea(), taskLocationInfoMapper.getCoordType(), taskLocationInfoMapper.getMapType().name(), taskLocationInfoMapper.getStreetId()));
        }
        //String fileType = file.getName().substring(file.getName().lastIndexOf(".")+1);
        //TaskInfo taskInfo = new TaskInfo(taskInfoMappper.getId().toString(),taskInfoMappper.getTaskName(), taskInfoMappper.getCity(), BaiduMapConfig.code(taskInfoMappper.getCity()), taskInfoMappper.getArea(), taskInfoMappper.getKeyword(), taskInfoMappper.getRate().toString(), String.valueOf(taskInfoMappper.getBeginTime().getTime()), String.valueOf(taskInfoMappper.getEndTime()), fileType,taskLocations);
        //转换为string
        //String json = JSONUtil.beanToJson(taskInfo, false);
        Map<String, Object> map = new HashMap<>();
        map.put("id", taskInfoMappper.getId().toString());
        map.put("taskName", taskInfoMappper.getTaskName());
        map.put("city", taskInfoMappper.getCity());
        map.put("cityCode", BaiduMapConfig.code(taskInfoMappper.getCity()));
        map.put("area", taskInfoMappper.getArea());
        map.put("keyword", taskInfoMappper.getKeyword());
        map.put("rate", CalculateUtil.format(taskInfoMappper.getRate(), 4));
        map.put("beginTime", String.valueOf(taskInfoMappper.getBeginTime().getTime()));
        map.put("endTime", String.valueOf(taskInfoMappper.getEndTime().getTime()));
        map.put("taskIcon", FileUploadPathConfig.IMAGEREQUESTPATH + taskInfoMappper.getTaskIcon());
        map.put("taskRadius", taskInfoMappper.getTaskRadius());
        map.put("taskRemark", taskInfoMappper.getTaskRemark());
        map.put("taskLevel", taskInfoMappper.getTaskLevel());
        map.put("taskAward", taskInfoMappper.getTaskAward());
        map.put("list", JSONUtil.beanToJson(taskLocations, false));
        Map<String, File> attachmentMap = new HashMap<>();
        //attachmentMap.put("iconFile", file);
        //System.out.println(JSONUtil.mapToJson(map, false));
        String response = HttpClientUtil.doPost(WKAPPSERVER_TASK, null, map, attachmentMap);
        LOGGER.info(".... finished push to remote server[" + WKAPPSERVER_TASK + "], result: " + (StringUtils.isEmpty(response) ? "null" : response));
        Assert.isTrue(StringUtils.isNotEmpty(response), "task push failed, remote server return empty");
        Result result = JSONUtil.jsonToBean(response, Result.class);
        Assert.isTrue(result.getCode() == 0, "task push failed, remote server return error code, message[" + (result.getMessage() == null ? "" : result.getMessage()) + "]");
        return result.getCode() == 0;
    }

    /**
     * 推送广告信息至挖矿APP
     *
     * @param advertisementMapper
     * @return
     */
    public boolean pushAdvertisementToWkAPP(AdvertisementMapper advertisementMapper) {
//        File file = new File(FileUploadPathConfig.IMAGEPATH + advertisementMapper.getAdvertIcon());
//        Assert.isTrue(file.exists(), "advertisement push failed, icon file not found");
//        File picFile = new File(FileUploadPathConfig.ZIPPATH + advertisementMapper.getAdvertPic());
//        Assert.isTrue(picFile.exists(), "advertisement push failed, pic file not found");
        Map<String, Object> map = new HashMap<>();
        map.put("id", advertisementMapper.getId().toString());
        map.put("advertName", advertisementMapper.getAdvertName());
        map.put("city", advertisementMapper.getCity());
        map.put("cityCode", BaiduMapConfig.code(advertisementMapper.getCity()));
        map.put("area", advertisementMapper.getArea());
        map.put("advertRemark", advertisementMapper.getAdvertRemark());
        map.put("advertIcon", FileUploadPathConfig.IMAGEREQUESTPATH + advertisementMapper.getAdvertIcon());
        map.put("advertPic", FileUploadPathConfig.IMAGEREQUESTPATH + advertisementMapper.getAdvertPic());
        map.put("beginTime", advertisementMapper.getBeginTime().getTime());
        map.put("endTime", advertisementMapper.getEndTime().getTime());
        map.put("advertisementType", advertisementMapper.getAdvertisementType().name());
        map.put("totalCount", advertisementMapper.getTotalCount().toString());
        map.put("advertisementDescribe", advertisementMapper.getAdvertisementDescribe());
        map.put("totalStarPoint", CalculateUtil.format(advertisementMapper.getTotalAccessStarPoint(), 4));
        map.put("totalClickStarPoint", CalculateUtil.format(advertisementMapper.getTotalClickStarPoint(), 4));
        map.put("onceStarPoint", CalculateUtil.format(advertisementMapper.getOnceAccessStarPoint(), 4));
        map.put("onceClickStarPoint", CalculateUtil.format(advertisementMapper.getOnceClickStarPoint(), 4));
        map.put("link", advertisementMapper.getAdvertisementLink());
        map.put("advertisementAttribute", advertisementMapper.getAdvertisementAttribute().name());
        Map<String, File> attachmentMap = new HashMap<>();
//        attachmentMap.put("iconFile", file);
//        attachmentMap.put("picFile", picFile);
//        System.out.println(JSONUtil.mapToJson(map, false));
        String response = HttpClientUtil.doPost(WKAPPSERVER_ADVERTISEMENT, null, map, attachmentMap);
        LOGGER.info(".... finished push to remote server[" + WKAPPSERVER_ADVERTISEMENT + "], result: " + (StringUtils.isEmpty(response) ? "null" : response));
        Assert.isTrue(StringUtils.isNotEmpty(response), "task push failed, remote server return empty");
        Result result = JSONUtil.jsonToBean(response, Result.class);
        Assert.isTrue(result.getCode() == 0, "task push failed, remote server return error code, message[" + (result.getMessage() == null ? "" : result.getMessage()) + "]");
        return result.getCode() == 0;
    }

    /**
     * 推送系统设置
     *
     * @param systemParamMapper
     * @return
     */
    public boolean pushSystemSetting(SystemParamMapper systemParamMapper) {
        Map<String, Object> map = new HashMap<>();
        String paramName = ResourceBundleUtil.getStringValue(systemParamMapper.getSystemParamType().name() + ".name", "push-server-config");
        map.put(paramName, CalculateUtil.format(Double.valueOf(systemParamMapper.getSystemValue()), 4));
        String response = HttpClientUtil.doPost(WKAPPSERVER_ADWARD, null, map, null);
        LOGGER.info(".... finished push to remote server[" + WKAPPSERVER_ADWARD + "], result: " + (StringUtils.isEmpty(response) ? "null" : response));
        Assert.isTrue(StringUtils.isNotEmpty(response), "system setting push failed, remote server return empty");
        Result result = JSONUtil.jsonToBean(response, Result.class);
        Assert.isTrue(result.getCode() == 0, "system setting, remote server return error code, message[" + (result.getMessage() == null ? "" : result.getMessage()) + "]");
        return result.getCode() == 0;
    }

    /**
     * 广告悬浮窗推送
     *
     * @param appPic
     * @param appPicLink
     * @param beginTime
     * @param endTime
     * @return
     */
    public boolean pushSuspend(String appPic, String appPicLink, Date beginTime, Date endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("appPic", FileUploadPathConfig.IMAGEREQUESTPATH + appPic);
        if (StringUtils.isNotEmpty(appPicLink))
            map.put("appPicLink", appPicLink);
        map.put("beginTime", CalendarUtil.dateTime2String(beginTime));
        map.put("endTime", CalendarUtil.dateTime2String(endTime));
        String response = HttpClientUtil.doPost(WKAPPSERVER_SUSPEND, null, map, null);
        LOGGER.info(".... finished push to remote server[" + WKAPPSERVER_SUSPEND + "], result: " + (StringUtils.isEmpty(response) ? "null" : response));
        Assert.isTrue(StringUtils.isNotEmpty(response), "app suspend push failed, remote server return empty");
        Result result = JSONUtil.jsonToBean(response, Result.class);
        Assert.isTrue(result.getCode() == 0, "app suspend, remote server return error code, message[" + (result.getMessage() == null ? "" : result.getMessage()) + "]");
        return result.getCode() == 0;
    }

    class Result {

        private int code;

        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    class TaskInfo implements Serializable {

        /**
         * id
         */
        private String id;

        /**
         * 任务名称
         */
        private String taskName;

        /**
         * 市
         */
        private String city;

        /**
         * 市代码
         */
        private String cityCode;

        /**
         * 区
         */
        private String area;

        /**
         * 关键字
         */
        private String keyword;

        /**
         * 汇率(mac:star 一对一关系, 一个mac换多少星点)
         */
        private String rate;

        /**
         * 开始时间
         */
        private String beginTime;

        /**
         * 结束时间
         */
        private String endTime;

        /**
         * 任务图标(对应的file名称)
         */
        private String taskIcon;

        /**
         * 位置信息
         */
        private List<TaskLocation> list;

        public TaskInfo() {
        }

        public TaskInfo(String id, String taskName, String city, String cityCode, String area, String keyword, String rate, String beginTime, String endTime, String taskIcon, List<TaskLocation> list) {
            this.id = id;
            this.taskName = taskName;
            this.city = city;
            this.cityCode = cityCode;
            this.area = area;
            this.keyword = keyword;
            this.rate = rate;
            this.beginTime = beginTime;
            this.endTime = endTime;
            this.taskIcon = taskIcon;
            this.list = list;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getTaskIcon() {
            return taskIcon;
        }

        public void setTaskIcon(String taskIcon) {
            this.taskIcon = taskIcon;
        }

        public List<TaskLocation> getList() {
            return list;
        }

        public void setList(List<TaskLocation> list) {
            this.list = list;
        }
    }

    class TaskLocation implements Serializable {

        /**
         * id
         */
        private String id;

        /**
         * 所属任务
         */
        private String taskId;

        /**
         * 名字
         */
        private String name;

        /**
         * 经纬度（格式:lat|lng）
         */
        private String location;

        /**
         * 地址
         */
        private String address;

        /**
         * 省份
         */
        private String province;

        /**
         * 城市
         */
        private String city;

        /**
         * 区域
         */
        private String area;

        /**
         * 坐标类型
         */
        private String coordType;

        /**
         * 地图类型
         */
        private String mapType;

        /**
         * 街道ID
         */
        private String streetId;

        public TaskLocation() {
        }

        public TaskLocation(String id, String taskId, String name, String location, String address, String province, String city, String area, String coordType, String mapType, String streetId) {
            this.id = id;
            this.taskId = taskId;
            this.name = name;
            this.location = location;
            this.address = address;
            this.province = province;
            this.city = city;
            this.area = area;
            this.coordType = coordType;
            this.mapType = mapType;
            this.streetId = streetId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCoordType() {
            return coordType;
        }

        public void setCoordType(String coordType) {
            this.coordType = coordType;
        }

        public String getMapType() {
            return mapType;
        }

        public void setMapType(String mapType) {
            this.mapType = mapType;
        }

        public String getStreetId() {
            return streetId;
        }

        public void setStreetId(String streetId) {
            this.streetId = streetId;
        }
    }

}
