package test.org.sagesource.zookeeperdriver.command;

import org.junit.Test;
import org.sagesource.zookeeperdriver.command.enums.CommandEnums;
import org.sagesource.zookeeperdriver.command.manager.SocketConnectManager;
import org.sagesource.zookeeperdriver.command.property.SocketConnectProperty;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/22
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class SocketConnectManagerTest extends BaseTest {


	@Test
	public void connectAndReadTest() throws ZkDriverPlatformException {
		SocketConnectProperty socketConnectProperty = new SocketConnectProperty();
		socketConnectProperty.setHost("zk.sagesource.com");
		socketConnectProperty.setPort(2182);
		socketConnectProperty.setCommand(CommandEnums.STAT);

		List<String> result = SocketConnectManager.connectAndRead(socketConnectProperty);
		result.forEach((line) -> System.out.println(line));
	}

}
