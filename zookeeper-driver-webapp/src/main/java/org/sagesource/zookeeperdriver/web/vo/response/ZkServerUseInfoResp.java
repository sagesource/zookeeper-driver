package org.sagesource.zookeeperdriver.web.vo.response;

/**
 * <p>ZK 可用节点信息Resp</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkServerUseInfoResp {
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 集群机器列表
	 */
	private String  servers;
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
