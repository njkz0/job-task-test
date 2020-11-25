package com.firstspringapplication.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    @Bean
    @Profile("prod")
        // @Qualifier("dbprod")
    DataSource dataSourceProd() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/first_spring");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("1234567890");
        return dataSourceBuilder.build();
    }

    @Bean
    @Profile("test")
        // @Qualifier("dbtest")
    DataSource dataSourceTest() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/first_spring_test");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("1234567890");
        return dataSourceBuilder.build();
    }
}
