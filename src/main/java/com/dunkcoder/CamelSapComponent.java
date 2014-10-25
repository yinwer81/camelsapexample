package com.dunkcoder;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.session.SessionManager;

/**
 * Camel component that manages {@link CamelSapEndpoint}.
 */
public class CamelSapComponent extends DefaultComponent {

	private static final Log log = LogFactory.getLog(CamelSapComponent.class);

	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		log.info(String.format("creating sap endpoint: uri=%s, remaining=%s", uri, remaining));
		for (String key : parameters.keySet()) {
			log.debug("parameter key: " + parameters.get(key));
		}

		if (remaining == null) {
			throw new IllegalArgumentException("need hibersap session manager configuration in endpoint uri");
		}

		AnnotationConfiguration annotationConfig = null;
		try {
			annotationConfig = new AnnotationConfiguration(remaining);
		} catch (Exception ex) {
		}

		if (annotationConfig == null) {
			SessionManagerConfig sessionConfig = getCamelContext().getRegistry().lookup(remaining, SessionManagerConfig.class);
			if (sessionConfig == null) {
				Map<String, SessionManagerConfig> configs = getCamelContext().getRegistry().lookupByType(SessionManagerConfig.class);
				for (SessionManagerConfig config : configs.values()) {
					if (config.getName().equals(remaining)) {
						sessionConfig = config;
					}
				}
			}
			if (sessionConfig != null) {
				annotationConfig = new AnnotationConfiguration(sessionConfig);
			}

		}

		if (annotationConfig == null) {
			throw new IllegalArgumentException("no hibersap session manager configuration found with name " + remaining);
		}

		SessionManagerConfig sessionConfig = annotationConfig.buildSessionManager().getConfig();
		setProperties(sessionConfig, parameters);
		SessionManager sessionManager = annotationConfig.buildSessionManager();

		return new CamelSapEndpoint(uri, this, sessionManager);
	}

}