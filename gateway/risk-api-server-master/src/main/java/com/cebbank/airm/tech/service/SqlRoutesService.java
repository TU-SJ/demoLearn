package com.cebbank.airm.tech.service;

import com.cebbank.airm.tech.domain.GatewayRoutes;

import java.util.List;
import java.util.Map;


public interface SqlRoutesService {

    Boolean add(GatewayRoutes route) throws Exception;

    Boolean delete(String routeId) throws Exception;

    /**
     * 查询路由信息
     *
     * @return
     */
    List<GatewayRoutes> getRoutes();

    /**
     * 根据路由信息查看
     * @param routeId
     * @return
     * @throws Exception
     */
    GatewayRoutes getInfoByRouteName(String routeId) throws Exception;

    GatewayRoutes getInfoByProduceName(String produceId) throws Exception;

    /**
     * 刷新缓存信息，因为可能在开机过程中向数据库添加路由信息，此时缓存不存在实时信息，需要刷新一下
     * 将数据库的信息重新更新到缓存中
     * @return
     */
    Map<String, GatewayRoutes> cacheRotes() throws Exception;
}
