package com.falconScheduler.multitenant.multitenant;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import static com.falconScheduler.multitenant.multitenant.MultiTenantConstants.DEFAULT_TENANT_ID;

import java.util.HashMap;
import java.util.Map;
@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	@Autowired
    DataSource defaultDS;

	@Autowired
    ApplicationContext context;

	static Map<String, DataSource> map = new HashMap<>();

	boolean init = false;

	@PostConstruct
	public void load() {
		map.put(DEFAULT_TENANT_ID, defaultDS);
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return map.get(DEFAULT_TENANT_ID);
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		if (!init) {
			init = true;
			TenantDataSource tenantDataSource = context.getBean(TenantDataSource.class);
			map.putAll(tenantDataSource.getAll());
		}
		return map.get(tenantIdentifier);
	}
}