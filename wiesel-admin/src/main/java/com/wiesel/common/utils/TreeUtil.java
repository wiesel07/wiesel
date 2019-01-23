package com.wiesel.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

/**
 *
 * @ClassName 类名：TreeUtil
 * @Description 功能说明：树型工具类
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
@UtilityClass
public class TreeUtil {

	private final static String PARENT_ID = "";

	/**
	 * 判断两个父ID是否相同
	 * 
	 * @param p1
	 * @param p2
	 * @return
	 */
	private boolean isEqualsParentId(Object p1, Object p2) {
		if (p1 != null && p2 != null) {
			return p1.equals(p2);
		} else if (p1 == null && p2 == null) {
			return true;
		} else if (p1 == null && p2 != null) {
			if (PARENT_ID.equals(p2.toString())) {
				return true;
			} else {
				return false;
			}
		} else if (p1 != null && p2 == null) {
			if (PARENT_ID.equals(p1.toString())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 根据父节点的ID获取所有子节点，该方法顶级节点必须为空
	 * 
	 * @param list     分类表
	 * @param parentId 传入的父节点ID
	 * @return String
	 */
	public  List<TreeNode> getChildTreeNodes(List<TreeNode> list, Object parentId) {
		List<TreeNode> returnList = new ArrayList<TreeNode>();
		if (list != null && list.size() > 0) {
			for (Iterator<TreeNode> iterator = list.iterator(); iterator.hasNext();) {
				TreeNode t = (TreeNode) iterator.next();
				// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
				if (isEqualsParentId(t.getNodeParentId(), parentId)) {
					recursionFn(list, t);
					returnList.add(t);
				}
			}
		}
		return returnList;
	}

	/**
	 * 根据父节点的ID获取所有子节点，该方法顶级节点可以不为空,非树直接返回
	 * 
	 * @param list 分类表
	 * @return String
	 */
	public List<TreeNode> getChildTreeNodes(List<TreeNode> list) {
		if (list != null && list.size() > 0) {
			List<TreeNode> topList = new ArrayList<>();
			List<TreeNode> subList = new ArrayList<>();

			Map<String, String> idMap = new HashMap<>();

			for (Iterator<TreeNode> iterator = list.iterator(); iterator.hasNext();) {
				// 归并所有list的id集合
				TreeNode t = (TreeNode) iterator.next();
				idMap.put(t.getNodeId().toString(), t.getNodeId().toString());
			}

			for (Iterator<TreeNode> iterator = list.iterator(); iterator.hasNext();) {
				// 获取最顶级的list
				TreeNode t = (TreeNode) iterator.next();
				if (t.getNodeParentId() == null || StrUtil.isEmpty(t.getNodeParentId().toString())) {
					topList.add(t);
				} else {
					String id = idMap.get(t.getNodeParentId().toString());
					if (StrUtil.isEmpty(id)) {
						topList.add(t);
					} else {
						subList.add(t);
					}
				}
			}

			if (topList != null && topList.size() > 0 && subList != null && subList.size() > 0) {
				List<TreeNode> resultList = new ArrayList<>();
				for (TreeNode t : topList) {
					// 将儿子级别的list归并到顶级中
					List<TreeNode> subOneList = new ArrayList<>();

					for (TreeNode sub : subList) {
						//  一、根据传入的某个父节点ID,遍历该父节点的所有子节点
						if (isEqualsParentId(sub.getNodeParentId(), t.getNodeId())) {
							recursionFn(subList, sub);
							subOneList.add(sub);
						}
					}
					t.setNodeChildren(subOneList);

					resultList.add(t);
				}
				return resultList;
			} else {
				return list;
			}
		}
		return list;
	}

	/**
	 * 递归列表
	 * 
	 * @param list
	 * @param t
	 */
	private void recursionFn(List<TreeNode> list, TreeNode t) {
		// 得到子节点列表
		List<TreeNode> childList = getChildList(list, t);
		if (childList!=null && childList.size()>0) {
			t.setNodeChildren(childList);
			for (TreeNode tChild : childList) {
				// 判断是否有子节点
				if (hasChild(list, tChild)) {
					Iterator<TreeNode> it = childList.iterator();
					while (it.hasNext()) {
						TreeNode n = (TreeNode) it.next();
						recursionFn(list, n);
					}
				}
			}
		}
	}

	// 得到子节点列表
	private List<TreeNode> getChildList(List<TreeNode> list, TreeNode t) {

		List<TreeNode> tlist = new ArrayList<TreeNode>();
		Iterator<TreeNode> it = list.iterator();
		while (it.hasNext()) {
			TreeNode n = (TreeNode) it.next();
			if (isEqualsParentId(n.getNodeParentId(), t.getNodeId())) {
				tlist.add(n);
			}
		}
		return tlist;
	}


	// 判断是否有子节点
	private boolean hasChild(List<TreeNode> list, TreeNode t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}

}
