package org.sagesource.zookeeperdriver.web.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * <p>Base Resp对象</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@ApiModel("响应对象")
public class BaseResp<T> {
	/**
	 * 返回错误码
	 */
	@ApiModelProperty("返回码")
	private int    code;
	/**
	 * 返回描述
	 */
	@ApiModelProperty("返回描述")
	private String message;
	/**
	 * 返回信息
	 */
	@ApiModelProperty("返回内容")
	private T      response;
	/**
	 * 返回上下文
	 */
	@ApiModelProperty("响应上下文信息")
	private RespContext respcontext = new RespContext();

	@ApiModel("响应上下文对象")
	public static class RespContext {
		@ApiModelProperty("请求接收时间")
		private long reqTime;
		@ApiModelProperty("请求响应时间")
		private long respTime;

		public long getReqTime() {
			return reqTime;
		}

		public void setReqTime(long reqTime) {
			this.reqTime = reqTime;
		}

		public long getRespTime() {
			return respTime;
		}

		public void setRespTime(long respTime) {
			this.respTime = respTime;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public RespContext getRespcontext() {
		return respcontext;
	}

	public void setRespcontext(RespContext respcontext) {
		this.respcontext = respcontext;
	}
}
