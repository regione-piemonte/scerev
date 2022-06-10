/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.scerev.scerevbe.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Security;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSSignedDataParser;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

public class FirmaDigitaleUtility {
	private final static Logger log = Logger.getLogger(FirmaDigitaleUtility.class.getName());

	public static final String NON_FIRMATO = "NF";

	public static boolean signedAttachment(byte[] file, boolean logAttivo) {
		ByteArrayInputStream byteIn = new ByteArrayInputStream(file);
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

		try {
			IOUtils.copy(byteIn, byteOut);
		} catch (IOException e) {
			if (logAttivo) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		String tipoFirma = null;
		tipoFirma = FirmaDigitaleUtility.getTipoFirmaFromStream(tipoFirma, byteOut, logAttivo);

		return tipoFirma != null ? true : false;
	}

	public static boolean signedAttachment(String filebase64, boolean logAttivo) {
		ByteArrayInputStream byteIn = new ByteArrayInputStream(Base64.decodeBase64(filebase64.getBytes()));
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

		try {
			IOUtils.copy(byteIn, byteOut);
		} catch (IOException e) {
			if (logAttivo) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}

		String tipoFirma = null;
		tipoFirma = FirmaDigitaleUtility.getTipoFirmaFromStream(tipoFirma, byteOut, logAttivo);

		return tipoFirma != null ? true : false;
	}

	public static byte[] verificaCades(ByteArrayOutputStream out, boolean logAttivo) {
		byte[] docFirmato = null;
		try {
			Security.addProvider(new BouncyCastleProvider());
			docFirmato = decrypt(out.toByteArray(), logAttivo); // Verifico CADES
		} catch (Exception e) {
			if (logAttivo) {
				log.info("ERRORE durante l'operazione di decrypt di openSSL: " + e.getMessage());
			}
		}
		return docFirmato;
	}

	public static byte[] verificaPades(ByteArrayOutputStream out, boolean logAttivo) {
		byte[] docNonFirmato = null;

		try {
			Security.addProvider(new BouncyCastleProvider());
			PDDocument doc = PDDocument.load(out.toByteArray());
			List<PDSignature> listPDSignature = doc.getSignatureDictionaries();

			if (doc.getSignatureDictionaries() != null && doc.getSignatureDictionaries().size() > 0) {
				doc.getSignatureDictionaries().clear();
				ByteArrayOutputStream a = new ByteArrayOutputStream();
				doc.save(a);
				docNonFirmato = a.toByteArray();
			}

		} catch (Exception e2) {
			if (logAttivo) {
				log.info("ERRORE durante l'operazione di verifica PADES: " + e2.getMessage());
			}
		}
		return docNonFirmato;
	}

	public static String getTipoFirmaFromStream(String tipoFirma, ByteArrayOutputStream out, boolean logAttivo) {
		byte[] docFirmato = null;

		docFirmato = FirmaDigitaleUtility.verificaCades(out, logAttivo);
		if (docFirmato != null && docFirmato.length != 0) {
			tipoFirma = "C";
		} else {
			docFirmato = FirmaDigitaleUtility.verificaPades(out, logAttivo);// Verifico PADES
			if (docFirmato != null && docFirmato.length != 0) {
				tipoFirma = "PB";
			}
		}
		return tipoFirma;
	}

	public static byte[] decrypt(byte[] dataSource, boolean logAttivo) {
		CMSTypedStream typedStream = null;

		try {
			CMSSignedDataParser dataParser = new CMSSignedDataParser(
					new JcaDigestCalculatorProviderBuilder().setProvider("BC").build(), dataSource);
			typedStream = dataParser.getSignedContent();

			// Obbligatorio eseguirlo prima della verifica con certificato
			byte[] retValue = IOUtils.toByteArray(typedStream.getContentStream());

			// Verifica con certificato
			if (!verifyWithCertificate(dataParser))
				throw new Exception("Verifica con certificato fallita!");

			return retValue;

		} catch (Exception e) {
			if (logAttivo) {
				System.err.println("ERRORE durante l'operazione di decrypt: " + e.getMessage());
			}
		} finally {
			if (typedStream != null) {
				try {
					typedStream.drain();
				} catch (IOException e) {
					if (logAttivo) {
						System.err.println("ERRORE durante la chiusura dello stream: " + e.getMessage());
					}
				}
			}
		}

		return new byte[0];
	}

	/*
	 * Verifica i certificati di un documento CMS
	 */
	private static boolean verifyWithCertificate(CMSSignedDataParser dataParser) throws Exception {
		Store certStore = dataParser.getCertificates();
		SignerInformationStore signerInfos = dataParser.getSignerInfos();
		Iterator<SignerInformation> it = signerInfos.getSigners().iterator();
		while (it.hasNext()) {
			SignerInformation signer = it.next();
			Iterator certIt = certStore.getMatches(signer.getSID()).iterator();
			X509CertificateHolder cert = (X509CertificateHolder) certIt.next();
			if (!signer.verify(new JcaSimpleSignerInfoVerifierBuilder().setProvider("BC").build(cert))) {
				return false;
			}
		}
		return true;
	}

}
