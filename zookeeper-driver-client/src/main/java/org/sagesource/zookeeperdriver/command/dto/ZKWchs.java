package org.sagesource.zookeeperdriver.command.dto;

/**
 * <p>zk watcher汇总 dto</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZKWchs {

	/**
	 * 总 watch数
	 */
	private int totalWatches;

	public int getTotalWatches() {
		return totalWatches;
	}

	public void setTotalWatches(int totalWatches) {
		this.totalWatches = totalWatches;
	}
}
