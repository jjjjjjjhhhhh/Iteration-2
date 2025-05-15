package com.cl.dao;

import com.cl.entity.PopupRemindEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.PopupRemindView;


/**
 * 吃药提醒
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface PopupRemindDao extends BaseMapper<PopupRemindEntity> {
	
	List<PopupRemindView> selectListView(@Param("ew") Wrapper<PopupRemindEntity> wrapper);

	List<PopupRemindView> selectListView(Pagination page,@Param("ew") Wrapper<PopupRemindEntity> wrapper);
	
	PopupRemindView selectView(@Param("ew") Wrapper<PopupRemindEntity> wrapper);


}
