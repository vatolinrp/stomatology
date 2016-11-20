package by.bsu.vatolinrp.subd.model;

import java.util.Date;

public class Doctor
{
  private Long doctorId;

  private String name;

  private String surname;

  private String middlename;

  private Date dateOfBirth;

  private String address;

  public Long getDoctorId()
  {
    return doctorId;
  }

  public void setDoctorId( Long doctorId )
  {
    this.doctorId = doctorId;
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
}
