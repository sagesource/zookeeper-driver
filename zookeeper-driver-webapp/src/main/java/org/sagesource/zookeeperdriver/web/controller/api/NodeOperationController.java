package org.sagesource.zookeeperdriver.web.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.sagesource.zookeeperdriver.helper.enums.HttpRespEnum;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeDto;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeService;
import org.sagesource.zookeeperdriver.web.controller.base.BaseController;
import org.sagesource.zookeeperdriver.web.vo.base.BaseResp;
import org.sagesource.zookeeperdriver.web.vo.response.NodeChildrenResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>节点操作Controller</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RestController
@RequestMapping(value = "/node",produces = "application/json")
@Api(description = "ZK节点操作Api")
public class NodeOperationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeOperationController.class);

	@Autowired
	private IZkNodeService zkNodeService;

	/**
	 * 查询子节点列表
	 *
	 * @param clientKey
	 * @param path
	 * @return
	 */
	@ApiOperation(value = "查询子节点列表")
	@RequestMapping(value = "children", method = RequestMethod.GET)
	public BaseResp<List<NodeChildrenResp>> queryNodeChildrenList(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                                                              @ApiParam("路径") @RequestParam String path) throws ZkDriverPlatformException, ZkDriverBusinessException {
		BaseResp<List<NodeChildrenResp>> baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			List<ZkNodeDto>        nodeList = zkNodeService.findChildrenNode(clientKey, path);
			List<NodeChildrenResp> respList = new ArrayList<>();

			nodeList.forEach((dto) -> {
				NodeChildrenResp resp = new NodeChildrenResp();
				BeanUtils.copyProperties(dto, resp);
				respList.add(resp);
			});
			baseResp.setResponse(respList);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}

		return baseResp;
	}
}
