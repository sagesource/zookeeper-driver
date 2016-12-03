package test.org.sagesource.zookeeperdriver.client;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.After;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.dto.ZkNode;
import org.sagesource.zookeeperdriver.client.dto.ZkData;
import org.sagesource.zookeeperdriver.client.manager.ZkClientManager;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;

import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkClientManagerTest {
	private String connectionString = "zk.sagesource.com:2181,zk.sagesource.com:2182,zk.sagesource.com:2183";
	private ZkClientWrapper client;

	@After
	public void after() {
		ZkClientManager.closeZkClient(client);
	}

	@Test
	public void getZkClientByOptionalTest() {
		ZkClientConnectProperty zkClientConnectProperty = new ZkClientConnectProperty();
		zkClientConnectProperty.setConnectionString(connectionString);

		client = ZkClientManager.getZkClient(zkClientConnectProperty);
		System.out.println("client===" + ReflectionToStringBuilder.toString(client));
	}

	@Test
	public void wrapperExist() throws Exception {
		client = ZkClientManager.getZkClient("test", connectionString);
		boolean result = client.exist("/test");
		System.out.println("====" + result + "====" + client.getSessionId());
	}

	@Test
	public void wrapperGetChildrenTest() throws Exception {
		client = ZkClientManager.getZkClient("test", connectionString);

		List<ZkNode> list = client.getChildren("/sage");

		list.forEach((dto) -> {
			System.out.println(ReflectionToStringBuilder.toString(dto));
		});
	}

	@Test
	public void wrapperReadData() throws Exception {
		client = ZkClientManager.getZkClient("test", connectionString);
		ZkData data = client.readData("/sage/wrapper");
		System.out.println("data===" + ReflectionToStringBuilder.toString(data));
	}

	@Test
	public void wrapperCreate() throws Exception {
		client = ZkClientManager.getZkClient("test", connectionString);
		client.create("/sage/wrapper", "sage-wrapper".getBytes());
	}

	@Test
	public void wrapperSetNodeData() throws Exception {
		client = ZkClientManager.getZkClient("test", connectionString);
		client.editData("/sage/wrapper", "sage-111".getBytes(), 1);
	}

	@Test
	public void wrapperDelete() throws Exception {
		client = ZkClientManager.getZkClient("test", connectionString);
		client.delete("/sage", true);
	}
}
