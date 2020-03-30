//package com.cebbank.airm.tech.common;
//
//import com.alibaba.fastjson.JSONObject;
//import com.cebbank.airm.tech.domain.GatewayFilterDefinition;
//import com.cebbank.airm.tech.domain.GatewayPredicateDefinition;
//import com.cebbank.airm.tech.domain.GatewayRoutes;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class UnPackUntil {
//
//    //用于解包，将前端给我们的JSON转换成我们需要的单独的字符串形式，并放入集合//
//    public List getRouteMessage(String str) {
//        List<GatewayRoutes> routeMList = JSONObject.parseArray(str, GatewayRoutes.class);
//        return routeMList;
//    }
//
//
//    public List getFilterList(String str) {
//        List<GatewayFilterDefinition> filterlist = JSONObject.parseArray(str, GatewayFilterDefinition.class);
//        return filterlist;
//    }
//
//    public List<GatewayPredicateDefinition> getPreList(String str) {
//        List<GatewayPredicateDefinition> preList = JSONObject.parseArray(str, GatewayPredicateDefinition.class);
//        return preList;
//    }
//}
