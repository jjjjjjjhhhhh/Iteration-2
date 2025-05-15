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

import com.cl.entity.DiscussjiankangzhishiEntity;
import com.cl.entity.view.DiscussjiankangzhishiView;

import com.cl.service.DiscussjiankangzhishiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 健康知识评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@RestController
@RequestMapping("/discussjiankangzhishi")
public class DiscussjiankangzhishiController {
    @Autowired
    private DiscussjiankangzhishiService discussjiankangzhishiService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussjiankangzhishiEntity discussjiankangzhishi,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscussjiankangzhishiEntity> ew = new EntityWrapper<DiscussjiankangzhishiEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discussjiankangzhishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangzhishi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussjiankangzhishiEntity discussjiankangzhishi,
		HttpServletRequest request){
        EntityWrapper<DiscussjiankangzhishiEntity> ew = new EntityWrapper<DiscussjiankangzhishiEntity>();

		PageUtils page = discussjiankangzhishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangzhishi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussjiankangzhishiEntity discussjiankangzhishi){
       	EntityWrapper<DiscussjiankangzhishiEntity> ew = new EntityWrapper<DiscussjiankangzhishiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussjiankangzhishi, "discussjiankangzhishi")); 
        return R.ok().put("data", discussjiankangzhishiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussjiankangzhishiEntity discussjiankangzhishi){
        EntityWrapper< DiscussjiankangzhishiEntity> ew = new EntityWrapper< DiscussjiankangzhishiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussjiankangzhishi, "discussjiankangzhishi")); 
		DiscussjiankangzhishiView discussjiankangzhishiView =  discussjiankangzhishiService.selectView(ew);
		return R.ok("查询健康知识评论表成功").put("data", discussjiankangzhishiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussjiankangzhishiEntity discussjiankangzhishi = discussjiankangzhishiService.selectById(id);
		discussjiankangzhishi = discussjiankangzhishiService.selectView(new EntityWrapper<DiscussjiankangzhishiEntity>().eq("id", id));
        return R.ok().put("data", discussjiankangzhishi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussjiankangzhishiEntity discussjiankangzhishi = discussjiankangzhishiService.selectById(id);
		discussjiankangzhishi = discussjiankangzhishiService.selectView(new EntityWrapper<DiscussjiankangzhishiEntity>().eq("id", id));
        return R.ok().put("data", discussjiankangzhishi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增健康知识评论表")
    public R save(@RequestBody DiscussjiankangzhishiEntity discussjiankangzhishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussjiankangzhishi);
        discussjiankangzhishiService.insert(discussjiankangzhishi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增健康知识评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussjiankangzhishiEntity discussjiankangzhishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussjiankangzhishi);
        discussjiankangzhishiService.insert(discussjiankangzhishi);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussjiankangzhishiEntity discussjiankangzhishi = discussjiankangzhishiService.selectOne(new EntityWrapper<DiscussjiankangzhishiEntity>().eq("", username));
        return R.ok().put("data", discussjiankangzhishi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussjiankangzhishiEntity discussjiankangzhishi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussjiankangzhishi);
        discussjiankangzhishiService.updateById(discussjiankangzhishi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除健康知识评论表")
    public R delete(@RequestBody Long[] ids){
        discussjiankangzhishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussjiankangzhishiEntity discussjiankangzhishi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussjiankangzhishiEntity> ew = new EntityWrapper<DiscussjiankangzhishiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = discussjiankangzhishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangzhishi), params), params));
        return R.ok().put("data", page);
    }









}
