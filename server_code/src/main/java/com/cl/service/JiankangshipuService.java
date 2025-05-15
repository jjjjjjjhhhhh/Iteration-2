package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiankangshipuEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiankangshipuView;


/**
 * 健康食谱
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
public interface JiankangshipuService extends IService<JiankangshipuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiankangshipuView> selectListView(Wrapper<JiankangshipuEntity> wrapper);
   	
   	JiankangshipuView selectView(@Param("ew") Wrapper<JiankangshipuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiankangshipuEntity> wrapper);
   	
   
}

