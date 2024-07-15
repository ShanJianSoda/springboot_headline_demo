package com.chen.controller;

import com.alibaba.druid.util.StringUtils;
import com.chen.pojo.Headline;
import com.chen.pojo.PortalVo;
import com.chen.service.HeadlineService;
import com.chen.utils.JwtHelper;
import com.chen.utils.Result;
import com.chen.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token) {
        //无需校验，拦截器执行
//        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token))
//            return Result.build(null, ResultCodeEnum.NOTLOGIN);

        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer hid) {
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.update(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid) {
        Result result = headlineService.removeByHid(hid);
        return result;
    }
}
