package com.cebbank.airm.tech.service.impl;

import com.cebbank.airm.tech.domain.GatewayRoutes;
import com.cebbank.airm.tech.domain.GatewayRoutesExample;
import com.cebbank.airm.tech.mapper.GatewayRoutesMapper;

import com.cebbank.airm.tech.service.SqlRoutesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据库路由
 */
@Slf4j
@Service
public class RoutesServiceImpl  implements SqlRoutesService {

    @Resource
    private GatewayRoutesMapper gatewayRoutesMapper;

    private GatewayRoutes routes;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(GatewayRoutes route) throws Exception {
        if(getInfoByRouteName(route.getRouteId()) == null){
        route.setCreateTime(new Date());
        route.setUpdateTime(new Date());
        int result = gatewayRoutesMapper.insert(route);
        if(result > 0){
            return true;
        }
            throw new Exception("添加服务失败");
        }
        throw new Exception("服务已存在，不能继续添加");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean delete(String routeId) throws Exception {
        GatewayRoutes route = getInfoByRouteName(routeId);
        if (route == null) {
            throw new Exception("表中没有此服务名");
        }
        Long id = route.getId();
        int count = gatewayRoutesMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return true;
        }
        throw new Exception("删除路由失败");
    }


    /**
     * 查询路由信息
     *
     * @return
     */
    @Override
    public List<GatewayRoutes> getRoutes() {
        GatewayRoutesExample gatewayRoutesExample = new GatewayRoutesExample();
        return gatewayRoutesMapper.selectByExample(gatewayRoutesExample);
    }

    /**
     * 将数据库的数据都添加至缓存中
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, GatewayRoutes> cacheRotes() throws Exception {
        Map<String,GatewayRoutes> cacheMap = new HashMap<>();
        try{
            cacheMap = getRoutes()
                    .stream()
                    .collect(Collectors.toMap
                            (GatewayRoutes::getRouteId,v->v));
            log.info("缓存信息【{}】",cacheMap);
        }catch (Exception e){
            throw new Exception("缓存数据失败");
        }
        log.info("数据库的路由信息存到缓存中 【{}】",cacheMap);
        return cacheMap;
    }

    /**
     * 通过路由名字来查看路由信息
     *
     * @param routeId
     * @return
     * @throws Exception
     */
    public GatewayRoutes getInfoByRouteName(String routeId) throws Exception {
        GatewayRoutesExample gatewayRoutesExample = new GatewayRoutesExample();
        gatewayRoutesExample.createCriteria().andRouteIdEqualTo(routeId);

        List<GatewayRoutes> gatewayRoutesList = gatewayRoutesMapper.selectByExample(gatewayRoutesExample);

        if (gatewayRoutesList != null && gatewayRoutesList.size() != 0) {
            return gatewayRoutesList.get(0);
        }
        return null;
    }

    /**
     * 通过产品名查看路由信息
     */
    public GatewayRoutes getInfoByProduceName(String produceId) throws Exception {
        GatewayRoutesExample gatewayRoutesExample = new GatewayRoutesExample();
        gatewayRoutesExample.createCriteria().andRouteIdEqualTo(produceId);

        List<GatewayRoutes> list = gatewayRoutesMapper
                .selectByExample(gatewayRoutesExample)
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
        ;
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}
