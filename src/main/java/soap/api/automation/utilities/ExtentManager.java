package soap.api.automation.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

/**
 * Extent report configurations
 * 
 * @author Manoj
 *
 */
public class ExtentManager {
    private static final Logger logger = LoggerFactory.getLogger(ExtentManager.class);
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentHtmlReporter htmlReporter;
    private static String filePath = "target/ExtentReports.html";

    public static ExtentReports GetExtent() {
        if (extent != null)
            return extent; // avoid creating new instance of html file
        extent = new ExtentReports();
        extent.attachReporter(getHtmlReporter());
        return extent;
    }

    private static ExtentHtmlReporter getHtmlReporter() {
        htmlReporter = new ExtentHtmlReporter(filePath);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("SOAP API Automation Report");
        logger.info("TestNG File Name = " + System.getProperty("suiteName"));
        //htmlReporter.config().setReportName(System.getProperty("env.suiteName") + " cycle");
        htmlReporter.config().setReportName("Test Report");
        return htmlReporter;
    }

    public static ExtentTest createTest(String name, String description) {
        test = extent.createTest(name, description);
        return test;
    }
}
