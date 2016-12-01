package test.org.sagesource.zookeeperdriver.service;

import org.junit.Assert;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.service.intf.IZkService;
import org.springframework.beans.factory.annotation.Autowired;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkServiceTest extends BaseTest{

	@Autowired
	private IZkService zkService;

	@Test
	public void lineToZookeeperTest() {
		ZkClientWrapper client = zkService.lineToZookeeper(1);
		Assert.assertNotNull(client);
	}
}
