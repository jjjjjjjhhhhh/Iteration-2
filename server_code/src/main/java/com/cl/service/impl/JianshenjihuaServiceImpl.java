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


import com.cl.dao.JianshenjihuaDao;
import com.cl.entity.JianshenjihuaEntity;
import com.cl.service.JianshenjihuaService;
import com.cl.entity.view.JianshenjihuaView;

@Service("jianshenjihuaService")
public class JianshenjihuaServiceImpl extends ServiceImpl<JianshenjihuaDao, JianshenjihuaEntity> implements JianshenjihuaService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JianshenjihuaEntity> page = this.selectPage(
                new Query<JianshenjihuaEntity>(params).getPage(),
                new EntityWrapper<JianshenjihuaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JianshenjihuaEntity> wrapper) {
		  Page<JianshenjihuaView> page =new Query<JianshenjihuaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<JianshenjihuaView> selectListView(Wrapper<JianshenjihuaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JianshenjihuaView selectView(Wrapper<JianshenjihuaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
