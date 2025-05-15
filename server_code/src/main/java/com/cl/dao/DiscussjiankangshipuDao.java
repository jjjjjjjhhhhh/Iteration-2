package com.cl.dao;

import com.cl.entity.DiscussjiankangshipuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussjiankangshipuView;


/**
 * 健康食谱评论表
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface DiscussjiankangshipuDao extends BaseMapper<DiscussjiankangshipuEntity> {
	
	List<DiscussjiankangshipuView> selectListView(@Param("ew") Wrapper<DiscussjiankangshipuEntity> wrapper);

	List<DiscussjiankangshipuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussjiankangshipuEntity> wrapper);
	
	DiscussjiankangshipuView selectView(@Param("ew") Wrapper<DiscussjiankangshipuEntity> wrapper);


}
