package com.fourninja.goblin.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.fourninja.goblin.config.model.entity.Datasourceconfig;
import com.fourninja.goblin.config.multi.MultiTenantConstants;
import com.fourninja.goblin.config.multi.MultiTenantDSProvider;
import com.fourninja.goblin.config.multi.TenantIdentifierResolver;
import com.fourninja.goblin.model.dao.jdbc.DatasourceconfigDao;

@Configuration
@ComponentScan(basePackages="com.fourninja.goblin.model",useDefaultFilters=false,includeFilters = @Filter(type = FilterType.ANNOTATION, classes=org.springframework.stereotype.Repository.class))
@ComponentScan("com.fourninja.goblin.model.dao.jdbc")
@EnableJpaRepositories("com.fourninja.goblin.model.repository")
public class JpaConfig {

	@Value("${db.username}")
	private String username;
	@Value("${db.password}")
	private String password;
	@Value("${db.url}")
	private String url;
	@Value("${db.driverClassName}")
	private String driverClassName;
	@Value("${persistence.unit}")
	private String persistenceUnitName;
	@Value("${db.dialect}")
	private String dbDialect;
	
	
	@Bean 
	public LazyConnectionDataSourceProxy dataSource(){
		BasicDataSource ds=new BasicDataSource();
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setUrl(url);
		ds.setDriverClassName(driverClassName);
		ds.setInitialSize(1);
		ds.setMaxActive(50);
		ds.setMaxWait(5000);
		ds.setTestOnBorrow(true);
		ds.setValidationQuery("select version()");
		ds.setPoolPreparedStatements(true);
		ds.setMinIdle(1);
		
		LazyConnectionDataSourceProxy proxy=new LazyConnectionDataSourceProxy(ds);
		return proxy;
	}
	
	@Bean(name = "dataSourcesList" )
	public Map<String, DataSource> dataSourcesList() {
		DatasourceconfigDao datasourceconfigDao=new DatasourceconfigDao(dataSource());
		Map<String, DataSource> result = new HashMap<>();
		for (Datasourceconfig dsProperties: datasourceconfigDao.getAll()) {
			DataSourceBuilder factory = DataSourceBuilder
				.create()
				.url(dsProperties.getUrl())
				.username(dsProperties.getUsername())
				.password(dsProperties.getPassword())
				.driverClassName(dsProperties.getDriverclassname());
			BasicDataSource ds=(BasicDataSource) factory.build();
			ds.setInitialSize(1);
			ds.setMaxActive(50);
			ds.setMaxWait(5000);
			ds.setTestOnBorrow(true);
			ds.setValidationQuery("select version()");
			ds.setPoolPreparedStatements(true);
			ds.setMinIdle(1);
			result.put(dsProperties.getName(), ds);
		}
		result.put(MultiTenantConstants.DEFAULT_TENANT_ID, dataSource());
		return result;
	}
	
	@Bean
	public MultiTenantConnectionProvider multiTenantConnectionProvider() {
		// Autowires dataSourcesList
		return new MultiTenantDSProvider(dataSourcesList());
	}

	@Bean
	public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
		return new TenantIdentifierResolver();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(MultiTenantConnectionProvider multiTenantConnectionProvider,
		CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

		Map<String, Object> hibernateProps = new LinkedHashMap<>();
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
		hibernateProps.put("hibernate.temp.use_jdbc_metadata_defaults", "false");

		// No dataSource is set to resulting entityManagerFactoryBean
		LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
		result.setPackagesToScan("com.fourninja.goblin.model.entity");
		HibernateJpaVendorAdapter vendor=new HibernateJpaVendorAdapter();
		vendor.setShowSql(true);
		vendor.setDatabasePlatform(dbDialect);
		result.setJpaVendorAdapter(vendor);
		result.setJpaPropertyMap(hibernateProps);

		return result;
	}


}
