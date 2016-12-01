package org.sagesource.zookeeperdriver.client.dto;

import org.apache.zookeeper.data.Stat;

/**
 * <p>ZK 节点数据对象</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkData {
	/**
	 * 节点数据
	 */
	private byte[] data;
	/**
	 * 节点状态
	 */
	private Stat   stat;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}
}
