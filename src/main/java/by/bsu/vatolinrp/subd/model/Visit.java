package by.bsu.vatolinrp.subd.model;

import java.util.Date;

public class Visit
{
  private Long visitId;

  private Long patientId;

  private Long paymentTypeId;

  private Long currentDoctorId;

  private Date dateOfVisit;

  private Long dentalFormulaId;

  private Integer cost;

  private String description;

  private Long doctorForReplaceId;

  public Long getVisitId()
  {
    return visitId;
  }

  public void setVisitId( Long visitId )
  {
    this.visitId = visitId;
  }

  public Long getPatientId()
  {
    return patientId;
  }

  public void setPatientId( Long patientId )
  {
    this.patientId = patientId;
  }

  public Long getPaymentTypeId()
  {
    return paymentTypeId;
  }

  public void setPaymentTypeId( Long paymentTypeId )
  {
    this.paymentTypeId = paymentTypeId;
  }

  public Long getCurrentDoctorId()
  {
    return currentDoctorId;
  }

  public void setCurrentDoctorId( Long currentDoctorId )
  {
    this.currentDoctorId = currentDoctorId;
  }

  public Date getDateOfVisit()
  {
    return dateOfVisit;
  }

  public void setDateOfVisit( Date dateOfVisit )
  {
    this.dateOfVisit = dateOfVisit;
  }

  public Long getDentalFormulaId()
  {
    return dentalFormulaId;
  }

  public void setDentalFormulaId( Long dentalFormulaId )
  {
    this.dentalFormulaId = dentalFormulaId;
  }

  public Integer getCost()
  {
    return cost;
  }

  public void setCost( Integer cost )
  {
    this.cost = cost;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription( String description )
  {
    this.description = description;
  }

  public Long getDoctorForReplaceId()
  {
    return doctorForReplaceId;
  }

  public void setDoctorForReplaceId( Long doctorForReplaceId )
  {
    this.doctorForReplaceId = doctorForReplaceId;
  }
}
