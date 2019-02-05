package soap.api.automation.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import soap.api.automation.constants.Constant;
import soap.api.automation.utilities.ExtentManager;
import soap.api.automation.utilities.RestUtils;

public class APISetup {
	private static final Logger logger = LoggerFactory.getLogger(APISetup.class);
	public static ExtentReports extentReport;

	@BeforeSuite
	public static void setUp() {
		logger.info("Setup");
		extentReport = ExtentManager.GetExtent();
		if (System.getProperty("apiCallType").equals(Constant.RESTCALL)) {
			RestUtils.setBaseURI(Constant.BASEURI);
			logger.info("Base URI : {}", Constant.BASEURI);
			RestUtils.setBasePath(Constant.BASEPATH);
			logger.info("Base Path : {}", Constant.BASEPATH);
		}
	}
}
