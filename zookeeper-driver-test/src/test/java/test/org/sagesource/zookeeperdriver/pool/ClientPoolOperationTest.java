package test.org.sagesource.zookeeperdriver.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import org.sagesource.zookeeperdriver.client.pool.ClientPoolOperation;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientPoolOperationTest {

	@Test
	public void initPoolTest() throws InterruptedException {
		ZkClientConnectProperty zkClientConnectProperty = new ZkClientConnectProperty();
		zkClientConnectProperty.setClientKey("111122221111");
		zkClientConnectProperty.setConnectionString("zk.sagesource.com:2181,zk.sagesource.com:2182,zk.sagesource.com:2183");
		zkClientConnectProperty.setConnectionTimeoutMs(10000);
		zkClientConnectProperty.setSessionTimeoutMs(10000);
		zkClientConnectProperty.setRetryPolicy(new ExponentialBackoffRetry(1000, 3));

		/** 连接池的配置 */
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();

		/** 下面的配置均为默认配置,默认配置的参数可以在BaseObjectPoolConfig中找到 */
		poolConfig.setMaxTotal(8); // 池中的最大连接数
		poolConfig.setMinIdle(0); // 最少的空闲连接数
		poolConfig.setMaxIdle(8); // 最多的空闲连接数
		poolConfig.setMaxWaitMillis(-1); // 当连接池资源耗尽时,调用者最大阻塞的时间,超时时抛出异常 单位:毫秒数
		poolConfig.setLifo(true); // 连接池存放池化对象方式,true放在空闲队列最前面,false放在空闲队列最后
		poolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L); // 连接空闲的最小时间,达到此值后空闲连接可能会被移除,默认即为30分钟

		poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞,默认为true

		Thread t1 = new Thread(new InitRunnable(zkClientConnectProperty, poolConfig));
		Thread t2 = new Thread(new InitRunnable(zkClientConnectProperty, poolConfig));
		Thread t3 = new Thread(new InitRunnable(zkClientConnectProperty, poolConfig));
		Thread t4 = new Thread(new InitRunnable(zkClientConnectProperty, poolConfig));
		Thread t5 = new Thread(new InitRunnable(zkClientConnectProperty, poolConfig));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}

	static class InitRunnable implements Runnable {
		private ZkClientConnectProperty zkClientConnectProperty;
		private GenericObjectPoolConfig poolConfig;

		public InitRunnable(ZkClientConnectProperty zkClientConnectProperty, GenericObjectPoolConfig poolConfig) {
			this.zkClientConnectProperty = zkClientConnectProperty;
			this.poolConfig = poolConfig;
		}

		@Override
		public void run() {
			ClientPoolOperation.initClientPool(zkClientConnectProperty, poolConfig);
		}
	}
}
