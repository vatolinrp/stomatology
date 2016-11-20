package by.bsu.vatolinrp.subd.util;

import by.bsu.vatolinrp.subd.model.Patient;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class ClinicUtil
{
  public static String getHtml( List<Patient> patients )
  {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    sb.append( "<tr>" )
      .append( "<td>" )
      .append( "patientId       | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "currentDoctorId | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "dentalFormulaId | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "name            | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "surname         | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "middlename      | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "dateOfBirth     | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "address" )
      .append( "</td>" )
      .append( "</tr>" );
    for( Patient patient : patients ) {
      sb.append( "<tr>" );
      sb.append( "<td>" );
      sb.append( patient.getPatientId() );
      printSpaces( 15 - patient.getPatientId().toString().length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( patient.getCurrentDoctorId() );
      printSpaces( 15 - patient.getCurrentDoctorId().toString().length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( patient.getDentalFormulaId() );
      printSpaces( 15 - patient.getDentalFormulaId().toString().length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( patient.getName() );
      printSpaces( 15 - patient.getName().length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( patient.getSurname() );
      printSpaces( 15 - patient.getSurname().length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( patient.getMiddlename() );
      printSpaces( 15 - patient.getMiddlename().length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( simpleDateFormat.format( patient.getDateOfBirth() ) );
      printSpaces( 15 - simpleDateFormat.format( patient.getDateOfBirth() ).length(), sb );
      sb.append( " | " );
      sb.append( "</td>" );
      sb.append( "<td>" );
      sb.append( patient.getAddress() );
      sb.append( "</td>" );
      sb.append( "</tr>" );
    }
    return sb.toString();
  }

  public static void printPatients( List<Patient> patients )
  {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    System.out.print( "patientId       | " );
    System.out.print( "currentDoctorId | " );
    System.out.print( "dentalFormulaId | " );
    System.out.print( "name            | " );
    System.out.print( "surname         | " );
    System.out.print( "middlename      | " );
    System.out.print( "dateOfBirth     | " );
    System.out.println( "address" );
    System.out.println( "------------------------------------------" );
    for( Patient patient : patients ) {
      System.out.print( patient.getPatientId() );
      printSpaces( 15 - patient.getPatientId().toString().length() );
      System.out.print( " | " );
      System.out.print( patient.getCurrentDoctorId() );
      printSpaces( 15 - patient.getCurrentDoctorId().toString().length() );
      System.out.print( " | " );
      System.out.print( patient.getDentalFormulaId() );
      printSpaces( 15 - patient.getDentalFormulaId().toString().length() );
      System.out.print( " | " );
      System.out.print( patient.getName() );
      printSpaces( 15 - patient.getName().length() );
      System.out.print( " | " );
      System.out.print( patient.getSurname() );
      printSpaces( 15 - patient.getSurname().length() );
      System.out.print( " | " );
      System.out.print( patient.getMiddlename() );
      printSpaces( 15 - patient.getMiddlename().length() );
      System.out.print( " | " );
      System.out.print( simpleDateFormat.format( patient.getDateOfBirth() ) );
      printSpaces( 15 - simpleDateFormat.format( patient.getDateOfBirth() ).length() );
      System.out.print( " | " );
      System.out.println( patient.getAddress() );
    }

  }

  public static void printSpaces( int numberOfSpaces, StringBuilder sb )
  {
    while ( numberOfSpaces > 0 ) {
      sb.append( "_" );
      numberOfSpaces--;
    }
  }

  public static void printSpaces( int numberOfSpaces )
  {
    while ( numberOfSpaces > 0 ) {
      System.out.print( " " );
      numberOfSpaces--;
    }
  }

  public static void printSumsPerMonth( Map<String, String> map )
  {
    StringBuilder sb = new StringBuilder();
    System.out.print( "month     | " );
    System.out.println( "total     | " );
    for( int i = 1; i <= 12; i++ ) {
      System.out.print( String.valueOf( i ) );
      printSpaces( 12 - String.valueOf( i ).length() );
      if ( map.get( String.valueOf( i ) ) != null ) {
        System.out.println( map.get( String.valueOf( i ) ) );
      } else {
        System.out.println( 0 );
      }
    }
  }

  public static String getHtml( Map<String, String> map )
  {
    StringBuilder sb = new StringBuilder();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
    sb.append( "<tr>" )
      .append( "<td>" )
      .append( "month     | " )
      .append( "</td>" )
      .append( "<td>" )
      .append( "total     | " )
      .append( "</td>" )
      .append( "</tr>" );
    for( int i = 1; i <= 12; i++ ) {
      System.out.print( String.valueOf( i ) );
      printSpaces( 12 - String.valueOf( i ).length() );
      if ( map.get( String.valueOf( i ) ) != null ) {
        System.out.println( map.get( String.valueOf( i ) ) );
      } else {
        System.out.println( 0 );
      }
    }
    sb.append( "</tr>" );
    return sb.toString();
  }
}
