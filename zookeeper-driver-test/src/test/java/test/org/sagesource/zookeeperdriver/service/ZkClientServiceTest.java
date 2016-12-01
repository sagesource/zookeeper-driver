package test.org.sagesource.zookeeperdriver.service;

import org.junit.Assert;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.service.intf.IZkClientService;
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
public class ZkClientServiceTest extends BaseTest{

	@Autowired
	private IZkClientService zkClientService;

	@Test
	public void lineToZookeeperTest() throws Exception {
		ZkClientWrapper client = zkClientService.lineToZookeeper(1);
		Assert.assertNotNull(client);

		zkClientService.closeZkClient(client);
	}
}
