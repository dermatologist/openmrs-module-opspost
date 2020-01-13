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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/rest/" + RestConstants.VERSION_1 + "/opspost")
public class OpspostResourceController extends MainResourceController {

	private static final Logger log = LoggerFactory.getLogger(OpspostResourceController.class);
	
	@Autowired
	RestService restService;
	
	@Autowired
	BaseUriSetup baseUriSetup;
	
	/**
	 * @see org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController#getNamespace()
	 */
	@Override
	public String getNamespace() {
		return "v1/opspost";
	}
	
	/**
	 * @param post from the owa
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/owa", method = RequestMethod.POST)
	@ResponseBody
	public Object createShd(@RequestBody SimpleObject post, HttpServletRequest request, HttpServletResponse response)
	        throws ResponseException {
		baseUriSetup.setup(request);
		
//		AdministrationService administrationService = Context.getAdministrationService();
//		String shdUrl = administrationService.getGlobalProperty(OpspostConstants.DERM_SHD_SERVICEURL,
//		    OpspostConstants.DERM_SHD_SERVICEURL_DEFAULT);
//
//		String shdUser = administrationService.getGlobalProperty(OpspostConstants.DERM_SHD_USERNAME,
//		    OpspostConstants.DERM_SHD_USERNAME_DEFAULT);
//
//		String shdPw = administrationService.getGlobalProperty(OpspostConstants.DERM_SHD_PASSWORD,
//		    OpspostConstants.DERM_SHD_PASSWORD_DEFAULT);
//
//		String shdCallback = administrationService.getGlobalProperty(OpspostConstants.DERM_SHD_CALLBACK,
//		    OpspostConstants.DERM_SHD_CALLBACK_DEFAULT);
		
//		SimpleObject output = new SimpleObject();
//		output.add("post", post);
//		output.add("callback", shdCallback);
//		final CloseableHttpClient client = HttpClients.createDefault();
//		final HttpPost httpPost = new HttpPost(shdUrl);
//		final UsernamePasswordCredentials creds = new UsernamePasswordCredentials(shdUser, shdPw);
//		Gson gson = new Gson();
//		try {
//			httpPost.setEntity(new StringEntity(gson.toJson(output)));
//			httpPost.addHeader(new BasicScheme().authenticate(creds, httpPost, null));
//			final CloseableHttpResponse shdResponse = client.execute(httpPost);
//			output.add("shd", shdResponse.toString());
//			output.add("message", "Success!");
//			client.close();
//		}
//		catch (AuthenticationException e) {
//			e.printStackTrace();
//		}
//		catch (ClientProtocolException e) {
//			e.printStackTrace();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
//		log.info("Posted message from OWA to SHD");
//		return output;
		return post;
	}
	
	/**
	 * @param post callback from shd
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/shd", method = RequestMethod.POST)
	@ResponseBody
	public Object fromShd(@RequestBody SimpleObject post, HttpServletRequest request, HttpServletResponse response)
	        throws ResponseException, IOException {
		baseUriSetup.setup(request);

		// Build the Complex concept		
//		ConceptComplex conceptComplex = (ConceptComplex) Context.getConceptService().getConceptByUuid(
//				(String) post.get("conceptId"));
//		Obs obs = new Obs();
//		obs.setConcept(conceptComplex);
//
//		String img = post.get("image");
//		DermImageFile dermImageFile = new DermImageFile(img);
//		String extension = dermImageFile.getFileExtension();
//		BufferedImage image = ShdUtils.decodeToImage(dermImageFile.getBase64ImageString());
//
//		// Add watermark
//		String message = post.get("message");
//		if (message.length() > 1)
//			image = ShdUtilsToRefactor.watermark(image, message);
//
//		String fileName = "Shd_" + obs.getUuid() + "." + extension;
//		ComplexData complexData = new ComplexData(fileName, image);
//		complexData.setMimeType(dermImageFile.getMimeType());
//		obs.setComplexData(complexData);
//		Person person = Context.getPersonService().getPersonByUuid((String) post.get("patientId"));
//		obs.setPerson(person);
//		obs.setCreator(Context.getAuthenticatedUser());
//		obs.setDateCreated(new Date());
//		obs.setObsDatetime(new Date());
//		Context.getObsService().saveObs(obs, fileName);
//
//		SimpleObject output = new SimpleObject();
//		output.add("post", post);
//		String obsId = obs.getId().toString();
//		output.add("id", obsId);
//		log.info("Received an image from SHD");
//		return output;
		return post;
	}
	
}
