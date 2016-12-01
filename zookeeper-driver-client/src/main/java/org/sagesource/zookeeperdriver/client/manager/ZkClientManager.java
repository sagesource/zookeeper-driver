package org.sagesource.zookeeperdriver.client.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.sagesource.zookeeperdriver.client.listener.ZkConnectionStatListener;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;

/**
 * <p>Curator ZK操作客户端</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkClientManager {
	/**
	 * 根据client key获取zk客户端 并判断不存在的时候是否创建
	 *
	 * @param clientKey
	 * @return
	 */
	public static ZkClientWrapper getZkClient(String clientKey, String connectionString) {
		ZkClientConnectProperty zkClientConnectProperty = new ZkClientConnectProperty();
		zkClientConnectProperty.setClientKey(clientKey);
		zkClientConnectProperty.setConnectionString(connectionString);

		return getZkClient(zkClientConnectProperty);
	}

	/**
	 * 根据连接对象获取zk客户端
	 *
	 * @param zkClientConnectProperty 连接参数
	 * @return
	 */
	public static ZkClientWrapper getZkClient(ZkClientConnectProperty zkClientConnectProperty) {
		if (zkClientConnectProperty == null) throw new NullPointerException("client connect property is null");

		return obtainZkClient(zkClientConnectProperty);
	}

	/**
	 * 关闭连接
	 *
	 * @param client
	 */
	public static synchronized void closeZkClient(ZkClientWrapper client) {
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
	 * @param zkClientConnectProperty ZK客户端连接配置
	 * @return
	 */
	private static ZkClientWrapper obtainZkClient(ZkClientConnectProperty zkClientConnectProperty) {
		if (StringUtils.isEmpty(zkClientConnectProperty.getConnectionString()))
			throw new NullPointerException("connection string is empty");
		String clientKey = zkClientConnectProperty.getClientKey();

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(zkClientConnectProperty.getConnectionString())
				.retryPolicy(zkClientConnectProperty.getRetryPolicy())
				.connectionTimeoutMs(zkClientConnectProperty.getConnectionTimeoutMs())
				.sessionTimeoutMs(zkClientConnectProperty.getSessionTimeoutMs())
				.build();

		// 注册监听器
		client.getConnectionStateListenable().addListener(new ZkConnectionStatListener());

		client.start();

		if (StringUtils.isEmpty(clientKey)) {
			clientKey = String.valueOf(System.currentTimeMillis());
		}
		ZkClientWrapper zkClientWrapper = new ZkClientWrapper();
		zkClientWrapper.setClientKey(clientKey);
		zkClientWrapper.setCuratorFramework(client);

		return zkClientWrapper;
	}
}
