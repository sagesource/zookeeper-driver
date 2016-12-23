package org.sagesource.zookeeperdriver.command.holder;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.sagesource.zookeeperdriver.command.dto.ZKWchs;
import org.sagesource.zookeeperdriver.command.dto.ZkStat;

import java.util.List;

/**
 * <p>命令返回结果List处理Functions</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/23
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ListProcessorFunctions {

	/**
	 * 将stat命令的返回结果转换为Zkstat对象
	 *
	 * @param statList
	 * @return
	 */
	public static ZkStat convertList2ZkStat(List<String> statList) {
		ZkStat zkStat = new ZkStat();

		Splitter splitter = Splitter.on(":").omitEmptyStrings().trimResults();
		statList.forEach(line -> {
			if (!StringUtils.isEmpty(line)) {
				if (StringUtils.startsWith(line, "Latency")) {  //延迟时间 Latency min/avg/max: 0/0/49
					String       tmpResult   = splitter.splitToList(line).get(1);
					List<String> latencyList = Splitter.on("/").omitEmptyStrings().trimResults().splitToList(tmpResult);
					int          index       = 0;

					zkStat.setMinLatency(Long.parseLong(latencyList.get(index++)));
					zkStat.setAvgLatency(Long.parseLong(latencyList.get(index++)));
					zkStat.setMaxLatency(Long.parseLong(latencyList.get(index++)));
				} else if (StringUtils.startsWith(line, "Received")) {  //接收数 Received: 181597
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setReceived(Long.parseLong(tmpResult));

				} else if (StringUtils.startsWith(line, "Sent")) {  //发送数 Sent: 181596
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setSent(Long.parseLong(tmpResult));

				} else if (StringUtils.startsWith(line, "Connections")) {   //Connections: 3
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setConnections(Integer.parseInt(tmpResult));

				} else if (StringUtils.startsWith(line, "Outstanding")) {   //Outstanding: 0
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setOutstanding(Integer.parseInt(tmpResult));

				} else if (StringUtils.startsWith(line, "Zxid")) {  //Zxid: 0x100000306
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setZxid(tmpResult);

				} else if (StringUtils.startsWith(line, "Mode")) {  //Mode: leader
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setMode(tmpResult);

				} else if (StringUtils.startsWith(line, "Node count")) {    //Node count: 24
					String tmpResult = splitter.splitToList(line).get(1);
					zkStat.setNodeCount(Long.parseLong(tmpResult));
				}
			}
		});

		return zkStat;
	}

	/**
	 * 将wchs命令的返回结果转换为ZKWchs对象
	 *
	 * @param wchsList
	 * @return
	 */
	public static ZKWchs convertList2ZKWchs(List<String> wchsList) {
		ZKWchs zkWchs = new ZKWchs();

		wchsList.forEach(line -> {
			if (!StringUtils.isEmpty(line)) {
				if (StringUtils.startsWith(line, "Total watches")) {
					String total = Splitter.on(":").omitEmptyStrings().trimResults().splitToList(line).get(1);
					zkWchs.setTotalWatches(Integer.parseInt(total));
				}
			}
		});

		return zkWchs;
	}

}
