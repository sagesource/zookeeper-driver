package org.sagesource.zookeeperdriver.web.vo.base;

/**
 * <p>Base Resp对象</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class BaseResp<T> {
	/**
	 * 返回错误码
	 */
	private int         code;
	/**
	 * 返回信息
	 */
	private T      response;
	/**
	 * 返回上下文
	 */
	private RespContext respcontext;

	public static class RespContext {
		private long respTime;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
