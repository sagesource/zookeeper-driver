package test.org.sagesource.zookeeperdriver.client.manager;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.After;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.dto.ZNodeDto;
import org.sagesource.zookeeperdriver.client.dto.ZkDataDto;
import org.sagesource.zookeeperdriver.client.manager.ClientManager;
import org.sagesource.zookeeperdriver.client.property.ClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ClientWrapper;

import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientManagerTest {
	private String connectionString = "zk.sagesource.com:2181,zk.sagesource.com:2182,zk.sagesource.com:2183";
	private ClientWrapper client;

	@After
	public void after() {
		ClientManager.closeZkClient(client);
	}

	@Test
	public void getZkClientByOptionalTest() {
		ClientConnectProperty clientConnectProperty = new ClientConnectProperty();
		clientConnectProperty.setConnectionString(connectionString);

		client = ClientManager.getZkClient(clientConnectProperty);
		System.out.println("client===" + ReflectionToStringBuilder.toString(client));
	}

	@Test
	public void wrapperExist() throws Exception {
		client = ClientManager.getZkClient("test", connectionString);
		boolean result = client.exist("/test");
		System.out.println("====" + result + "====" + client.getSessionId());
	}

	@Test
	public void wrapperGetChildrenTest() throws Exception {
		client = ClientManager.getZkClient("test", connectionString);

		List<ZNodeDto> list = client.getChildren("/sage");

		list.forEach((dto) -> {
			System.out.println(ReflectionToStringBuilder.toString(dto));
		});
	}

	@Test
	public void wrapperReadData() throws Exception {
		client = ClientManager.getZkClient("test", connectionString);
		ZkDataDto data = client.readData("/sage/wrapper");
		System.out.println("data===" + ReflectionToStringBuilder.toString(data));
	}

	@Test
	public void wrapperCreate() throws Exception {
		client = ClientManager.getZkClient("test", connectionString);
		client.create("/sage/wrapper", "sage-wrapper".getBytes());
	}

	@Test
	public void wrapperSetNodeData() throws Exception {
		client = ClientManager.getZkClient("test", connectionString);
		client.editData("/sage/wrapper", "sage-111".getBytes(), 1);
	}

	@Test
	public void wrapperDelete() throws Exception {
		client = ClientManager.getZkClient("test", connectionString);
		client.delete("/sage", true);
	}
}
