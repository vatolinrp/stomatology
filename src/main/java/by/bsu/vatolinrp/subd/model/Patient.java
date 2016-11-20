package by.bsu.vatolinrp.subd.model;

import java.util.Date;

public class Patient
{
  private Long patientId;

  private Long currentDoctorId;

  private Long dentalFormulaId;

  private String name;

  private String surname;

  private String middlename;

  private Date dateOfBirth;

  private String address;

  public Long getPatientId()
  {
    return patientId;
  }

  public void setPatientId( Long patientId )
  {
    this.patientId = patientId;
  }

  public Long getCurrentDoctorId()
  {
    return currentDoctorId;
  }

  public void setCurrentDoctorId( Long currentDoctorId )
  {
    this.currentDoctorId = currentDoctorId;
  }

  public Long getDentalFormulaId()
  {
    return dentalFormulaId;
  }

  public void setDentalFormulaId( Long dentalFormulaId )
  {
    this.dentalFormulaId = dentalFormulaId;
  }

  public String getName()
  {
    return name;
  }

  public void setName( String name )
  {
    this.name = name;
  }

  public String getSurname()
  {
    return surname;
  }

  public void setSurname( String surname )
  {
    this.surname = surname;
  }

  public String getMiddlename()
  {
    return middlename;
  }

  public void setMiddlename( String middlename )
  {
    this.middlename = middlename;
  }

  public Date getDateOfBirth()
  {
    return dateOfBirth;
  }

  public void setDateOfBirth( Date dateOfBirth )
  {
    this.dateOfBirth = dateOfBirth;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress( String address )
  {
    this.address = address;
  }

  @Override
  public String toString()
  {
    return "Patient{" + "patientId=" + patientId + ", currentDoctorId=" + currentDoctorId + ", dentalFormulaId="
      + dentalFormulaId + ", name='" + name + '\'' + ", surname='" + surname + '\'' + ", middlename='" + middlename
      + '\'' + ", dateOfBirth=" + dateOfBirth + ", address='" + address + '\'' + '}';
  }
}
