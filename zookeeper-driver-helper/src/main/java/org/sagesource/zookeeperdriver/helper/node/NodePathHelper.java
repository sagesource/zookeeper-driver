package org.sagesource.zookeeperdriver.helper.node;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>节点路径Helper工具类</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/2
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class NodePathHelper {
	private static Pattern nodePattern = Pattern.compile("^\\/$|^\\/([^\\/]+\\/?)+$");

	/**
	 * 校验节点格式是否正确
	 *
	 * @param path
	 * @return
	 */
	public static boolean checkPath(String path) {
		if (StringUtils.equals(path, "/")) return true;
		if (StringUtils.isEmpty(path)) return false;
		if (StringUtils.endsWith(path, "/")) return false;

		Matcher matcher = nodePattern.matcher(path);
		int     count   = 0;
		while (matcher.find()) {
			count++;
		}

		return count == 1;
	}


}
