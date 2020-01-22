package org.openmrs.module.opspost.fragment.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.beanutils.PropertyUtils;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.module.opspost.Item;
import org.openmrs.module.opspost.api.OpspostService;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

public class OpspostFragmentController {
	
	@Autowired
	OpspostService opspostService;
	
	@Autowired
	PatientService patientService;
	
	private static final char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	public void controller(FragmentConfiguration config, @SpringBean("patientService") PatientService patientService,
	        @SpringBean("opspost.OpspostService") OpspostService opspostService, FragmentModel model) throws Exception {
		// unfortunately in OpenMRS 2.1 the coreapps patient page only gives us a patientId for this extension point
		// (not a patient) but I assume we'll fix this to pass patient, so I'll code defensively
		Patient patient;
		config.require("patient|patientId");
		Object pt = config.getAttribute("patient");
		if (pt == null) {
			patient = patientService.getPatient((Integer) config.getAttribute("patientId"));
		} else {
			// in case we are passed a PatientDomainWrapper (but this module doesn't know about emrapi)
			patient = (Patient) (pt instanceof Patient ? pt : PropertyUtils.getProperty(pt, "patient"));
		}
		Item item = new Item();
		String apiKey = "";
		if (opspostService.getItemByPatient(patient) != null) {
			item = opspostService.getItemByPatient(patient);
			apiKey = item.getApiKey();
		}
		model.addAttribute("patient", patient);
		model.addAttribute("apiKey", apiKey);
	}
	
	public Object resetApiKey(@RequestParam("patientId") String patientId) throws NoSuchAlgorithmException,
	        UnsupportedEncodingException {
		
		SimpleObject output = new SimpleObject();
		
		Item item = new Item();
		
		Patient patient = patientService.getPatient(Integer.parseInt(patientId));
		
		item.setPatient(patient);
		item.setApiKey(generateUniqueKeysWithUUIDAndMessageDigest());
		
		opspostService.saveItem(item);
		
		// if (toDelete.delete()) {
		// 	output = SimpleObject.create("message", MESSAGE_SUCCESS);
		// } else {
		// 	output = SimpleObject.create("message", MESSAGE_ERROR);
		// }
		
		output.put("apiKey", item.getApiKey());
		
		return (Object) output;
	}
	
	// https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java/src/main/java/com/baeldung/uuid/UUIDGenerator.java
	/**
	 * Unique Keys Generation Using Message Digest and Type 4 UUID
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String generateUniqueKeysWithUUIDAndMessageDigest() throws NoSuchAlgorithmException,
	        UnsupportedEncodingException {
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		String digest = bytesToHex(salt.digest());
		return digest;
	}
	
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
}
