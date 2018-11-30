package com.cjalturas.presentation.backingBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.time.DateUtils;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjalturas.model.Certificate;
import com.cjalturas.model.Group;
import com.cjalturas.model.Inscription;
import com.cjalturas.model.dto.GroupDTO;
import com.cjalturas.presentation.businessDelegate.IBusinessDelegatorView;
import com.cjalturas.utilities.CertificateGenerator;
import com.cjalturas.utilities.DelayUtils;
import com.cjalturas.utilities.FacesUtils;


/**
 * Bean de la vista de lista y edición de grupos.
 * @author Edison
 */
@ManagedBean
@ViewScoped
public class ReportInscriptionsView implements Serializable {
  private static final long serialVersionUID = 1L;

  private static final Logger log = LoggerFactory.getLogger(ReportInscriptionsView.class);

  private List<Inscription> data;

  private Inscription selectedInscription;

  private Group entity;
  
  private StreamedContent fileCert;

  @ManagedProperty(value = "#{BusinessDelegatorView}")
  private IBusinessDelegatorView businessDelegatorView;

  public ReportInscriptionsView() {
    super();
  }
  
  public void certificate() {
//    System.out.println("certificar a:::: " + this.getSelectedInscription().getLearner().getPerson().getFullName());
    
//    DelayUtils.delay(DelayUtils.SEC_1);
    Inscription inscriptionToCertify = this.getSelectedInscription();
    if (inscriptionToCertify == null) return;
    
    try {
      Certificate certificate;
      if (inscriptionToCertify.isCertified()) {
        certificate = businessDelegatorView.getCertificate(inscriptionToCertify.getIdInscription());
      }else {
        certificate = businessDelegatorView.certificate(inscriptionToCertify);
      }
      fileCert = CertificateGenerator.generatePdfCertificate(certificate);
    } catch (Exception e) {
      FacesUtils.addErrorMessage(e.getMessage());
      log.error("Falló la acción de certificación del aprendiz", e);
    }
  }

  public List<Inscription> getData() {
    try {
      if (data == null) {
        data = businessDelegatorView.getAllInscriptions();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  public boolean filterByDate(Object value, Object filter, Locale locale) {

    if (filter == null) {
      return true;
    }

    if (value == null) {
      return false;
    }

    return DateUtils.truncatedEquals((Date) filter, (Date) value, java.util.Calendar.DATE);
  }


 

  public Inscription getSelectedInscription() {
    return selectedInscription;
  }

  public void setSelectedInscription(Inscription selectedInscription) {
    this.selectedInscription = selectedInscription;
  }

  public TimeZone getTimeZone() {
    return java.util.TimeZone.getDefault();
  }

  public IBusinessDelegatorView getBusinessDelegatorView() {
    return businessDelegatorView;
  }

  public void setBusinessDelegatorView(IBusinessDelegatorView businessDelegatorView) {
    this.businessDelegatorView = businessDelegatorView;
  }

  public Group getEntity() {
    return entity;
  }

  public void setEntity(Group entity) {
    this.entity = entity;
  }

  public StreamedContent getFileCert() {
    return fileCert;
  }

  public void setFileCert(StreamedContent fileCert) {
    this.fileCert = fileCert;
  }
  
}
