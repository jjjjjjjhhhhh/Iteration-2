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


import com.cl.dao.YongyaoguanliDao;
import com.cl.entity.YongyaoguanliEntity;
import com.cl.service.YongyaoguanliService;
import com.cl.entity.view.YongyaoguanliView;

@Service("yongyaoguanliService")
public class YongyaoguanliServiceImpl extends ServiceImpl<YongyaoguanliDao, YongyaoguanliEntity> implements YongyaoguanliService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YongyaoguanliEntity> page = this.selectPage(
                new Query<YongyaoguanliEntity>(params).getPage(),
                new EntityWrapper<YongyaoguanliEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YongyaoguanliEntity> wrapper) {
		  Page<YongyaoguanliView> page =new Query<YongyaoguanliView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YongyaoguanliView> selectListView(Wrapper<YongyaoguanliEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YongyaoguanliView selectView(Wrapper<YongyaoguanliEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
