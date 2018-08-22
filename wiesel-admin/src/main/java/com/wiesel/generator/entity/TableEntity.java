package com.wiesel.generator.entity;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @ClassName 类名：TableEntity
 * @Description 功能说明：
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年8月11日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年8月11日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
@Data
@Accessors(chain = true)
public class TableEntity {

	// 表的名称
	private String tableName;

	// 表的备注
	private String comments;

	// 表的主键
	private ColumnEntity pk;

	// 表的列名(不包含主键)
	private List<ColumnEntity> columns;

	// 类名(第一个字母大写)，如：sys_user => SysUser
	private String className;

	
	// 类名(第一个字母小写)，如：sys_user => sysUser
	private String classname;

	
	// 判断是否存在
	private Boolean hasBigDecimal;
	
	// 关联的字段
	private List<ReferencedTable> listReferencedTable;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
