package org.sagesource.zookeeperdriver.client.listener.spring;

import org.sagesource.zookeeperdriver.client.pool.ClientPoolOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * <p>Spring容器关闭事件监听</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/6
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@Component
public class ContextClosedListener implements ApplicationListener<ContextClosedEvent> {
	private static final Logger LOGGER = LoggerFactory.getLogger(ContextClosedListener.class);

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		//防止重复执行。
		if(event.getApplicationContext().getParent() == null) {
			LOGGER.debug("spring context closing, ready close zk client pool");
			ClientPoolOperation.destory();
		}
	}
}
