package org.sagesource.zookeeperdriver.client.pool;

import org.sagesource.zookeeperdriver.client.wrapper.ZkClientWrapper;
import org.sagesource.zookeeperdriver.helper.exception.ZkDriverPlatformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>连接池客户端操作Invoke,封装资源获取和还原的过程</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/4
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ClientPoolInvoke {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientPoolInvoke.class);

	/**
	 * invoke执行方法,可以在里面封装链接的获取和归还
	 *
	 * @param clientKey 客户端key
	 * @param processor 处理逻辑类
	 * @param <T>       返回值对象
	 * @return
	 */
	public static <T> T invoke(String clientKey, PoolInvokeProcessor<T> processor) throws ZkDriverPlatformException {
		LOGGER.debug("invoke client method,clientKey:[{}]", clientKey);
		try {
			ZkClientWrapper client = ClientPoolOperation.getClientFromPool(clientKey);
			T               result = processor.processor(client);
			ClientPoolOperation.returnClientToPool(client);
			return result;
		} catch (ZkDriverPlatformException e) {
			throw e;
		} catch (Exception e) {
			throw new ZkDriverPlatformException("pool invoke method exception", e);
		}
	}

	/**
	 * properties接口 在实现接口的时候 直接使用client即可 会自动注进去
	 *
	 * @param <T>
	 */
	public interface PoolInvokeProcessor<T> {
		public T processor(ZkClientWrapper client) throws Exception;
	}
}
