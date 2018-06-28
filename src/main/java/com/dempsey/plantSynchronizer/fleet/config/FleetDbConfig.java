package com.dempsey.plantSynchronizer.fleet.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "fleetEntityManagerFactory",
        transactionManagerRef = "fleetTransactionManager",
        basePackages = { "com.dempsey.plantSynchronizer.fleet" }
)
public class FleetDbConfig {

    @Bean(name = "fleetDataSource")
    @ConfigurationProperties(prefix = "fleet.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "fleetEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    barEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("fleetDataSource") DataSource dataSource
    ) {
        return
                builder
                        .dataSource(dataSource)
                        .packages("com.dempsey.plantSynchronizer.fleet.domain")
                        .persistenceUnit("fleet")
                        .build();
    }
    @Bean(name = "fleetTransactionManager")
    public PlatformTransactionManager fleetTransactionManager(
            @Qualifier("fleetEntityManagerFactory") EntityManagerFactory
                    fleetEntityManagerFactory
    ) {
        return new JpaTransactionManager(fleetEntityManagerFactory);
    }
}
