package com.cl.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.cl.utils.PageUtils;
import com.cl.entity.JiankangzhuanjiaEntity;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.cl.entity.view.JiankangzhuanjiaView;


/**
 * 健康专家
 *
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
public interface JiankangzhuanjiaService extends IService<JiankangzhuanjiaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JiankangzhuanjiaView> selectListView(Wrapper<JiankangzhuanjiaEntity> wrapper);
   	
   	JiankangzhuanjiaView selectView(@Param("ew") Wrapper<JiankangzhuanjiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JiankangzhuanjiaEntity> wrapper);
   	
   
}

