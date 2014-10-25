package com.dunkcoder;

import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dunkcoder.example.CompanyCode;
import com.dunkcoder.example.CompanyCodeGetList;
import com.dunkcoder.example.StfcConnection;
import com.dunkcoder.example.SystemInfo;

public class CamelSapTest extends CamelSpringTestSupport {

	@Test
	public void testSessionManagerAndCamelConfig() {

		resolveMandatoryEndpoint("hibersap:A12");
		resolveMandatoryEndpoint("hibersap:A13");
		resolveMandatoryEndpoint("hibersap:A14");

		try {
			resolveMandatoryEndpoint("hibersap:A15");
			fail("there is no session manager named A15");
		} catch (Exception ex) {

		}

		try {
			StfcConnection request = new StfcConnection();
			request.setRequestText("test sap");
			template.requestBody("direct:A12", request, StfcConnection.class);
			fail("StfcConnection is not mapped on session manager A12");
		} catch (Exception ex) {

		}

	}

	@Test
	public void testStfcConnection() throws Exception {

		StfcConnection request = new StfcConnection();
		request.setRequestText("test sap");

		StfcConnection response = template.requestBody("direct:A13", request, StfcConnection.class);
		assertNotNull(response);
		assertNotNull(response.getEchoText());
		System.out.println("request: " + request);
		System.out.println("response: " + response);
		assertTrue(request.equals(response) == false);

	}

	@Test
	public void testSystemInfo() throws Exception {

		SystemInfo systemInfo = new SystemInfo();
		systemInfo = template.requestBody("direct:A13", systemInfo, SystemInfo.class);
		assertNotNull(systemInfo);
		assertNotNull(systemInfo.getInfo());
		System.out.println("systemInfo: " + systemInfo);

	}

	@Test
	public void testCompanyCodeList() throws Exception {

		CompanyCodeGetList companyList = new CompanyCodeGetList();
		companyList = template.requestBody("direct:A14", companyList, CompanyCodeGetList.class);
		assertNotNull(companyList);
		assertNotNull(companyList.getBapiReturn());
		System.out.println("return type: " + companyList.getBapiReturn().getType());
		assertTrue(companyList.getCompanyCodeList().isEmpty() == false);
		for (CompanyCode cc : companyList.getCompanyCodeList()) {
			System.out.println("company: " + cc);
		}

	}

	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/test-context.xml");
	}

}