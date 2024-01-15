package edu.hust.it4409.data;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;

import edu.hust.it4409.data.DataAccessConfiguration.Config;
import edu.hust.it4409.data.DataAccessConfiguration.RawDataSource;

@Configuration
@PropertySource("classpath:./db/pg/pg.properties")
@EnableConfigurationProperties
class PostgresConfig {
    
    @Bean
    @ConfigurationProperties
    @RawDataSource
    PGSimpleDataSource pgConnectionPoolDataSource() {
        return new PGSimpleDataSource();
    }
    
    @Bean
    @ConfigurationProperties(prefix = "hikari")
    @Config
    HikariConfig hikariConfig() {
        var config = new HikariConfig();
        config.setDataSource(pgConnectionPoolDataSource());
        return config;
    }
    
}
