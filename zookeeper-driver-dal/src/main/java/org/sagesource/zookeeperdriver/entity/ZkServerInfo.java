package org.sagesource.zookeeperdriver.entity;

import java.util.Date;

public class ZkServerInfo {
    private Integer id;

    private String servers;

    private String isuse;

    private Integer retrySleepTime;

    private Integer retryTimes;

    private Integer connTimeout;

    private Integer sessionTimeout;

    private Date createTime;

    private Date updateTime;

    private String serverDesc;

    private String clientKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers == null ? null : servers.trim();
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public Integer getRetrySleepTime() {
        return retrySleepTime;
    }

    public void setRetrySleepTime(Integer retrySleepTime) {
        this.retrySleepTime = retrySleepTime;
    }

    public Integer getRetryTimes() {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes) {
        this.retryTimes = retryTimes;
    }

    public Integer getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(Integer connTimeout) {
        this.connTimeout = connTimeout;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getServerDesc() {
        return serverDesc;
    }

    public void setServerDesc(String serverDesc) {
        this.serverDesc = serverDesc == null ? null : serverDesc.trim();
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey == null ? null : clientKey.trim();
    }
}