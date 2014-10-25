package com.dunkcoder.example;

import java.io.Serializable;
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import org.hibersap.annotations.Table;
import org.hibersap.annotations.ThrowExceptionOnError;
import org.hibersap.bapi.BapiConstants;
import org.hibersap.bapi.BapiRet2;

@Bapi("BAPI_COMPANYCODE_GETLIST")
@ThrowExceptionOnError
public class CompanyCodeGetList implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Table
	@Parameter("COMPANYCODE_LIST")
	private List<CompanyCode> companyCodeList;

	@Export
	@Parameter(value = BapiConstants.RETURN, type = ParameterType.STRUCTURE)
	private BapiRet2 bapiReturn;

	public BapiRet2 getBapiReturn() {
		return bapiReturn;
	}

	public void setBapiReturn(BapiRet2 bapiReturn) {
		this.bapiReturn = bapiReturn;
	}

	public List<CompanyCode> getCompanyCodeList() {
		return companyCodeList;
	}

	public void setCompanyCodeList(List<CompanyCode> companyCodeList) {
		this.companyCodeList = companyCodeList;
	}

	public String toString() {
		return "CompanyCodeGetList{" + "companyCodeList=" + companyCodeList + "bapiReturn=" + bapiReturn + '}';
	}

}