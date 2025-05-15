package com.cl.dao;

import com.cl.entity.JianshenjihuaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JianshenjihuaView;


/**
 * 健身计划
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
public interface JianshenjihuaDao extends BaseMapper<JianshenjihuaEntity> {
	
	List<JianshenjihuaView> selectListView(@Param("ew") Wrapper<JianshenjihuaEntity> wrapper);

	List<JianshenjihuaView> selectListView(Pagination page,@Param("ew") Wrapper<JianshenjihuaEntity> wrapper);
	
	JianshenjihuaView selectView(@Param("ew") Wrapper<JianshenjihuaEntity> wrapper);


}
