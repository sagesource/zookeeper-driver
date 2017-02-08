package org.sagesource.zookeeperdriver.web.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.sagesource.zookeeperdriver.helper.enums.HttpRespEnum;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.helper.node.NodePathHelper;
import org.sagesource.zookeeperdriver.service.dto.ZkDataDto;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeDto;
import org.sagesource.zookeeperdriver.service.dto.ZkNodeWatcherInfoDto;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeService;
import org.sagesource.zookeeperdriver.service.intf.IZkNodeWatcherService;
import org.sagesource.zookeeperdriver.web.controller.base.BaseApiController;
import org.sagesource.zookeeperdriver.web.vo.base.BaseResp;
import org.sagesource.zookeeperdriver.web.vo.response.NodeChildrenResp;
import org.sagesource.zookeeperdriver.web.vo.response.NodeChildrenRespForZtree;
import org.sagesource.zookeeperdriver.web.vo.response.NodeDataResp;
import org.sagesource.zookeeperdriver.web.vo.response.NodeWatcherInfoResp;
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
@RequestMapping(value = "/api/node", produces = "application/json")
@Api(description = "ZK节点操作Api")
public class NodeOperationApiController extends BaseApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeOperationApiController.class);

	@Autowired
	private IZkNodeService        zkNodeService;
	@Autowired
	private IZkNodeWatcherService zkNodeWatcherService;

	/**
	 * 查询子节点列表-Ztree
	 *
	 * @param clientKey
	 * @param path
	 * @return
	 *
	 * @throws Exception
	 */
	@ApiOperation(value = "查询子节点列表-Ztree")
	@RequestMapping(value = "childrenForZtree", method = RequestMethod.GET)
	public BaseResp<NodeChildrenRespForZtree> queryNodeChildrenForZtree(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                                                                    @ApiParam("路径,默认为 /") @RequestParam(name = "id", required = false) String path) throws Exception {
		if (StringUtils.isEmpty(path)) path = "/";

		BaseResp<NodeChildrenRespForZtree> baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			// 查询子节点信息
			List<ZkNodeDto> nodeList = zkNodeService.findChildrenNode(clientKey, path);
			// 主对象只有/节点的时候有作用
			NodeChildrenRespForZtree ztree = new NodeChildrenRespForZtree();
			ztree.setId(path);
			ztree.setName(path);
			ztree.setOpen(true);

			// 处理子节点信息
			List<NodeChildrenRespForZtree.ZtreeChildren> children = new ArrayList<>();
			nodeList.forEach((dto) -> {
				NodeChildrenRespForZtree.ZtreeChildren ztreeChildren = new NodeChildrenRespForZtree.ZtreeChildren();
				ztreeChildren.setId(NodePathHelper.joinPath(dto.getParentPath(), dto.getName()));
				ztreeChildren.setName(dto.getName());
				ztreeChildren.setIsParent(dto.isHasChildren());
				children.add(ztreeChildren);
			});

			ztree.setChildren(children);

			baseResp.setResponse(ztree);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}
		return baseResp;
	}

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
	                                                              @ApiParam("路径") @RequestParam String path) throws Exception {
		BaseResp<List<NodeChildrenResp>> baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			List<ZkNodeDto>        nodeList = zkNodeService.findChildrenNode(clientKey, path);
			List<NodeChildrenResp> respList = new ArrayList<>();

			nodeList.forEach((dto) -> {
				NodeChildrenResp      resp = new NodeChildrenResp();
				NodeChildrenResp.Stat stat = new NodeChildrenResp.Stat();
				BeanUtils.copyProperties(dto, resp);
				BeanUtils.copyProperties(dto.getStat(), stat);

				resp.setStat(stat);
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

	/**
	 * 读取节点数据
	 *
	 * @param clientKey
	 * @param path
	 * @return
	 */
	@ApiOperation(value = "读取节点数据")
	@RequestMapping(value = "readData", method = RequestMethod.GET)
	public BaseResp<NodeDataResp> readData(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                                       @ApiParam("路径") @RequestParam String path) throws Exception {
		BaseResp<NodeDataResp> baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			ZkDataDto         data = zkNodeService.readNodeData(clientKey, path);
			NodeDataResp      resp = new NodeDataResp();
			NodeDataResp.Stat stat = new NodeDataResp.Stat();
			BeanUtils.copyProperties(data, resp);
			BeanUtils.copyProperties(data.getStat(), stat);

			resp.setStat(stat);
			baseResp.setResponse(resp);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}
		return baseResp;
	}

	/**
	 * 创建节点
	 *
	 * @param clientKey
	 * @param path
	 * @param data
	 * @return
	 */
	@ApiOperation(value = "创建节点")
	@RequestMapping(value = "createNode", method = RequestMethod.POST)
	public BaseResp createNode(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                           @ApiParam("创建路径") @RequestParam String path,
	                           @ApiParam("节点数据") @RequestParam String data) throws Exception {
		BaseResp baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			zkNodeService.createNode(clientKey, path, data);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}
		return baseResp;
	}

	/**
	 * 更新节点数据
	 *
	 * @param clientKey
	 * @param path
	 * @param data
	 * @return
	 *
	 * @throws Exception
	 */
	@ApiOperation(value = "更新节点数据")
	@RequestMapping(value = "editData", method = RequestMethod.POST)
	public BaseResp editData(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                         @ApiParam("创建路径") @RequestParam String path,
	                         @ApiParam("节点数据") @RequestParam String data) throws Exception {
		BaseResp baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			zkNodeService.editNodeData(clientKey, path, data);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}

		return baseResp;
	}

	/**
	 * 删除节点
	 *
	 * @param clientKey
	 * @param path
	 * @return
	 *
	 * @throws Exception
	 */
	@ApiOperation("删除节点")
	@RequestMapping(value = "deleteNode", method = RequestMethod.POST)
	public BaseResp deleteNode(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                           @ApiParam("创建路径") @RequestParam String path) throws Exception {
		BaseResp baseResp = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			zkNodeService.deleteNode(clientKey, path);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}

		return baseResp;
	}

	/**
	 * 查询节点的Watcher信息
	 *
	 * @param clientKey
	 * @param path
	 * @return
	 *
	 * @throws Exception
	 */
	@ApiOperation("Watcher信息查询")
	@RequestMapping(value = "watcherInfo", method = RequestMethod.GET)
	public BaseResp<NodeWatcherInfoResp> watcherInfo(@ApiParam("客户端client_key") @RequestParam String clientKey,
	                                                 @ApiParam("创建路径") @RequestParam String path) throws Exception {
		NodeWatcherInfoResp           nodeWatcherInfoResp = new NodeWatcherInfoResp();
		BaseResp<NodeWatcherInfoResp> baseResp            = new BaseResp<>();
		baseResp.setCode(HttpRespEnum.R_100.getCode());
		baseResp.setMessage(HttpRespEnum.R_100.getMessage());

		try {
			// 1. 校验节点是否存在
			zkNodeService.checkNodeExist(clientKey, path);

			// 2. 汇总节点的watcher总数
			List<ZkNodeWatcherInfoDto> watcherInfoList = zkNodeWatcherService.findWatcherInfo(clientKey, path);
			final long[]               total           = {0L};
			watcherInfoList.forEach(watcherInfo -> {
				total[0] = total[0] + watcherInfo.getWatcherTotal();
			});
			nodeWatcherInfoResp.setWatcherTotal(total[0]);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}

		baseResp.setResponse(nodeWatcherInfoResp);
		return baseResp;
	}
}
