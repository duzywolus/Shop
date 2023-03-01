package com.onlineshop.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
@EntityScan({"com.onlineshop.common.entity", "com.onlineshop.admin"})

public class OnlineShopBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopBackEndApplication.class, args);
	}

}
