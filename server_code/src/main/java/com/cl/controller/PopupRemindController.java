package com.cl.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import com.cl.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cl.annotation.IgnoreAuth;
import com.cl.annotation.SysLog;

import com.cl.entity.PopupRemindEntity;
import com.cl.entity.view.PopupRemindView;

import com.cl.service.PopupRemindService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 吃药提醒
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@RestController
@RequestMapping("/popupremind")
public class PopupRemindController {
    @Autowired
    private PopupRemindService popupRemindService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,PopupRemindEntity popupRemind,
                                                                                                                            HttpServletRequest request){
                                    if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
                    popupRemind.setUserid((Long)request.getSession().getAttribute("userId"));
                }
                            EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = popupRemindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupRemind), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,PopupRemindEntity popupRemind,
		HttpServletRequest request){
        EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();

		PageUtils page = popupRemindService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, popupRemind), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( PopupRemindEntity popupRemind){
       	EntityWrapper<PopupRemindEntity> ew = new EntityWrapper<PopupRemindEntity>();
      	ew.allEq(MPUtil.allEQMapPre( popupRemind, "popupRemind")); 
        return R.ok().put("data", popupRemindService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(PopupRemindEntity popupRemind){
        EntityWrapper< PopupRemindEntity> ew = new EntityWrapper< PopupRemindEntity>();
 		ew.allEq(MPUtil.allEQMapPre( popupRemind, "popupRemind")); 
		PopupRemindView popupRemindView =  popupRemindService.selectView(ew);
		return R.ok("查询吃药提醒成功").put("data", popupRemindView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        PopupRemindEntity popupRemind = popupRemindService.selectById(id);
		popupRemind = popupRemindService.selectView(new EntityWrapper<PopupRemindEntity>().eq("id", id));
        return R.ok().put("data", popupRemind);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        PopupRemindEntity popupRemind = popupRemindService.selectById(id);
		popupRemind = popupRemindService.selectView(new EntityWrapper<PopupRemindEntity>().eq("id", id));
        return R.ok().put("data", popupRemind);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增吃药提醒")
    public R save(@RequestBody PopupRemindEntity popupRemind, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(popupRemind);
    	popupRemind.setUserid((Long)request.getSession().getAttribute("userId"));
        popupRemindService.insert(popupRemind);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增吃药提醒")
    @RequestMapping("/add")
    public R add(@RequestBody PopupRemindEntity popupRemind, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(popupRemind);
        popupRemindService.insert(popupRemind);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改吃药提醒")
    public R update(@RequestBody PopupRemindEntity popupRemind, HttpServletRequest request){
        //ValidatorUtils.validateEntity(popupRemind);
        popupRemindService.updateById(popupRemind);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除吃药提醒")
    public R delete(@RequestBody Long[] ids){
        popupRemindService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
