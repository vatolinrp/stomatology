package by.bsu.vatolinrp.subd.controller;

import by.bsu.vatolinrp.subd.dao.ClinicDao;
import by.bsu.vatolinrp.subd.model.DentalFormula;
import by.bsu.vatolinrp.subd.model.Doctor;
import by.bsu.vatolinrp.subd.model.Patient;
import by.bsu.vatolinrp.subd.model.PaymentType;
import by.bsu.vatolinrp.subd.model.ReportLine;
import by.bsu.vatolinrp.subd.model.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ClinicController
{
  @Resource
  private ClinicDao clinicDao;

  @RequestMapping(value = "/patientsWithRightDoctors", method = RequestMethod.GET)
  public ModelAndView getPatientsWithRightDoctors( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    List<Patient> patientList = clinicDao.getPatientsWithRightDoctors();
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    model.addObject( "patients", patientList );
    return model;
  }

  @RequestMapping(value = "/patientsByDate", method = RequestMethod.POST)
  public ModelAndView getPatientsByDate( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    String date = request.getParameter( "date" );
    List<Patient> patientList = clinicDao.getPatientsByDate( date );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    model.addObject( "patients", patientList );
    return model;
  }

  @RequestMapping(value = "/deleteVisit", method = RequestMethod.POST)
  public ModelAndView deleteVisit( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    Long visitId = new Long( request.getParameter( "visitId" ) );
    clinicDao.deleteVisit( visitId );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/deletePatient", method = RequestMethod.POST)
  public ModelAndView deletePatient( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    Long visitId = new Long( request.getParameter( "patientId" ) );
    clinicDao.deleteVisit( visitId );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/deleteDoctor", method = RequestMethod.POST)
  public ModelAndView deleteDoctor( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    Long visitId = new Long( request.getParameter( "doctorId" ) );
    clinicDao.deleteVisit( visitId );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/getVisitForUpdate", method = RequestMethod.POST)
  public ModelAndView getVisitForUpdate( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    Long visitId = new Long( request.getParameter( "visitId" ) );
    Visit visit = clinicDao.getVisitById( visitId );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "visitForUpdate", visit );
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/updateVisit", method = RequestMethod.POST)
  public ModelAndView updateVisit( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    ModelAndView model = new ModelAndView( "MainPage" );
    Visit visit = new Visit();
    visit.setVisitId( new Long( request.getParameter( "visitId" ) ) );
    visit.setPatientId( new Long( request.getParameter( "patientId" ) ) );
    visit.setPaymentTypeId( new Long( request.getParameter( "paymentTypeId" ) ) );
    visit.setCurrentDoctorId( new Long( request.getParameter( "currentDoctorId" ) ) );
    visit.setDentalFormulaId( new Long( request.getParameter( "dentalFormulaId" ) ) );
    visit.setDoctorForReplaceId( new Long( request.getParameter( "doctorForReplaceId" ) ) );
    visit.setDateOfVisit( simpleDateFormat.parse( request.getParameter( "dateOfVisit" ) ) );
    visit.setCost( new Integer( request.getParameter( "cost" ) ) );
    visit.setDescription( request.getParameter( "description" ) );
    clinicDao.updateVisit( visit );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }


  @RequestMapping(value = "/createVisit", method = RequestMethod.POST)
  public ModelAndView createVisit( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    ModelAndView model = new ModelAndView( "MainPage" );
    Visit visit = new Visit();
    visit.setPatientId( new Long( request.getParameter( "patientId" ) ) );
    visit.setPaymentTypeId( new Long( request.getParameter( "paymentTypeId" ) ) );
    visit.setCurrentDoctorId( new Long( request.getParameter( "currentDoctorId" ) ) );
    visit.setDentalFormulaId( new Long( request.getParameter( "dentalFormulaId" ) ) );
    visit.setDoctorForReplaceId( new Long( request.getParameter( "doctorForReplaceId" ) ) );
    visit.setDateOfVisit( simpleDateFormat.parse( request.getParameter( "dateOfVisit" ) ) );
    visit.setCost( new Integer( request.getParameter( "cost" ) ) );
    visit.setDescription( request.getParameter( "description" ) );
    clinicDao.createVisit( visit );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/createDoctor", method = RequestMethod.POST)
  public ModelAndView createDoctor( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    ModelAndView model = new ModelAndView( "MainPage" );
    Doctor doctor = new Doctor();
    doctor.setName( request.getParameter( "name" ) );
    doctor.setSurname( request.getParameter( "surname" ) );
    doctor.setMiddlename( request.getParameter( "middlename" ) );
    doctor.setDateOfBirth( simpleDateFormat.parse( request.getParameter( "dateOfBirth" ) ) );
    doctor.setAddress( request.getParameter( "address" ) );
    clinicDao.createDoctor( doctor );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/createPatient", method = RequestMethod.POST)
  public ModelAndView createPatient( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    ModelAndView model = new ModelAndView( "MainPage" );
    Patient patient = new Patient();
    patient.setName( request.getParameter( "name" ) );
    patient.setSurname( request.getParameter( "surname" ) );
    patient.setMiddlename( request.getParameter( "middlename" ) );
    patient.setDateOfBirth( simpleDateFormat.parse( request.getParameter( "dateOfBirth" ) ) );
    patient.setAddress( request.getParameter( "address" ) );
    patient.setCurrentDoctorId( new Long( request.getParameter( "currentDoctorId" ) ) );
    patient.setDentalFormulaId( new Long( request.getParameter( "dentalFormulaId" ) ) );
    clinicDao.createPatient( patient );
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    return model;
  }

  @RequestMapping(value = "/sumsForEachMonth", method = RequestMethod.GET)
  public ModelAndView getSumsForEachMonth( HttpServletRequest request, HttpServletResponse response )
    throws Exception
  {
    ModelAndView model = new ModelAndView( "MainPage" );
    List<ReportLine> reportLines = clinicDao.getSumsForEachMonth();
    List<Doctor> allDoctors = clinicDao.getAllDoctors();
    List<Patient> allPatients = clinicDao.getAllPatients();
    List<DentalFormula> allDentalFormulas = clinicDao.getAllDentFormulas();
    List<PaymentType> allPaymentTypes = clinicDao.getAllPaymentTypes();
    List<Visit> allVisits = clinicDao.getAllVisits();
    model.addObject( "allDoctors", allDoctors );
    model.addObject( "allPatients", allPatients );
    model.addObject( "allDentalFormulas", allDentalFormulas );
    model.addObject( "allPaymentTypes", allPaymentTypes );
    model.addObject( "allVisits", allVisits );
    model.addObject( "reportLines", reportLines );
    return model;
  }
}
