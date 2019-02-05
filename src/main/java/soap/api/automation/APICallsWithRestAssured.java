package soap.api.automation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import soap.api.automation.constants.Constant;

public class APICallsWithRestAssured {

	private static final Logger logger = LoggerFactory.getLogger(APICallsWithRestAssured.class);

	public void getSoapResponseByRestAssuredPostMethod(String requestFilePath, String responseFilePath)
			throws IOException, FileNotFoundException, ParserConfigurationException, SAXException,
			TransformerException {
		FileInputStream fileInputStream = new FileInputStream(new File(requestFilePath));
		Response response = RestAssured.given().header("Content-Type", "text/xml").and()
				.body(IOUtils.toString(fileInputStream, "UTF-8")).when().post(Constant.ENDPOINT).then().statusCode(200)
				.and().log().all().contentType("text/xml").extract().response();
		logger.info("Response Code : {}", response.statusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(response.asInputStream()));
		String line = "";
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		PrintWriter pw = new PrintWriter(responseFilePath);
		pw.write(sb.toString());
		pw.close();
		pw.flush();
	}
}
