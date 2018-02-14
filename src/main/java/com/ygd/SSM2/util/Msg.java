/**   
* @Description: 通用消息返回类 
* @author ygd  
* @date 2018年2月5日  
*/ 
package com.ygd.SSM2.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Msg {
	
	//状态码，100成功，200失败
	private int code;
	
	//信息提示
	private String msg;
	
	//返回数据
	private Map<String, Object> extend = new HashMap<String, Object>();
	
	public static Msg success(){
		Msg result = new Msg();
		
		result.setCode(100);
		result.setMsg("处理成功！");
		
		return result;
	}
	
	public static Msg fail(){
		Msg result = new Msg();
		
		result.setCode(200);
		result.setMsg("处理失败！");
		
		return result;
	}
	
	public Msg add(String key,Object value){
		
		this.getExtend().put(key, value);
		
		return this;
	}
}
