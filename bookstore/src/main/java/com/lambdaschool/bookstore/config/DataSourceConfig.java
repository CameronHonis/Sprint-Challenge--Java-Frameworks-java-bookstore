package com.lambdaschool.bookstore.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Value("${local.run.db:POSTGRESQL}")
    private String dbValue;

    @Value("${spring.datasouce.url:}")
    private String dbURL;

    public DataSource dataSource(){
        if (dbValue.equalsIgnoreCase("POSTGRESQL")){
            HikariConfig config = new HikariConfig();
            config.setDriverClassName("org.postgresql.Driver");
            config.setJdbcUrl(dbURL);
            return new HikariDataSource(config);
        } else {
            String myURLString = "jdbc:h2:mem:testdb";
            String myDriverClass = "org.h2.Driver";
            String myDBUser = "sa";
            String myDBPassword = "";

            return DataSourceBuilder.create()
                    .username(myDBUser)
                    .password(myDBPassword)
                    .url(myURLString)
                    .driverClassName(myDriverClass)
                    .build();
        }
    }
}
