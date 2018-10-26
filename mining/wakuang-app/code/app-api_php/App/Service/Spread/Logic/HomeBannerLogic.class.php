<?php

namespace Service\Spread\Logic;

use Think\Exception;
use Service\Spread\Model;
use Common\Common\Redis;

/**
 * 广告图片管理
 */
class HomeBannerLogic {

    /**
     * 获取图片广告位
     * @param type $type
     * @return type
     * --
     */
    public function getImg($type) {
        $arr = array();

        $model = new Model\HomeBannerModel();
        $banner = $model->getAll();

        foreach ($banner as $key => $value) {
            if ($value["dic_type"] == $type) {
                $arr[] = $value;
            }
        }
        return $arr;
    }


    /**
     * 紧急修复IOS不能发包问题
     * --
     */
    public function handleImg($info, $version, $terminal) {
        if ($version == "2.6.2" && $terminal == "3") {//测试环境 2.0.0  正式环境 2.6.2
            $key = 0;
            foreach ($info as $k => $v) {
                $banisDel = preg_match("/Mobile\/activity\/novAct/", $v["linkUrl"]);
                $cenIsDel = preg_match("/Mobile\/activity\/novAct/", $v["cenLink"]);
                if (!empty($banisDel) || !empty($cenIsDel)) {
                    continue;
                }
                $data[$key] = $v;
                $key += 1;
            }
        }
        return $data = !empty($data) ? $data : $info;
    }

    /**
     *  获取前台广告图
     * @param string $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getBanner($where, $whereArr, $limit) {
        $mod = new Model\HomeBannerModel();
        $where .= "AND banner_status >'%d'";
        array_push($whereArr, 0);
        $result = $mod->getBanner("(1=1)" . $where, $whereArr, $limit);
        foreach ($result["rows"] as $k => $v) {
            $result["rows"][$k]["banner_start_time"] = !empty($v["banner_start_time"]) ? $v["banner_start_time"] : "不限制";
            $result["rows"][$k]["banner_end_time"] = !empty($v["banner_end_time"]) ? $v["banner_end_time"] : "不限制";
        }
        return $result;
    }

    /**
     * 删除前台广告图
     * @param type $key
     * @return boolean
     */
    public function delBanner($bannerID) {
        if ($bannerID * 1 < 1) {
            return FALSE;
        }
        $redis = Redis::GetInstance();
        $redis->del("Banner");
        $data["banner_status"] = 0;
        $where["banner_id"] = $bannerID;
        $mod = new Model\HomeBannerModel();
        $result = $mod->saveBanner($data, $where);
        return $result;
    }

    /**
     * 获得广告图编辑信息
     * @param type $bannerID
     */
    public function getSinBanner($bannerID) {
        $model = new Model\HomeBannerModel();
        if ($bannerID * 1 < 1) {
            return FALSE;
        }
        $where["banner_id"] = $bannerID;
        $where["banner_status"] = 1;
        $result = $model->getSinBanner($where);
        $result["remark"] = htmlspecialchars_decode($result["remark"], ENT_COMPAT);
        return $result;
    }

    /**
     * 保存广告图信息
     * @param type $data
     */
    public function saveBanner($data) {
        try {
            $where = array();
            if (empty(trim($data["banner_starttime"])) || empty(trim($data["banner_endtime"]))) {
                $data["banner_starttime"] = 0;
                $data["banner_endtime"] = 0;
            } else {
                $data["banner_starttime"] = strtotime($data["banner_starttime"]);
                $data["banner_endtime"] = strtotime($data["banner_endtime"]);
            }
            if ($data["banner_id"] * 1 > 0 && count($data) > 0) {
                $where["banner_id"] = $data["banner_id"];
                $data ['edit_user_id'] = C('userID');
                $data ['edit_user_name'] = C('userName');
                $data ['edit_datetime'] = time();
                unset($data["banner_id"]);
            } else {
                $data ['add_user_id'] = C('userID');
                $data ['add_user_name'] = C('userName');
                $data ['add_datetime'] = time();
            }
            $data["remark"] = !empty(remark) ? filter_UEditor(htmlspecialchars(stripslashes($data['remark']))) : "";
            $model = new Model\HomeBannerModel();
            $result = $model->saveBanner($data, $where);
            $cache = Redis::GetInstance(); //删除缓存
            $cache->del("Banner");
            return $result;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取后台配置的图片信息并且判断时间
     * @param type $type
     * @return type
     * --
     */
    public function getImgInfo($type) {
        $arr = array();
        $cache = Redis::GetInstance();
        $banner = $cache->get("Banner");
        if (empty($banner) || is_null($banner)) {
            $model = new Model\HomeBannerModel();
            $banner = $model->getAll();
            $cache->set("Banner", $banner, 86400 * 7); //存储一周
        }
        $time = time();
        foreach ($banner as $value) {
            if ($value["dic_type"] == $type && empty($arr)) {
                if ($time * 1 >= $value["banner_starttime"] && $time < ($value["banner_endtime"] + 86400)) {
                    $value["banner_url"] = UPLOAD . $value["banner_url"];
                    $arr = $value;
                }
            }
        }
        return $arr;
    }

    /**
     * 获取多张图片并组合
     * @param type $type
     * @return string
     * --
     */
    public function getImgListInfo($type) {
        $arr = array();
        $cache = Redis::GetInstance();
        $banner = $cache->get("Banner");
        if (empty($banner) || is_null($banner)) {
            $model = new Model\HomeBannerModel();
            $banner = $model->getAll();
            $cache->set("Banner", $banner, 86400 * 7); //存储一周
        }
        $time = time();
        foreach ($banner as $value) {
            if ($value["dic_type"] == $type) {
                if ($time * 1 >= $value["banner_starttime"] && $time < ($value["banner_endTime"] + 86400)) {
                    $value["banner_url"] = UPLOAD . $value["banner_url"];
                    $arr[] = $value;
                } else {
                    $arr = [];
                    break;
                }
            }
        }
        return $arr;
    }

}
