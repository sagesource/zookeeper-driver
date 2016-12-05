package org.sagesource.zookeeperdriver.web.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.sagesource.zookeeperdriver.helper.enums.HttpRespEnum;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.service.dto.ZkServerInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkServerService;
import org.sagesource.zookeeperdriver.web.controller.base.BaseController;
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
 * <p>ZK服务节点操作Controller</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RestController
@RequestMapping(value = "/server", produces = "application/json")
@Api(description = "ZK节点信息Api")
public class ServerOperationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerOperationController.class);

	@Autowired
	private IZkServerService zkServerService;

	/**
	 * 可用节点列表
	 *
	 * @return
	 */
	@ApiOperation(value = "查询可用节点列表")
	@RequestMapping(value = "useList", method = RequestMethod.GET)
	public BaseResp<List<ZkServerUseInfoResp>> serverUseList() {
		BaseResp<List<ZkServerUseInfoResp>> resp = new BaseResp<>();
		resp.getRespcontext().setReqTime(System.currentTimeMillis());

		try {
			List<ZkServerInfoDto>     serverList = zkServerService.useServerList();
			List<ZkServerUseInfoResp> respList   = new ArrayList<>();
			serverList.forEach(dto -> {
				ZkServerUseInfoResp infoResp = new ZkServerUseInfoResp();
				BeanUtils.copyProperties(dto, infoResp);
				respList.add(infoResp);
			});

			resp.setResponse(respList);
			resp.setCode(HttpRespEnum.R_100.getCode());
			resp.setMessage(HttpRespEnum.R_100.getMessage());
		} catch (ZkDriverBusinessException e) {
			resp.setCode(HttpRespEnum.R_200.getCode());
			resp.setMessage(e.getMessage());
		} catch (ZkDriverPlatformException e) {
			resp.setCode(HttpRespEnum.R_300.getCode());
			resp.setMessage(HttpRespEnum.R_300.getMessage());
		} catch (Exception e) {
			resp.setCode(HttpRespEnum.R_400.getCode());
			resp.setMessage(HttpRespEnum.R_400.getMessage());
		} finally {
			resp.getRespcontext().setRespTime(System.currentTimeMillis());
		}
		return resp;
	}
}
