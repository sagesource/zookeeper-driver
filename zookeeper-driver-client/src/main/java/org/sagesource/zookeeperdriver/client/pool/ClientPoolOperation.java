package org.sagesource.zookeeperdriver.client.pool;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverClientPoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>连接池操作</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientPoolOperation {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientPoolOperation.class);

	private static Object                                                        LOCK_OBJECT = new Object();
	private static ConcurrentHashMap<String, GenericObjectPool<ZkClientWrapper>> factoryMap  = new ConcurrentHashMap<>();

	/**
	 * 初始化连接池
	 *
	 * @param property   连接配置
	 * @param poolConfig 连接池配置
	 */
	public static void initClientPool(ZkClientConnectProperty property, GenericObjectPoolConfig poolConfig) {
		String propertyStr = ReflectionToStringBuilder.toString(property);
		LOGGER.debug("init client pool,property:[{}]", propertyStr);

		GenericObjectPool<ZkClientWrapper> existFactory = factoryMap.get(property.getClientKey());
		if (existFactory == null) {
			synchronized (LOCK_OBJECT) {
				existFactory = factoryMap.get(property.getClientKey());
				if (existFactory == null) {
					GenericObjectPool<ZkClientWrapper> clientPool = new GenericObjectPool<>(new ClientPoolFactory(property), poolConfig);
					LOGGER.debug("init client pool success,property:[{}]", propertyStr);
					factoryMap.put(property.getClientKey(), clientPool);
					return;
				}
			}
		}
		LOGGER.debug("client pool already exist,property:[{}]", propertyStr);
	}

	/**
	 * 从连接池中获取一个连接
	 *
	 * @param clientKey
	 * @return
	 */
	public static ZkClientWrapper getClientFromPool(String clientKey) throws Exception {
		LOGGER.debug("get client from pool, client_key:[{}]", clientKey);
		if (factoryMap.get(clientKey) == null) throw new ZkDriverClientPoolException("连接池不存在");

		ZkClientWrapper client = factoryMap.get(clientKey).borrowObject();
		return client;
	}

	/**
	 * 将连接返回给连接池
	 *
	 * @param client
	 */
	public static void returnClientToPool(ZkClientWrapper client) throws ZkDriverClientPoolException {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.debug("return client to pool,client_key:[{}]", client.getClientKey());

		if (factoryMap.get(client.getClientKey()) == null) throw new ZkDriverClientPoolException("连接池不存在");

		factoryMap.get(client.getClientKey()).returnObject(client);
	}

	/**
	 * 销毁连接池
	 */
	public static synchronized void destory() {
		factoryMap.entrySet().forEach((entry) -> destory(entry.getKey()));
	}

	public static synchronized void destory(String clientKey) {
		if (factoryMap.get(clientKey) != null) {
			factoryMap.get(clientKey).clear();
			factoryMap.remove(clientKey);
		}
	}
}
