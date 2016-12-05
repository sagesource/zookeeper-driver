package org.sagesource.zookeeperdriver.service.impl;

import org.sagesource.zookeeperdriver.entity.ZkServerInfo;
import org.sagesource.zookeeperdriver.entity.ZkServerInfoExample;
import org.sagesource.zookeeperdriver.helper.enums.ServerUseEnum;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.helper.logger.LoggerHelper;
import org.sagesource.zookeeperdriver.mapper.ZkServerInfoMapper;
import org.sagesource.zookeeperdriver.service.dto.ZkServerInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Service
public class ZkServerService implements IZkServerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ZkServerService.class);

	@Autowired
	private ZkServerInfoMapper zkServerInfoMapper;

	@Override
	public List<ZkServerInfoDto> useServerList() throws ZkDriverBusinessException, ZkDriverPlatformException {
		LOGGER.info("查询正在使用的服务列表");

		try {
			ZkServerInfoExample          zkServerInfoExample = new ZkServerInfoExample();
			ZkServerInfoExample.Criteria criteria            = zkServerInfoExample.createCriteria();
			criteria.andIsuseEqualTo(ServerUseEnum.IS_USE.getCode());
			zkServerInfoExample.or(criteria);

			List<ZkServerInfo>    infoList   = zkServerInfoMapper.selectByExample(zkServerInfoExample);
			List<ZkServerInfoDto> resultList = new ArrayList<>();

			infoList.forEach((info) -> {
				ZkServerInfoDto dto = new ZkServerInfoDto();
				BeanUtils.copyProperties(info, dto);
				resultList.add(dto);
			});

			return resultList;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("查询正在使用的服务错误"), e);
			throw new ZkDriverPlatformException(e);
		}
	}
}
