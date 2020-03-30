//package com.cebbank.airm.tech.service.impl;
//
//
//import javassist.NotFoundException;
//import com.cebbank.airm.tech.service.DynamicRoutesService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import java.util.Collections;
//import java.util.LinkedHashMap;
//import java.util.Map;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import reactor.core.publisher.Mono;
//@Service
//public class DynamicRoutesServiceImpl implements DynamicRoutesService, ApplicationEventPublisherAware {
//
//    @Autowired
//    private RouteDefinitionWriter routeDefinitionWriter;
//
//    private ApplicationEventPublisher applicationEventPublisher;
//
//    private Map<String, RouteDefinition> routes = Collections.synchronizedMap(new LinkedHashMap());
//
//    @Override
//    public Boolean add(RouteDefinition routeDefinition) throws Exception {
//        try {
//            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
//            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
//            return true;
//        } catch (Exception e) {
//            throw new Exception("添加缓存失败");
//        }
//    }
//
//    @Override
//    public Boolean update(RouteDefinition routeDefinition) throws Exception {
//        try {
//            delete(routeDefinition.getId()).subscribe();
//        } catch (Exception e) {
//            throw new Exception("修改失败，找不着id：" + routeDefinition.getId());
//        }
//        try {
//            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
//            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
//            return true;
//        } catch (Exception e) {
//            throw new Exception("修改路由失败");
//        }
//    }
//
//    @Override
//    public Mono<ResponseEntity<Object>> delete(String id) throws Exception {
//        try {
////            this.routeDefinitionWriter.delete(Mono.just(id))
////                    .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
////                    .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build())
////                    ).subscribe();
//            routeDefinitionWriter.delete(Mono.just(id)).subscribe();
//            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
//        } catch (Exception e) {
//            throw new Exception("删除缓存失败");
//        }
//        return Mono.empty();
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.applicationEventPublisher = applicationEventPublisher;
//    }
//}
