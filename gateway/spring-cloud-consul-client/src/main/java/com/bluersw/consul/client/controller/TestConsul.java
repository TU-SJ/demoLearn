package com.bluersw.consul.client.controller;

import com.bluersw.consul.client.service.GatewayRemote;
import com.bluersw.consul.client.service.ServiceProvider1Remote;

import com.bluersw.consul.client.service.ServiceProvider2Remote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConsul {

	@Autowired
	ServiceProvider1Remote remote1;

	@Autowired
	ServiceProvider2Remote remote2;

	@Autowired
	GatewayRemote gatewayRemote;

	@RequestMapping("/TestHello")
	public String TestHello(){
		String first = remote1.Hello("first-SWS");
		String second = remote2.Hello("second-SWS");
		return first + " | " + second ;
	}

	@RequestMapping("/Test")
	public String Test(){
		return "OK";
	}

	@RequestMapping("/TestGW")
	public String TestGW(){
		String first = gatewayRemote.Hello("first-SWS");
		String second = gatewayRemote.Hello("second-SWS");
		return first + " | " + second;
	}
}
