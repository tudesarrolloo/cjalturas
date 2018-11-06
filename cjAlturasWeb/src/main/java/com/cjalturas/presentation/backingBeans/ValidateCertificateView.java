package com.cjalturas.presentation.backingBeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.cjalturas.messages.ApplicationMessages;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.FormatUtils;


@ViewScoped
@ManagedBean(name = "validateCertificateView")
public class ValidateCertificateView {
  private String codeCertificate;
  
  private String message;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public void validate() {
    
    System.out.println("validar certificado de " + codeCertificate);
    try {
      ApplicationMessages applicationMessages = ApplicationMessages.getInstance();
      Date dateExpiration = this.businessDelegatorView.validateCertificate(codeCertificate);
      boolean valid = false;
      if (dateExpiration==null) {
        setMessage(applicationMessages.getMessage("certificate.validate.invalid",codeCertificate));
      }else {
        valid=true;
        setMessage(applicationMessages.getMessage("certificate.validate.valid",codeCertificate,FormatUtils.convertDate(dateExpiration, FormatUtils.CERTIFICATE_DATE)));
      }
      setCodeCertificate("");
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
