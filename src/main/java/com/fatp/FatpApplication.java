package com.fatp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

import com.huajin.baymax.db.annotation.MyBatisDao;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ImportResource({"classpath:/spring/spring-context.xml"})
@MapperScan(
		annotationClass=MyBatisDao.class, 
		basePackages="com.fatp.dao")
public class FatpApplication extends SpringBootServletInitializer{

	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FatpApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FatpApplication.class, args);
    }
    
}
