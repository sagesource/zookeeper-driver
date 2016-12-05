package org.sagesource.zookeeperdriver.helper.exception;

/**
 * <p>连接池异常</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkDriverPlatformException extends Exception {
	private String message;

	public ZkDriverPlatformException(String message) {
		super(message);
		this.message = message;
	}

	public ZkDriverPlatformException(String message, Throwable e) {
		super(message, e);
	}

	public ZkDriverPlatformException(Throwable e) {
		super(e);
	}

	@Override
	public String getMessage() {
		return message;
	}
}
