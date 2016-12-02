package org.sagesource.zookeeperdriver.client.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.sagesource.zookeeperdriver.client.dto.ZkNode;
import org.sagesource.zookeeperdriver.client.dto.ZkData;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.node.NodePathHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Curator ZK操作客户端的包装</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkClientWrapper {
	/**
	 * 客户端编号(可以指定也可手工传入)
	 */
	private String                clientKey;
	/**
	 * 客户端对象
	 */
	private CuratorFramework      curatorFramework;
	/**
	 * 连接session_id
	 */
	private long                  sessionId;
	/**
	 * 连接状态
	 */
	private CuratorFrameworkState state;

	//..............操作方法..................//

	/**
	 * 检查节点是否存在
	 *
	 * @param path 待检查的节点路径
	 * @return true:已存在 false:不存在
	 *
	 * @throws Exception
	 */
	public boolean exist(String path) throws Exception {
		path = builtPath(path);
		Stat result = curatorFramework.checkExists().forPath(path);
		return result != null;
	}

	/**
	 * 获取节点的子节点
	 *
	 * @param path 节点
	 * @return
	 *
	 * @throws Exception
	 */
	public List<ZkNode> getChildren(String path) throws Exception {
		// 构建path
		path = builtPath(path);
		// 当前节点状态
		Stat         stat             = new Stat();
		List<String> childrenNameList = curatorFramework.getChildren().storingStatIn(stat).forPath(path);

		List<ZkNode> childrenList = new ArrayList<>();
		final String finalPath    = path;
		// 遍历子节点 判断子节点是否为根节点
		childrenNameList.forEach((childrenName) -> {
			try {
				// 默认没有子节点
				boolean hasChildren = false;

				// 获取子节点的子节点信息
				List<String> tmpChildren = curatorFramework.getChildren().forPath(joinPath(finalPath, childrenName));
				if (tmpChildren != null && tmpChildren.size() > 0) hasChildren = true;

				ZkNode znode = new ZkNode();
				znode.setStat(stat);
				znode.setHasChildren(hasChildren);
				znode.setName(childrenName);
				znode.setParentPath(finalPath);
				childrenList.add(znode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return childrenList;
	}

	/**
	 * 获取节点的数据
	 *
	 * @param path 节点
	 * @return
	 *
	 * @throws Exception
	 */
	public ZkData readData(String path) throws Exception {
		path = builtPath(path);
		Stat stat = new Stat();

		byte[] data = curatorFramework.getData().storingStatIn(stat).forPath(path);

		ZkData zkData = new ZkData();
		zkData.setData(data);
		zkData.setStat(stat);

		return zkData;
	}

	/**
	 * 创建节点数据
	 *
	 * @param path 要创建的节点
	 * @param data 节点的数据
	 * @throws Exception
	 */
	public void create(String path, byte[] data) throws Exception {
		path = builtPath(path);
		curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, data);
	}

	/**
	 * 更新节点数据
	 *
	 * @param path       待更新的节点
	 * @param data       更新数据
	 * @param oldVersion 旧的数据版本
	 * @throws Exception
	 */
	public void editData(String path, byte[] data, int oldVersion) throws Exception {
		path = builtPath(path);
		curatorFramework.setData().withVersion(oldVersion).forPath(path, data);
	}

	/**
	 * 删除节点
	 *
	 * @param path        节点
	 * @param delChildren 递归删除子节点,true-递归删除
	 * @throws Exception
	 */
	public void delete(String path, boolean delChildren) throws Exception {
		path = builtPath(path);
		if (delChildren)
			curatorFramework.delete().deletingChildrenIfNeeded().forPath(path);
		else
			curatorFramework.delete().guaranteed().forPath(path);
	}

	//.......................................//

	/**
	 * 构建path路径,对一些可预估的错误进行处理
	 *
	 * @param path
	 * @return
	 */
	private String builtPath(String path) throws ZkDriverBusinessException {
		path = StringUtils.isEmpty(path) ? "/" : path;
		path = StringUtils.startsWith(path, "/") ? path : "/" + path;

		if (!NodePathHelper.checkPath(path)) throw new ZkDriverBusinessException("node路径错误");

		return path;
	}

	/**
	 * 连接节点
	 *
	 * @param path
	 * @param children
	 * @return
	 */
	private String joinPath(String path, String children) {
		if ("/".equals(path)) return path + children;
		return path + "/" + children;
	}

	//.......................................//
	public String getClientKey() {
		return clientKey;
	}

	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

	public CuratorFramework getCuratorFramework() {
		return curatorFramework;
	}

	public void setCuratorFramework(CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}

	public long getSessionId() {
		try {
			sessionId = curatorFramework.getZookeeperClient().getZooKeeper().getSessionId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.sessionId;
	}

	public CuratorFrameworkState getState() {
		return curatorFramework.getState();
	}
}
