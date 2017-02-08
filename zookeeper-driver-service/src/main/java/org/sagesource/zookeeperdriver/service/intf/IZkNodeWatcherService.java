package org.sagesource.zookeeperdriver.service.intf;

import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeWatcherInfoDto;

import java.util.List;

/**
 * <p>ZK节点Watcher操作Service</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2017/2/8
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public interface IZkNodeWatcherService {

	/**
	 * 获取节点的watcher总数
	 *
	 * @param clientKey
	 * @param path      节点名称
	 * @return 由于集群情况, 每一个集群的节点watcher汇总成一个对象返回
	 *
	 * @throws ZkDriverBusinessException
	 * @throws ZkDriverPlatformException
	 */
	List<ZkNodeWatcherInfoDto> findWatcherInfo(String clientKey, String path) throws Exception;
}
