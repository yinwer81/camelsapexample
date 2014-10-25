package com.dunkcoder.example;

import java.io.Serializable;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;

@Bapi("STFC_CONNECTION")
public class StfcConnection implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Import
	@Parameter("REQUTEXT")
	private String requestText;

	@Export
	@Parameter("RESPTEXT")
	private String responseText;

	@Export
	@Parameter("ECHOTEXT")
	private String echoText;

	public String getEchoText() {
		return echoText;
	}

	public void setEchoText(String echoText) {
		this.echoText = echoText;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public String toString() {
		return "StfcConnection{" + " reqeust=" + requestText + " response=" + responseText + " echo=" + echoText + "}";
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StfcConnection other = (StfcConnection) obj;
		if ((this.requestText == null) ? (other.requestText != null) : !this.requestText.equals(other.requestText)) {
			return false;
		}
		if ((this.responseText == null) ? (other.responseText != null) : !this.responseText.equals(other.responseText)) {
			return false;
		}
		if ((this.echoText == null) ? (other.echoText != null) : !this.echoText.equals(other.echoText)) {
			return false;
		}
		return true;
	}

	public int hashCode() {
		int hash = 3;
		hash = 47 * hash + (this.requestText != null ? this.requestText.hashCode() : 0);
		hash = 47 * hash + (this.responseText != null ? this.responseText.hashCode() : 0);
		hash = 47 * hash + (this.echoText != null ? this.echoText.hashCode() : 0);
		return hash;
	}

}