package com.chen.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.pojo.Headline;
import com.chen.pojo.PortalVo;
import com.chen.service.HeadlineService;
import com.chen.mapper.HeadlineMapper;
import com.chen.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenchen
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2023-10-09 14:26:10
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        //1.条件拼接 需要非空判断
        LambdaQueryWrapper<Headline> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(portalVo.getKeyWords()), Headline::getTitle, portalVo.getKeyWords())
                .eq(portalVo.getType() != null, Headline::getType, portalVo.getType());

        //2.分页参数
        IPage<Headline> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        //3.分页查询
        //查询的结果 "pastHours":"3"   // 发布时间已过小时数 我们查询返回一个map
        //自定义方法
        headlineMapper.selectPageMap(page, portalVo);

        //4.结果封装
        //分页数据封装
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageData", page.getRecords());
        pageInfo.put("pageNum", page.getCurrent());
        pageInfo.put("pageSize", page.getSize());
        pageInfo.put("totalPage", page.getPages());
        pageInfo.put("totalSize", page.getTotal());

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        // 响应JSON
        return Result.ok(pageInfoMap);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        //查找数据
        Map headlineDetail = headlineMapper.selectDetailMap(hid);
        System.out.println(headlineDetail.toString());

        //更新数据，浏览量+1
        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) headlineDetail.get("pageViews") + 1); //阅读量+1
        headline.setVersion((Integer) headlineDetail.get("version")); //设置版本
        headlineMapper.updateById(headline);

        Map<String, Map> headlineMap = new HashMap();
        headlineMap.put("headline", headlineDetail);
        return Result.ok(headlineMap);
    }

    @Override
    public Result publish(Headline headline) {
//        LambdaUpdateWrapper wrapper = new LambdaUpdateWrapper();
//        wrapper.setEntity(headline);

        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map pageInfo = new HashMap();
        pageInfo.put("headline", headline);
        return Result.ok(pageInfo);
    }

    @Override
    public Result update(Headline headline) {
        //读取版本
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);
        headline.setUpdateTime(new Date());

        headlineMapper.updateById(headline);

        return Result.ok(null);
    }

    @Override
    public Result removeByHid(Integer hid) {
        headlineMapper.deleteById(hid);
        return Result.ok(null);
    }
}




