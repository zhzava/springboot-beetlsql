package com.bosi.itms;


import com.spring4all.swagger.EnableSwagger2Doc;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement // 开启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableCaching //开启缓存
@EnableSwagger2Doc //开启swagger2
@SpringBootApplication
public class ApiMain {

	public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ApiMain.class);
        app.setBannerMode(Banner.Mode.OFF);
        PageQuery.DEFAULT_PAGE_SIZE = 20 ;
        app.run(args);
    }

	
	

    
}