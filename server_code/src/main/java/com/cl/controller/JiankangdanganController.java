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

import com.cl.entity.JiankangdanganEntity;
import com.cl.entity.view.JiankangdanganView;

import com.cl.service.JiankangdanganService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 健康档案
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
@RestController
@RequestMapping("/jiankangdangan")
public class JiankangdanganController {
    @Autowired
    private JiankangdanganService jiankangdanganService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiankangdanganEntity jiankangdangan,
                                                                                                                                                                                HttpServletRequest request){
                    String tableName = request.getSession().getAttribute("tableName").toString();
                                            if(tableName.equals("yonghu")) {
                    jiankangdangan.setYonghuzhanghao((String)request.getSession().getAttribute("username"));
                                    }
                                                                                                                                                                                                                                                                                                                                                                                    EntityWrapper<JiankangdanganEntity> ew = new EntityWrapper<JiankangdanganEntity>();
                                                                                                                                                                                                                                                                                    
        
        
        PageUtils page = jiankangdanganService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangdangan), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiankangdanganEntity jiankangdangan,
		HttpServletRequest request){
        EntityWrapper<JiankangdanganEntity> ew = new EntityWrapper<JiankangdanganEntity>();

		PageUtils page = jiankangdanganService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangdangan), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiankangdanganEntity jiankangdangan){
       	EntityWrapper<JiankangdanganEntity> ew = new EntityWrapper<JiankangdanganEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiankangdangan, "jiankangdangan")); 
        return R.ok().put("data", jiankangdanganService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiankangdanganEntity jiankangdangan){
        EntityWrapper< JiankangdanganEntity> ew = new EntityWrapper< JiankangdanganEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiankangdangan, "jiankangdangan")); 
		JiankangdanganView jiankangdanganView =  jiankangdanganService.selectView(ew);
		return R.ok("查询健康档案成功").put("data", jiankangdanganView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiankangdanganEntity jiankangdangan = jiankangdanganService.selectById(id);
		jiankangdangan = jiankangdanganService.selectView(new EntityWrapper<JiankangdanganEntity>().eq("id", id));
        return R.ok().put("data", jiankangdangan);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiankangdanganEntity jiankangdangan = jiankangdanganService.selectById(id);
		jiankangdangan = jiankangdanganService.selectView(new EntityWrapper<JiankangdanganEntity>().eq("id", id));
        return R.ok().put("data", jiankangdangan);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增健康档案")
    public R save(@RequestBody JiankangdanganEntity jiankangdangan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiankangdangan);
        jiankangdanganService.insert(jiankangdangan);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增健康档案")
    @RequestMapping("/add")
    public R add(@RequestBody JiankangdanganEntity jiankangdangan, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiankangdangan);
        jiankangdanganService.insert(jiankangdangan);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改健康档案")
    public R update(@RequestBody JiankangdanganEntity jiankangdangan, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiankangdangan);
        jiankangdanganService.updateById(jiankangdangan);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除健康档案")
    public R delete(@RequestBody Long[] ids){
        jiankangdanganService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
