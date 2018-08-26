package com.wiesel.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
//import com.baomidou.mybatisplus.enums.IEnum; um;
/**
 *
 * @ClassName 类名：SexEnum
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月26日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月26日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements  BaseEnum<SexEnum,String>{
	MALE(1, "男"), FEMALE(2, "女");

	private int code;

	private String name;

	SexEnum(final int code, final String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return String.valueOf(this.code);
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return this.name;
	}


	static Map<String,SexEnum> enumMap=new HashMap<String, SexEnum>();  
    static {  
        for(SexEnum type:SexEnum.values()){  
            enumMap.put(String.valueOf(type.getValue()), type);  
        }  
    }  
	public static SexEnum parse(String value) {
		return enumMap.get(value);
	}
}
