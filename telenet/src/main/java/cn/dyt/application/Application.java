package cn.dyt.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = { "cn.dyt" })
@MapperScan("cn.dyt.dao")
@EnableScheduling
public class Application{
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}