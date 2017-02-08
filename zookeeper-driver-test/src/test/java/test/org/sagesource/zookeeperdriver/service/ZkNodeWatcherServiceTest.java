package test.org.sagesource.zookeeperdriver.service;

import org.junit.Test;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeWatcherInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeWatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2017/2/8
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkNodeWatcherServiceTest extends BaseTest {

	@Autowired
	private IZkNodeWatcherService zkNodeWatcherService;

	@Test
	public void findWatcherInfoTest() throws Exception {
		List<ZkNodeWatcherInfoDto> result = zkNodeWatcherService.findWatcherInfo("120102198765", "/");
		System.out.println(result);
	}

}
