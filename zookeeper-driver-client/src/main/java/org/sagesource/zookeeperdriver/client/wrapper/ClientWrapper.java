package org.sagesource.zookeeperdriver.client.wrapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.zookeeper.data.Stat;
import org.sagesource.zookeeperdriver.client.dto.ZNodeDto;
import org.sagesource.zookeeperdriver.client.dto.ZkDataDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Curator ZK操作客户端的包装</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/11/20
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientWrapper {
	/**
	 * 客户端编号(可以指定也可手工传入)
	 */
	private String           clientKey;
	/**
	 * 客户端对象
	 */
	private CuratorFramework curatorFramework;

	//..............操作方法..................//

	/**
	 * 获取节点的子节点
	 *
	 * @param path 节点
	 * @return
	 */
	public List<ZNodeDto> getChildren(String path) throws Exception {
		// 构建path
		path = builtPath(path);
		// 当前节点状态
		Stat           stat             = new Stat();
		List<String>   childrenNameList = curatorFramework.getChildren().storingStatIn(stat).forPath(path);

		List<ZNodeDto> childrenList     = new ArrayList<>();
		final String   finalPath        = path;
		// 遍历子节点 判断子节点是否为根节点
		childrenNameList.forEach((childrenName) -> {
			try {
				// 默认没有子节点
				boolean hasChildren = false;

				// 获取子节点的子节点信息
				List<String> tmpChildren = curatorFramework.getChildren().forPath(finalPath + "/" + childrenName);
				if (tmpChildren != null && tmpChildren.size() > 0) hasChildren = true;

				ZNodeDto znode = new ZNodeDto();
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
	 */
	public ZkDataDto readData(String path) throws Exception {
		path = builtPath(path);
		Stat stat = new Stat();

		byte[] data = curatorFramework.getData().storingStatIn(stat).forPath(path);

		ZkDataDto zkDataDto = new ZkDataDto();
		zkDataDto.setData(data);
		zkDataDto.setStat(stat);

		return zkDataDto;
	}

	//.......................................//

	/**
	 * 构建path路径,对一些可预估的错误进行处理
	 *
	 * @param path
	 * @return
	 */
	private String builtPath(String path) {
		path = StringUtils.isEmpty(path) ? "/" : path;
		path = StringUtils.startsWith(path, "/") ? path : "/" + path;

		return path;
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
}
