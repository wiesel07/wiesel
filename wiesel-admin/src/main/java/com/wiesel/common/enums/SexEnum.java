package com.wiesel.common.enums;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
//import com.baomidou.mybatisplus.enums.IEnum;

import com.baomidou.mybatisplus.core.enums.IEnum;
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
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum implements IEnum<Integer>{
	MAN(1, "男"), WOMAN(2, "女");

	private int value;

	private String desc;

	SexEnum(final int value, final String desc) {
		this.value = value;
		this.desc = desc;
	}

	
	
	public Integer getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
	@JsonValue
	public String getDesc() {
		return this.desc;
	}
}
