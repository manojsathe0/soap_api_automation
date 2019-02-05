package soap.api.automation.constants;

/**
 * @author msathe
 *
 */
public class Constant {
	public final static String URL = "https://www.w3schools.com/xml/tempconvert.asmx";
	public final static String BASEURI = "https://www.w3schools.com/";
	public final static String BASEPATH = "xml/";
	public final static String ENDPOINT = "tempconvert.asmx";
	public final static String HTTPCLIENTCALL = "HttpClient";
	public final static String RESTCALL = "RestAssured";
	public final static String REQUESTFILEPATH = System.getProperty("user.dir")
			+ "//src//test//resources//Requests//CelsiusToFahrenheitRequset.xml";
	public final static String RESPONSEFILEPATH = System.getProperty("user.dir")
			+ "//src//test//resources//Responses//CelsiusToFahrenheitResponse.xml";
}
