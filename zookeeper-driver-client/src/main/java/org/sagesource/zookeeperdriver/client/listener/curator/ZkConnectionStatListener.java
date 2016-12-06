package org.sagesource.zookeeperdriver.client.listener.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;

/**
 * <p>连接状态监听</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkConnectionStatListener implements ConnectionStateListener {
	@Override
	public void stateChanged(CuratorFramework client, ConnectionState newState) {
		if (newState == ConnectionState.LOST) {
			for (int i = 0; i < 100; i++) {
				try {
					if (client.getZookeeperClient().blockUntilConnectedOrTimedOut()) {
						client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
								.forPath("/zk-driver-tmp", "tmp".getBytes());
						break;
					}
				} catch (InterruptedException e) {
					break;
				} catch (Exception e) {
				}
			}
		}
	}
}
