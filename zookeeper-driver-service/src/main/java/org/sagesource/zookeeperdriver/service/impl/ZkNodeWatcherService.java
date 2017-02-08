package org.sagesource.zookeeperdriver.service.impl;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.sagesource.zookeeperdriver.command.dto.ZkWchp;
import org.sagesource.zookeeperdriver.command.holder.CommandHolder;
import org.sagesource.zookeeperdriver.entity.ZkServerInfo;
import org.sagesource.zookeeperdriver.entity.ZkServerInfoExample;
import org.sagesource.zookeeperdriver.helper.Constants;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.helper.logger.LoggerHelper;
import org.sagesource.zookeeperdriver.mapper.ZkServerInfoMapper;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeWatcherInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeWatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 * <pre>
 *     author      Sage XueQi
 *     date        2017/2/8
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Service
public class ZkNodeWatcherService implements IZkNodeWatcherService {
	private static Logger LOGGER = LoggerFactory.getLogger(ZkNodeWatcherService.class);

	@Autowired
	private ZkServerInfoMapper zkServerInfoMapper;

	@Transactional(readOnly = true)
	@Override
	public List<ZkNodeWatcherInfoDto> findWatcherInfo(String clientKey, String path) throws Exception {
		LOGGER.info("查询节点的Watcher信息 clientKey=[{}],path=[{}]", clientKey, path);

		List<ZkNodeWatcherInfoDto> resultList = new ArrayList<>();
		try {
			if (StringUtils.isEmpty(clientKey)) throw new ZkDriverBusinessException("client key is null");

			//1. 查询连接串信息
			ZkServerInfoExample          params   = new ZkServerInfoExample();
			ZkServerInfoExample.Criteria criteria = params.createCriteria().andClientKeyEqualTo(clientKey);
			params.or(criteria);
			List<ZkServerInfo> zkerverInfoList = zkServerInfoMapper.selectByExample(params);
			if (zkerverInfoList == null || zkerverInfoList.size() == 0)
				throw new ZkDriverBusinessException("zk服务信息不存在");

			//2. 分割
			ZkServerInfo zkServerInfo = zkerverInfoList.get(0);
			String       connectStr   = zkServerInfo.getServers();
			List<String> connStrList  = Splitter.on(Constants.COMMA_SEPARATE).trimResults().omitEmptyStrings().splitToList(connectStr);

			//3. 汇总节点Watcher数
			for (String conn : connStrList) {
				ZkNodeWatcherInfoDto dto = new ZkNodeWatcherInfoDto();
				dto.setConnStr(conn);
				dto.setPath(path);

				Map<String, ZkWchp> wchpResult = CommandHolder.getInstance().execWchp(conn);
				ZkWchp              zkWchp     = wchpResult.get(path);
				if (zkWchp != null && zkWchp.getSessions() != null) dto.setWatcherTotal(zkWchp.getSessions().size());
				else dto.setWatcherTotal(0L);

				resultList.add(dto);
			}
		} catch (ZkDriverBusinessException | ZkDriverPlatformException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(LoggerHelper.unknownException("查询节点的Watcher信息失败 client_key=[{}],path=[{}]"), clientKey, path, e);
			throw new ZkDriverPlatformException(e);
		}
		return resultList;
	}
}
