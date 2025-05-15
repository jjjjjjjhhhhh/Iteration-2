package com.cl.dao;

import com.cl.entity.JiankangzhishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiankangzhishiView;


/**
 * 健康知识
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface JiankangzhishiDao extends BaseMapper<JiankangzhishiEntity> {
	
	List<JiankangzhishiView> selectListView(@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);

	List<JiankangzhishiView> selectListView(Pagination page,@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);
	
	JiankangzhishiView selectView(@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);


    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<JiankangzhishiEntity> wrapper);



}
