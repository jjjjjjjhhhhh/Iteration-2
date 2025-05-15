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


import com.cl.dao.OnlineMessageDao;
import com.cl.entity.OnlineMessageEntity;
import com.cl.service.OnlineMessageService;
import com.cl.entity.view.OnlineMessageView;

@Service("onlineMessageService")
public class OnlineMessageServiceImpl extends ServiceImpl<OnlineMessageDao, OnlineMessageEntity> implements OnlineMessageService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OnlineMessageEntity> page = this.selectPage(
                new Query<OnlineMessageEntity>(params).getPage(),
                new EntityWrapper<OnlineMessageEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<OnlineMessageEntity> wrapper) {
		  Page<OnlineMessageView> page =new Query<OnlineMessageView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<OnlineMessageView> selectListView(Wrapper<OnlineMessageEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public OnlineMessageView selectView(Wrapper<OnlineMessageEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
