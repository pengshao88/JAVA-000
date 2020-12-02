package com.yezp.read_write_01.datasource;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Description: 自定义事务
 * Created on 2020/12/3 0:22.
 *
 * @author yezp
 */
@Configuration
@EnableTransactionManagement
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration {


    private static Logger logger = Logger.getLogger(DataSourceContextHolder.class);
    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，
     * 只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，
     * 否则事务管理会不起作用。
     * @return
     */
    @Resource(name = "writeDataSource")
    private DataSource dataSource;

    @Bean(name = "myTransactionManager")
    public org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManagers() {
        logger.info("-------------------- transactionManager init ---------------------");
        return new org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
    }

}
