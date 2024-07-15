package com.chen.service;

import com.chen.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.pojo.PortalVo;
import com.chen.utils.Result;

/**
* @author chenchen
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-09 14:26:10
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result update(Headline headline);

    Result removeByHid(Integer hid);
}
