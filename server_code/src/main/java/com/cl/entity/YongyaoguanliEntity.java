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
 * 用药管理
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
@TableName("yongyaoguanli")
public class YongyaoguanliEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public YongyaoguanliEntity() {
		
	}
	
	public YongyaoguanliEntity(T t) {
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
	 * 药品名称
	 */
					
	private String yaopinmingcheng;
	
	/**
	 * 规格
	 */
					
	private String guige;
	
	/**
	 * 服用剂量
	 */
					
	private String fuyongjiliang;
	
	/**
	 * 每日服用次数
	 */
					
	private String meirifuyongcishu;
	
	/**
	 * 起始服用时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date qishifuyongshijian;
	
	/**
	 * 预计结束时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat 		
	private Date yujijieshushijian;
	
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
	 * 设置：药品名称
	 */
	public void setYaopinmingcheng(String yaopinmingcheng) {
		this.yaopinmingcheng = yaopinmingcheng;
	}
	/**
	 * 获取：药品名称
	 */
	public String getYaopinmingcheng() {
		return yaopinmingcheng;
	}
	/**
	 * 设置：规格
	 */
	public void setGuige(String guige) {
		this.guige = guige;
	}
	/**
	 * 获取：规格
	 */
	public String getGuige() {
		return guige;
	}
	/**
	 * 设置：服用剂量
	 */
	public void setFuyongjiliang(String fuyongjiliang) {
		this.fuyongjiliang = fuyongjiliang;
	}
	/**
	 * 获取：服用剂量
	 */
	public String getFuyongjiliang() {
		return fuyongjiliang;
	}
	/**
	 * 设置：每日服用次数
	 */
	public void setMeirifuyongcishu(String meirifuyongcishu) {
		this.meirifuyongcishu = meirifuyongcishu;
	}
	/**
	 * 获取：每日服用次数
	 */
	public String getMeirifuyongcishu() {
		return meirifuyongcishu;
	}
	/**
	 * 设置：起始服用时间
	 */
	public void setQishifuyongshijian(Date qishifuyongshijian) {
		this.qishifuyongshijian = qishifuyongshijian;
	}
	/**
	 * 获取：起始服用时间
	 */
	public Date getQishifuyongshijian() {
		return qishifuyongshijian;
	}
	/**
	 * 设置：预计结束时间
	 */
	public void setYujijieshushijian(Date yujijieshushijian) {
		this.yujijieshushijian = yujijieshushijian;
	}
	/**
	 * 获取：预计结束时间
	 */
	public Date getYujijieshushijian() {
		return yujijieshushijian;
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
