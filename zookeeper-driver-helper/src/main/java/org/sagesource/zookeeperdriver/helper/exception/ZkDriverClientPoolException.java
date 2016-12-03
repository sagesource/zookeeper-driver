package org.sagesource.zookeeperdriver.helper.exception;

/**
 * <p>连接池异常</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkDriverClientPoolException extends Exception {
	private String message;

	public ZkDriverClientPoolException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
