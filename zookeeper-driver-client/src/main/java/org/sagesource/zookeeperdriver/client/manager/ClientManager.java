package org.sagesource.zookeeperdriver.client.manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.sagesource.zookeeperdriver.client.property.ClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ClientWrapper;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Curator ZK操作客户端</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientManager {
	private static Object LOCK_OBJECT = new Object();
	/**
	 * ZK客户端池
	 */
	private static ConcurrentHashMap<String, ClientWrapper> clientPool = new ConcurrentHashMap<>();

	/**
	 * 根据client key获取zk客户端,如果不存在,抛出异常
	 *
	 * @param clientKey 客户端编号
	 * @return
	 */
	public static ClientWrapper getZkClient(String clientKey) {
		if (StringUtils.isEmpty(clientKey)) throw new NullPointerException("client key is empty");

		ClientWrapper client = clientPool.get(clientKey);
		return client;
	}

	/**
	 * 根据client key获取zk客户端 并判断不存在的时候是否创建
	 * @param clientKey
	 * @param isCreate
	 * @return
	 */
	public static ClientWrapper getZkClient(String clientKey, boolean isCreate) {
		ClientConnectProperty clientConnectProperty = new ClientConnectProperty();
		clientConnectProperty.setClientKey(clientKey);

		return getZkClient(clientConnectProperty, isCreate);
	}

	/**
	 * 根据连接对象获取zk客户端,并判断不存在的时候是否创建
	 *
	 * @param clientConnectProperty 连接参数
	 * @param isCreate              是否创建
	 * @return
	 */
	public static ClientWrapper getZkClient(ClientConnectProperty clientConnectProperty, boolean isCreate) {
		if (clientConnectProperty == null) throw new NullPointerException("client connect property is null");

		ClientWrapper client = clientPool.get(clientConnectProperty.getClientKey());
		if (client != null) return client;
		if (client == null && isCreate) {
			// 虽然说可能是多线程创建不同的zk连接 但是也要预防多线程创建同一个zk连接
			synchronized (LOCK_OBJECT) {
				client = clientPool.get(clientConnectProperty.getClientKey());
				if (client != null) return client;

				return obtainZkClient(clientConnectProperty);
			}
		}

		return null;
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

		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(clientConnectProperty.getConnectionString())
				.retryPolicy(clientConnectProperty.getRetryPolicy())
				.connectionTimeoutMs(clientConnectProperty.getConnectionTimeoutMs())
				.sessionTimeoutMs(clientConnectProperty.getSessionTimeoutMs())
				.build();

		String clientKey = clientConnectProperty.getClientKey();
		if (StringUtils.isEmpty(clientKey)) {
			clientKey = String.valueOf(System.currentTimeMillis());
		}

		ClientWrapper clientWrapper = new ClientWrapper();
		clientWrapper.setClientKey(clientKey);
		clientWrapper.setCuratorFramework(client);

		clientPool.put(clientKey, clientWrapper);
		return clientWrapper;
	}
}
