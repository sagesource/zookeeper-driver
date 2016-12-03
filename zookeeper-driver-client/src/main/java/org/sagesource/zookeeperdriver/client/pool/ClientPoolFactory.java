package org.sagesource.zookeeperdriver.client.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.sagesource.zookeeperdriver.client.manager.ZkClientManager;
import org.sagesource.zookeeperdriver.client.property.ZkClientConnectProperty;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;

/**
 * <p>连接池Factory</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientPoolFactory extends BasePooledObjectFactory<ZkClientWrapper> {
	private ZkClientConnectProperty zkClientConnectProperty;

	public ClientPoolFactory(ZkClientConnectProperty zkClientConnectProperty) {
		this.zkClientConnectProperty = zkClientConnectProperty;
	}

	@Override
	public ZkClientWrapper create() throws Exception {
		return ZkClientManager.getZkClient(zkClientConnectProperty);
	}

	@Override
	public PooledObject<ZkClientWrapper> wrap(ZkClientWrapper client) {
		return new DefaultPooledObject<>(client);
	}

	@Override
	public void destroyObject(PooledObject<ZkClientWrapper> p) throws Exception {
		ZkClientManager.closeZkClient(p.getObject());
		super.destroyObject(p);
	}
}
