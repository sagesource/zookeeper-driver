package org.sagesource.zookeeperdriver.service.dto;

import java.util.Date;

/**
 * <p>ZK服务端信息DTO</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkServerInfoDto {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 集群机器列表
	 */
	private String  servers;
	/**
	 * 启用状态
	 */
	private String  isuse;
	/**
	 * 重试休眠时间
	 */
	private Integer retrySleepTime;
	/**
	 * 重试次数
	 */
	private Integer retryTimes;
	/**
	 * 连接超时时间
	 */
	private Integer connTimeout;
	/**
	 * session超时时间
	 */
	private Integer sessionTimeout;
	/**
	 * 创建时间
	 */
	private Date    createTime;
	/**
	 * 更新时间
	 */
	private Date    updateTime;
	/**
	 * Server描述
	 */
	private String  serverDesc;
	/**
	 * Client Key
	 */
	private String  clientKey;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServers() {
		return servers;
	}

	public void setServers(String servers) {
		this.servers = servers;
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse;
	}

	public Integer getRetrySleepTime() {
		return retrySleepTime;
	}

	public void setRetrySleepTime(Integer retrySleepTime) {
		this.retrySleepTime = retrySleepTime;
	}

	public Integer getRetryTimes() {
		return retryTimes;
	}

	public void setRetryTimes(Integer retryTimes) {
		this.retryTimes = retryTimes;
	}

	public Integer getConnTimeout() {
		return connTimeout;
	}

	public void setConnTimeout(Integer connTimeout) {
		this.connTimeout = connTimeout;
	}

	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getServerDesc() {
		return serverDesc;
	}

	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
	}

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}
}
