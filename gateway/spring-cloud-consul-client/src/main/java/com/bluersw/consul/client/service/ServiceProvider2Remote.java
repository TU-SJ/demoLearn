package com.bluersw.consul.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name= "service-provider2")
public interface ServiceProvider2Remote {
    @RequestMapping("/hello")
    public String Hello(@RequestParam String name);
}
