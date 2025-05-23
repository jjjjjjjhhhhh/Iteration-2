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


import com.cl.dao.YinshijiluDao;
import com.cl.entity.YinshijiluEntity;
import com.cl.service.YinshijiluService;
import com.cl.entity.view.YinshijiluView;

@Service("yinshijiluService")
public class YinshijiluServiceImpl extends ServiceImpl<YinshijiluDao, YinshijiluEntity> implements YinshijiluService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YinshijiluEntity> page = this.selectPage(
                new Query<YinshijiluEntity>(params).getPage(),
                new EntityWrapper<YinshijiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YinshijiluEntity> wrapper) {
		  Page<YinshijiluView> page =new Query<YinshijiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<YinshijiluView> selectListView(Wrapper<YinshijiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YinshijiluView selectView(Wrapper<YinshijiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
