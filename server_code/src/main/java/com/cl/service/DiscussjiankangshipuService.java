package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.DiscussjiankangshipuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussjiankangshipuView;


/**
 * 健康食谱评论表
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface DiscussjiankangshipuService extends IService<DiscussjiankangshipuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussjiankangshipuView> selectListView(Wrapper<DiscussjiankangshipuEntity> wrapper);
   	
   	DiscussjiankangshipuView selectView(@Param("ew") Wrapper<DiscussjiankangshipuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussjiankangshipuEntity> wrapper);
   	
   
}

