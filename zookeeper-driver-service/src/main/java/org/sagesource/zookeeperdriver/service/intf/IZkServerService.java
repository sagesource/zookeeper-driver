package org.sagesource.zookeeperdriver.service.intf;

import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.service.dto.ZkServerInfoDto;

import java.util.List;

/**
 * <p>ZK服务端操作Service</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public interface IZkServerService {

	/**
	 * 正在使用的服务列表
	 *
	 * @return
	 *
	 * @throws ZkDriverBusinessException
	 * @throws ZkDriverPlatformException
	 */
	List<ZkServerInfoDto> useServerList() throws ZkDriverBusinessException, ZkDriverPlatformException;

}
