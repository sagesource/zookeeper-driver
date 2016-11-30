package org.sagesource.zookeeperdriver.client.property;

import org.apache.curator.RetryPolicy;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>ZK客户端连接配置</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientConnectProperty {
	/**
	 * 客户端编号(可以指定也可手工传入)
	 */
	private String clientKey = "";
	/**
	 * 连接字符串
	 */
	private String connectionString;
	/**
	 * 重试连接策略:
	 * <p>连接ZK服务过程中重新连接测策略.
	 * 在它的实现类ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries)中,
	 * baseSleepTimeMs参数代表两次连接的等待时间,maxRetries参数表示最大的尝试连接次数</p>
	 */
	private RetryPolicy retryPolicy         = new ExponentialBackoffRetry(1000, 3);
	/**
	 * 连接超时时间
	 */
	private int         connectionTimeoutMs = 10000;
	/**
	 * Session超时时间
	 */
	private int         sessionTimeoutMs    = 100000;

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public RetryPolicy getRetryPolicy() {
		return retryPolicy;
	}

	public void setRetryPolicy(RetryPolicy retryPolicy) {
		this.retryPolicy = retryPolicy;
	}

	public int getConnectionTimeoutMs() {
		return connectionTimeoutMs;
	}

	public void setConnectionTimeoutMs(int connectionTimeoutMs) {
		this.connectionTimeoutMs = connectionTimeoutMs;
	}

	public int getSessionTimeoutMs() {
		return sessionTimeoutMs;
	}

	public void setSessionTimeoutMs(int sessionTimeoutMs) {
		this.sessionTimeoutMs = sessionTimeoutMs;
	}
}
