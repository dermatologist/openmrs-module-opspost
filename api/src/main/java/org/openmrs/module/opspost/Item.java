/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.opspost;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.User;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Lombok
 * 
 * @Data All together now: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields,
 * @Setter on all non-final fields, and @RequiredArgsConstructor! Please note that a corresponding
 *         table schema must be created in liquibase.xml.
 */
//Uncomment 2 lines below if you want to make the Item class persistable, see also OpspostDaoTest and liquibase.xml
@Data(staticConstructor = "of")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "opspost.Item")
@Table(name = "opspost_item")
public class Item extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "opspost_item_id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "patient")
	private Patient patient;
	
	@Basic
	@Column(name = "api_key", length = 72)
	private String apiKey = "";
	
	@Basic
	@Column(name = "updated_by", length = 255)
	private String updatedBy = "UNKNOWN";
	
	@Basic
	@Column(name = "updated_on")
	private Date updatedOn;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public String getUuid() {
		return super.getUuid();
	}
	
	@Override
	public void setUuid(String uuid) {
		super.setUuid(uuid);
	}
	
	// Lombok creates @Getters and setters
	
}
