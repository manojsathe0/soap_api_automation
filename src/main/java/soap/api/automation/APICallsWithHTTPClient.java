package soap.api.automation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author msathe
 *
 */
public class APICallsWithHTTPClient 
{
	private static final Logger logger = LoggerFactory.getLogger(APICallsWithHTTPClient.class);
	
	public void getSoapResponseByPostMethod(String url, String requestFilePath, String responseFilePath)
			throws FileNotFoundException, IOException {
		File requestFile = new File(requestFilePath);
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		post.setEntity(new InputStreamEntity(new FileInputStream(requestFile)));
		post.setHeader("Content-type", "text/xml");
		HttpResponse response = client.execute(post);
		logger.info("HTTP Response Extracted Successfully with Status Code: {}",response.getStatusLine().getStatusCode());
		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
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
