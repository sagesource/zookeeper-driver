package test.org.sagesource.zookeeperdriver.service;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.sagesource.zookeeperdriver.service.dto.ZkServerInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkServerService;
import org.springframework.beans.factory.annotation.Autowired;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkServerServiceTest extends BaseTest {

	@Autowired
	private IZkServerService zkServerService;

	@Test
	public void useServerListTest() throws Exception {
		List<ZkServerInfoDto> list = zkServerService.useServerList();
		list.forEach(dto -> System.out.println(ReflectionToStringBuilder.toString(dto)));
	}

}
