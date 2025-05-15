package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussjiankangzhishiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussjiankangzhishiView;


/**
 * 健康知识评论表
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface DiscussjiankangzhishiService extends IService<DiscussjiankangzhishiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussjiankangzhishiView> selectListView(Wrapper<DiscussjiankangzhishiEntity> wrapper);
   	
   	DiscussjiankangzhishiView selectView(@Param("ew") Wrapper<DiscussjiankangzhishiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussjiankangzhishiEntity> wrapper);
   	
   
}

