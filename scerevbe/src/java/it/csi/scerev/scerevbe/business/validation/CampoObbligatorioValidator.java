/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolationException;

public class CampoObbligatorioValidator implements ConstraintValidator<CampoObbligatorio, String> {

	public void initialize(CampoObbligatorio campo) {
	}

	public boolean isValid(String campo, ConstraintValidatorContext cxt) {
		if (campo != null && campo.trim().length() > 0)
			throw new ConstraintViolationException(null);
		return false;
	}
}
