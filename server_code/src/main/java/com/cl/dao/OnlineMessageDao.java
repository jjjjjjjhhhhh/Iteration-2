package com.cl.dao;

import com.cl.entity.OnlineMessageEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.OnlineMessageView;


/**
 * 在线留言
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface OnlineMessageDao extends BaseMapper<OnlineMessageEntity> {
	
	List<OnlineMessageView> selectListView(@Param("ew") Wrapper<OnlineMessageEntity> wrapper);

	List<OnlineMessageView> selectListView(Pagination page,@Param("ew") Wrapper<OnlineMessageEntity> wrapper);
	
	OnlineMessageView selectView(@Param("ew") Wrapper<OnlineMessageEntity> wrapper);


}
