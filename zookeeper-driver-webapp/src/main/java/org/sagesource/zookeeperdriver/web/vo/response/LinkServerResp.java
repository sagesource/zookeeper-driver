package org.sagesource.zookeeperdriver.web.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>连接sever响应 resp</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@ApiModel("连接sever响应")
public class LinkServerResp {

	/**
	 * 客户端Key
	 */
	@ApiModelProperty("客户端Key")
	private String  clientKey;
	/**
	 * 连接结果
	 */
	@ApiModelProperty("连接结果")
	private boolean isLink;

	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public boolean isLink() {
		return isLink;
	}

	public void setLink(boolean link) {
		isLink = link;
	}
}
