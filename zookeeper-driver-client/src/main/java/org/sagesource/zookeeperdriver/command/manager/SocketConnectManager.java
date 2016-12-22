package org.sagesource.zookeeperdriver.command.manager;

import org.sagesource.zookeeperdriver.command.property.SocketConnectProperty;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>服务器Socket连接管理</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class SocketConnectManager {
	private static final Logger LOGGER = LoggerFactory.getLogger(SocketConnectManager.class);

	/**
	 * 连接并读取数据
	 *
	 * @param socketConnectProperty
	 * @return
	 */
	public static List<String> connectAndRead(SocketConnectProperty socketConnectProperty) throws ZkDriverPlatformException {
		Socket         socket = null;
		BufferedReader reader = null;
		try {
			socket = new Socket(socketConnectProperty.getHost(), socketConnectProperty.getPort());

			String cmd = socketConnectProperty.getCommand().getValue();
			LOGGER.debug("exec 4 words command:[{}]", cmd);
			OutputStream outputStream = socket.getOutputStream();
			outputStream.write(cmd.getBytes());
			outputStream.flush();
			socket.shutdownOutput();

			List<String> result = new ArrayList<>();
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				result.add(line);
			}
			return result;
		} catch (IOException e) {
			throw new ZkDriverPlatformException("connect zk and read exception", e);
		} finally {
			try {
				if (reader != null) reader.close();
				if (socket != null) socket.close();
			} catch (IOException e) {
				LOGGER.error("close zookeeper server socket exception:", e);
			}
		}
	}

}
