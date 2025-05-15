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
 * 健康食谱
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
@TableName("jiankangshipu")
public class JiankangshipuEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JiankangshipuEntity() {
		
	}
	
	public JiankangshipuEntity(T t) {
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
	 * 食谱名称
	 */
					
	private String shipumingcheng;
	
	/**
	 * 封面
	 */
					
	private String fengmian;
	
	/**
	 * 所需食材
	 */
					
	private String suoxushicai;
	
	/**
	 * 食谱功效
	 */
					
	private String shipugongxiao;
	
	/**
	 * 适合人群
	 */
					
	private String shiherenqun;
	
	/**
	 * 制作方法
	 */
					
	private String zhizuofangfa;
	
	/**
	 * 发布时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date fabushijian;
	
	/**
	 * 详情简介
	 */
					
	private String xiangqingjianjie;
	
	/**
	 * 赞
	 */
					
	private Integer thumbsupNumber;
	
	/**
	 * 踩
	 */
					
	private Integer crazilyNumber;
	
	/**
	 * 收藏数
	 */
					
	private Integer storeupNumber;
	
	/**
	 * 评论数
	 */
					
	private Integer discussNumber;
	
	/**
	 * 最近点击时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date clicktime;
	

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
	 * 设置：食谱名称
	 */
	public void setShipumingcheng(String shipumingcheng) {
		this.shipumingcheng = shipumingcheng;
	}
	/**
	 * 获取：食谱名称
	 */
	public String getShipumingcheng() {
		return shipumingcheng;
	}
	/**
	 * 设置：封面
	 */
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
	/**
	 * 设置：所需食材
	 */
	public void setSuoxushicai(String suoxushicai) {
		this.suoxushicai = suoxushicai;
	}
	/**
	 * 获取：所需食材
	 */
	public String getSuoxushicai() {
		return suoxushicai;
	}
	/**
	 * 设置：食谱功效
	 */
	public void setShipugongxiao(String shipugongxiao) {
		this.shipugongxiao = shipugongxiao;
	}
	/**
	 * 获取：食谱功效
	 */
	public String getShipugongxiao() {
		return shipugongxiao;
	}
	/**
	 * 设置：适合人群
	 */
	public void setShiherenqun(String shiherenqun) {
		this.shiherenqun = shiherenqun;
	}
	/**
	 * 获取：适合人群
	 */
	public String getShiherenqun() {
		return shiherenqun;
	}
	/**
	 * 设置：制作方法
	 */
	public void setZhizuofangfa(String zhizuofangfa) {
		this.zhizuofangfa = zhizuofangfa;
	}
	/**
	 * 获取：制作方法
	 */
	public String getZhizuofangfa() {
		return zhizuofangfa;
	}
	/**
	 * 设置：发布时间
	 */
	public void setFabushijian(Date fabushijian) {
		this.fabushijian = fabushijian;
	}
	/**
	 * 获取：发布时间
	 */
	public Date getFabushijian() {
		return fabushijian;
	}
	/**
	 * 设置：详情简介
	 */
	public void setXiangqingjianjie(String xiangqingjianjie) {
		this.xiangqingjianjie = xiangqingjianjie;
	}
	/**
	 * 获取：详情简介
	 */
	public String getXiangqingjianjie() {
		return xiangqingjianjie;
	}
	/**
	 * 设置：赞
	 */
	public void setThumbsupNumber(Integer thumbsupNumber) {
		this.thumbsupNumber = thumbsupNumber;
	}
	/**
	 * 获取：赞
	 */
	public Integer getThumbsupNumber() {
		return thumbsupNumber;
	}
	/**
	 * 设置：踩
	 */
	public void setCrazilyNumber(Integer crazilyNumber) {
		this.crazilyNumber = crazilyNumber;
	}
	/**
	 * 获取：踩
	 */
	public Integer getCrazilyNumber() {
		return crazilyNumber;
	}
	/**
	 * 设置：收藏数
	 */
	public void setStoreupNumber(Integer storeupNumber) {
		this.storeupNumber = storeupNumber;
	}
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupNumber() {
		return storeupNumber;
	}
	/**
	 * 设置：评论数
	 */
	public void setDiscussNumber(Integer discussNumber) {
		this.discussNumber = discussNumber;
	}
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussNumber() {
		return discussNumber;
	}
	/**
	 * 设置：最近点击时间
	 */
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
	}

}
