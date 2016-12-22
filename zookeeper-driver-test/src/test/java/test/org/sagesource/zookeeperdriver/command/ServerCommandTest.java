package test.org.sagesource.zookeeperdriver.command;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.manager.ZkClientManager;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ServerCommandTest extends BaseTest {
	private String connectionString = "zk.sagesource.com:2182";

	private String host = "zk.sagesource.com";
	private int    port = 2182;

	private ZkClientWrapper client;

	private Socket sock;

	@Before
	public void before() throws IOException {
		sock = new Socket(host, port);
	}

	@After
	public void after() {
		ZkClientManager.closeZkClient(client);
	}

	@Test
	public void statTest() throws IOException {
		read("stat");
	}

	@Test
	public void wchsTest() throws Exception {
		registerWatcher();
		Thread.sleep(1000);
		read("wchs");
	}

	@Test
	public void wchpTest() throws Exception {
		registerWatcher();
		registerWatcher();
		read("wchp");
	}

	//...........//
	private void registerWatcher() throws Exception {
		ZkClientConnectProperty zkClientConnectProperty = new ZkClientConnectProperty();
		zkClientConnectProperty.setConnectionString(connectionString);

		client = ZkClientManager.getZkClient(zkClientConnectProperty);

		// 注册观察者，当节点变动时触发
		byte[] data = client.getCuratorFramework().getData().usingWatcher(new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				System.out.println("获取 two 节点 监听器 : " + event);
			}
		}).forPath("/sage/wrapper");
	}

	//...........//
	private void read(String cmd) throws IOException {
		System.out.println("=======================" + cmd + "=======================");
		BufferedReader reader = null;
		try {
			OutputStream outputStream = sock.getOutputStream();
			// 通过Zookeeper的四字命令获取服务器的状态
			outputStream.write(cmd.getBytes());
			outputStream.flush();
			sock.shutdownOutput();

			reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} finally {
			sock.close();
			if (reader != null) {
				reader.close();
			}
		}
	}
}
