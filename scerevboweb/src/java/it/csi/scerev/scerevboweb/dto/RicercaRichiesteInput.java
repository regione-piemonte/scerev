/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

import it.csi.scerev.scerevboweb.dto.datatables.DataTableRequest;

public class RicercaRichiesteInput {
	private Ricerca ricerca;
	private DataTableRequest dataTablesParameter;

	public Ricerca getRicerca() {
		return ricerca;
	}

	public DataTableRequest getDataTablesParameter() {
		return dataTablesParameter;
	}

	public void setRicerca(Ricerca ricerca) {
		this.ricerca = ricerca;
	}

	public void setDataTablesParameter(DataTableRequest dataTablesParameter) {
		this.dataTablesParameter = dataTablesParameter;
	}

}
