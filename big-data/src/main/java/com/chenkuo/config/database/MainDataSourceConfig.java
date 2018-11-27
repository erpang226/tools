package com.chenkuo.config.database;

import com.chenkuo.common.config.datasource.DataSourceConfigUtil;
import com.chenkuo.common.config.datasource.DataSourceProperties;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 主数据源配置
 *
 * @author SongYongChang
 * @version [版本号, 2017/8/7]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
@MapperScan(basePackages = MainDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mainSqlSessionFactory")
public class MainDataSourceConfig {
    static final String PACKAGE = "com.chenkuo.dao";

    static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    @Resource(name = "mainDataSourceProperties")
    private DataSourceProperties properties;

    @Bean(name = "mainDataSource")
    @Primary
    public DataSource mainDataSource() {
        return DataSourceConfigUtil.configDataSource(properties);
    }

    @Bean(name = "mainTransactionManager")
    @Primary
    public DataSourceTransactionManager mainTransactionManager() {
        return new DataSourceTransactionManager(mainDataSource());
    }

    @Bean(name = "mainSqlSessionFactory")
    @Primary
    public SqlSessionFactory mainSqlSessionFactory(@Qualifier("mainDataSource") DataSource mainDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mainDataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(MainDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "mainSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mainSqlSessionTemplate(
            @Qualifier("mainSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);

    }

}
