package com.falconScheduler.multitenant.multitenant;

import static com.falconScheduler.multitenant.multitenant.MultiTenantConstants.CURRENT_TENANT_IDENTIFIER;
import static com.falconScheduler.multitenant.multitenant.MultiTenantConstants.DEFAULT_TENANT_ID;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			String tenantId = (String) requestAttributes.getAttribute(CURRENT_TENANT_IDENTIFIER,
					RequestAttributes.SCOPE_REQUEST);
			if (tenantId != null) {
				return tenantId;
			}
		}
		return DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
