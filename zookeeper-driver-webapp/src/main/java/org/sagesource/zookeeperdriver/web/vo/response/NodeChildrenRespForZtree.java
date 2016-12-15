package org.sagesource.zookeeperdriver.web.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * <p>node子节点信息Resp For Ztree</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/15
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@ApiModel("node子节点信息-Ztree")
public class NodeChildrenRespForZtree {
	/**
	 * 节点id
	 */
	@ApiModelProperty("节点id-节点全路径名称")
	private String id;
	/**
	 * 节点名称
	 */
	@ApiModelProperty("节点名称-显示用")
	private String name;

	/**
	 * 节点是否打开
	 */
	@ApiModelProperty("节点是否打开")
	private boolean open;

	/**
	 * 子节点信息
	 */
	@ApiModelProperty("子节点信息")
	private List<ZtreeChildren> children;

	@ApiModel("node子节点信息详细对象")
	public static class ZtreeChildren {
		/**
		 * 节点id
		 */
		@ApiModelProperty("节点id-节点全路径名称")
		private String  id;
		/**
		 * 子节点名称
		 */
		@ApiModelProperty("节点名称-显示用")
		private String  name;
		/**
		 * 子节点是否为父节点标志位
		 */
		@ApiModelProperty("子节点是否为父节点标志位")
		private boolean isParent;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public boolean getIsParent() {
			return isParent;
		}

		public void setIsParent(boolean parent) {
			isParent = parent;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public List<ZtreeChildren> getChildren() {
		return children;
	}

	public void setChildren(List<ZtreeChildren> children) {
		this.children = children;
	}
}
