package org.openmrs.module.opspost.web.controller;

import com.google.gson.Gson;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openmrs.ConceptComplex;
import org.openmrs.Obs;
import org.openmrs.Person;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.opspost.api.OpspostService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.response.ResponseException;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseUriSetup;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.openmrs.obs.ComplexData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + "/opspost")
public class OpspostResourceController extends MainResourceController {
	
	private static final Logger log = LoggerFactory.getLogger(OpspostResourceController.class);
	
	@Autowired
	RestService restService;
	
	@Autowired
	BaseUriSetup baseUriSetup;
	
	@Autowired
	OpspostService opspostService;
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController#getNamespace()
	 */
	@Override
	public String getNamespace() {
		return "v1/opspost";
	}
	
	// GET /openmrs/ws/rest/v1/session with the BASIC credentials will return the current token value
	// /openmrs/ws/rest/v1/opspost/your-api-key
	@RequestMapping(value = "/{apikey}", method = RequestMethod.GET)
	@ResponseBody
	public String retrieve(@PathVariable("apikey") String apikey, HttpServletRequest servletRequest) throws IOException {
	
		String result = "";

		if(opspostService.getItemByApikey(apikey) != null){
			HttpGet request = new HttpGet("http://tomcat.nuchange.ca:6091/openmrs/ws/rest/v1/session");

			String encoding = Base64.getEncoder().encodeToString(("admin:Admin123").getBytes());

			request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
			// CredentialsProvider provider = new BasicCredentialsProvider();
			// provider.setCredentials(
			// 		AuthScope.ANY,
			// 		new UsernamePasswordCredentials("admin", "Admin123")
			// );

			// https://mkyong.com/java/apache-httpclient-examples/
			try (CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(request)) {

				// Get HttpResponse Status
				// System.out.println(response.getProtocolVersion());              // HTTP/1.1
				// System.out.println(response.getStatusLine().getStatusCode());   // 200
				// System.out.println(response.getStatusLine().getReasonPhrase()); // OK
				// System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// return it as a String
					result = EntityUtils.toString(entity);
					System.out.println(result);
				}

			}

			// try (CloseableHttpClient httpClient = HttpClientBuilder.create()
			// 		.setDefaultCredentialsProvider(provider)
			// 		.build();
			// 	CloseableHttpResponse response = httpClient.execute(request)) {

			// 	// 401 if wrong user/password
			// 	System.out.println(response.getStatusLine().getStatusCode());   

			// 	HttpEntity entity = response.getEntity();
			// 	if (entity != null) {
			// 		// return it as a String
			// 		result = EntityUtils.toString(entity);
			// 		System.out.println(result);
			// 	}

			// }
		}
		return result;
	}
}
