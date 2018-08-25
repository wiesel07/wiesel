package wiesel.common.base.entity;

/**
 *
 * @ClassName 类名：ZtreeNode
 * @Description 功能说明：Ztree节点属性
 *              <p>
 *              TODO
 *              </p>
 ************************************************************************
 * @date 创建日期：2018年7月30日
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ***************************          修订记录*************************************
 * 
 *          2018年7月30日 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */
public class ZtreeNode {
	/**
	 * 节点ID
	 */
	private String id;
	
	
	/**
	 * 节点父ID
	 */
	private String pid;
	
	/**
	 * 节点名称
	 */
	private String name;
	
	
	private Boolean checked;


	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
//	/**
//	 * 节点状态
//	 */
//	private Integer state;
//	
//	/**
//	 * 节点自定义图标的 URL 路径
//	 */
//	private String icon;
//	/**
//	 * 父节点自定义折叠时图标的 URL 路径
//	 */
//	private String iconClose;
//	
//	/**
//	 * 父节点自定义展开时图标的 URL 路径
//	 */
//	private String iconOpen;
//	
//	
//	
//	/**
//	 * 记录 treeNode 节点的 展开 / 折叠 状态。
//	 */
//	private boolean open;
//
//	/**
//	 * 记录 treeNode 节点是否为父节点。
//	 */
//	private boolean isParent;
//	
//	
//	/**
//	 * 节点链接的目标 URL
//	 */
//	private String url;
//	
//	/**
//	 * 节点是否被选中 true false
//	 */
//	private boolean checked = false;
//	
//	/**
//	 * 节点的子节点
//	 */
//	private List<ZtreeNode> children = new ArrayList<ZtreeNode>();

	
	
}
