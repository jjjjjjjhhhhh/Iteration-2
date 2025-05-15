package com.cl.entity.view;

import com.cl.entity.PopupRemindEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 吃药提醒
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@TableName("popup_remind")
public class PopupRemindView  extends PopupRemindEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public PopupRemindView(){
	}
 
 	public PopupRemindView(PopupRemindEntity popupRemindEntity){
 	try {
			BeanUtils.copyProperties(this, popupRemindEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
