package org.sagesource.zookeeperdriver.command.holder;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.sagesource.zookeeperdriver.command.dto.ZKWchs;
import org.sagesource.zookeeperdriver.command.dto.ZkStat;
import org.sagesource.zookeeperdriver.command.dto.ZkWchp;
import org.sagesource.zookeeperdriver.command.enums.CommandEnums;
import org.sagesource.zookeeperdriver.command.manager.SocketConnectManager;
import org.sagesource.zookeeperdriver.command.property.SocketConnectProperty;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * <p>命令执行Holder</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/23
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class CommandHolder {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandHolder.class);

	private CommandHolder() {
	}

	private static class SingletonHolder {
		//静态初始化器，由JVM来保证线程安全
		private static CommandHolder instance = new CommandHolder();
	}

	public static CommandHolder getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 执行stat命令
	 *
	 * @param connStr 连接信息
	 * @return
	 */
	public ZkStat execStat(String connStr) throws ZkDriverPlatformException {
		SocketConnectProperty connProperty = convertConnStr(connStr, CommandEnums.STAT);

		LOGGER.debug("connection string convert to property success! exec command:[{}]", connProperty.getCommand());

		List<String> statList = SocketConnectManager.connectAndRead(connProperty);
		ZkStat       zkStat   = ListProcessorFunctions.convertList2ZkStat(statList);

		return zkStat;
	}

	/**
	 * 执行wchs命令
	 *
	 * @param connStr
	 * @return
	 */
	public ZKWchs execWchs(String connStr) throws ZkDriverPlatformException {
		SocketConnectProperty connProperty = convertConnStr(connStr, CommandEnums.WCHS);

		LOGGER.debug("connection string convert to property success! exec command:[{}]", connProperty.getCommand());

		List<String> wchsList = SocketConnectManager.connectAndRead(connProperty);
		ZKWchs       zkWchs   = ListProcessorFunctions.convertList2ZKWchs(wchsList);

		return zkWchs;
	}

	/**
	 * 执行wchp命令
	 *
	 * @param connStr
	 * @return key-节点名称 | value-节点watcher的session
	 *
	 * @throws ZkDriverPlatformException
	 */
	public Map<String, ZkWchp> execWchp(String connStr) throws ZkDriverPlatformException {
		SocketConnectProperty connProperty = convertConnStr(connStr, CommandEnums.WCHP);

		LOGGER.debug("connection string convert to property success! exec command:[{}]", connProperty.getCommand());

		List<String>        wchpList       = SocketConnectManager.connectAndRead(connProperty);
		Map<String, ZkWchp> nodeWatcherMap = ListProcessorFunctions.convertList2ZkWchp(wchpList);

		return nodeWatcherMap;
	}

	//..............//

	/**
	 * 将connStr转换为SocketConnectProperty对象
	 *
	 * @param connStr
	 * @param command
	 * @return
	 */
	private static SocketConnectProperty convertConnStr(String connStr, CommandEnums command) {
		List<String>          list                  = Splitter.on(":").omitEmptyStrings().trimResults().splitToList(connStr);
		SocketConnectProperty socketConnectProperty = new SocketConnectProperty();

		socketConnectProperty.setHost(list.get(0));
		socketConnectProperty.setPort(Integer.parseInt(list.get(1)));
		socketConnectProperty.setCommand(command);

		return socketConnectProperty;
	}
}
