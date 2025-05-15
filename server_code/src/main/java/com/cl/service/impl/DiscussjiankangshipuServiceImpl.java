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


import com.cl.dao.DiscussjiankangshipuDao;
import com.cl.entity.DiscussjiankangshipuEntity;
import com.cl.service.DiscussjiankangshipuService;
import com.cl.entity.view.DiscussjiankangshipuView;

@Service("discussjiankangshipuService")
public class DiscussjiankangshipuServiceImpl extends ServiceImpl<DiscussjiankangshipuDao, DiscussjiankangshipuEntity> implements DiscussjiankangshipuService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussjiankangshipuEntity> page = this.selectPage(
                new Query<DiscussjiankangshipuEntity>(params).getPage(),
                new EntityWrapper<DiscussjiankangshipuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussjiankangshipuEntity> wrapper) {
		  Page<DiscussjiankangshipuView> page =new Query<DiscussjiankangshipuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<DiscussjiankangshipuView> selectListView(Wrapper<DiscussjiankangshipuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussjiankangshipuView selectView(Wrapper<DiscussjiankangshipuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
