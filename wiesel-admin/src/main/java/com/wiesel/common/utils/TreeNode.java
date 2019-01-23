package com.wiesel.common.utils;

import java.util.List;

/**
 *
 * @ClassName 类名：TreeObject
 * @Description 功能说明：树型结构接口
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2019年1月23日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录***************************************
 * 
 *          2019年1月23日 wuj 创建该类功能。
 *
 ************************************************************************
 *          </p>
 */
public interface TreeNode {
	
	Object getNodeId();

	void setNodeId(Object id);

	Object getNodeParentId();

	void setNodeParentId(Object parentId);

	String getNodeName();

	void setNodeName(String name);

	List<?> getNodeChildren();

	void setNodeChildren(List<?> children);
}
