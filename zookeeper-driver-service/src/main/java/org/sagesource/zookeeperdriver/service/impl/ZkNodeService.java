package org.sagesource.zookeeperdriver.service.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.sagesource.zookeeperdriver.client.dto.ZkData;
import org.sagesource.zookeeperdriver.client.dto.ZkNode;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.service.dto.ZkDataDto;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeDto;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		LOGGER.info("获取节点的数据 client_key=[{}],path=[{}]", client.getClientKey(), path);

		try {
			ZkData    data = client.readData(path);
			ZkDataDto dto  = new ZkDataDto();
			dto.setData(new String(data.getData(), "UTF-8"));
			dto.setStat(data.getStat());

			return dto;
		} catch (Exception e) {
			LOGGER.error("获取节点的数据失败 client_key=[{}],path=[{}]", client.getClientKey(), path, e);
			throw e;
		}
	}
}
