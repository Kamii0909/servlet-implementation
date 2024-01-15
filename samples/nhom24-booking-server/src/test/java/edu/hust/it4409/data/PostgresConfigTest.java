package edu.hust.it4409.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.zaxxer.hikari.HikariConfig;

import edu.hust.it4409.data.DataAccessConfiguration.Config;
import edu.hust.it4409.data.DataAccessConfiguration.RawDataSource;

@ExtendWith(SpringExtension.class)
class PostgresConfigTest {
    
    @TestConfiguration
    @Import(PostgresConfig.class)
    static class PGTestConfig {
        
    }
    
    @Autowired
    GenericApplicationContext ctx;
    @Autowired
    @RawDataSource
    PGSimpleDataSource pgds;
    @Autowired
    @Config
    HikariConfig config;
    
    @Test()
    void loadContext() {
        assertEquals("dev", pgds.getCurrentSchema());
        assertEquals(5, config.getMaximumPoolSize());
    }
}
