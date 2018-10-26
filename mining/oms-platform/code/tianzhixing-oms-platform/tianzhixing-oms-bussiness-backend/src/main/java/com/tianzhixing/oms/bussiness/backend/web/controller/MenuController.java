package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.vo.MenuVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by routine.k on 16/8/16.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {

    /**
     * 加载资源菜单
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MenuVo> list() {
        //TODO 暂时做简单的菜单展示
        List<MenuVo> menuVoList = new ArrayList<MenuVo>();
        List<MenuVo> taskNodes = new ArrayList<MenuVo>();
        taskNodes.add(_configVo(2, "任务列表", "/task/index", 1,"fa fa-list-ul", null));
        taskNodes.add(_configVo(3, "任务地图", "/task/location", 1,"fa fa-map-marker", null));
        MenuVo taskMenu = _configVo(1, "任务管理", null, null, "fa fa-tasks", taskNodes);
        menuVoList.add(taskMenu);
        List<MenuVo> advertisementNodes = new ArrayList<MenuVo>();
        advertisementNodes.add(_configVo(5, "广告列表", "/advert/index", 4, "fa fa-list-ul", null));
//        advertisementNodes.add(_configVo(6, "广告地图", "/advert/", 1, "fa fa-map-marker", null));
        MenuVo advertisementMenu = _configVo(4, "广告管理", null, null, "fa fa-magic", advertisementNodes);
        menuVoList.add(advertisementMenu);

        List<MenuVo> systemNodes = new ArrayList<MenuVo>();
        systemNodes.add(_configVo(7, "参数设置", "/system/index", 6, "fa fa-codepen", null));
        MenuVo systemMenu = _configVo(6, "系统参数", null, null, "fa fa-cog", systemNodes);
        menuVoList.add(systemMenu);

        List<MenuVo> appNodes = new ArrayList<MenuVo>();
        appNodes.add(_configVo(9, "挖矿APP悬浮窗", "/app/wksuspend/index", 8, "fa fa-window-maximize", null));
        MenuVo appMenu = _configVo(8, "APP管理", null, null, "fa  fa-mobile", appNodes);
        menuVoList.add(appMenu);

        List<MenuVo> statisticsNodes = new ArrayList<MenuVo>();
        statisticsNodes.add(_configVo(11, "应用程序启动统计", "/statistics/application/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(12, "页面访问统计", "/statistics/pages/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(13, "用户数据统计", "/statistics/userinfo/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(14, "用户登录登出统计", "/statistics/userLoginStatus/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(15, "用户访问/点击广告信息统计", "/statistics/userAdvertisement/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(16, "用户任务统计", "/statistics/userTask/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(17, "用户认证统计", "/statistics/userAuth/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(18, "设备操作统计", "/statistics/device/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(19, "设备上线/下线统计", "/statistics/deviceOnline/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(20, "用户星点增长统计", "/statistics/userStarPointIncrement/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(21, "用户贺卡/明信片/绿卡统计", "/statistics/userCard/index", 10, "fa fa-window-maximize", null));
        statisticsNodes.add(_configVo(22, "商城用户订单支付统计", "/statistics/mallUserOrderPay/index", 10, "fa fa-window-maximize", null));
        MenuVo statisticsMenu = _configVo(10, "运营统计", null, null, "fa  fa-bar-chart", statisticsNodes);
        menuVoList.add(statisticsMenu);
        
        List<MenuVo> statisticsDimensionNodes = new ArrayList<MenuVo>();
        statisticsDimensionNodes.add(_configVo(18, "系统平台维度", "/dimension/applicationDimension/index", 12, "fa fa-window-maximize", null));
        statisticsDimensionNodes.add(_configVo(13, "设备统计维度", "/dimension/deviceDimension/index", 12, "fa fa-window-maximize", null));
        statisticsDimensionNodes.add(_configVo(14, "用户认证统计维度", "/dimension/userAuthDimension/index", 12, "fa fa-window-maximize", null));
        statisticsDimensionNodes.add(_configVo(15, "用户渠道统计维度", "/dimension/userChannelDimension/index", 12, "fa fa-window-maximize", null));
        statisticsDimensionNodes.add(_configVo(16, "链接统计维度", "/dimension/pagesDimension/index", 12, "fa fa-window-maximize", null));
        statisticsDimensionNodes.add(_configVo(17, "用户星点消费统计维度", "/dimension/starPointConsumeDimension/index", 12, "fa fa-window-maximize", null));
        statisticsDimensionNodes.add(_configVo(19, "产品统计维度", "/dimension/mallProduct/index", 12, "fa fa-window-maximize", null));
        MenuVo statisticsDimensionMenu = _configVo(12, "统计维度管理", null, null, "fa  fa-connectdevelop", statisticsDimensionNodes);
        menuVoList.add(statisticsDimensionMenu);
        
        List<MenuVo> userNodes = new ArrayList<MenuVo>();
        userNodes.add(_configVo(15, "产品管理", "/user/product/index", 10, "fa fa-window-maximize", null));
        userNodes.add(_configVo(16, "视频课程管理", "/user/courseDetail/index", 10, "fa fa-window-maximize", null));
//       userNodes.add(_configVo(17, "优惠券管理", "/user/promotionCode/index", 10, "fa fa-window-maximize", null));
        userNodes.add(_configVo(17, "用户管理", "/user/user/index", 10, "fa fa-window-maximize", null));
//        userNodes.add(_configVo(19, "用户优惠券", "/user/userPromotionCode/index", 10, "fa fa-window-maximize", null));
//        userNodes.add(_configVo(20, "用户下单管理", "/user/userOrder/index", 10, "fa fa-window-maximize", null));
        userNodes.add(_configVo(18, "公众号用户管理", "/user/userWechat/index", 10, "fa fa-window-maximize", null));
//        userNodes.add(_configVo(19, "基因检测报告管理", "/user/userGeneInterpr/index", 10, "fa fa-window-maximize", null));
        userNodes.add(_configVo(19, "用户消息管理", "/user/userTopic/index", 10, "fa fa-window-maximize", null));
        MenuVo userMenu = _configVo(14, "用户订单管理", null, null, "fa  fa-bar-chart", userNodes);
        menuVoList.add(userMenu);
        
        return responseEntity(200, "menus.list.suc", menuVoList.size(), menuVoList);
    }

    private MenuVo _configVo(Integer id, String name, String url, Integer pId, String pic, List<MenuVo> node) {
        MenuVo menuVo = new MenuVo();
        menuVo.setId(id);
        menuVo.setName(name);
        menuVo.setNode(node);
        menuVo.setpId(pId);
        menuVo.setUrl(url);
        menuVo.setPic(pic);
        return menuVo;
    }
}
