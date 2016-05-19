package by.bsu.vatolinrp.subd.dao;

import by.bsu.vatolinrp.subd.model.DentalFormula;
import by.bsu.vatolinrp.subd.model.Doctor;
import by.bsu.vatolinrp.subd.model.Patient;
import by.bsu.vatolinrp.subd.model.PaymentType;
import by.bsu.vatolinrp.subd.model.ReportLine;
import by.bsu.vatolinrp.subd.model.Visit;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClinicDaoImpl implements ClinicDao
{


  public List<Patient> getPatientsByDate( String date )
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      preparedStatement = connection.prepareStatement("SELECT PATIENT.* FROM PATIENT WHERE (((PATIENT.PATIENT_ID) In (SELECT VISIT.PATIENT_ID FROM VISIT WHERE VISIT.DATE_OF_VISIT=?)))");
      preparedStatement.setDate( 1, new java.sql.Date( simpleDateFormat.parse( date ).getTime() ) );
      resultSet = preparedStatement.executeQuery();
      Patient patient;
      List<Patient> patientList = new ArrayList<Patient>();
      while(resultSet.next())
      {
        patient = new Patient();
        patient.setPatientId( resultSet.getLong( "PATIENT_ID" ) );
        patient.setCurrentDoctorId( resultSet.getLong( "CURRENT_DOCTOR_ID" ) );
        patient.setDentalFormulaId( resultSet.getLong( "DENTAL_FORMULA_ID" ) );
        patient.setName( resultSet.getString( "NAME" ) );
        patient.setSurname( resultSet.getString( "SURNAME" ) );
        patient.setMiddlename( resultSet.getString( "MIDDLENAME" ) );
        patient.setDateOfBirth( new Date( resultSet.getDate( "DATE_OF_BIRTH" ).getTime() ) );
        patient.setAddress( resultSet.getString( "ADDRESS" ) );
        patientList.add( patient );
      }
      return patientList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(preparedStatement != null)
        {
          preparedStatement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  public List<Patient> getPatientsWithRightDoctors()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT PATIENT.* FROM PATIENT WHERE PATIENT_ID IN (SELECT VISIT.PATIENT_ID FROM VISIT WHERE VISIT.CURRENT_DOCTOR_ID=PATIENT.CURRENT_DOCTOR_ID)");
      Patient patient;
      List<Patient> patientList = new ArrayList<Patient>();
      while(resultSet.next())
      {
        patient = new Patient();
        patient.setPatientId( resultSet.getLong( "PATIENT_ID" ) );
        patient.setCurrentDoctorId( resultSet.getLong( "CURRENT_DOCTOR_ID" ) );
        patient.setDentalFormulaId( resultSet.getLong( "DENTAL_FORMULA_ID" ) );
        patient.setName( resultSet.getString( "NAME" ) );
        patient.setSurname( resultSet.getString( "SURNAME" ) );
        patient.setMiddlename( resultSet.getString( "MIDDLENAME" ) );
        patient.setDateOfBirth( new Date( resultSet.getDate( "DATE_OF_BIRTH" ).getTime() ) );
        patient.setAddress( resultSet.getString( "ADDRESS" ) );
        patientList.add( patient );
      }
      return patientList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  public List<ReportLine> getSumsForEachMonth()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT MONTH, SUM(COST) as TOTAL FROM (SELECT EXTRACT (MONTH FROM date_of_visit) AS MONTH, COST, EXTRACT (YEAR FROM date_of_visit) AS YEAR FROM visit WHERE EXTRACT (YEAR FROM date_of_visit) = EXTRACT (YEAR FROM sysdate) ) GROUP BY MONTH ");
      Map<String,String> patientMap = new HashMap<String,String>();
      while(resultSet.next())
      {
        String month =  resultSet.getString( "Month" );
        String total =  resultSet.getString( "Total" );
        patientMap.put( month, total );
      }

      for( int i=1; i<=12; i++ )
      {
        if(patientMap.get( String.valueOf( i ) ) == null )
        {
          patientMap.put( String.valueOf( i ), "0" );
        }
      }
      List<ReportLine> reportLines = new ArrayList<>();
      for( int i=1; i<=12; i++ )
      {
        reportLines.add( new ReportLine( getMonthForInt( i-1 ), Integer.valueOf( patientMap.get( String.valueOf( i ) ) ) ) );
      }
      return reportLines;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  private String getMonthForInt( int num )
  {
    String month = "wrong";
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    if (num >= 0 && num <= 11 )
    {
      month = months[num];
    }
    return month;
  }

  @Override
  public List<DentalFormula> getAllDentFormulas()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT DENTAL_FORMULA.* FROM DENTAL_FORMULA");
      DentalFormula dentalFormula;
      List<DentalFormula> dentalFormulaList = new ArrayList<>();
      while( resultSet.next() )
      {
        dentalFormula = new DentalFormula();
        dentalFormula.setDentalFormulaId( resultSet.getLong( "DENTAL_FORMULA_ID" ) );
        dentalFormula.setTooth11( resultSet.getString( "TOOTH_11" ) );
        dentalFormula.setTooth12( resultSet.getString( "TOOTH_12" ) );
        dentalFormula.setTooth13( resultSet.getString( "TOOTH_13" ) );
        dentalFormula.setTooth14( resultSet.getString( "TOOTH_14" ) );
        dentalFormula.setTooth15( resultSet.getString( "TOOTH_15" ) );
        dentalFormula.setTooth16( resultSet.getString( "TOOTH_16" ) );
        dentalFormula.setTooth17( resultSet.getString( "TOOTH_17" ) );
        dentalFormula.setTooth18( resultSet.getString( "TOOTH_18" ) );
        dentalFormula.setTooth21( resultSet.getString( "TOOTH_21" ) );
        dentalFormula.setTooth22( resultSet.getString( "TOOTH_22" ) );
        dentalFormula.setTooth23( resultSet.getString( "TOOTH_23" ) );
        dentalFormula.setTooth24( resultSet.getString( "TOOTH_24" ) );
        dentalFormula.setTooth25( resultSet.getString( "TOOTH_25" ) );
        dentalFormula.setTooth26( resultSet.getString( "TOOTH_26" ) );
        dentalFormula.setTooth27( resultSet.getString( "TOOTH_27" ) );
        dentalFormula.setTooth28( resultSet.getString( "TOOTH_28" ) );
        dentalFormula.setTooth31( resultSet.getString( "TOOTH_31" ) );
        dentalFormula.setTooth32( resultSet.getString( "TOOTH_32" ) );
        dentalFormula.setTooth33( resultSet.getString( "TOOTH_33" ) );
        dentalFormula.setTooth34( resultSet.getString( "TOOTH_34" ) );
        dentalFormula.setTooth35( resultSet.getString( "TOOTH_35" ) );
        dentalFormula.setTooth36( resultSet.getString( "TOOTH_36" ) );
        dentalFormula.setTooth37( resultSet.getString( "TOOTH_37" ) );
        dentalFormula.setTooth38( resultSet.getString( "TOOTH_38" ) );
        dentalFormula.setTooth41( resultSet.getString( "TOOTH_41" ) );
        dentalFormula.setTooth42( resultSet.getString( "TOOTH_42" ) );
        dentalFormula.setTooth43( resultSet.getString( "TOOTH_43" ) );
        dentalFormula.setTooth44( resultSet.getString( "TOOTH_44" ) );
        dentalFormula.setTooth45( resultSet.getString( "TOOTH_45" ) );
        dentalFormula.setTooth46( resultSet.getString( "TOOTH_46" ) );
        dentalFormula.setTooth47( resultSet.getString( "TOOTH_47" ) );
        dentalFormula.setTooth48( resultSet.getString( "TOOTH_48" ) );
        dentalFormulaList.add( dentalFormula );
      }
      return dentalFormulaList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<Doctor> getAllDoctors()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT DOCTOR.* FROM DOCTOR");
      Doctor doctor;
      List<Doctor> doctorList = new ArrayList<>();
      while( resultSet.next() )
      {
        doctor = new Doctor();
        doctor.setDoctorId( resultSet.getLong( "DOCTOR_ID" ) );
        doctor.setName( resultSet.getString( "NAME" ) );
        doctor.setSurname( resultSet.getString( "SURNAME" ) );
        doctor.setMiddlename( resultSet.getString( "MIDDLENAME" ) );
        doctor.setDateOfBirth( new Date( resultSet.getDate( "DATE_OF_BIRTH" ).getTime() ) );
        doctor.setAddress( resultSet.getString( "ADDRESS" ) );
        doctorList.add( doctor );
      }
      return doctorList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<Patient> getAllPatients()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT PATIENT.* FROM PATIENT");
      Patient patient;
      List<Patient> patientList = new ArrayList<Patient>();
      while(resultSet.next())
      {
        patient = new Patient();
        patient.setPatientId( resultSet.getLong( "PATIENT_ID" ) );
        patient.setCurrentDoctorId( resultSet.getLong( "CURRENT_DOCTOR_ID" ) );
        patient.setDentalFormulaId( resultSet.getLong( "DENTAL_FORMULA_ID" ) );
        patient.setName( resultSet.getString( "NAME" ) );
        patient.setSurname( resultSet.getString( "SURNAME" ) );
        patient.setMiddlename( resultSet.getString( "MIDDLENAME" ) );
        patient.setDateOfBirth( new Date( resultSet.getDate( "DATE_OF_BIRTH" ).getTime() ) );
        patient.setAddress( resultSet.getString( "ADDRESS" ) );
        patientList.add( patient );
      }
      return patientList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<PaymentType> getAllPaymentTypes()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT PAYMENT_TYPE.* FROM PAYMENT_TYPE");
      PaymentType paymentType;
      List<PaymentType> paymentTypeList = new ArrayList<>();
      while(resultSet.next())
      {
        paymentType = new PaymentType();
        paymentType.setPaymentTypeId( resultSet.getLong( "PAYMENT_TYPE_ID" ) );
        paymentType.setPaymentType( resultSet.getString( "PAYMENT_TYPE" ) );
        paymentTypeList.add( paymentType );
      }
      return paymentTypeList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public List<Visit> getAllVisits()
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT VISIT.* FROM VISIT");
      Visit visit;
      List<Visit> visitList = new ArrayList<>();
      while( resultSet.next() )
      {
        visit = new Visit();
        visit.setVisitId( resultSet.getLong( "VISIT_ID" ) );
        visit.setPatientId( resultSet.getLong( "PATIENT_ID" ) );
        visit.setPaymentTypeId( resultSet.getLong( "PAYMENT_TYPE_ID" ) );
        visit.setCurrentDoctorId( resultSet.getLong( "CURRENT_DOCTOR_ID" ) );
        visit.setDateOfVisit( new Date( resultSet.getDate( "DATE_OF_VISIT" ).getTime() ) );
        visit.setDentalFormulaId( resultSet.getLong( "DENTAL_FORMULA_ID" ) );
        visit.setCost( resultSet.getInt( "COST" ) );
        visit.setDescription( resultSet.getString( "DESCRIPTION" ) );
        visit.setDoctorForReplaceId( resultSet.getLong( "DOCTOR_FOR_REPLACE_ID" ) );
        visitList.add( visit );
      }
      return visitList;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }

  @Override
  public void deleteVisit( Long visitId )
  {
    Connection connection = null;
    Statement statement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      statement.executeQuery("DELETE FROM VISIT WHERE VISIT_ID = " + visitId.toString());
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void deletePatient(Long visitId) {
    Connection connection = null;
    Statement statement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      statement.executeQuery("DELETE FROM patient WHERE patient_ID = " + visitId.toString());
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void deleteDoctor(Long visitId) {
    Connection connection = null;
    Statement statement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      statement.executeQuery("DELETE FROM doctor WHERE doctor_ID = " + visitId.toString());
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void createVisit(Visit visit)
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      preparedStatement = connection.prepareStatement( "insert into visit (patient_id, payment_type_id, current_doctor_id, date_of_visit, dental_formula_id, cost, description, doctor_for_replace_id) values (?,?,?,?,?,?,?,?) ");
      preparedStatement.setLong( 1, visit.getPatientId() );
      preparedStatement.setLong( 2, visit.getPaymentTypeId() );
      preparedStatement.setLong( 3, visit.getCurrentDoctorId() );
      preparedStatement.setDate( 4, new java.sql.Date( visit.getDateOfVisit().getTime() ) );
      preparedStatement.setLong( 5, visit.getDentalFormulaId() );
      preparedStatement.setInt( 6, visit.getCost() );
      preparedStatement.setString( 7, visit.getDescription() );
      preparedStatement.setLong( 8, visit.getDoctorForReplaceId() );
      preparedStatement.executeQuery();
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(preparedStatement != null)
        {
          preparedStatement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void createDoctor( Doctor doctor )
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      preparedStatement = connection.prepareStatement( "insert into doctor (name, surname, middlename, date_of_birth, address) values (?,?,?,?,?) ");
      preparedStatement.setString( 1, doctor.getName() );
      preparedStatement.setString( 2, doctor.getSurname() );
      preparedStatement.setString( 3, doctor.getMiddlename() );
      preparedStatement.setDate( 4, new java.sql.Date( doctor.getDateOfBirth().getTime()));
      preparedStatement.setString( 5, doctor.getAddress() );
      preparedStatement.executeQuery();
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(preparedStatement != null)
        {
          preparedStatement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void createPatient(Patient patient)
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      preparedStatement = connection.prepareStatement( "insert into patient (current_doctor_id, dental_formula_id, name, surname, middlename, date_of_birth, address) values (?,?,?,?,?,?,?) ");
      preparedStatement.setLong( 1, patient.getCurrentDoctorId() );
      preparedStatement.setLong( 2, patient.getDentalFormulaId() );
      preparedStatement.setString( 3, patient.getName() );
      preparedStatement.setString( 4, patient.getSurname() );
      preparedStatement.setString( 5, patient.getMiddlename() );
      preparedStatement.setDate( 6, new java.sql.Date( patient.getDateOfBirth().getTime()));
      preparedStatement.setString( 7, patient.getAddress() );
      preparedStatement.executeQuery();
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(preparedStatement != null)
        {
          preparedStatement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public void updateVisit( Visit visit )
  {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      preparedStatement = connection.prepareStatement( "update visit set patient_id = ?, payment_type_id = ?, " +
          " current_doctor_id = ?, date_of_visit = ?, dental_formula_id = ?, cost = ?, description = ?," +
          "  doctor_for_replace_id = ? where visit_id = ? ");
      preparedStatement.setLong( 1, visit.getPatientId() );
      preparedStatement.setLong( 2, visit.getPaymentTypeId() );
      preparedStatement.setLong( 3, visit.getCurrentDoctorId() );
      preparedStatement.setDate( 4, new java.sql.Date( visit.getDateOfVisit().getTime() ) );
      preparedStatement.setLong( 5, visit.getDentalFormulaId() );
      preparedStatement.setInt( 6, visit.getCost() );
      preparedStatement.setString( 7, visit.getDescription() );
      preparedStatement.setLong( 8, visit.getDoctorForReplaceId() );
      preparedStatement.setLong( 9, visit.getVisitId());
      preparedStatement.executeQuery();
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(preparedStatement != null)
        {
          preparedStatement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public Visit getVisitById( Long visitId )
  {
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    try
    {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      connection = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","subd","password" );
      statement = connection.createStatement();
      resultSet = statement.executeQuery("SELECT VISIT.* FROM VISIT where visit_id = " + visitId.toString() );
      Visit visit = null;
      if( resultSet.next() )
      {
        visit = new Visit();
        visit.setVisitId( resultSet.getLong( "VISIT_ID" ) );
        visit.setPatientId( resultSet.getLong( "PATIENT_ID" ) );
        visit.setPaymentTypeId( resultSet.getLong( "PAYMENT_TYPE_ID" ) );
        visit.setCurrentDoctorId( resultSet.getLong( "CURRENT_DOCTOR_ID" ) );
        visit.setDateOfVisit( new Date( resultSet.getDate( "DATE_OF_VISIT" ).getTime() ) );
        visit.setDentalFormulaId( resultSet.getLong( "DENTAL_FORMULA_ID" ) );
        visit.setCost( resultSet.getInt( "COST" ) );
        visit.setDescription( resultSet.getString( "DESCRIPTION" ) );
        visit.setDoctorForReplaceId( resultSet.getLong( "DOCTOR_FOR_REPLACE_ID" ) );
      }
      return visit;
    }
    catch( Exception ex )
    {
      ex.printStackTrace();
    }
    finally
    {
      try
      {
        if(resultSet != null)
        {
          resultSet.close();
        }
        if(statement != null)
        {
          statement.close();
        }
        if(connection != null)
        {
          connection.close();
        }
      }
      catch(Exception ex)
      {
        ex.printStackTrace();
      }
    }
    return null;
  }
}
