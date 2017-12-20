package com.fourninja.goblin.config.multi;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class MultiTenantDSProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl{

	private static final long serialVersionUID = 1L;

	
	private Map<String, DataSource> dataSourcesList;
	
	@Autowired
	public MultiTenantDSProvider(Map<String, DataSource> dataSourcesList){
		this.dataSourcesList=dataSourcesList;
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return this.dataSourcesList.get(MultiTenantConstants.DEFAULT_TENANT_ID);
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		DataSource sc=this.dataSourcesList.get(tenantIdentifier);
		return sc;
	}

}
