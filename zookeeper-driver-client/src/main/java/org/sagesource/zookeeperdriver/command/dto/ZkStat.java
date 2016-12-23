package org.sagesource.zookeeperdriver.command.dto;

/**
 * <p>zk服务状态dto</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkStat {
	/**
	 * 最小延迟时间
	 */
	private long minLatency;
	/**
	 * 平均延迟时间
	 */
	private long avgLatency;
	/**
	 * 最大延迟时间
	 */
	private long maxLatency;
	/**
	 * 接收数据
	 */
	private long received;
	/**
	 * 发送数据
	 */
	private long sent;

	private int    connections;
	private int    outstanding;
	private String zxid;
	private String mode;
	private long   nodeCount;

	public long getMinLatency() {
		return minLatency;
	}

	public void setMinLatency(long minLatency) {
		this.minLatency = minLatency;
	}

	public long getAvgLatency() {
		return avgLatency;
	}

	public void setAvgLatency(long avgLatency) {
		this.avgLatency = avgLatency;
	}

	public long getMaxLatency() {
		return maxLatency;
	}

	public void setMaxLatency(long maxLatency) {
		this.maxLatency = maxLatency;
	}

	public long getReceived() {
		return received;
	}

	public void setReceived(long received) {
		this.received = received;
	}

	public long getSent() {
		return sent;
	}

	public void setSent(long sent) {
		this.sent = sent;
	}

	public int getConnections() {
		return connections;
	}

	public void setConnections(int connections) {
		this.connections = connections;
	}

	public int getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(int outstanding) {
		this.outstanding = outstanding;
	}

	public String getZxid() {
		return zxid;
	}

	public void setZxid(String zxid) {
		this.zxid = zxid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public long getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(long nodeCount) {
		this.nodeCount = nodeCount;
	}
}
