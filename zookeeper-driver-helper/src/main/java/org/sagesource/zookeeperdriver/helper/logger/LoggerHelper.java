package org.sagesource.zookeeperdriver.helper.logger;

/**
 * <p>日志帮助类</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class LoggerHelper {

	public static String unknownException(String logDesc) {
		StringBuilder builder = new StringBuilder("[unknown_exception]");
		builder.append(logDesc);
		return builder.toString();
	}
}
