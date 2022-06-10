/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import java.util.List;

public class RicercaRichiesteOutput {
		
	/** The records filtered. */
	private String recordsFiltered;
	
	/** The records total. */
	private String recordsTotal;

	/** The list of data objects. */
	List<RicercaOutput> data;
		

	/**
	 * Gets the records filtered.
	 *
	 * @return the recordsFiltered
	 */
	public String getRecordsFiltered() {
		return recordsFiltered;
	}

	/**
	 * Sets the records filtered.
	 *
	 * @param recordsFiltered the recordsFiltered to set
	 */
	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/**
	 * Gets the records total.
	 *
	 * @return the recordsTotal
	 */
	public String getRecordsTotal() {
		return recordsTotal;
	}

	/**
	 * Sets the records total.
	 *
	 * @param recordsTotal the recordsTotal to set
	 */
	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	/**
	 * Gets the list of data objects.
	 *
	 * @return the listOfDataObjects
	 */
	public List<RicercaOutput> getData() {
		return data;
	}

	/**
	 * Sets the list of data objects.
	 *
	 * @param listOfDataObjects the listOfDataObjects to set
	 */
	public void setData(List<RicercaOutput> listOfDataObjects) {
		this.data = listOfDataObjects;
	}
	
}
