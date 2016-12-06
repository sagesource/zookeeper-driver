package org.sagesource.zookeeperdriver.web.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sagesource.zookeeperdriver.helper.enums.HttpRespEnum;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.service.dto.ZkServerInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkServerService;
import org.sagesource.zookeeperdriver.web.controller.base.BaseApiController;
import org.sagesource.zookeeperdriver.web.vo.base.BaseResp;
import org.sagesource.zookeeperdriver.web.vo.response.ZkServerUseInfoResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ZK server操作Controller</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RestController
@RequestMapping(value = "/api/server", produces = "application/json")
@Api(description = "ZK Server操作Api")
public class ServerOperationApiController extends BaseApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerOperationApiController.class);

	@Autowired
	private IZkServerService zkServerService;

	/**
	 * 可用server列表
	 *
	 * @return
	 */
	@ApiOperation(value = "查询可用server列表")
	@RequestMapping(value = "useServerList", method = RequestMethod.GET)
	public BaseResp<List<ZkServerUseInfoResp>> useServerList() throws ZkDriverBusinessException, ZkDriverPlatformException {
		BaseResp<List<ZkServerUseInfoResp>> resp = new BaseResp<>();
		resp.setCode(HttpRespEnum.R_100.getCode());
		resp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			List<ZkServerInfoDto>     serverList = zkServerService.useServerList();
			List<ZkServerUseInfoResp> respList   = new ArrayList<>();
			serverList.forEach(dto -> {
				ZkServerUseInfoResp infoResp = new ZkServerUseInfoResp();
				BeanUtils.copyProperties(dto, infoResp);
				respList.add(infoResp);
			});

			resp.setResponse(respList);
		} catch (Exception e) {
			throw e;
		} finally {
			resp.getRespcontext().setRespTime(System.currentTimeMillis());
		}
		return resp;
	}
}
