package com.wiesel.generator.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/** 
*
* @ClassName   类名：ReferencedTable 
* @Description 功能说明：表数据
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
public class ReferencedTable {
	//表名
		private String tableName;
		//列名
		private String columnName;
		//关联表名
		private String referencedTableName;
		//关联列名
		private String referencedColumnName;
		
		//表名java
		private String tableNameJava;
		//列名java
		private String columnNameJava;
		//关联表名java
		private String referencedTableNameJava;
		//关联列名java
		private String referencedColumnNameJava;
}
