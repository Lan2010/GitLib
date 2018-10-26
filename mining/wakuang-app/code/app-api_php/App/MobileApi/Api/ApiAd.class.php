<?php

namespace MobileApi\Api;

use MobileApi\Common\MobileApiBase;
use Service\Advertisement\Logic\AdvertisementLogic;

/**
 * Description of index
 *
 * @author Administrator
 */
class ApiAd extends MobileApiBase
{

    public $return = array('code' => 0, 'msg' => 'Welcome to use Api!', 'info' => array());

    public function getRules()
    {
        return array(
            'getAdList' => array(
                'pageindex' => array('name' => 'pageindex', 'type' => 'int', 'default' => 1, 'desc' => '页码'),
                'pagesize' => array('name' => 'pagesize', 'type' => 'int', 'default' => 15, 'desc' => '每页显示数量')
            ),
            "chlickAd" => array(
                'adID' => array('name' => 'adID', 'type' => 'int', "require" => true, 'desc' => '广告的ID')
            ),
            "getLocationStar" => array(
                "code" => array('name' => 'code', 'type' => 'string', "require" => true, 'desc' => '城市代码'),
                "lat" => array('name' => 'lat', 'type' => 'string', "require" => true, 'desc' => '纬度'),
                "lng" => array('name' => 'lng', 'type' => 'string', "require" => true, 'desc' => '广告的经度'),
                "m" => array('name' => 'm', 'type' => 'int', "require" => true, 'desc' => '距离米'),
            ),
        );
    }


    /**
     * 获取到广告星星
     * @desc 用于获取到随机的广告星星
     * @return string  ad_id   对应的ID
     * @return string  advertName  广告名字
     * @return string  area   地区
     * @return string  city   城市
     * @return string  cityCode 城市对应的节点
     * @return string  advertisementType 广告的类型 0 弹出 1浏览
     * @return string  totalCount  总的点击次数 100000
     * @return array   advertPic 广告图片
     * @return string  advertIcon 广告logo
     * @return string  totalStarPoint 总的浏览星星
     * @return string  advertRemark  备注
     * @return string  onceStarPoint   0.01  浏览一次获取到的星星
     * @return string  beginTime  2018-06-25 20:50:00  开始时间
     * @return string  endTime   2018-07-07 23:55:00 结束时间
     * @return string  Lat  38.998562 纬度
     * @return string  Long  92.000073  经度
     * @return string  onceClickStarPoint   点击一次获取到的星星数量
     * @return string  totalClickStarPoint  总的点击星星数
     * @return string  advertisement_describe   描述
     * @return string  user_browse_starpoint  已经被获取到的浏览星星数量
     * @return code int 0代表成功 1代表失败
     * @return msg string 提示信息
     */
    public function getLocationStar()
    {
        $logic = new AdvertisementLogic();
        $this->lat = sprintf("%.6f", $this->lat);
        $this->lng = sprintf("%.6f", $this->lng);
        $range_lan_long = getAround($this->lat, $this->lng, $this->m);
        $lat_rand_num = $range_lan_long['maxLat'] - $range_lan_long['minLat'];
        $lng_rand_num = $range_lan_long['maxLong'] - $range_lan_long['minLong'];

        $where['total_count'] = array("gt", 0);
        $where['begin_time'] = array('elt', time());
        $where['end_time'] = array('egt', time());
        $where['city_code'] = $this->code;
        $field = [
            'order_no as ad_id', 'advert_name as advertName', 'area', 'ad_url','city', 'city_code as cityCode', 'advertisement_type as advertisementType',
            'total_count as totalCount', 'advert_pic as advertPic', 'advert_icon as advertIcon', 'total_starpoint as totalStarPoint', 'advert_remark as advertRemark', 'once_starpoint as onceStarPoint',
            'FROM_UNIXTIME(begin_time,"%Y-%m-%d %H:%i:%s") as beginTime ', 'FROM_UNIXTIME(end_time,"%Y-%m-%d %H:%i:%s") as endTime','advertisement_describe',
            'total_click_starpoint as totalClickStarPoint','once_click_starpoint as onceClickStarPoint','user_browse_starpoint',
        ];
        $adList = $logic->getAd($where, $field);
        if (empty($adList)) {
            $this->return['code'] = 1;
            $this->return['msg'] = "任务信息为空";
            return $this->return;
        }
        foreach ($adList as $key => $value) {
            $adList[$key]['Lat'] = sprintf("%.6f", rand(0, 10)/10 * $lat_rand_num + $range_lan_long["minLat"]);
            $adList[$key]['Lng'] = sprintf("%.6f", rand(0, 10)/10 * $lng_rand_num + $range_lan_long["minLong"]);
            $adList[$key]['advertIcon'] = $value["advertIcon"];
            $adList[$key]['advertPic'] =  $this->hidIMG($value['advertPic']);
            $adList[$key]['advertisementType'] = (strtolower($value['advertisementType']) == "alert") ? 0:1;
            //$adList[$key]['onceStarPoint'] = ($value['user_browse_starpoint'] >= $value['totalStarPoint'] ) ? 0:$value['onceStarPoint'];
        }

        $this->return['info'] = $adList;
        return $this->return;
    }


    /**
     * 处理图片
     */
    protected function hidIMG($data)
    {
        $arr[] = array("url" => $data);
        return $arr;
    }



}
