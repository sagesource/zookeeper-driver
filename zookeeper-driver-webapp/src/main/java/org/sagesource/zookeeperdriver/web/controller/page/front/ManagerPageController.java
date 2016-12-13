package org.sagesource.zookeeperdriver.web.controller.page.front;

import org.sagesource.zookeeperdriver.web.controller.base.BasePageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>管理页面Controller</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/13
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Controller
@RequestMapping("/manager")
public class ManagerPageController extends BasePageController {

	/**
	 * 跳转节点管理页面
	 *
	 * @return
	 */
	@RequestMapping(value = "node", method = RequestMethod.GET)
	public String nodeManager() {
		return "front/manager/node_manager";
	}

}
