package org.sagesource.zookeeperdriver.client.dto;

import org.apache.zookeeper.data.Stat;

/**
 * <p>ZK 节点对象</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZNodeDto {
	/**
	 * 父节点信息
	 */
	private String  parentPath;
	/**
	 * 节点名称
	 */
	private String  name;
	/**
	 * 是否还有叶子节点
	 */
	private boolean hasChildren;
	/**
	 * 节点状态
	 */
	private Stat    stat;

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}
}
