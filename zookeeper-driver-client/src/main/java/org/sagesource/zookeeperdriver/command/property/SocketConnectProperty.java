package org.sagesource.zookeeperdriver.command.property;

import org.sagesource.zookeeperdriver.command.enums.CommandEnums;

/**
 * <p>服务器Socket连接配置</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class SocketConnectProperty {

	/**
	 * 连接地址
	 */
	private String       host;
	/**
	 * 连接端口
	 */
	private int          port;
	/**
	 * 连接执行命令
	 */
	private CommandEnums command;


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public CommandEnums getCommand() {
		return command;
	}

	public void setCommand(CommandEnums command) {
		this.command = command;
	}
}
