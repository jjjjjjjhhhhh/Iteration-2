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

import com.cl.entity.JiankangzhuanjiaEntity;
import com.cl.entity.view.JiankangzhuanjiaView;

import com.cl.service.JiankangzhuanjiaService;
import com.cl.service.TokenService;
import com.cl.utils.PageUtils;
import com.cl.utils.R;
import com.cl.utils.EncryptUtil;
import com.cl.utils.MPUtil;
import com.cl.utils.MapUtils;
import com.cl.utils.CommonUtil;

/**
 * 健康专家
 * 后端接口
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@RestController
@RequestMapping("/jiankangzhuanjia")
public class JiankangzhuanjiaController {
    @Autowired
    private JiankangzhuanjiaService jiankangzhuanjiaService;



	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		JiankangzhuanjiaEntity u = jiankangzhuanjiaService.selectOne(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", username));
        if(u==null || !u.getZhuanjiamima().equals(EncryptUtil.md5(password))) {
            return R.error("账号或密码不正确");
        }
		String token = tokenService.generateToken(u.getId(), username,"jiankangzhuanjia",  "健康专家" );
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiankangzhuanjiaEntity jiankangzhuanjia){
    	//ValidatorUtils.validateEntity(jiankangzhuanjia);
                            JiankangzhuanjiaEntity u = jiankangzhuanjiaService.selectOne(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", jiankangzhuanjia.getZhuanjiazhanghao()));
                                                                                                		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		jiankangzhuanjia.setId(uId);
        jiankangzhuanjia.setZhuanjiamima(EncryptUtil.md5(jiankangzhuanjia.getZhuanjiamima()));
        jiankangzhuanjiaService.insert(jiankangzhuanjia);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        return R.ok().put("data", jiankangzhuanjiaService.selectView(new EntityWrapper<JiankangzhuanjiaEntity>().eq("id", id)));
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	JiankangzhuanjiaEntity u = jiankangzhuanjiaService.selectOne(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setZhuanjiamima(EncryptUtil.md5("123456"));
        jiankangzhuanjiaService.updateById(u);
        return R.ok("密码已重置为：123456");
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiankangzhuanjiaEntity jiankangzhuanjia,
                                                                                                                                HttpServletRequest request){
                                    EntityWrapper<JiankangzhuanjiaEntity> ew = new EntityWrapper<JiankangzhuanjiaEntity>();
                                                                                                                                                                                                    
        
        
        PageUtils page = jiankangzhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangzhuanjia), params), params));
        return R.ok().put("data", page);
    }







    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JiankangzhuanjiaEntity jiankangzhuanjia,
		HttpServletRequest request){
        EntityWrapper<JiankangzhuanjiaEntity> ew = new EntityWrapper<JiankangzhuanjiaEntity>();

		PageUtils page = jiankangzhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiankangzhuanjia), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiankangzhuanjiaEntity jiankangzhuanjia){
       	EntityWrapper<JiankangzhuanjiaEntity> ew = new EntityWrapper<JiankangzhuanjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiankangzhuanjia, "jiankangzhuanjia")); 
        return R.ok().put("data", jiankangzhuanjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiankangzhuanjiaEntity jiankangzhuanjia){
        EntityWrapper< JiankangzhuanjiaEntity> ew = new EntityWrapper< JiankangzhuanjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiankangzhuanjia, "jiankangzhuanjia")); 
		JiankangzhuanjiaView jiankangzhuanjiaView =  jiankangzhuanjiaService.selectView(ew);
		return R.ok("查询健康专家成功").put("data", jiankangzhuanjiaView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiankangzhuanjiaEntity jiankangzhuanjia = jiankangzhuanjiaService.selectById(id);
		jiankangzhuanjia = jiankangzhuanjiaService.selectView(new EntityWrapper<JiankangzhuanjiaEntity>().eq("id", id));
        return R.ok().put("data", jiankangzhuanjia);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiankangzhuanjiaEntity jiankangzhuanjia = jiankangzhuanjiaService.selectById(id);
		jiankangzhuanjia = jiankangzhuanjiaService.selectView(new EntityWrapper<JiankangzhuanjiaEntity>().eq("id", id));
        return R.ok().put("data", jiankangzhuanjia);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    @SysLog("新增健康专家")
    public R save(@RequestBody JiankangzhuanjiaEntity jiankangzhuanjia, HttpServletRequest request){
        if(jiankangzhuanjiaService.selectCount(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", jiankangzhuanjia.getZhuanjiazhanghao()))>0) {
            return R.error("专家账号已存在");
        }
    	jiankangzhuanjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiankangzhuanjia);
        JiankangzhuanjiaEntity u = jiankangzhuanjiaService.selectOne(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", jiankangzhuanjia.getZhuanjiazhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		jiankangzhuanjia.setId(new Date().getTime());
        jiankangzhuanjia.setZhuanjiamima(EncryptUtil.md5(jiankangzhuanjia.getZhuanjiamima()));
        jiankangzhuanjiaService.insert(jiankangzhuanjia);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @SysLog("新增健康专家")
    @RequestMapping("/add")
    public R add(@RequestBody JiankangzhuanjiaEntity jiankangzhuanjia, HttpServletRequest request){
        if(jiankangzhuanjiaService.selectCount(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", jiankangzhuanjia.getZhuanjiazhanghao()))>0) {
            return R.error("专家账号已存在");
        }
    	jiankangzhuanjia.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(jiankangzhuanjia);
        JiankangzhuanjiaEntity u = jiankangzhuanjiaService.selectOne(new EntityWrapper<JiankangzhuanjiaEntity>().eq("zhuanjiazhanghao", jiankangzhuanjia.getZhuanjiazhanghao()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		jiankangzhuanjia.setId(new Date().getTime());
        jiankangzhuanjia.setZhuanjiamima(EncryptUtil.md5(jiankangzhuanjia.getZhuanjiamima()));
        jiankangzhuanjiaService.insert(jiankangzhuanjia);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @SysLog("修改健康专家")
    public R update(@RequestBody JiankangzhuanjiaEntity jiankangzhuanjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiankangzhuanjia);
        JiankangzhuanjiaEntity jiankangzhuanjiaEntity = jiankangzhuanjiaService.selectById(jiankangzhuanjia.getId());
        if(StringUtils.isNotBlank(jiankangzhuanjia.getZhuanjiamima()) && !jiankangzhuanjia.getZhuanjiamima().equals(jiankangzhuanjiaEntity.getZhuanjiamima())) {
            jiankangzhuanjia.setZhuanjiamima(EncryptUtil.md5(jiankangzhuanjia.getZhuanjiamima()));
        }
        jiankangzhuanjiaService.updateById(jiankangzhuanjia);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @SysLog("删除健康专家")
    public R delete(@RequestBody Long[] ids){
        jiankangzhuanjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	









}
