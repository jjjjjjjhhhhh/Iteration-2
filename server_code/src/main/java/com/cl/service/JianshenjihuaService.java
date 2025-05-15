package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JianshenjihuaEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JianshenjihuaView;


/**
 * 健身计划
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
public interface JianshenjihuaService extends IService<JianshenjihuaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JianshenjihuaView> selectListView(Wrapper<JianshenjihuaEntity> wrapper);
   	
   	JianshenjihuaView selectView(@Param("ew") Wrapper<JianshenjihuaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JianshenjihuaEntity> wrapper);
   	
   
}

