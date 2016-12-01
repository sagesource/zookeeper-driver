package org.sagesource.zookeeperdriver.service.dto;

import org.apache.zookeeper.data.Stat;

/**
 * <p>ZKnode 节点数据DTO</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkDataDto {
	/**
	 * 节点数据
	 */
	private String data;
	/**
	 * 节点状态
	 */
	private Stat   stat;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}
}
