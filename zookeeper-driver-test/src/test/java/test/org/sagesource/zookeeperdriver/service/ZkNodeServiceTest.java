package test.org.sagesource.zookeeperdriver.service;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.service.dto.ZkDataDto;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeDto;
import org.sagesource.zookeeperdriver.service.intf.IZkClientService;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkNodeServiceTest extends BaseTest {

	@Autowired
	private IZkNodeService   zkNodeService;
	@Autowired
	private IZkClientService zkClientService;

	private ZkClientWrapper client;

	@Before
	public void before() throws Exception {
		client = zkClientService.lineToZookeeper(1);
		Assert.assertNotNull(client);
	}

	@Test
	public void after() {
		zkClientService.closeZkClient(client);
	}

	@Test
	public void checkNodeExistTest() throws Exception {
		zkNodeService.checkNodeExist(client, "/");
	}

	@Test
	public void findChildrenNodeTest() throws Exception {
		List<ZkNodeDto> list = zkNodeService.findChildrenNode(client, "/xueqi");
		list.forEach((dto) -> {
			System.out.println(ReflectionToStringBuilder.toString(dto));
		});
	}

	@Test
	public void readNodeDataTest() throws Exception {
		ZkDataDto data = zkNodeService.readNodeData(client, "/xueqi");
		Assert.assertNotNull(data);

		System.out.println(ReflectionToStringBuilder.toString(data));
	}

	@Test
	public void createNodeTest() throws Exception {
		zkNodeService.createNode(client, "/xueqi/unit", "unit");
	}

	@Test
	public void editNodeDataTest() throws Exception {
		zkNodeService.editNodeData(client, "/xueqi/unit", "unit222");
	}

	@Test
	public void deleteNodeTest() throws Exception {
		zkNodeService.deleteNode(client, "/xueqi/unit");
	}
}
