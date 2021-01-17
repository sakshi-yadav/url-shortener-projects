package com.lendoapp.dao;

import com.lendoapp.config.PostgreConfig;
import com.lendoapp.constants.PostgreConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class HibernateConfig {
    @Autowired
    PostgreConfig postgreConfig;

    @Bean(name = "postgreSessionFactory")
    public LocalSessionFactoryBean sessionFactoryBean() {
        Properties properties = new Properties();
        properties.put(PostgreConstants.HIBERNATE_DIALECT, postgreConfig.getDialect());
        properties.put(PostgreConstants.HIBERNATE_SHOW_SQL, postgreConfig.getShowSql());
        properties.put(PostgreConstants.HIBERNATE_CONNECTION_URL, postgreConfig.getUrl());
        properties.put(PostgreConstants.HIBERNATE_CONNECTION_DRIVER, postgreConfig.getDriverClassName());
        properties.put(PostgreConstants.HIBERNATE_USERNAME, postgreConfig.getUsername());
        properties.put(PostgreConstants.HIBERNATE_PASSWORD, postgreConfig.getPassword());
        properties.put(PostgreConstants.HIBERNATE_DDL_MODE, postgreConfig.getDdlMode());

        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setHibernateProperties(properties);
        localSessionFactoryBean.setPackagesToScan(PostgreConstants.HIBERNATE_PACKAGES_TO_SCAN);
        return localSessionFactoryBean;
    }

}
