package com.chen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chen.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chen.pojo.PortalVo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author chenchen
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-09 14:26:10
* @Entity com.chen.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    //自定义分页查询方法
    @MapKey("hid")
    IPage<Map> selectPageMap(IPage<Headline> page,
                             @Param("portalVo") PortalVo portalVo);

//    @MapKey("hid")
    Map<String, String> selectDetailMap(Integer hid);


}




