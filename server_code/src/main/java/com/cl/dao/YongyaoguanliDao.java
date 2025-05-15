package com.cl.dao;

import com.cl.entity.YongyaoguanliEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.YongyaoguanliView;


/**
 * 用药管理
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
public interface YongyaoguanliDao extends BaseMapper<YongyaoguanliEntity> {
	
	List<YongyaoguanliView> selectListView(@Param("ew") Wrapper<YongyaoguanliEntity> wrapper);

	List<YongyaoguanliView> selectListView(Pagination page,@Param("ew") Wrapper<YongyaoguanliEntity> wrapper);
	
	YongyaoguanliView selectView(@Param("ew") Wrapper<YongyaoguanliEntity> wrapper);


}
