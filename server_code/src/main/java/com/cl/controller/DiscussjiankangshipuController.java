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

import com.cl.entity.DiscussjiankangshipuEntity;
import com.cl.entity.view.DiscussjiankangshipuView;

import com.cl.service.DiscussjiankangshipuService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 健康食谱评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@RestController
@RequestMapping("/discussjiankangshipu")
public class DiscussjiankangshipuController {
    @Autowired
    private DiscussjiankangshipuService discussjiankangshipuService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussjiankangshipuEntity discussjiankangshipu,
                                                                                                                            HttpServletRequest request){
                                    EntityWrapper<DiscussjiankangshipuEntity> ew = new EntityWrapper<DiscussjiankangshipuEntity>();
                                                                                                                                                                                        
        
        
        PageUtils page = discussjiankangshipuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangshipu), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussjiankangshipuEntity discussjiankangshipu,
		HttpServletRequest request){
        EntityWrapper<DiscussjiankangshipuEntity> ew = new EntityWrapper<DiscussjiankangshipuEntity>();

		PageUtils page = discussjiankangshipuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangshipu), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussjiankangshipuEntity discussjiankangshipu){
       	EntityWrapper<DiscussjiankangshipuEntity> ew = new EntityWrapper<DiscussjiankangshipuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussjiankangshipu, "discussjiankangshipu")); 
        return R.ok().put("data", discussjiankangshipuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussjiankangshipuEntity discussjiankangshipu){
        EntityWrapper< DiscussjiankangshipuEntity> ew = new EntityWrapper< DiscussjiankangshipuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussjiankangshipu, "discussjiankangshipu")); 
		DiscussjiankangshipuView discussjiankangshipuView =  discussjiankangshipuService.selectView(ew);
		return R.ok("查询健康食谱评论表成功").put("data", discussjiankangshipuView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussjiankangshipuEntity discussjiankangshipu = discussjiankangshipuService.selectById(id);
		discussjiankangshipu = discussjiankangshipuService.selectView(new EntityWrapper<DiscussjiankangshipuEntity>().eq("id", id));
        return R.ok().put("data", discussjiankangshipu);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussjiankangshipuEntity discussjiankangshipu = discussjiankangshipuService.selectById(id);
		discussjiankangshipu = discussjiankangshipuService.selectView(new EntityWrapper<DiscussjiankangshipuEntity>().eq("id", id));
        return R.ok().put("data", discussjiankangshipu);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增健康食谱评论表")
    public R save(@RequestBody DiscussjiankangshipuEntity discussjiankangshipu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussjiankangshipu);
        discussjiankangshipuService.insert(discussjiankangshipu);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增健康食谱评论表")
    @RequestMapping("/add")
    public R add(@RequestBody DiscussjiankangshipuEntity discussjiankangshipu, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(discussjiankangshipu);
        discussjiankangshipuService.insert(discussjiankangshipu);
        return R.ok();
    }

     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussjiankangshipuEntity discussjiankangshipu = discussjiankangshipuService.selectOne(new EntityWrapper<DiscussjiankangshipuEntity>().eq("", username));
        return R.ok().put("data", discussjiankangshipu);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussjiankangshipuEntity discussjiankangshipu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussjiankangshipu);
        discussjiankangshipuService.updateById(discussjiankangshipu);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除健康食谱评论表")
    public R delete(@RequestBody Long[] ids){
        discussjiankangshipuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussjiankangshipuEntity discussjiankangshipu, HttpServletRequest request,String pre){
        EntityWrapper<DiscussjiankangshipuEntity> ew = new EntityWrapper<DiscussjiankangshipuEntity>();
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
		PageUtils page = discussjiankangshipuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjiankangshipu), params), params));
        return R.ok().put("data", page);
    }









}
