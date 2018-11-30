package com.cjalturas.presentation.backingBeans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.model.TypeId;
import com.cjalturas.model.dto.CertificateValidationDto;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FormatUtils;


@ViewScoped
@ManagedBean(name = "validateCertificateView")
public class ValidateCertificateView {
  private String codeCertificate;
  
  private String documentCertificate;
  
  private String message;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;
  

  public void validate() {
    try {
      ApplicationMessages applicationMessages = ApplicationMessages.getInstance();
      CertificateValidationDto certificate = this.businessDelegatorView.validateCertificate(codeCertificate, documentCertificate);
      boolean valid = false;
      if (certificate==null) {
        setMessage(applicationMessages.getMessage("certificate.validate.invalid"));
      }else {
        valid=true;
        setMessage(applicationMessages.getMessage("certificate.validate.valid",certificate.getCodeCertificate(),certificate.getDateExpeditionFormatted(),certificate.getNameLearner()));
      }
      setCodeCertificate("");
      setDocumentCertificate("");
      RequestContext context = RequestContext.getCurrentInstance();
      context.execute("showDivResult("+valid+")");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public String getCodeCertificate() {
    return codeCertificate;
  }


  public void setCodeCertificate(String codeCertificate) {
    this.codeCertificate = codeCertificate;
  }
  
  public String getDocumentCertificate() {
    return documentCertificate;
  }

  public void setDocumentCertificate(String documentCertificate) {
    this.documentCertificate = documentCertificate;
  }

  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }
  
  public IBusinessDelegatorView getBusinessDelegatorView() {
    return businessDelegatorView;
  }

  public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
    this.businessDelegatorView = businessDelegatorView;
  }
  
  
}
