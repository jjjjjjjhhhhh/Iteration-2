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


import com.cl.dao.PopupRemindDao;
import com.cl.entity.PopupRemindEntity;
import com.cl.service.PopupRemindService;
import com.cl.entity.view.PopupRemindView;

@Service("popupRemindService")
public class PopupRemindServiceImpl extends ServiceImpl<PopupRemindDao, PopupRemindEntity> implements PopupRemindService {

    	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<PopupRemindEntity> page = this.selectPage(
                new Query<PopupRemindEntity>(params).getPage(),
                new EntityWrapper<PopupRemindEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<PopupRemindEntity> wrapper) {
		  Page<PopupRemindView> page =new Query<PopupRemindView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
	@Override
	public List<PopupRemindView> selectListView(Wrapper<PopupRemindEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public PopupRemindView selectView(Wrapper<PopupRemindEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}
	
	


}
