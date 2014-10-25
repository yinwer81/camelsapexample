package com.dunkcoder;

import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.hibersap.session.SessionManager;

/**
 * Represents an Hibersap session manager endpoint to an SAP system.
 */
public class CamelSapEndpoint extends DefaultEndpoint {

	private SessionManager sessionManager = null;

	public SessionManager getSessionManager() {
		return sessionManager;
	}

	public CamelSapEndpoint(String uri, CamelSapComponent component, SessionManager sessionManager) {
		super(uri, component);
		this.sessionManager = sessionManager;
	}

	public Producer createProducer() throws Exception {
		return new CamelSapProducer(this);

	}

	public Consumer createConsumer(Processor processor) throws Exception {
		throw new UnsupportedOperationException("not supported yet");
	}

	public boolean isSingleton() {
		return true;
	}

}