package com.syc.javacodegenerator.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * mybatis事务的配置
 */
@Configuration
@MapperScan(basePackages = {"com.syc.javacodegenerator.dao"})
@EnableTransactionManagement
public class MybatisConfig {


    /**
     * 数据源配置
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    DruidDataSource druidDataSource() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        return new DruidDataSource();
    }


    /**
     * 事务管理配置
     */
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(druidDataSource());
    }

}
