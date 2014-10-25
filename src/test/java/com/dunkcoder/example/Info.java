package com.dunkcoder.example;

import java.io.Serializable;

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

@BapiStructure
public class Info implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Parameter("RFCDBSYS")
	private String dbSys;

	@Parameter("RFCIPADDR")
	private String ipAddress;

	@Parameter("RFCHOST")
	private String host;

	public String getDbSys() {
		return dbSys;
	}

	public void setDbSys(String dbSys) {
		this.dbSys = dbSys;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String toString() {
		return "Info{" + "dbSys=" + dbSys + "ipAddress=" + ipAddress + "host=" + host + '}';
	}

}