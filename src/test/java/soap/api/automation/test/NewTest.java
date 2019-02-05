package soap.api.automation.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

import soap.api.automation.APICallsWithHTTPClient;
import soap.api.automation.APICallsWithRestAssured;
import soap.api.automation.constants.Constant;
import soap.api.automation.utilities.ExtentManager;
import soap.api.automation.utilities.ExtentTestCase;
import soap.api.automation.utilities.XMLPrettyPrintUtility;

/**
 * Test Class
 * 
 * @author msathe
 *
 */
public class NewTest {
	private static final Logger logger = LoggerFactory.getLogger(Test.class);
	public static ExtentReports extentReport;

	@BeforeTest
	public static void setup() {
		ExtentTestCase.EXTENT = ExtentManager.GetExtent();
	}

	boolean flag = true;

	@Test
	public void soapAPIRequest() throws ParserConfigurationException, SAXException, TransformerException,
			FileNotFoundException, IOException {
		APISetup.extentReport = ExtentManager.GetExtent();
		logger.info("<--------------------- Test Execution Started --------------------->");
		ExtentTestCase.TEST = ExtentTestCase.EXTENT.createTest("SOAP API Test", "SOAP API Test - Post Method");
		String apiCallType = System.getProperty("apiCallType");
		APICallsWithHTTPClient objAPICallWithHTTPClient;
		APICallsWithRestAssured objAPICallsWithRestAssured;
		if (apiCallType.equals(Constant.HTTPCLIENTCALL)) {
			objAPICallWithHTTPClient = new APICallsWithHTTPClient();
			objAPICallWithHTTPClient.getSoapResponseByPostMethod(Constant.URL, Constant.REQUESTFILEPATH,
					Constant.RESPONSEFILEPATH);
		} else if (apiCallType.equals(Constant.RESTCALL)) {
			objAPICallsWithRestAssured = new APICallsWithRestAssured();
			objAPICallsWithRestAssured.getSoapResponseByRestAssuredPostMethod(Constant.REQUESTFILEPATH,
					Constant.RESPONSEFILEPATH);
		}
		XMLPrettyPrintUtility objXMLPrettyPrintUtility = new XMLPrettyPrintUtility();
		objXMLPrettyPrintUtility.getXMLPrettyPrintText(Constant.RESPONSEFILEPATH, Constant.RESPONSEFILEPATH);
		logger.info("<--------------------- Test Execution Completed --------------------->");
	}

	/*
	 * @Test(enabled = true) public void soapAPIRequestUsingRestAssured() throws
	 * ParserConfigurationException, SAXException, TransformerException,
	 * FileNotFoundException, IOException { APISetup.extentReport =
	 * ExtentManager.GetExtent(); logger.
	 * info("<--------------------- Test Execution Started --------------------->");
	 * ExtentTestCase.TEST = ExtentTestCase.EXTENT.createTest("SOAP API Test",
	 * "SOAP API Test - Post Method"); APICallsWithRestAssured objAPICall = new
	 * APICallsWithRestAssured(); XMLPrettyPrintUtility objXMLPrettyPrintUtility =
	 * new XMLPrettyPrintUtility();
	 * objAPICall.getSoapResponseByRestAssuredPostMethod(Constant.REQUESTFILEPATH,
	 * Constant.RESPONSEFILEPATH);
	 * objXMLPrettyPrintUtility.getXMLPrettyPrintText(Constant.RESPONSEFILEPATH,
	 * Constant.RESPONSEFILEPATH); logger.
	 * info("<--------------------- Test Execution Completed --------------------->"
	 * ); }
	 */

	@AfterMethod
	public void getResult(ITestResult result) {
		logger.info("Recording Results");
		if (result.getStatus() == ITestResult.FAILURE) {
			ExtentTestCase.TEST.fail(Status.FAIL + ", Test Case Failed is " + result.getName());
			ExtentTestCase.TEST.fail(Status.FAIL + ", Test Case Failed is " + result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			ExtentTestCase.TEST.skip(Status.SKIP + ", Test Case Skipped is " + result.getName());
		} else {
			ExtentTestCase.TEST.pass(Status.PASS + " ,Test Passed");
		}
		ExtentTestCase.EXTENT.flush();
	}
}
