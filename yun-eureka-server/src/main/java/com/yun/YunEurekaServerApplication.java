package com.yun;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class YunEurekaServerApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(YunEurekaServerApplication.class).web(true).run(args);
	}
}
