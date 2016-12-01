package org.sagesource.zookeeperdriver.service.intf;

import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;

/**
 * <p>ZK操作Service</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public interface IZkService {

	/**
	 * 根据clientKey 连接到zk服务
	 *
	 * @param clientKey
	 */
	public ZkClientWrapper lineToZookeeper(String clientKey);

}
