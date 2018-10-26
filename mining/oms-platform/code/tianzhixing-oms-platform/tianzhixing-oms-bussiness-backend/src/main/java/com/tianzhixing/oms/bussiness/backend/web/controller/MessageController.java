package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.vo.MessageVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 16/8/15.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

    /**
     * 消息列表
     *
     * @param pageNo
     * @param pageSize
     * @param isRead
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageVo> list(@RequestParam("pageNo") int pageNo,
                                          @RequestParam("pageSize") int pageSize,
                                          @RequestParam(value = "isRead", required = false) String isRead,
                                          @RequestParam(value = "messageBeginTime", required = false) String messageBeginTime,
                                          @RequestParam(value = "messageEndTime", required = false) String messageEndTime
    ) {
        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        Boolean isReaded = null;
        if (StringUtil.check(isRead)) {
            isReaded = Boolean.valueOf(isRead);
        }
        Date beginTime = null;
        if (StringUtil.check(messageBeginTime)) {
            beginTime = CalendarUtil.calBeginTimeForThisTime(CalendarUtil.stringToDate(messageBeginTime, "yyyy-MM-dd"));
        }
        Date endTime = null;
        if (StringUtil.check(messageEndTime)) {
            endTime = CalendarUtil.calEndTimeForThisTime(CalendarUtil.stringToDate(messageEndTime, "yyyy-MM-dd"));
        }
        long count = 0l;
        List<MessageVo> messageVoList = new ArrayList<MessageVo>();
        return responseEntity(200, "message.list.suc", count, messageVoList);
    }

    /**
     * 设置为已读或未读
     *
     * @return
     */
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageVo> read(@RequestParam("id") long id, @RequestParam("toRead") Boolean toRead) {
        return responseEntity(200, "message.setting.suc");
    }
}
