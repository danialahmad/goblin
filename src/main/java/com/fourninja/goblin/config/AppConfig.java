package com.fourninja.goblin.config;

import java.io.IOException;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import com.fourninja.goblin.config.multi.MultiTenantFilter;

@Configuration
public class AppConfig {
	
	@Autowired AutowireCapableBeanFactory beanFactory;
	
	@Bean
	@Profile("development")
	public static PropertyPlaceholderConfigurer propertyConfigurerDev() throws IOException {
	    PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
	    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	    yaml.setResources(new ClassPathResource("dev.yml"));
	    props.setIgnoreUnresolvablePlaceholders(true);
	    props.setProperties(yaml.getObject());
	    return props;
	}
	@Bean
	@Profile("production")
	public static PropertyPlaceholderConfigurer propertyConfigurerProd() throws IOException {
		PropertyPlaceholderConfigurer props = new PropertyPlaceholderConfigurer();
	    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	    yaml.setResources(new ClassPathResource("prod.yml"));
	    props.setIgnoreUnresolvablePlaceholders(true);
	    props.setProperties(yaml.getObject());
	    return props;
	}
	
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter tenantFilter = new MultiTenantFilter();
        beanFactory.autowireBean(tenantFilter);
        registration.setFilter(tenantFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
	
	
}
