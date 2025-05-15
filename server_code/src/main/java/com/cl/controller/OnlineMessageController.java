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

import com.cl.entity.OnlineMessageEntity;
import com.cl.entity.view.OnlineMessageView;

import com.cl.service.OnlineMessageService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 在线留言
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@RestController
@RequestMapping("/onlinemessage")
public class OnlineMessageController {
    @Autowired
    private OnlineMessageService onlineMessageService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,OnlineMessageEntity onlineMessage,
                                                                                        HttpServletRequest request){
                                    EntityWrapper<OnlineMessageEntity> ew = new EntityWrapper<OnlineMessageEntity>();
                                                                                                                            
        
        
        PageUtils page = onlineMessageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, onlineMessage), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,OnlineMessageEntity onlineMessage,
		HttpServletRequest request){
        EntityWrapper<OnlineMessageEntity> ew = new EntityWrapper<OnlineMessageEntity>();

		PageUtils page = onlineMessageService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, onlineMessage), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( OnlineMessageEntity onlineMessage){
       	EntityWrapper<OnlineMessageEntity> ew = new EntityWrapper<OnlineMessageEntity>();
      	ew.allEq(MPUtil.allEQMapPre( onlineMessage, "onlineMessage")); 
        return R.ok().put("data", onlineMessageService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(OnlineMessageEntity onlineMessage){
        EntityWrapper< OnlineMessageEntity> ew = new EntityWrapper< OnlineMessageEntity>();
 		ew.allEq(MPUtil.allEQMapPre( onlineMessage, "onlineMessage")); 
		OnlineMessageView onlineMessageView =  onlineMessageService.selectView(ew);
		return R.ok("查询在线留言成功").put("data", onlineMessageView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        OnlineMessageEntity onlineMessage = onlineMessageService.selectById(id);
		onlineMessage = onlineMessageService.selectView(new EntityWrapper<OnlineMessageEntity>().eq("id", id));
        return R.ok().put("data", onlineMessage);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        OnlineMessageEntity onlineMessage = onlineMessageService.selectById(id);
		onlineMessage = onlineMessageService.selectView(new EntityWrapper<OnlineMessageEntity>().eq("id", id));
        return R.ok().put("data", onlineMessage);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增在线留言")
    public R save(@RequestBody OnlineMessageEntity onlineMessage, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(onlineMessage);
        onlineMessageService.insert(onlineMessage);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增在线留言")
    @RequestMapping("/add")
    public R add(@RequestBody OnlineMessageEntity onlineMessage, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(onlineMessage);
        onlineMessageService.insert(onlineMessage);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改在线留言")
    public R update(@RequestBody OnlineMessageEntity onlineMessage, HttpServletRequest request){
        //ValidatorUtils.validateEntity(onlineMessage);
        onlineMessageService.updateById(onlineMessage);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除在线留言")
    public R delete(@RequestBody Long[] ids){
        onlineMessageService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
