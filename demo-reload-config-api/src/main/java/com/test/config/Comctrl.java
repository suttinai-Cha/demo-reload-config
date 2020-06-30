package com.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@Scope("prototype")
public class Comctrl {
	@Value("${com.test.hello.message}")
	protected String hello_msg;
	
	@Value("${com.test.hello.message2}")
	protected String hello_msg2;
	@GetMapping("/hello")    
	   public String hello(){
	   return hello_msg;
	}
	@GetMapping("/hello2")    
	   public String hello2(){
	   return hello_msg2;
	}
}
