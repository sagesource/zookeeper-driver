package org.sagesource.zookeeperdriver.service.impl;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.sagesource.zookeeperdriver.client.dto.ZkData;
import org.sagesource.zookeeperdriver.client.dto.ZkNode;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.helper.Constants;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
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
	public boolean checkNodeExist(ZkClientWrapper client, String path) throws Exception {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.info("判断节点是否存在 client_key=[{}],parentPath=[{}]", client.getClientKey(), path);
		try {
			return client.exist(path);
		} catch (Exception e) {
			LOGGER.error("判断节点是否存在失败 client_key=[{}],path=[{}]", client.getClientKey(), path, e);
			throw e;
		}
	}

	@Override
	public List<ZkNodeDto> findChildrenNode(ZkClientWrapper client, String parentPath) throws Exception {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.info("获得节点的子节点列表 client_key=[{}],parentPath=[{}]", client.getClientKey(), parentPath);
		List<ZkNodeDto> result = new ArrayList<>();
		try {
			List<ZkNode> nodeList = client.getChildren(parentPath);

			nodeList.forEach((node) -> {
				ZkNodeDto dto = new ZkNodeDto();
				BeanUtils.copyProperties(node, dto);
				result.add(dto);
			});

			return result;
		} catch (Exception e) {
			LOGGER.error("获得节点的子节点列表失败 client_key=[{}],parentPath=[{}]", client.getClientKey(), parentPath, e);
			throw e;
		}
	}

	@Override
	public ZkDataDto readNodeData(ZkClientWrapper client, String path) throws Exception {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.info("获取节点的数据 client_key=[{}],path=[{}]", client.getClientKey(), path);
		try {
			ZkData    data = client.readData(path);
			ZkDataDto dto  = new ZkDataDto();
			dto.setData(new String(data.getData(), "UTF-8"));
			dto.setStat(data.getStat());
			dto.setVersion(data.getStat().getVersion());

			return dto;
		} catch (Exception e) {
			LOGGER.error("获取节点的数据失败 client_key=[{}],path=[{}]", client.getClientKey(), path, e);
			throw e;
		}
	}

	@Override
	public void createNode(ZkClientWrapper client, String path, String data) throws Exception {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.info("创建节点 client_key=[{}],path=[{}],data=[{}]", client.getClientKey(), path, data);

		try {
			if (StringUtils.isEmpty(data)) throw new ZkDriverBusinessException("创建数据为空");
			client.create(path, data.getBytes(Constants.CHARSET_UTF_8));
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("创建节点失败 client_key=[{}],path=[{}],data=[{}]", client.getClientKey(), path, data, e);
			throw e;
		}
	}

	@Override
	public void editNodeData(ZkClientWrapper client, String path, String data) throws Exception {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.info("更新节点数据 client_key=[{}],path=[{}],data=[{}]", client.getClientKey(), path, data);
		try {
			if (StringUtils.isEmpty(data)) throw new ZkDriverBusinessException("更新数据为空");

			//1.查询旧的数据版本号
			ZkDataDto oldDataDto = readNodeData(client, path);
			int       oldVersion = oldDataDto.getVersion();

			LOGGER.info("更新节点数据 client_key=[{}],old_data=[{}],old_version=[{}]", client.getClientKey(), oldDataDto.getData(), oldVersion);
			client.editData(path, data.getBytes(Constants.CHARSET_UTF_8), oldVersion);
		} catch (ZkDriverBusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("更新节点数据失败 client_key=[{}],path=[{}],data=[{}]", client.getClientKey(), path, data, e);
			throw e;
		}

	}

	@Override
	public void deleteNode(ZkClientWrapper client, String path) throws Exception {
		Preconditions.checkNotNull(client, "client is null");

		LOGGER.info("删除节点 client_key=[{}],path=[{}]", client.getClientKey(), path);
		try {
			//查询旧节点的状态
			ZkDataDto oldZnode = readNodeData(client, path);
			LOGGER.info("删除节点 client_key=[{}],path=[{}],old_znode=[{}]", client.getClientKey(), path, ReflectionToStringBuilder.toString(oldZnode));

			client.delete(path, true);
		} catch (Exception e) {
			LOGGER.error("删除节点失败 client_key=[{}],path=[{}]", client.getClientKey(), path);
			throw e;
		}
	}


}
