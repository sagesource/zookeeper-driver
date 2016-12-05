package org.sagesource.zookeeperdriver.service.impl;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.sagesource.zookeeperdriver.client.manager.ZkClientManager;
import org.sagesource.zookeeperdriver.client.pool.ClientPoolOperation;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.entity.ZkServerInfo;
import org.sagesource.zookeeperdriver.entity.ZkServerInfoExample;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.mapper.ZkServerInfoMapper;
import org.sagesource.zookeeperdriver.service.intf.IZkClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

	@Autowired
	private GenericObjectPoolConfig zkPoolConfig;

	@Override
	@Transactional(readOnly = true)
	public ZkClientWrapper lineToZookeeper(String clientKey) throws Exception {
		try {
			LOGGER.info("查询zk服务配置 client_key=[{}]", clientKey);

			ZkServerInfoExample          zkServerInfoExample = new ZkServerInfoExample();
			ZkServerInfoExample.Criteria criteria            = zkServerInfoExample.createCriteria().andClientKeyEqualTo(clientKey);
			zkServerInfoExample.or(criteria);
			List<ZkServerInfo> serverList = zkServerInfoMapper.selectByExample(zkServerInfoExample);
			if (serverList == null || serverList.size() == 0) throw new ZkDriverBusinessException("ZK服务信息不存在");
			if (serverList.size() > 1) throw new ZkDriverBusinessException("ZK信息错误,不是唯一的clientkey");

			ZkServerInfo zkServerInfo        = serverList.get(0);
			String       connectionString    = zkServerInfo.getServers();
			int          retrySleepTime      = zkServerInfo.getRetrySleepTime();
			int          retryTimes          = zkServerInfo.getRetryTimes();
			int          connectionTimeoutMs = zkServerInfo.getConnTimeout();
			int          sessionTimeoutMs    = zkServerInfo.getSessionTimeout();
			LOGGER.info("创建zk连接 参数:connectionString=[{}],retrySleepTime=[{}],retryTimes=[{}],connectionTimeoutMs=[{}],sessionTimeoutMs=[{}]",
					connectionString, retrySleepTime, retryTimes, connectionTimeoutMs, sessionTimeoutMs);

			ZkClientConnectProperty zkClientConnectProperty = new ZkClientConnectProperty();
			zkClientConnectProperty.setClientKey(clientKey);
			zkClientConnectProperty.setConnectionString(connectionString);
			zkClientConnectProperty.setRetryPolicy(new ExponentialBackoffRetry(retrySleepTime, retryTimes));
			zkClientConnectProperty.setConnectionTimeoutMs(connectionTimeoutMs);
			zkClientConnectProperty.setSessionTimeoutMs(sessionTimeoutMs);

			ClientPoolOperation.initClientPool(zkClientConnectProperty, zkPoolConfig);
			ZkClientWrapper client = ClientPoolOperation.getClientFromPool(clientKey);
			LOGGER.info("创建zk连接客户端成功 client_key=[{}]", client.getClientKey());
			return client;
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("创建zk连接客户端失败! client_key=[{}]", clientKey, e);
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
