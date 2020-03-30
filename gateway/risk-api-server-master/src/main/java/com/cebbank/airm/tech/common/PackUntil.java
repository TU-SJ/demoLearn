//package com.cebbank.airm.tech.common;
//
//import com.cebbank.airm.tech.domain.GatewayFilterDefinition;
//import com.cebbank.airm.tech.domain.GatewayPredicateDefinition;
//import com.cebbank.airm.tech.domain.GatewayRoutes;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.FilterDefinition;
//import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.net.URI;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//
//
//@Component
//public class PackUntil{
//    @Autowired
//    UnPackUntil unPackUntil;
//    FilterDefinition filter = new FilterDefinition();
//    PredicateDefinition predicate = new PredicateDefinition();
//
//    public  RouteDefinition assembleRouteDefinition(GatewayRoutes route) {
//        RouteDefinition realRoute = new RouteDefinition();
//        //设置route内容
//        realRoute.setId(route.getRouteId());
//        realRoute.setOrder(route.getRouteOrder());
//        //解包 filter
//        String filterall = route.getFilter();//得到 filterCode，为下一步解包做准备
//        List<GatewayFilterDefinition> filterList =unPackUntil.getFilterList(filterall);//得到存有filter全部信息的list
//        FilterDefinition filterDefinition = new FilterDefinition();
//        for (int i = 0; i <filterList.size() ; i++) {
//            filterDefinition.setArgs(filterList.get(i).getArgs());
//            filterDefinition.setName(filterList.get(i).getName());
//        }
//        List<FilterDefinition> filters = new ArrayList();
//        filters.add(filterDefinition);
//        realRoute.setFilters(filters);
//        //解包 Pre
//        String preAll = route.getPredicate();//得到 filterCode，为下一步解包做准备
//        List<GatewayPredicateDefinition> preList =unPackUntil.getPreList(preAll);//得到存有p全部信息的list
//        for (int i = 0; i <preList.size(); i++) {
//            predicate.setArgs(preList.get(i).getArgs());
//            predicate.setName(preList.get(i).getName());
//        }
//        List<PredicateDefinition> predicateList = new LinkedList<>();
//        predicateList.add(predicate);
//        realRoute.setPredicates(predicateList);
//        //uri的设置
//        URI uri = null;
//        if(route.getRouteUri().startsWith("http")){
//            uri = UriComponentsBuilder.fromHttpUrl(route.getRouteUri()).build().toUri();
//        }else{
//            uri = URI.create(route.getRouteUri());
//        }
//        realRoute.setUri(uri);
//        return realRoute;
//        }
//}
////public class PackUntil
//// {
////    //把前端传递的参数转换成路由对象
////    public RouteDefinition assembleRouteDefinition(GatewayRoutes gwdefinition) {
////        RouteDefinition realRoute = new RouteDefinition();
////        realRoute.setId(String.valueOf(gwdefinition.getId()));
////        realRoute.setOrder(gwdefinition.getOrder());
////
////        //设置断言
////        List<PredicateDefinition> pdList=new ArrayList<>();
////        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList=gwdefinition.getPredicates();
////        for (GatewayPredicateDefinition gpDefinition: gatewayPredicateDefinitionList) {
////            PredicateDefinition predicate = new PredicateDefinition();
////            predicate.setArgs(gpDefinition.getArgs());
////            predicate.setName(gpDefinition.getName());
////            pdList.add(predicate);
////        }
////        realRoute.setPredicates(pdList);
////
////        //设置过滤器
////        List<FilterDefinition> filters = new ArrayList();
////        List<GatewayFilterDefinition> gatewayFilters = gwdefinition.getFilters();
////        for(GatewayFilterDefinition filterDefinition : gatewayFilters){
////            FilterDefinition filter = new FilterDefinition();
////            filter.setName(filterDefinition.getName());
////            filter.setArgs(filterDefinition.getArgs());
////            filters.add(filter);
////        }
////        realRoute.setFilters(filters);
////        URI uri = null;
////        if(gwdefinition.getRouteUri().startsWith("http")){
////            uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getRouteUri()).build().toUri();
////        }else{
////            uri = URI.create(gwdefinition.getRouteUri());
////        }
////        realRoute.setUri(uri);
////        return realRoute;
////    }
////}
