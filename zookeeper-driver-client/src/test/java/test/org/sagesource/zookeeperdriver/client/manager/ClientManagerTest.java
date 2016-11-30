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
	public void getZkClientByKeyTest() {
		client = ClientManager.getZkClient("test");
		System.out.println("======" + client + "======");
	}

	@Test
	public void getZkClientByOptionalTest() {
		ClientConnectProperty clientConnectProperty = new ClientConnectProperty();
		clientConnectProperty.setConnectionString(connectionString);

		client = ClientManager.getZkClient(clientConnectProperty, true);
		System.out.println("client===" + ReflectionToStringBuilder.toString(client));

		ClientWrapper queryClient = ClientManager.getZkClient(client.getClientKey());
		System.out.println("query===" + ReflectionToStringBuilder.toString(queryClient));
	}

	@Test
	public void wrapperExist() throws Exception {
		client = ClientManager.getZkClient("test", connectionString, true);
		boolean result = client.exist("/test");
		System.out.println("====" + result + "====");
	}

	@Test
	public void wrapperGetChildrenTest() throws Exception {
		client = ClientManager.getZkClient("test", connectionString, true);

		List<ZNodeDto> list = client.getChildren("/sage");

		list.forEach((dto) -> {
			System.out.println(ReflectionToStringBuilder.toString(dto));
		});
	}

	@Test
	public void wrapperReadData() throws Exception {
		client = ClientManager.getZkClient("test", connectionString, true);
		ZkDataDto data = client.readData("/sage/wrapper");
		System.out.println("data===" + ReflectionToStringBuilder.toString(data));
	}

	@Test
	public void wrapperCreate() throws Exception {
		client = ClientManager.getZkClient("test", connectionString, true);
		client.create("/sage/wrapper", "sage-wrapper".getBytes());
	}

	@Test
	public void wrapperSetNodeData() throws Exception {
		client = ClientManager.getZkClient("test", connectionString, true);
		client.editData("/sage/wrapper", "sage-111".getBytes(), 1);
	}

	@Test
	public void wrapperDelete() throws Exception {
		client = ClientManager.getZkClient("test", connectionString, true);
		client.delete("/sage", true);
	}
}
