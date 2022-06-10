/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevboweb.dto;

public class UpdatePratica {
	private int id;
	private String motivazione;
	private String nota_assistito;
	private String nota_interna;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMotivazione() {
		return motivazione;
	}
	public void setMotivazione(String motivazone) {
		this.motivazione = motivazone;
	}
	public String getNota_assistito() {
		return nota_assistito;
	}
	public void setNota_assistito(String nota_assistito) {
		this.nota_assistito = nota_assistito;
	}
	public String getNota_interna() {
		return nota_interna;
	}
	public void setNota_interna(String nota_interna) {
		this.nota_interna = nota_interna;
	}
	
	
}
