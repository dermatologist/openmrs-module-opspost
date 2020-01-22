/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.opspost.api;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.module.opspost.Item;
import org.openmrs.module.opspost.api.dao.OpspostDao;
import org.openmrs.module.opspost.api.impl.OpspostServiceImpl;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * This is a unit test, which verifies logic in OpspostService. It doesn't extend
 * BaseModuleContextSensitiveTest, thus it is run without the in-memory DB and Spring context.
 */
public class OpspostServiceTest {
	
	@InjectMocks
	OpspostServiceImpl basicModuleService;
	
	@Mock
	OpspostDao dao;
	
	@Mock
	PatientService patientService;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveItem_shouldSetOwnerIfNotSet() {
		//Given
		Item item = new Item();
		item.setApiKey("some-api-key");
		
		when(dao.saveItem(item)).thenReturn(item);
		
		Patient patient = new Patient();
		when(patientService.getPatient(1)).thenReturn(patient);
		
		item.setPatient(patient);
		//When
		basicModuleService.saveItem(item);
		
		//Then
		assertThat(item, hasProperty("patient", is(patient)));
	}
}
