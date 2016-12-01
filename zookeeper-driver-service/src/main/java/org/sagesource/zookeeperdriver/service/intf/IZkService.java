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
	 * 根据serverId 连接到zk服务
	 *
	 * @param serverInfoId
	 */
	public ZkClientWrapper lineToZookeeper(int serverInfoId);

}
