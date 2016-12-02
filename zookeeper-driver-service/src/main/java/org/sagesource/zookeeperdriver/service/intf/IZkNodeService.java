package org.sagesource.zookeeperdriver.service.intf;

import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.service.dto.ZkDataDto;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeDto;

import java.util.List;

/**
 * <p>ZK节点操作Service</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public interface IZkNodeService {

	/**
	 * 判断节点是否存在
	 *
	 * @param client zk客户端
	 * @param path   待检查节点
	 * @return true:存在 false:不存在
	 */
	boolean checkNodeExist(ZkClientWrapper client, String path) throws Exception;

	/**
	 * 获得节点的子节点列表
	 *
	 * @param client     zk连接客户端
	 * @param parentPath 当前节点
	 * @return
	 */
	List<ZkNodeDto> findChildrenNode(ZkClientWrapper client, String parentPath) throws Exception;

	/**
	 * 获取节点的数据
	 *
	 * @param client zk连接客户端
	 * @param path   当前节点
	 * @return
	 */
	ZkDataDto readNodeData(ZkClientWrapper client, String path) throws Exception;

	/**
	 * 创建节点
	 *
	 * @param client
	 * @param path   节点
	 * @param data   数据
	 */
	void createNode(ZkClientWrapper client, String path, String data) throws Exception;
}
