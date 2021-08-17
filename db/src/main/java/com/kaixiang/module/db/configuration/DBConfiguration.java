package com.kaixiang.module.db.configuration;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {

//    @Value("#{'${db.mapper.locations}'.split(',')}")
//    private List<String> mapperLocations;

    @Value("${db.mapper.locations}")
    private String mapperLocation;

    @Bean
    public SqlSessionFactoryBean sessionFactory(DataSource dataSource,
                                                org.apache.ibatis.session.Configuration mybatisConfiguration,
                                                Resource... mapperLocationsResources) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfiguration(mybatisConfiguration);
        sqlSessionFactoryBean.setMapperLocations(mapperLocationsResources);
        return sqlSessionFactoryBean;
    }

    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer(@Value("${db.basepackage}") String basePackage) {
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.kaixiang.module.user.repository");
        return configurer;
    }

//    @Bean
//    public List<Resource> mapperLocationsResources() {
//        return mapperLocations.stream().map(ClassPathResource::new).collect(Collectors.toList());
//    }

    @Bean
    public Resource mapperLocation() {
        return new ClassPathResource("classpath:com/kaixiang/module/user/repository/*.xml");
//        return new ClassPathResource(mapperLocation);
    }

    @Bean
    public org.apache.ibatis.session.Configuration mybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    @Bean
    public DataSource dataSource(@Value("${db.datasource.url}") String url,
                                 @Value("${db.datasource.username}") String username,
                                 @Value("${db.datasource.password}") String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
