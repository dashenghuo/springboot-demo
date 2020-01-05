package com.dalishen.demo.config.hikari;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


/**
 *  Druid 配置
 */
@Configuration
@MapperScan(basePackages = HikariManageConfig.PACKAGE, sqlSessionFactoryRef = "manageSqlSessionFactory")
public class HikariManageConfig {

    // mapper Java包路径
    static final String PACKAGE = "com.dalishen.demo.mapper";

    // mapper xml包路径
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean(name = "manageDataSourceProp")
    @ConfigurationProperties(prefix = "manage.datasource")
    public DataSourceProperties manageDataSourceProp() {
        return new DataSourceProperties();
    }


    @Bean(name = "manageDataSource")
    @ConfigurationProperties(prefix = "manage.datasource.hikari")
    @Primary
    public DataSource manageDataSource() {
        return manageDataSourceProp().initializeDataSourceBuilder().build();
    }

    @Bean(name = "manageTransactionManager")
    @Primary
    public PlatformTransactionManager manageTransactionManager() {
        return new DataSourceTransactionManager(manageDataSource());
    }

    @Bean(name = "manageSqlSessionFactory")
    @Primary
    public SqlSessionFactory manageSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(manageDataSource());
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(HikariManageConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}