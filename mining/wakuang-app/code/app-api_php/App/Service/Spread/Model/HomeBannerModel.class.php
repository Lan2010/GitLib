<?php

namespace Service\Spread\Model;

use Think\Model;
use Common\Model\SlaveModel;
use Think\Exception;

class HomeBannerModel extends SlaveModel {

    /**
     * 获取前台广告  查询从库
     * @param type $where
     * @param type $whereArr
     * @param type $limit
     * @return type
     */
    public function getBanner($where, $whereArr, $limit) {
        $result["total"] = $this->SlaveDB()->table("d_home_banner")->where($where, $whereArr)->count();
        $field = "banner_name,dic_type,banner_title,banner_sort,add_user_name,add_datetime,dic_name,banner_id,banner_url,in_link,banner_starttime,banner_endtime";
        $result["rows"] = $this->SlaveDB()->table("d_home_banner")->field($field)
                        ->where($where, $whereArr)->order("add_datetime desc")->limit($limit)->select();
        return $result;
    }

    /**
     * 获取所有广告位
     * --
     */
    public function getAll() {
        $field = "banner_name,dic_type,banner_title,banner_sort,dic_name,banner_url,link_url,banner_starttime,banner_endtime,remark";
        $result = $this->SlaveDB()->table("d_home_banner")->field($field)->where("banner_status=1")->order("banner_sort desc")->select();
        return $result;
    }
    /**
     * 删除前台广告
     * 保存广告信息
     * @param type $data
     * @param type $where
     * @return type
     */
    public function saveBanner($data, $where) {

        try {
            $model = M("HomeBanner");
            if (is_array($where) && count($where) > 0) {
                $result = $model->where($where)->data($data)->save();
                return $result * 1 > 0 ? $where["banner_id"] : false;
            } else {
               return  $model->add($data);

            }
            return false;
        } catch (Exception $ex) {
            logger(__METHOD__, $ex->getMessage());
            return false;
        }
    }

    /**
     * 获取广告编辑的信息
     * @param type $where
     */
    public function getSinBanner($where) {
        $result = $this->SlaveDB()->table("d_home_banner")->where($where)->find();
        return $result;
    }

}
