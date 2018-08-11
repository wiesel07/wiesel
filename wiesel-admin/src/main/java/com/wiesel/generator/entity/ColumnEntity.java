package com.wiesel.generator.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：ColumnEntity 
* @Description 功能说明：列的属性
* <p>
* TODO
*</p>
************************************************************************
* @date        创建日期：2018年8月11日
* @author      创建人：wuj
* @version     版本号：V1.0
*<p>
***************************修订记录*************************************
* 
*   2018年8月11日   wuj     创建该类功能。
*
***********************************************************************
*</p>
*/
@Data
@Accessors(chain = true)
public class ColumnEntity {

	//列名
    private String columnName;
    //列名类型
    private String dataType;
    //列名备注
    private String comments;
    
    //属性名称(第一个字母大写)，如：user_name => UserName
    private String attrName;
    
    //属性名称(第一个字母小写)，如：user_name => userName
    private String attrname;
    
    
    //属性类型
    private String attrType;
    //auto_increment
    private String extra;
    
    //是否索引 是否主键
    private String columnKey;
    
    //是否为空
    private String isNullable;

	public String getAttrname() {
		return attrname;
	}

	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

}
