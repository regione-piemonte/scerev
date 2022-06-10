/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.business.validation;

//import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.ws.rs.NameBinding;

@NameBinding
//@Documented
@Constraint(validatedBy = CampoObbligatorioValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CampoObbligatorio {
	
	String message();
//	int VALID = 200;
//	String errorCode();
//	String fieldName();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
