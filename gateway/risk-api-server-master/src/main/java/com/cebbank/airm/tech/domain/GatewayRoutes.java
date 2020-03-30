package com.cebbank.airm.tech.domain;

import java.util.Date;
import java.util.Map;

public class GatewayRoutes {
    private Long id;

    private String routeId;

    private String produceName;

    private Date createTime;

    private Date updateTime;

    //做一个缓存，开机自动把数据库的东西都存到这里
    private Map<String,GatewayRoutes> cacheMap;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId == null ? null : routeId.trim();
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName == null ? null : produceName.trim();
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

    public Map<String, GatewayRoutes> getCacheMap() {
        return cacheMap;
    }

    public void setCacheMap(Map<String, GatewayRoutes> cacheMap) {
        this.cacheMap = cacheMap;
    }
}