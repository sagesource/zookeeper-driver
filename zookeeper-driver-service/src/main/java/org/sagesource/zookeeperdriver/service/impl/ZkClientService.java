package org.sagesource.zookeeperdriver.service.impl;

import org.apache.curator.retry.ExponentialBackoffRetry;
import org.sagesource.zookeeperdriver.client.manager.ZkClientManager;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.entity.ZkServerInfo;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.mapper.ZkServerInfoMapper;
import org.sagesource.zookeeperdriver.service.intf.IZkClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Service
public class ZkClientService implements IZkClientService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkClientService.class);

	@Autowired
	private ZkServerInfoMapper zkServerInfoMapper;

	@Override
	@Transactional(readOnly = true)
	public ZkClientWrapper lineToZookeeper(int serverInfoId) throws Exception {
		try {
			LOGGER.info("查询zk服务配置 zk_server_info_id=[{}]", serverInfoId);

			ZkServerInfo zkServerInfo = zkServerInfoMapper.selectByPrimaryKey(serverInfoId);
			if (zkServerInfo == null) throw new ZkDriverBusinessException("ZK服务信息不存在");

			String clientKey           = zkServerInfo.getClientKey();
			String connectionString    = zkServerInfo.getServers();
			int    retrySleepTime      = zkServerInfo.getRetrySleepTime();
			int    retryTimes          = zkServerInfo.getRetryTimes();
			int    connectionTimeoutMs = zkServerInfo.getConnTimeout();
			int    sessionTimeoutMs    = zkServerInfo.getSessionTimeout();
			LOGGER.info("创建zk连接 参数:connectionString=[{}],retrySleepTime=[{}],retryTimes=[{}],connectionTimeoutMs=[{}],sessionTimeoutMs=[{}]",
					connectionString, retrySleepTime, retryTimes, connectionTimeoutMs, sessionTimeoutMs);

			ZkClientConnectProperty zkClientConnectProperty = new ZkClientConnectProperty();
			zkClientConnectProperty.setClientKey(clientKey);
			zkClientConnectProperty.setConnectionString(connectionString);
			zkClientConnectProperty.setRetryPolicy(new ExponentialBackoffRetry(retrySleepTime, retryTimes));
			zkClientConnectProperty.setConnectionTimeoutMs(connectionTimeoutMs);
			zkClientConnectProperty.setSessionTimeoutMs(sessionTimeoutMs);

			ZkClientWrapper client = ZkClientManager.getZkClient(zkClientConnectProperty);
			LOGGER.info("创建zk连接客户端成功 client_key=[{}]", client.getClientKey());
			return client;
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("创建zk连接客户端失败! serverInfoId=[{}]", serverInfoId, e);
			throw e;
		}
	}

	@Override
	public void closeZkClient(ZkClientWrapper zkClient) {
		try {
			LOGGER.info("关闭zk连接 client_key=[{}]", zkClient.getClientKey());
			ZkClientManager.closeZkClient(zkClient);

			LOGGER.info("关闭zk连接成功 client_key=[{}]", zkClient.getClientKey());
		} catch (Exception e) {
			LOGGER.error("关闭zk连接客户端失败! client_key=[{}]", zkClient.getClientKey(), e);
			throw e;
		}
	}
}
