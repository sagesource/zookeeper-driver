package org.sagesource.zookeeperdriver.web.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>节点watcher信息resp</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2017/2/8
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@ApiModel("节点watcher信息")
public class NodeWatcherInfoResp {

	@ApiModelProperty("节点名称")
	private String node;
	@ApiModelProperty("watcher总数")
	private long   watcherTotal;

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public long getWatcherTotal() {
		return watcherTotal;
	}

	public void setWatcherTotal(long watcherTotal) {
		this.watcherTotal = watcherTotal;
	}
}
