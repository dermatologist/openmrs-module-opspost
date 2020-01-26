package org.openmrs.module.opspost.web.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.module.opspost.OpspostConstants;
import org.openmrs.module.opspost.api.OpspostService;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.api.RestService;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseUriSetup;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
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

		AdministrationService administrationService = Context.getAdministrationService();

		String opsUser = administrationService.getGlobalProperty(OpspostConstants.OPSPOST_USERNAME,
		    OpspostConstants.OPSPOST_USERNAME_DEFAULT);
		
		String opsPw = administrationService.getGlobalProperty(OpspostConstants.OPSPOST_PASSWORD,
		    OpspostConstants.OPSPOST_PASSWORD_DEFAULT);
		
		String opsBaseUrl = administrationService.getGlobalProperty(OpspostConstants.OPSPOST_BASEURL,
			OpspostConstants.OPSPOST_BASEURL_DEFAULT);
			
		String base64UsernamePw = opsUser + ":" + opsPw;

		String serviceUrl = opsBaseUrl + "/openmrs/ws/rest/v1/session";

		System.out.println(serviceUrl);
		if(opspostService.getItemByApikey(apikey) != null){
			HttpGet request = new HttpGet(serviceUrl);

			String encoding = Base64.getEncoder().encodeToString((base64UsernamePw).getBytes());

			request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
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
					log.debug(result);
				}

			}

		}
		return result;
	}
}
