package org.sagesource.zookeeperdriver.service.dto;

/**
 * <p>ZKnode 节点 Watcher DTO</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2017/2/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkNodeWatcherInfoDto {
	/**
	 * 连接地址
	 */
	private String connStr;
	/**
	 * 节点路径
	 */
	private String path;
	/**
	 * watcher总数
	 */
	private long   watcherTotal;

	public String getConnStr() {
		return connStr;
	}

	public void setConnStr(String connStr) {
		this.connStr = connStr;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getWatcherTotal() {
		return watcherTotal;
	}

	public void setWatcherTotal(long watcherTotal) {
		this.watcherTotal = watcherTotal;
	}
}
