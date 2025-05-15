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

import com.cl.entity.YongyaoguanliEntity;
import com.cl.entity.view.YongyaoguanliView;

import com.cl.service.YongyaoguanliService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 用药管理
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
@RestController
@RequestMapping("/yongyaoguanli")
public class YongyaoguanliController {
    @Autowired
    private YongyaoguanliService yongyaoguanliService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YongyaoguanliEntity yongyaoguanli,
                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                                                                                                                                                                                                    if(tableName.equals("yonghu")) {
                    yongyaoguanli.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                            EntityWrapper<YongyaoguanliEntity> ew = new EntityWrapper<YongyaoguanliEntity>();
                                                                                                                                                                                                    
        
        
        PageUtils page = yongyaoguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yongyaoguanli), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YongyaoguanliEntity yongyaoguanli,
		HttpServletRequest request){
        EntityWrapper<YongyaoguanliEntity> ew = new EntityWrapper<YongyaoguanliEntity>();

		PageUtils page = yongyaoguanliService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yongyaoguanli), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YongyaoguanliEntity yongyaoguanli){
       	EntityWrapper<YongyaoguanliEntity> ew = new EntityWrapper<YongyaoguanliEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yongyaoguanli, "yongyaoguanli")); 
        return R.ok().put("data", yongyaoguanliService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YongyaoguanliEntity yongyaoguanli){
        EntityWrapper< YongyaoguanliEntity> ew = new EntityWrapper< YongyaoguanliEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yongyaoguanli, "yongyaoguanli")); 
		YongyaoguanliView yongyaoguanliView =  yongyaoguanliService.selectView(ew);
		return R.ok("查询用药管理成功").put("data", yongyaoguanliView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YongyaoguanliEntity yongyaoguanli = yongyaoguanliService.selectById(id);
		yongyaoguanli = yongyaoguanliService.selectView(new EntityWrapper<YongyaoguanliEntity>().eq("id", id));
        return R.ok().put("data", yongyaoguanli);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YongyaoguanliEntity yongyaoguanli = yongyaoguanliService.selectById(id);
		yongyaoguanli = yongyaoguanliService.selectView(new EntityWrapper<YongyaoguanliEntity>().eq("id", id));
        return R.ok().put("data", yongyaoguanli);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增用药管理")
    public R save(@RequestBody YongyaoguanliEntity yongyaoguanli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yongyaoguanli);
        yongyaoguanliService.insert(yongyaoguanli);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增用药管理")
    @RequestMapping("/add")
    public R add(@RequestBody YongyaoguanliEntity yongyaoguanli, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(yongyaoguanli);
        yongyaoguanliService.insert(yongyaoguanli);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改用药管理")
    public R update(@RequestBody YongyaoguanliEntity yongyaoguanli, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yongyaoguanli);
        yongyaoguanliService.updateById(yongyaoguanli);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除用药管理")
    public R delete(@RequestBody Long[] ids){
        yongyaoguanliService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
