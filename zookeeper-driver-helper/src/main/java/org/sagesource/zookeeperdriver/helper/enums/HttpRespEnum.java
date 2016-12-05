package org.sagesource.zookeeperdriver.helper.enums;

/**
 * <p>http响应枚举</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public enum HttpRespEnum {
	R_100(100, "SUCCESS"),
	R_200(200, "Business Error"),
	R_300(300, "系统错误"),
	R_400(400, "未知错误");

	private int    code;
	private String message;

	HttpRespEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
