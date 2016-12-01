package org.sagesource.zookeeperdriver.service.impl;

import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.service.intf.IZkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Service
public class ZkService implements IZkService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkService.class);

	@Override
	public ZkClientWrapper lineToZookeeper(String clientKey) {
		return null;
	}
}
