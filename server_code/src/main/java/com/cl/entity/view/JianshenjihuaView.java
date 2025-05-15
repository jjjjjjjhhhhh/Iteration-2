package com.cl.entity.view;

import com.cl.entity.JianshenjihuaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 健身计划
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-05-08 20:32:23
 */
@TableName("jianshenjihua")
public class JianshenjihuaView  extends JianshenjihuaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public JianshenjihuaView(){
	}
 
 	public JianshenjihuaView(JianshenjihuaEntity jianshenjihuaEntity){
 	try {
			BeanUtils.copyProperties(this, jianshenjihuaEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
