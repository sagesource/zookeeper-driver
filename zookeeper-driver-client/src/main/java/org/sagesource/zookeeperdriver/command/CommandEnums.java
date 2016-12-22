package org.sagesource.zookeeperdriver.command;

/**
 * <p>ZK 四字管理命令</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public enum CommandEnums {
	STAT("stat"),
	WCHS("wchs"),
	WCHP("wchp");

	private String value;

	CommandEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
