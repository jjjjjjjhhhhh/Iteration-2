package com.cl.entity.view;

import com.cl.entity.OnlineMessageEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.cl.utils.EncryptUtil;
 

/**
 * 在线留言
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2025-05-08 20:32:24
 */
@TableName("online_message")
public class OnlineMessageView  extends OnlineMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public OnlineMessageView(){
	}
 
 	public OnlineMessageView(OnlineMessageEntity onlineMessageEntity){
 	try {
			BeanUtils.copyProperties(this, onlineMessageEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}



}
