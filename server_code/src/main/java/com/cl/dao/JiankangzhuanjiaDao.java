package com.cl.dao;

import com.cl.entity.JiankangzhuanjiaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiankangzhuanjiaView;


/**
 * 健康专家
 * 
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface JiankangzhuanjiaDao extends BaseMapper<JiankangzhuanjiaEntity> {
	
	List<JiankangzhuanjiaView> selectListView(@Param("ew") Wrapper<JiankangzhuanjiaEntity> wrapper);

	List<JiankangzhuanjiaView> selectListView(Pagination page,@Param("ew") Wrapper<JiankangzhuanjiaEntity> wrapper);
	
	JiankangzhuanjiaView selectView(@Param("ew") Wrapper<JiankangzhuanjiaEntity> wrapper);


}
