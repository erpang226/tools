package com.chenkuo.batch.config.database;

import com.chenkuo.common.config.datasource.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 主数据源配置信息
 *
 * @author syc
 * @version [版本号, 2017/12/18]
 */
@Component("mainDataSourceProperties")
@ConfigurationProperties(prefix = "spring.datasource")
public class MainDataSourceProperties extends DataSourceProperties {

}
