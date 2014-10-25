package com.dunkcoder.example;

import java.io.Serializable;
import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

@BapiStructure
public class CompanyCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Parameter("COMP_CODE")
	private String code;

	@Parameter("COMP_NAME")
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return "CompanyCode{" + "code=" + code + "name=" + name + '}';
	}

}