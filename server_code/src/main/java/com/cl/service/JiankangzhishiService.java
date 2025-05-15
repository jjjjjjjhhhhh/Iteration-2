package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiankangzhishiEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiankangzhishiView;


/**
 * 健康知识
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface JiankangzhishiService extends IService<JiankangzhishiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiankangzhishiView> selectListView(Wrapper<JiankangzhishiEntity> wrapper);
   	
   	JiankangzhishiView selectView(@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiankangzhishiEntity> wrapper);
   	
   
    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<JiankangzhishiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<JiankangzhishiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<JiankangzhishiEntity> wrapper);



}

