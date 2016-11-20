package by.bsu.vatolinrp.subd.dao;

import by.bsu.vatolinrp.subd.model.DentalFormula;
import by.bsu.vatolinrp.subd.model.Doctor;
import by.bsu.vatolinrp.subd.model.Patient;
import by.bsu.vatolinrp.subd.model.PaymentType;
import by.bsu.vatolinrp.subd.model.ReportLine;
import by.bsu.vatolinrp.subd.model.Visit;
import by.bsu.vatolinrp.subd.util.ClinicUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

public class ClinicDaoImplTest
{
  @Test
  public void getPatientsWithRightDoctorsTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<Patient> patientList = clinicDao.getPatientsWithRightDoctors();
    ClinicUtil.printPatients( patientList );
  }

  @Test
  public void getAllPatientsTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<Patient> patientList = clinicDao.getAllPatients();
    ClinicUtil.printPatients( patientList );
  }

  @Test
  public void getAllDoctorsTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<Doctor> doctorList = clinicDao.getAllDoctors();
    Assert.assertNotNull( doctorList );
  }

  @Test
  public void getAllPaymentTypesTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<PaymentType> paymentTypeList = clinicDao.getAllPaymentTypes();
    Assert.assertNotNull( paymentTypeList );
  }

  @Test
  public void getAllVisitsTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<Visit> visitList = clinicDao.getAllVisits();
    Assert.assertNotNull( visitList );
  }

  @Test
  public void getAllDentalFormulasTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<DentalFormula> dentalFormulaList = clinicDao.getAllDentFormulas();
    Assert.assertNotNull( dentalFormulaList );
  }

  @Test
  public void getSumsForEachMonthTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<ReportLine> reportLines;
    reportLines = clinicDao.getSumsForEachMonth();
    Assert.assertNotNull( reportLines );
  }

  @Test
  public void getPatientsByDate()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    List<Patient> patientList = clinicDao.getPatientsByDate( "14-10-2016" );
    ClinicUtil.printPatients( patientList );
  }

  @Test
  public void deleteVisitTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    clinicDao.deleteVisit( 21L );
  }

  @Test
  public void getVisitById()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    Visit visit = clinicDao.getVisitById( 1L );
    Assert.assertEquals( visit.getVisitId(), new Long( 1 ) );
  }

  @Test
  public void createVisitTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    Visit visit = new Visit();
    visit.setPatientId( 1L );
    visit.setPaymentTypeId( 1L );
    visit.setCurrentDoctorId( 1L );
    visit.setDateOfVisit( new Date() );
    visit.setDentalFormulaId( 1L );
    visit.setCost( 100 );
    visit.setDescription( "LOL" );
    visit.setDoctorForReplaceId( 1L );
    clinicDao.createVisit( visit );
  }

  @Test
  public void updateVisitTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    Visit visit = new Visit();
    visit.setVisitId( 22L );
    visit.setPatientId( 1L );
    visit.setPaymentTypeId( 1L );
    visit.setCurrentDoctorId( 1L );
    visit.setDateOfVisit( new Date() );
    visit.setDentalFormulaId( 1L );
    visit.setCost( 100 );
    visit.setDescription( "not LOL" );
    visit.setDoctorForReplaceId( 1L );
    clinicDao.updateVisit( visit );
  }

  @Test
  public void createDoctorTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    Doctor doctor = new Doctor();
    doctor.setName( "Name" );
    doctor.setSurname( "surname" );
    doctor.setMiddlename( "middlename" );
    doctor.setDateOfBirth( new Date() );
    doctor.setAddress( "address" );
    clinicDao.createDoctor( doctor );
  }

  @Test
  public void createPatientTest()
  {
    ClinicDao clinicDao = new ClinicDaoImpl();
    Patient patient = new Patient();
    patient.setDentalFormulaId( 1L );
    patient.setCurrentDoctorId( 1L );
    patient.setName( "name" );
    patient.setSurname( "surname" );
    patient.setMiddlename( "middlename" );
    patient.setDateOfBirth( new Date() );
    patient.setAddress( "address" );
    clinicDao.createPatient( patient );
  }

}
