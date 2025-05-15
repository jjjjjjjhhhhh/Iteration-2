package com.cl.dao;

import com.cl.entity.DiscussjiankangzhishiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.DiscussjiankangzhishiView;


/**
 * 健康知识评论表
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface DiscussjiankangzhishiDao extends BaseMapper<DiscussjiankangzhishiEntity> {
	
	List<DiscussjiankangzhishiView> selectListView(@Param("ew") Wrapper<DiscussjiankangzhishiEntity> wrapper);

	List<DiscussjiankangzhishiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussjiankangzhishiEntity> wrapper);
	
	DiscussjiankangzhishiView selectView(@Param("ew") Wrapper<DiscussjiankangzhishiEntity> wrapper);


}
