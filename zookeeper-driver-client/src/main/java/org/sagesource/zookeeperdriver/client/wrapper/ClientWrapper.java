package org.sagesource.zookeeperdriver.client.wrapper;

import org.apache.curator.framework.CuratorFramework;

/**
 * <p>Curator ZK操作客户端的包装</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientWrapper {
	/**
	 * 客户端编号(可以指定也可手工传入)
	 */
	private String clientKey;
	/**
	 * 客户端对象
	 */
	private CuratorFramework curatorFramework;

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}

	public void setCuratorFramework(CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}
}
