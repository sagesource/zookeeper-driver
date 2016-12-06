package org.sagesource.zookeeperdriver.web.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>节点数据 响应Resp</p>
 * <pre>
 *     author      Sage XueQi
 *     date        2016/12/3
 *     email       job.xueqi@gmail.com
 * </pre>
 */
@ApiModel("node节点数据信息")
public class NodeDataResp {
	/**
	 * 节点数据
	 */
	@ApiModelProperty("节点数据")
	private String data;
	/**
	 * 数据版本
	 */
	@ApiModelProperty("数据版本")
	private int    version;
	/**
	 * 节点状态信息
	 */
	@ApiModelProperty("节点状态信息")
	private Stat   stat;

	public static class Stat {
		/**
		 * 节点创建时的zxid
		 */
		@ApiModelProperty("节点创建时的zxid")
		private long czxid;
		/**
		 * 节点最新一次更新发生时的zxid
		 */
		@ApiModelProperty("节点最新一次更新发生时的zxid")
		private long mzxid;
		/**
		 * 节点创建时的时间戳
		 */
		@ApiModelProperty("节点创建时的时间戳")
		private long ctime;
		/**
		 * 节点最新一次更新发生时的时间戳
		 */
		@ApiModelProperty("节点最新一次更新发生时的时间戳")
		private long mtime;
		/**
		 * 节点数据的更新次数
		 */
		@ApiModelProperty("节点数据的更新次数")
		private int  version;
		/**
		 * 其子节点的更新次数
		 */
		@ApiModelProperty("其子节点的更新次数")
		private int  cversion;
		/**
		 * 节点ACL(授权信息)的更新次数
		 */
		@ApiModelProperty("节点ACL(授权信息)的更新次数")
		private int  aversion;
		/**
		 * 如果该节点为ephemeral节点, ephemeralOwner值表示与该节点绑定的session id.
		 */
		@ApiModelProperty("如果该节点为ephemeral节点, ephemeralOwner值表示与该节点绑定的session id")
		private long ephemeralOwner;
		/**
		 * 节点数据的字节数
		 */
		@ApiModelProperty("节点数据的字节数")
		private int  dataLength;
		/**
		 * 子节点个数
		 */
		@ApiModelProperty("子节点个数")
		private int  numChildren;
		/**
		 * 是与 该节点的子节点（或该节点）的最近一次 创建 / 删除 的时间戳
		 */
		@ApiModelProperty("子节点最近创建/删除时间")
		private long pzxid;

		public long getCzxid() {
			return czxid;
		}

		public void setCzxid(long czxid) {
			this.czxid = czxid;
		}

		public long getMzxid() {
			return mzxid;
		}

		public void setMzxid(long mzxid) {
			this.mzxid = mzxid;
		}

		public long getCtime() {
			return ctime;
		}

		public void setCtime(long ctime) {
			this.ctime = ctime;
		}

		public long getMtime() {
			return mtime;
		}

		public void setMtime(long mtime) {
			this.mtime = mtime;
		}

		public int getVersion() {
			return version;
		}

		public void setVersion(int version) {
			this.version = version;
		}

		public int getCversion() {
			return cversion;
		}

		public void setCversion(int cversion) {
			this.cversion = cversion;
		}

		public int getAversion() {
			return aversion;
		}

		public void setAversion(int aversion) {
			this.aversion = aversion;
		}

		public long getEphemeralOwner() {
			return ephemeralOwner;
		}

		public void setEphemeralOwner(long ephemeralOwner) {
			this.ephemeralOwner = ephemeralOwner;
		}

		public int getDataLength() {
			return dataLength;
		}

		public void setDataLength(int dataLength) {
			this.dataLength = dataLength;
		}

		public int getNumChildren() {
			return numChildren;
		}

		public void setNumChildren(int numChildren) {
			this.numChildren = numChildren;
		}

		public long getPzxid() {
			return pzxid;
		}

		public void setPzxid(long pzxid) {
			this.pzxid = pzxid;
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}
}
