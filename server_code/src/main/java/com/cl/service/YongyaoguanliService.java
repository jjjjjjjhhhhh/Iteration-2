package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.YongyaoguanliEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YongyaoguanliView;


/**
 * 用药管理
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
public interface YongyaoguanliService extends IService<YongyaoguanliEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YongyaoguanliView> selectListView(Wrapper<YongyaoguanliEntity> wrapper);
   	
   	YongyaoguanliView selectView(@Param("ew") Wrapper<YongyaoguanliEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YongyaoguanliEntity> wrapper);
   	
   
}

