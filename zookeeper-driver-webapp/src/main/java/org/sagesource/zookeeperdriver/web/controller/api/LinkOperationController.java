package org.sagesource.zookeeperdriver.web.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.service.intf.IZkClientService;
import org.sagesource.zookeeperdriver.web.controller.base.BaseController;
import org.sagesource.zookeeperdriver.web.vo.base.BaseResp;
import org.sagesource.zookeeperdriver.web.vo.response.LinkServerResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>ZK链接操作Controller</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@RestController
@RequestMapping(value = "/link", produces = "application/json")
@Api(description = "连接操作Api")
public class LinkOperationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkOperationController.class);

	@Autowired
	private IZkClientService zkClientService;

	/**
	 * 连接到zk服务器
	 *
	 * @param clientKey
	 * @return
	 *
	 * @throws Exception
	 */
	@ApiOperation(value = "连接到zk服务器")
	@RequestMapping(value = "connectServer", method = RequestMethod.POST)
	public BaseResp<LinkServerResp> connectServer(String clientKey) throws Exception {
		BaseResp<LinkServerResp> baseResp = new BaseResp<>();

		try {
			ZkClientWrapper client = zkClientService.lineToZookeeper(clientKey);
			LinkServerResp  resp   = new LinkServerResp();
			resp.setClientKey(clientKey);

			if (client != null) resp.setLink(true);
			else resp.setLink(false);

			baseResp.setResponse(resp);
		} catch (Exception e) {
			throw e;
		} finally {
			baseResp.getRespcontext().setRespTime(System.currentTimeMillis());
		}

		return baseResp;
	}
}
