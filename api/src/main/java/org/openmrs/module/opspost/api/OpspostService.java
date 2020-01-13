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

import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.opspost.OpspostConfig;
import org.openmrs.module.opspost.Item;
import org.springframework.transaction.annotation.Transactional;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface OpspostService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	@Authorized(OpspostConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;


	/**
	 * Returns an item by apiKey. Used to fetch the patient for a post.
	 * Can be called by users with this module's privilege. 
	 * 
	 * @param apiKey
	 * @return
	 * @throws APIException
	 */
	@Authorized(OpspostConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	Item getItemByApikey(String apiKey) throws APIException;

	/**
	 * Returns an item by apiKey. Used to fetch the api key for display in patient chart.
	 * Can be called by users with this module's privilege.
	 * 
	 * @param patient
	 * @return
	 * @throws APIException
	 */
	@Authorized(OpspostConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	Item getItemByPatient(Patient patient) throws APIException;


}
