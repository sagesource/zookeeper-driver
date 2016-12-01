package org.sagesource.zookeeperdriver.client.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.sagesource.zookeeperdriver.client.listener.ConnectionStatListener;
import org.sagesource.zookeeperdriver.client.property.ClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ClientWrapper;

/**
 * <p>Curator ZK操作客户端</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientManager {
	/**
	 * 根据client key获取zk客户端 并判断不存在的时候是否创建
	 *
	 * @param clientKey
	 * @return
	 */
	public static ClientWrapper getZkClient(String clientKey, String connectionString) {
		ClientConnectProperty clientConnectProperty = new ClientConnectProperty();
		clientConnectProperty.setClientKey(clientKey);
		clientConnectProperty.setConnectionString(connectionString);

		return getZkClient(clientConnectProperty);
	}

	/**
	 * 根据连接对象获取zk客户端
	 *
	 * @param clientConnectProperty 连接参数
	 * @return
	 */
	public static ClientWrapper getZkClient(ClientConnectProperty clientConnectProperty) {
		if (clientConnectProperty == null) throw new NullPointerException("client connect property is null");

		return obtainZkClient(clientConnectProperty);
	}

	/**
	 * 关闭连接
	 *
	 * @param client
	 */
	public static synchronized void closeZkClient(ClientWrapper client) {
		if (client != null) {
			CuratorFramework realClient = client.getCuratorFramework();
			if (realClient != null && CuratorFrameworkState.STARTED.equals(realClient.getState())) {
				realClient.close();
			}
		}
	}

	//........................................................................................//

	/**
	 * 创建ZK客户端
	 *
	 * @param clientConnectProperty ZK客户端连接配置
	 * @return
	 */
	private static ClientWrapper obtainZkClient(ClientConnectProperty clientConnectProperty) {
		if (StringUtils.isEmpty(clientConnectProperty.getConnectionString()))
			throw new NullPointerException("connection string is empty");
		String clientKey = clientConnectProperty.getClientKey();

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(clientConnectProperty.getConnectionString())
				.retryPolicy(clientConnectProperty.getRetryPolicy())
				.connectionTimeoutMs(clientConnectProperty.getConnectionTimeoutMs())
				.sessionTimeoutMs(clientConnectProperty.getSessionTimeoutMs())
				.build();

		// 注册监听器
		client.getConnectionStateListenable().addListener(new ConnectionStatListener());

		client.start();

		if (StringUtils.isEmpty(clientKey)) {
			clientKey = String.valueOf(System.currentTimeMillis());
		}
		ClientWrapper clientWrapper = new ClientWrapper();
		clientWrapper.setClientKey(clientKey);
		clientWrapper.setCuratorFramework(client);

		return clientWrapper;
	}
}
