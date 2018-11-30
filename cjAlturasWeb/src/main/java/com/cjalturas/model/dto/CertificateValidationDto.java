package com.cjalturas.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.cjalturas.utilities.FormatUtils;


public class CertificateValidationDto implements Serializable {

  private static final long serialVersionUID = 362191300505314321L;

  private Date dateExpedition;

  private String nameLearner;
  
  private String codeCertificate;
  
  public CertificateValidationDto(Date dateExpedition, String nameLearner, String codeCertificate) {
    super();
    this.dateExpedition = dateExpedition;
    this.nameLearner = nameLearner;
    this.codeCertificate = codeCertificate;
  }

  public Date getDateExpedition() {
    return dateExpedition;
  }

  public void setDateExpedition(Date dateExpedition) {
    this.dateExpedition = dateExpedition;
  }

  public String getNameLearner() {
    return nameLearner;
  }

  public void setNameLearner(String nameLearner) {
    this.nameLearner = nameLearner;
  }

  public String getCodeCertificate() {
    return codeCertificate;
  }

  public void setCodeCertificate(String codeCertificate) {
    this.codeCertificate = codeCertificate;
  }

  public String getDateExpeditionFormatted() {
    return FormatUtils.convertDate(getDateExpedition(), FormatUtils.CERTIFICATE_DATE);
  }
  
}
