package test.org.sagesource.zookeeperdriver.client.manager;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.manager.ClientManager;
import org.sagesource.zookeeperdriver.client.property.ClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ClientWrapper;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientManagerTest {

	@Test
	public void getZkClientByKeyTest() {
		ClientWrapper client = ClientManager.getZkClient("test");
		System.out.println("======" + client + "======");
	}

	@Test
	public void getZkClientByOptionalTest() {
		ClientConnectProperty clientConnectProperty = new ClientConnectProperty();
		clientConnectProperty.setConnectionString("zk.sagesource.com:2181,zk.sagesource.com:2182,zk.sagesource.com:2183");

		ClientWrapper client = ClientManager.getZkClient(clientConnectProperty, true);
		System.out.println("client===" + ReflectionToStringBuilder.toString(client));

		ClientWrapper queryClient = ClientManager.getZkClient(client.getClientKey());
		System.out.println("query===" + ReflectionToStringBuilder.toString(queryClient));
	}
}
