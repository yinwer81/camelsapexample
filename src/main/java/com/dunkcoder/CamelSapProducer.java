package com.dunkcoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibersap.annotations.Bapi;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;

public class CamelSapProducer extends DefaultProducer implements AsyncProcessor {

	private static final Log log = LogFactory.getLog(CamelSapProducer.class);
	private CamelSapEndpoint endpoint;

	public CamelSapProducer(CamelSapEndpoint endpoint) {
		super(endpoint);
		this.endpoint = endpoint;
	}

	public void process(Exchange exchange) throws Exception {
		SessionManager sessionManager = endpoint.getSessionManager();
		log.debug("exchange process called, getting serializable body");
		Object body = exchange.getIn().getMandatoryBody(Serializable.class);

		log.debug("checking if hibersap annotation present");
		if (!body.getClass().isAnnotationPresent(Bapi.class)) {
			throw new IllegalArgumentException("body must be a class with @org.hibersap.annotations.Bapi annotations");
		}

		// copy request object as the hibersap will make changes to our original request object
		Object copy = copy(body);

		log.debug("opening session");
		final Session session = sessionManager.openSession();
		try {
			// session.beginTransaction();
			log.debug("executing function call");
			session.execute(copy);
			if (exchange.getPattern().isOutCapable()) {
				exchange.getOut().setBody(copy);
			}
			// session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	protected Object copy(Object obj) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		os.close();

		ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
		Object copy = is.readObject();
		is.close();
		return copy;
	}

	public boolean process(Exchange exchange, AsyncCallback callback) {
		boolean sync = true;
		try {
			process(exchange);
		} catch (Exception ex) {
			exchange.setException(ex);
		} finally {
			callback.done(sync);
		}

		return sync;
	}

}