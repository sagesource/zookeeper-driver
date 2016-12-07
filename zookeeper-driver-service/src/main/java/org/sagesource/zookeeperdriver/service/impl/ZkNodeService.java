package org.sagesource.zookeeperdriver.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.sagesource.zookeeperdriver.client.dto.ZkData;
import org.sagesource.zookeeperdriver.client.dto.ZkNode;
import org.sagesource.zookeeperdriver.client.pool.ClientPoolInvoke;
import org.sagesource.zookeeperdriver.helper.Constants;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.helper.logger.LoggerHelper;
import org.sagesource.zookeeperdriver.service.dto.ZkDataDto;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeDto;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/1
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Service
public class ZkNodeService implements IZkNodeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkNodeService.class);

	@Override
	public boolean checkNodeExist(String clientKey, String path) throws ZkDriverPlatformException, ZkDriverBusinessException {
		LOGGER.info("判断节点是否存在 client_key=[{}],parentPath=[{}]", clientKey, path);

		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");

			return baseCheckNodeExist(clientKey, path);
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (ZkDriverPlatformException e) {
			LOGGER.error(LoggerHelper.platformException("判断节点是否存在失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("判断节点是否存在失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw new ZkDriverPlatformException(e);
		}
	}

	@Override
	public List<ZkNodeDto> findChildrenNode(String clientKey, String parentPath) throws ZkDriverBusinessException, ZkDriverPlatformException {
		LOGGER.info("获得节点的子节点列表 client_key=[{}],parentPath=[{}]", clientKey, parentPath);
		List<ZkNodeDto> result = new ArrayList<>();

		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");
			if (!baseCheckNodeExist(clientKey, parentPath)) throw new ZkDriverBusinessException("节点不存在");

			List<ZkNode> nodeList = ClientPoolInvoke.invoke(clientKey, client -> client.getChildren(parentPath));
			nodeList.forEach((node) -> {
				ZkNodeDto dto = new ZkNodeDto();
				BeanUtils.copyProperties(node, dto);
				result.add(dto);
			});

			return result;
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (ZkDriverPlatformException e) {
			LOGGER.error(LoggerHelper.platformException("获得节点的子节点列表失败 client_key=[{}],parentPath=[{}]"), clientKey, parentPath, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("获得节点的子节点列表失败 client_key=[{}],parentPath=[{}]"), clientKey, parentPath, e);
			throw new ZkDriverPlatformException(e);
		}
	}

	@Override
	public ZkDataDto readNodeData(String clientKey, String path) throws ZkDriverBusinessException, ZkDriverPlatformException {
		LOGGER.info("获取节点的数据 client_key=[{}],path=[{}]", clientKey, path);
		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");
			if (!baseCheckNodeExist(clientKey, path)) throw new ZkDriverBusinessException("节点不存在");

			ZkData    data = ClientPoolInvoke.invoke(clientKey, client -> client.readData(path));
			ZkDataDto dto  = new ZkDataDto();
			dto.setData(new String(data.getData(), Constants.CHARSET_UTF_8));
			dto.setStat(data.getStat());
			dto.setVersion(data.getStat().getVersion());

			return dto;
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (ZkDriverPlatformException e) {
			LOGGER.error(LoggerHelper.platformException("获取节点的数据失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("获取节点的数据失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw new ZkDriverPlatformException(e);
		}
	}

	@Override
	public void createNode(String clientKey, String path, String data) throws ZkDriverBusinessException, ZkDriverPlatformException {
		LOGGER.info("创建节点 client_key=[{}],path=[{}],data=[{}]", clientKey, path, data);
		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");
			if (StringUtils.isEmpty(data)) throw new ZkDriverBusinessException("创建数据为空");
			if (baseCheckNodeExist(clientKey, path)) throw new ZkDriverBusinessException("节点已存在");

			ClientPoolInvoke.invoke(clientKey, client -> {
				client.create(path, data.getBytes(Constants.CHARSET_UTF_8));
				return null;
			});

		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (ZkDriverPlatformException e) {
			LOGGER.error(LoggerHelper.platformException("创建节点失败 client_key=[{}],path=[{}],data=[{}]"), clientKey, path, data, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("创建节点失败 client_key=[{}],path=[{}],data=[{}]"), clientKey, path, data, e);
			throw new ZkDriverPlatformException(e);
		}
	}

	@Override
	public void editNodeData(String clientKey, String path, String data) throws ZkDriverBusinessException, ZkDriverPlatformException {
		LOGGER.info("更新节点数据 client_key=[{}],path=[{}],data=[{}]", clientKey, path, data);
		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");
			if (StringUtils.isEmpty(data)) throw new ZkDriverBusinessException("更新数据为空");
			if (!baseCheckNodeExist(clientKey, path)) throw new ZkDriverBusinessException("节点不存在");

			//1.查询旧的数据版本号
			ZkDataDto oldDataDto = readNodeData(clientKey, path);
			int       oldVersion = oldDataDto.getVersion();

			LOGGER.info("更新节点数据 client_key=[{}],old_data=[{}],old_version=[{}]", clientKey, oldDataDto.getData(), oldVersion);
			ClientPoolInvoke.invoke(clientKey, client -> {
				client.editData(path, data.getBytes(Constants.CHARSET_UTF_8), oldVersion);
				return null;
			});
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (ZkDriverPlatformException e) {
			LOGGER.error(LoggerHelper.platformException("更新节点数据失败 client_key=[{}],path=[{}],data=[{}]"), clientKey, path, data, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("更新节点数据失败 client_key=[{}],path=[{}],data=[{}]"), clientKey, path, data, e);
			throw new ZkDriverPlatformException(e);
		}
	}

	@Override
	public void deleteNode(String clientKey, String path) throws ZkDriverBusinessException, ZkDriverPlatformException {
		LOGGER.info("删除节点 client_key=[{}],path=[{}]", clientKey, path);

		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");
			if (!baseCheckNodeExist(clientKey, path)) throw new ZkDriverBusinessException("节点不存在");

			//查询旧节点的状态
			ZkDataDto oldZnode = readNodeData(clientKey, path);
			LOGGER.info("删除节点 client_key=[{}],path=[{}],old_znode=[{}]", clientKey, path, ReflectionToStringBuilder.toString(oldZnode));

			ClientPoolInvoke.invoke(clientKey, client -> {
				client.delete(path, true);
				return null;
			});
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (ZkDriverPlatformException e) {
			LOGGER.error(LoggerHelper.platformException("删除节点失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("删除节点失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw new ZkDriverPlatformException(e);
		}
	}

	//...............//

	/**
	 * 通用校验节点是否存在
	 *
	 * @param clientKey
	 * @param path
	 * @return
	 *
	 * @throws Exception
	 */
	private boolean baseCheckNodeExist(String clientKey, String path) throws Exception {
		return ClientPoolInvoke.invoke(clientKey, client -> client.exist(path));
	}
}
