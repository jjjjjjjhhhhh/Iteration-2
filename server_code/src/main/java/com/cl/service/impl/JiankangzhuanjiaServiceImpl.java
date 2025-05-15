package com.cl.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cl.utils.PageUtils;
import com.cl.utils.Query;


import com.cl.dao.JiankangzhuanjiaDao;
import com.cl.entity.JiankangzhuanjiaEntity;
import com.cl.service.JiankangzhuanjiaService;
import com.cl.entity.view.JiankangzhuanjiaView;

@Service("jiankangzhuanjiaService")
public class JiankangzhuanjiaServiceImpl extends ServiceImpl<JiankangzhuanjiaDao, JiankangzhuanjiaEntity> implements JiankangzhuanjiaService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JiankangzhuanjiaEntity> page = this.selectPage(
                new Query<JiankangzhuanjiaEntity>(params).getPage(),
                new EntityWrapper<JiankangzhuanjiaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JiankangzhuanjiaEntity> wrapper) {
		  Page<JiankangzhuanjiaView> page =new Query<JiankangzhuanjiaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JiankangzhuanjiaView> selectListView(Wrapper<JiankangzhuanjiaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JiankangzhuanjiaView selectView(Wrapper<JiankangzhuanjiaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
