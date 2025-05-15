package com.cl.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 健康目标
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
@TableName("jiankangmubiao")
public class JiankangmubiaoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JiankangmubiaoEntity() {
		
	}
	
	public JiankangmubiaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 目标体重
	 */
					
	private String mubiaotizhong;
	
	/**
	 * 目标心率
	 */
					
	private String mubiaoxinlv;
	
	/**
	 * 目标血压
	 */
					
	private String mubiaoxueya;
	
	/**
	 * 计划
	 */
					
	private String jihua;
	
	/**
	 * 用户账号
	 */
					
	private String yonghuzhanghao;
	
	/**
	 * 用户姓名
	 */
					
	private String yonghuxingming;
	

	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：目标体重
	 */
	public void setMubiaotizhong(String mubiaotizhong) {
		this.mubiaotizhong = mubiaotizhong;
	}
	/**
	 * 获取：目标体重
	 */
	public String getMubiaotizhong() {
		return mubiaotizhong;
	}
	/**
	 * 设置：目标心率
	 */
	public void setMubiaoxinlv(String mubiaoxinlv) {
		this.mubiaoxinlv = mubiaoxinlv;
	}
	/**
	 * 获取：目标心率
	 */
	public String getMubiaoxinlv() {
		return mubiaoxinlv;
	}
	/**
	 * 设置：目标血压
	 */
	public void setMubiaoxueya(String mubiaoxueya) {
		this.mubiaoxueya = mubiaoxueya;
	}
	/**
	 * 获取：目标血压
	 */
	public String getMubiaoxueya() {
		return mubiaoxueya;
	}
	/**
	 * 设置：计划
	 */
	public void setJihua(String jihua) {
		this.jihua = jihua;
	}
	/**
	 * 获取：计划
	 */
	public String getJihua() {
		return jihua;
	}
	/**
	 * 设置：用户账号
	 */
	public void setYonghuzhanghao(String yonghuzhanghao) {
		this.yonghuzhanghao = yonghuzhanghao;
	}
	/**
	 * 获取：用户账号
	 */
	public String getYonghuzhanghao() {
		return yonghuzhanghao;
	}
	/**
	 * 设置：用户姓名
	 */
	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getYonghuxingming() {
		return yonghuxingming;
	}

}
