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

import com.cl.entity.JiankangzhishiEntity;
import com.cl.entity.view.JiankangzhishiView;

import com.cl.service.JiankangzhishiService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;
import com.cl.service.StoreupService;
import com.cl.entity.StoreupEntity;

/**
 * 健康知识
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@RestController
@RequestMapping("/jiankangzhishi")
public class JiankangzhishiController {
    @Autowired
    private JiankangzhishiService jiankangzhishiService;

    @Autowired
    private StoreupService storeupService;








    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiankangzhishiEntity jiankangzhishi,
                                                                                                                    HttpServletRequest request){
                                    EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
                                                                                                                                                                                
        
        
        PageUtils page = jiankangzhishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangzhishi), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiankangzhishiEntity jiankangzhishi,
		HttpServletRequest request){
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();

		PageUtils page = jiankangzhishiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangzhishi), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiankangzhishiEntity jiankangzhishi){
       	EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiankangzhishi, "jiankangzhishi")); 
        return R.ok().put("data", jiankangzhishiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiankangzhishiEntity jiankangzhishi){
        EntityWrapper< JiankangzhishiEntity> ew = new EntityWrapper< JiankangzhishiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiankangzhishi, "jiankangzhishi")); 
		JiankangzhishiView jiankangzhishiView =  jiankangzhishiService.selectView(ew);
		return R.ok("查询健康知识成功").put("data", jiankangzhishiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiankangzhishiEntity jiankangzhishi = jiankangzhishiService.selectById(id);
		jiankangzhishi = jiankangzhishiService.selectView(new EntityWrapper<JiankangzhishiEntity>().eq("id", id));
        return R.ok().put("data", jiankangzhishi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiankangzhishiEntity jiankangzhishi = jiankangzhishiService.selectById(id);
		jiankangzhishi = jiankangzhishiService.selectView(new EntityWrapper<JiankangzhishiEntity>().eq("id", id));
        return R.ok().put("data", jiankangzhishi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增健康知识")
    public R save(@RequestBody JiankangzhishiEntity jiankangzhishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiankangzhishi);
        jiankangzhishiService.insert(jiankangzhishi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增健康知识")
    @RequestMapping("/add")
    public R add(@RequestBody JiankangzhishiEntity jiankangzhishi, HttpServletRequest request){
    	//ValidatorUtils.validateEntity(jiankangzhishi);
        jiankangzhishiService.insert(jiankangzhishi);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改健康知识")
    public R update(@RequestBody JiankangzhishiEntity jiankangzhishi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiankangzhishi);
        jiankangzhishiService.updateById(jiankangzhishi);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除健康知识")
    public R delete(@RequestBody Long[] ids){
        jiankangzhishiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	





    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(jiankangzhishiService.selectValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }

        Collections.sort(result, (map1, map2) -> {
            // 假设 total 总是存在并且是数值类型
            Number total1 = (Number) map1.get("total");
            Number total2 = (Number) map2.get("total");
            if(total1==null)
            {
                total1 = 0;
            }
            if(total2==null)
            {
                total2 = 0;
            }
            String order = request.getParameter("order");
            if(StringUtils.isNotBlank(order)&&order.equals("desc")){
                return Double.compare(total2.doubleValue(), total1.doubleValue());
            }
            return Double.compare(total1.doubleValue(), total2.doubleValue());
        });

        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(jiankangzhishiService.selectValue(params, ew));
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", MPUtil.camelToSnake(xColumnName));
        params.put("yColumn", MPUtil.camelToSnake(yColumnName));
        params.put("timeStatType", timeStatType);
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(jiankangzhishiService.selectTimeStatValue(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = MPUtil.camelToSnake(yColumnNameMul).split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = MPUtil.snakeListToCamel(jiankangzhishiService.selectTimeStatValue(params, ew));
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", MPUtil.camelToSnake(columnName));
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
        List<Map<String, Object>> result = MPUtil.snakeListToCamel(jiankangzhishiService.selectGroup(params, ew));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,JiankangzhishiEntity jiankangzhishi, HttpServletRequest request){
        EntityWrapper<JiankangzhishiEntity> ew = new EntityWrapper<JiankangzhishiEntity>();
        int count = jiankangzhishiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangzhishi), params), params));
        return R.ok().put("data", count);
    }



}
