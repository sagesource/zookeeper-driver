package org.sagesource.zookeeperdriver.command.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>zk watcher sessions dto</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/30
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkWchp {
	/**
	 * 节点名称
	 */
	private String node;
	/**
	 * watcher session
	 */
	private List<String> sessions = new ArrayList<>();

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<String> getSessions() {
		return sessions;
	}

	public void setSessions(List<String> sessions) {
		this.sessions = sessions;
	}
}
