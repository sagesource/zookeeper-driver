package org.sagesource.zookeeperdriver.service.dto;

/**
 * <p>Server信息Dto</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public class ZkServerDto {

	/**
	 * id编号
	 */
	private int    id;
	/**
	 * 链接字符串
	 */
	private String connStr;
	/**
	 * 描述
	 */
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConnStr() {
		return connStr;
	}

	public void setConnStr(String connStr) {
		this.connStr = connStr;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
