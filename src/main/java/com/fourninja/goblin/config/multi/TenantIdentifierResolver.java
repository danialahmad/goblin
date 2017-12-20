package com.fourninja.goblin.config.multi;

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
			String tenantId = (String) requestAttributes.getAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER, RequestAttributes.SCOPE_REQUEST);
			if (tenantId != null) {
				return tenantId;
			}
		}
		return MultiTenantConstants.DEFAULT_TENANT_ID;
	}

	public void forceCurrentTenantIndetifier(String tenant) {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			requestAttributes.setAttribute(MultiTenantConstants.CURRENT_TENANT_IDENTIFIER, tenant, RequestAttributes.SCOPE_REQUEST);
		}
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		// TODO Auto-generated method stub
		return true;
	}

}
