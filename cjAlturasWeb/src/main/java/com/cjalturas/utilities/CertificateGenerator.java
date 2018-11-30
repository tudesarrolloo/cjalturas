package com.cjalturas.utilities;

import java.util.HashMap;

import org.primefaces.model.DefaultStreamedContent;

import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.Certificate;

public class CertificateGenerator {

  public static DefaultStreamedContent generatePdfCertificate(Certificate certificate) {
    ApplicationMessages applicationMessages = ApplicationMessages.getInstance();
    HashMap<String, String> map = new HashMap<>();
    map.put("#-CERTIFICATION-#", certificate.getCertification());
    map.put("#-NAME-#", certificate.getLearner());
    map.put("#-DOCUMENT-#", certificate.getLearnerTypeDocument() + " " + certificate.getLearnerDocument());
    map.put("#-LEVEL-#", certificate.getLevel().toUpperCase());
    map.put("#-INTENSITY-#", certificate.getIntensity());
    map.put("#-DAYS-#", certificate.getDays());
    map.put("#-CITY-#", certificate.getCity());
    map.put("#-COACH1-#", certificate.getInstructor1());
    map.put("#-COACH1_CHARGE-#", certificate.getInstructor1Charge());
    map.put("#-COACH1_SIGN-#", certificate.getInstructor1Sign());
    map.put("#-COACH2-#", certificate.getInstructor2());
    map.put("#-COACH2_CHARGE-#", certificate.getInstructor2Charge());
    map.put("#-COACH2_SIGN-#", certificate.getInstructor2Sign());
    map.put("#-DATE-#", certificate.getDate());
    map.put("#-CODE-#", certificate.getCode());
    map.put("#-EMAIL-#", applicationMessages.getMessage("certificate.email"));
    map.put("#-PHONES-#", applicationMessages.getMessage("certificate.phones"));
    return new PdfCreator().createPDf2AndDownload(map);
  }
}
