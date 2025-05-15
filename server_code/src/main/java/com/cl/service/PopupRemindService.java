package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.PopupRemindEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.PopupRemindView;


/**
 * 吃药提醒
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface PopupRemindService extends IService<PopupRemindEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<PopupRemindView> selectListView(Wrapper<PopupRemindEntity> wrapper);
   	
   	PopupRemindView selectView(@Param("ew") Wrapper<PopupRemindEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<PopupRemindEntity> wrapper);
   	
   
}

