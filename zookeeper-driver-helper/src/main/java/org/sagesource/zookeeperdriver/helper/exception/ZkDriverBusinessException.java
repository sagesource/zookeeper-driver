package org.sagesource.zookeeperdriver.helper.exception;

/**
 * <p>业务层面异常</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkDriverBusinessException extends Exception {
	private String message;

	public ZkDriverBusinessException(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
