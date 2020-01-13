package org.openmrs.module.opspost.fragment.controller;

import org.apache.commons.beanutils.PropertyUtils;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentConfiguration;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class OpspostFragmentController {

	public void controller(FragmentConfiguration config, @SpringBean("patientService") PatientService patientService,
	        FragmentModel model) throws Exception {
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
		String patientUUID = patient.getUuid();
		model.addAttribute("patient", patient);
	}
	
}
