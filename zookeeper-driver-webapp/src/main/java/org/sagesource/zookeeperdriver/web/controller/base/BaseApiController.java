package org.sagesource.zookeeperdriver.web.controller.base;

import org.apache.commons.lang3.StringUtils;
import org.sagesource.zookeeperdriver.helper.enums.HttpRespEnum;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverBusinessException;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.sagesource.zookeeperdriver.web.vo.base.BaseResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>基础Controller</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class BaseApiController {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler({Exception.class})
	public BaseResp exception(Exception e) {
		BaseResp baseResp = new BaseResp();

		if (e instanceof ZkDriverBusinessException) {
			baseResp.setCode(HttpRespEnum.R_200.getCode());
			baseResp.setMessage(e.getMessage());
		} else if (e instanceof ZkDriverPlatformException) {
			baseResp.setCode(HttpRespEnum.R_300.getCode());

			if (StringUtils.isEmpty(e.getMessage()))
				baseResp.setMessage(HttpRespEnum.R_300.getMessage());
			else
				baseResp.setMessage(e.getMessage());

			LOGGER.error("Controller Throw ZkDriverPlatformException:", e);
		} else {
			baseResp.setCode(HttpRespEnum.R_400.getCode());
			baseResp.setMessage(HttpRespEnum.R_400.getMessage());
			LOGGER.error("Controller Throw UnknownException:", e);
		}

		return baseResp;
	}

}
