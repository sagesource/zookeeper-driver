package org.sagesource.zookeeperdriver.service.intf;

import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;

/**
 * <p>ZK客户端操作Service</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public interface IZkClientService {

	/**
	 * 根据serverId 连接到zk服务
	 *
	 * @param clientKey
	 */
	ZkClientWrapper lineToZookeeper(String clientKey) throws ZkDriverBusinessException, ZkDriverPlatformException;

	/**
	 * 关闭客户端连接
	 *
	 * @param zkClient 客户端Wrapper对象
	 */
	void closeZkClient(ZkClientWrapper zkClient);
}
