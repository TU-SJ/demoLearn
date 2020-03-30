package com.cebbank.airm.tech.controller;

import com.cebbank.airm.tech.common.utils.ResponseData;
import com.cebbank.airm.tech.common.utils.StateCode;
import com.cebbank.airm.tech.domain.GatewayRoutes;
import com.cebbank.airm.tech.mapper.GatewayRoutesMapper;
import com.cebbank.airm.tech.service.SqlRoutesService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 路由的配置是静态的，在这里用代码改成动态配置，可以通过添加
 * 开启多个路由，修改改成自定义路由，或者删除该路由，核心在于
 * 将前端传递的参数转化为路由
 */
@Slf4j
@RestController
@RequestMapping("/route")
public class DynamicRouteInfoController implements ApplicationRunner, CommandLineRunner {

    @Resource
    private SqlRoutesService sqlRoutesService;

    private static Map<String,GatewayRoutes> map = new HashMap<>();

    /**
     * 以下是路由的的增删改查
     */
    @PostMapping("/addRoute")
    public ResponseData add(@RequestBody GatewayRoutes gatewayRoutes) throws Exception {
        if (gatewayRoutes.getRouteId() == null) {
            throw new Exception("RouteId不能为空");
        }
        if (gatewayRoutes.getProduceName() == null) {
            throw new Exception("ProduceName不能为空");
        }

        GatewayRoutes route = sqlRoutesService.getInfoByRouteName(gatewayRoutes.getRouteId());

        if (route == null) {
            Boolean isSuccess = sqlRoutesService.add(gatewayRoutes);
            if(isSuccess){
                map.put(gatewayRoutes.getRouteId(),gatewayRoutes);
                return ResponseData.ok();
            }
        }
        throw new Exception("路由名字已经存在，不能添加同名路由");
    }

    /**
     * 刷新缓存
     * @return
     * @throws Exception
     */
    @PostMapping("/refresh")
    public ResponseData refresh() throws Exception{
        map = sqlRoutesService.cacheRotes();
        return map == null ?
                ResponseData.fail(StateCode.ER_INNER_DATA.getCode(), StateCode.ER_INNER_DATA.getDesc()):
                ResponseData.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/delete")
    public ResponseData delete(@RequestBody GatewayRoutes gatewayRoutes) throws Exception {
        String routeId = gatewayRoutes.getRouteId();
        try{
            return sqlRoutesService.delete(routeId)
                    && map.containsKey(routeId)
                    && map.remove(routeId) != null ?
                    ResponseData.fail(StateCode.ER_INNER_DATA.getCode(), StateCode.ER_INNER_DATA.getDesc()):
                    ResponseData.ok();
        } catch (Exception e){
            throw new Exception("删除路由失败");
        }
    }

    @PostMapping("/getAll")
    public ResponseData gatAll() throws Exception {
        List list = map.entrySet().stream().collect(Collectors.toList());
        return list != null && list.size() > 0 ?
                 ResponseData.ok(list) :
                 ResponseData.fail(StateCode.ER_INNER_DATA.getCode(), StateCode.ER_INNER_DATA.getDesc());
    }


    @PostMapping("/getRouteInfo")
    public ResponseData getInfoByName(@RequestBody GatewayRoutes gatewayRoutes) throws Exception {
        GatewayRoutes routes = new GatewayRoutes();
        if (gatewayRoutes.getRouteId() == null && gatewayRoutes.getProduceName() == null) {
            throw new Exception("路由名或产品名不能为空");
        }

        if(gatewayRoutes.getRouteId() != null && map.containsKey(gatewayRoutes.getRouteId())){
            return ResponseData.ok(map.get(gatewayRoutes.getRouteId()));
        }else{
            routes = sqlRoutesService.getInfoByRouteName(gatewayRoutes.getRouteId());
        }

        if(gatewayRoutes.getProduceName() != null){
            routes = sqlRoutesService.getInfoByRouteName(gatewayRoutes.getProduceName());
        }

        if(routes != null){
            return ResponseData.ok(routes);
        }
         throw new Exception("不存在该路由信息");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @Override
    public void run(String... args) throws Exception {
        map = sqlRoutesService.cacheRotes();
        log.info("缓存成功，缓存数据是[{}]",map);
    }
}
