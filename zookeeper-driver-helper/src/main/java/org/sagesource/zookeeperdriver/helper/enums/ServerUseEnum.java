package org.sagesource.zookeeperdriver.helper.enums;

/**
 * <p>服务使用状态枚举</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/5
 *     email       job.xueqi@gmail.com
 * </pre>
 */
public enum ServerUseEnum {
	NO_USE("0", "未使用"),
	IS_USE("1", "使用"),
	IS_DEL("2", "删除");

	private String code;
	private String desc;

	ServerUseEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
