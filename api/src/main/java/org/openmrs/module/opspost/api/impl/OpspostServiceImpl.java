/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.opspost.api.impl;

import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.opspost.Item;
import org.openmrs.module.opspost.api.OpspostService;
import org.openmrs.module.opspost.api.dao.OpspostDao;

public class OpspostServiceImpl extends BaseOpenmrsService implements OpspostService {
	
	OpspostDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(OpspostDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		return dao.saveItem(item);
	}
	
	@Override
	public Item getItemByApikey(String apiKey) throws APIException {
		return dao.getItemByApikey(apiKey);
	}
	
	@Override
	public Item getItemByPatient(Patient patient) throws APIException {
		return dao.getItemByPatient(patient);
	}
	
}
