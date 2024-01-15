package edu.hust.it4409.data;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ConditionalOnClass({ DataSource.class, HikariDataSource.class })
@Import(HibernateConfiguration.class)
public class DataAccessConfiguration {
    
    /**
     * Qualifier meta annotation to require the injection of the configuration
     * {@link HikariConfig} bean (as opposed to HikariDataSource)
     */
    @Target({ CONSTRUCTOR, METHOD, PARAMETER, FIELD })
    @Retention(RUNTIME)
    @Qualifier("config")
    public @interface Config {
        
    }
    
    /**
     * Qualifier meta annotation to require the injection of
     * {@link HikariDataSource} bean (opposed to HikariConfig)
     */
    @Target({ CONSTRUCTOR, METHOD, PARAMETER, FIELD })
    @Retention(RUNTIME)
    @Qualifier("dataSource")
    public @interface PooledDataSource {
        
    }
    
    /**
     * Qualifier meta annotation to require the injection of
     * {@link PlatformTransactionManager} bean (as opposed to
     * {@link org.springframework.transaction.jta.JtaTransactionManager
     * XA-compatible TransactionManager})
     */
    @Target({ CONSTRUCTOR, METHOD, PARAMETER, FIELD })
    @Retention(RUNTIME)
    @Qualifier("transactionManager")
    public @interface PlatformTM {
        
    }
    
    /**
     * <p>
     * Qualifier meta annotation to require the injection of {@link DataSource}
     * instead of the wrapper DataSource(connection pool, aka Hikari).
     * <p>
     * Since this is the raw datasource implementation,
     * {@link DataSource#getConnection} will always return a new physical database
     * connection.
     */
    @Target({ CONSTRUCTOR, METHOD, PARAMETER, FIELD })
    @Retention(RUNTIME)
    @Qualifier("rawDataSource")
    public @interface RawDataSource {
        
    }
    
    @ConditionalOnMissingBean(DataSource.class)
    @ImportAutoConfiguration(DataSourceAutoConfiguration.class)
    static class FallbackDefaultConfig {
        
    }
    
    @Import(PostgresConfig.class)
    @ConditionalOnResource(resources = "classpath:./db/pg/pg.properties")
    @ConditionalOnClass({ PGSimpleDataSource.class })
    @Profile({ "pg", "pgdb", "postgres" })
    static class Postgres {
        
    }
    
    @Primary
    @PlatformTM
    @Bean({ "tm", "transactionManager", "defaultTransactionManager" })
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
    
    @Primary
    @PooledDataSource
    @Bean({ "hikariDataSource", "dataSource", "defaultDataSource" })
    HikariDataSource hikariDataSource(@Config HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }
}
