package test.org.sagesource.zookeeperdriver.command;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.Test;
import org.sagesource.zookeeperdriver.command.dto.ZKWchs;
import org.sagesource.zookeeperdriver.command.dto.ZkStat;
import org.sagesource.zookeeperdriver.command.holder.CommandHolder;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import test.org.sagesource.zookeeperdriver.base.BaseTest;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/23
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class CommandHolderTest extends BaseTest {
	private String connStr = "zk.sagesource.com:2182";

	@Test
	public void execStatTest() throws ZkDriverPlatformException {
		ZkStat result = CommandHolder.execStat(connStr);
		System.out.println(ReflectionToStringBuilder.toString(result));
	}

	@Test
	public void execWchsTest() throws ZkDriverPlatformException {
		ZKWchs result = CommandHolder.execWchs(connStr);
		System.out.println(ReflectionToStringBuilder.toString(result));
	}

}
