package org.sagesource.zookeeperdriver.service.dto;

import org.apache.zookeeper.data.Stat;

/**
 * <p>ZKnode 节点DTO</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkNodeDto {
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
