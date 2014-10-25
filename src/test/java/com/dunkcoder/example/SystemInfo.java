package com.dunkcoder.example;

import java.io.Serializable;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;

@Bapi("RFC_SYSTEM_INFO")
public class SystemInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Export
	@Parameter(value = "RFCSI_EXPORT", type = ParameterType.STRUCTURE)
	private Info info;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String toString() {
		return "SystemInfo{" + "info=" + info + '}';
	}

}