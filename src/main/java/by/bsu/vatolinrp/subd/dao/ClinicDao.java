package by.bsu.vatolinrp.subd.dao;

import by.bsu.vatolinrp.subd.model.DentalFormula;
import by.bsu.vatolinrp.subd.model.Doctor;
import by.bsu.vatolinrp.subd.model.Patient;
import by.bsu.vatolinrp.subd.model.PaymentType;
import by.bsu.vatolinrp.subd.model.ReportLine;
import by.bsu.vatolinrp.subd.model.Visit;

import java.util.List;
import java.util.Map;

public interface ClinicDao
{
  List<Patient> getPatientsByDate( String date );

  List<Patient> getPatientsWithRightDoctors();

  List<ReportLine> getSumsForEachMonth();

  List<DentalFormula> getAllDentFormulas();

  List<Doctor> getAllDoctors();

  List<Patient> getAllPatients();

  List<PaymentType> getAllPaymentTypes();

  List<Visit> getAllVisits();

  void deleteVisit( Long visitId );

  void deletePatient( Long visitId );

  void deleteDoctor( Long visitId );

  void createVisit( Visit visit );

  void createDoctor( Doctor doctor);

  void createPatient( Patient patient );

  void updateVisit( Visit visit );

  Visit getVisitById( Long visitId );
}
