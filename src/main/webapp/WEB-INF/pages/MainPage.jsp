<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <c:if test="${not empty patients}" >
            <tr align="center" >
                <td> patientId </td>
                <td> currentDoctorId </td>
                <td> dentalFormulaId </td>
                <td> name </td>
                <td> surname </td>
                <td> middlename </td>
                <td> dateOfBirth </td>
                <td> address </td>
            </tr>
        </c:if>
        <c:forEach var="patient" items="${patients}" >
        <tr align="center" >
            <td> <c:out value="${patient.patientId}"/> </td>
            <td> <c:out value="${patient.currentDoctorId}"/> </td>
            <td> <c:out value="${patient.dentalFormulaId}"/> </td>
            <td> <c:out value="${patient.name}"/> </td>
            <td> <c:out value="${patient.surname}"/> </td>
            <td> <c:out value="${patient.middlename}"/> </td>
            <td> <c:out value="${patient.dateOfBirth}"/> </td>
            <td> <c:out value="${patient.address}"/> </td>
        </tr>
        </c:forEach>
    </table>

    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <c:forEach var="reportLine" items="${reportLines}" >
        <tr align="center" >
            <td> <c:out value="${reportLine.monthName}"/> </td>
            <td> <c:out value="${reportLine.total}"/> </td>
        </tr>
        </c:forEach>
    </table>


    <form action="patientsByDate" method="post" >
      Please enter date in this format dd-MM-yyyy:<br>
      <input type="text" name="date"<br>
      <input type="submit" value="Get by date">
    </form>

    <form action="getVisitForUpdate" method="post" >
      Please enter visit id<br>
      <input type="text" name="visitId"<br>
      <input type="submit" value="Get visit for update">
    </form>

    <form action="deleteVisit" method="post" >
      <input type="text" name="visitId"<br>
      <input type="submit" value="delete visit by id">
    </form>

    <form action="deleteDoctor" method="post" >
      <input type="text" name="doctorId"<br>
      <input type="submit" value="delete doctor by id">
    </form>

    <form action="deletePatient" method="post" >
      <input type="text" name="patientId"<br>
      <input type="submit" value="delete patient by id">
    </form>

    <form action="createVisit" method="post" >
        Enter patient id <br>
      <input type="text" name="patientId"><br>
        Enter payment id <br>
      <input type="text" name="paymentTypeId"><br>
        Enter current doctor id <br>
      <input type="text" name="currentDoctorId"><br>
        Enter date of visit in this format dd-MM-yyyy<br>
      <input type="text" name="dateOfVisit"><br>
        Enter dental formula id <br>
      <input type="text" name="dentalFormulaId"><br>
        Enter cost <br>
      <input type="text" name="cost"><br>
       Enter description <br>
      <input type="text" name="description"><br>
       Enter doctor for replace id <br>
      <input type="text" name="doctorForReplaceId"><br>
      <input type="submit" value="create"><br>
    </form>

    <form action="createDoctor" method="post" >
        Enter doctor name <br>
      <input type="text" name="name"><br>
        Enter doctor surname <br>
      <input type="text" name="surname"><br>
        Enter doctor middlename <br>
      <input type="text" name="middlename"><br>
        Enter doctor date of birth in format dd-MM-yyyy<br>
      <input type="text" name="dateOfBirth"><br>
        Enter doctor address <br>
      <input type="text" name="address"><br>

      <input type="submit" value="create doctor"><br>
    </form>

    <form action="createPatient" method="post" >
        Enter patient name <br>
      <input type="text" name="name"><br>
        Enter patient surname <br>
      <input type="text" name="surname"><br>
        Enter patient middlename <br>
      <input type="text" name="middlename"><br>
        Enter patient date of birth in format dd-MM-yyyy<br>
      <input type="text" name="dateOfBirth"><br>
        Enter patient address <br>
      <input type="text" name="address"><br>
        Enter currentDoctorId <br>
      <input type="text" name="currentDoctorId"><br>
        Enter dentalFormulaId <br>
      <input type="text" name="dentalFormulaId"><br>

      <input type="submit" value="create patient"><br>
    </form>

    <c:if test="${not empty visitForUpdate}">
    <form action="updateVisit" method="post" >
        visit id <br>
      <input type="text" name="visitId" value="${visitForUpdate.visitId}"><br>
        Update patient id <br>
      <input type="text" name="patientId" value="${visitForUpdate.patientId}"><br>
        Update payment id <br>
      <input type="text" name="paymentTypeId" value="${visitForUpdate.paymentTypeId}"><br>
        Update current doctor id <br>
      <input type="text" name="currentDoctorId" value="${visitForUpdate.currentDoctorId}"><br>
        Update date of visit in this format dd-MM-yyyy<br>
      <input type="text" name="dateOfVisit" value="${visitForUpdate.dateOfVisit}"><br>
        Update dental formula id <br>
      <input type="text" name="dentalFormulaId" value="${visitForUpdate.dentalFormulaId}"><br>
        Update cost <br>
      <input type="text" name="cost" value="${visitForUpdate.cost}"><br>
       Update description <br>
      <input type="text" name="description" value="${visitForUpdate.description}" ><br>
       Update doctor for replace id <br>
      <input type="text" name="doctorForReplaceId" value="${visitForUpdate.doctorForReplaceId}"><br>
      <input type="submit" value="update"><br>
    </form>
    </c:if>

    <form action="patientsWithRightDoctors"  >
      <input type="submit" value="get with right doctors">
    </form>

    <form action="sumsForEachMonth"  >
      <input type="submit" value="get sums for each month" >
    </form>

    All patients : </br>
    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <tr align="center" >
            <td> patientId </td>
            <td> currentDoctorId </td>
            <td> dentalFormulaId </td>
            <td> name </td>
            <td> surname </td>
            <td> middlename </td>
            <td> dateOfBirth </td>
            <td> address </td>
        </tr>
        <c:forEach var="patient" items="${allPatients}" >
        <tr align="center" >
            <td> <c:out value="${patient.patientId}"/> </td>
            <td> <c:out value="${patient.currentDoctorId}"/> </td>
            <td> <c:out value="${patient.dentalFormulaId}"/> </td>
            <td> <c:out value="${patient.name}"/> </td>
            <td> <c:out value="${patient.surname}"/> </td>
            <td> <c:out value="${patient.middlename}"/> </td>
            <td> <c:out value="${patient.dateOfBirth}"/> </td>
            <td> <c:out value="${patient.address}"/> </td>
        </tr>
        </c:forEach>
    </table>
     All doctors : </br>
    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <tr align="center" >
            <td> doctorId </td>
            <td> name </td>
            <td> surname </td>
            <td> middlename </td>
            <td> dateOfBirth </td>
            <td> address </td>
        </tr>
        <c:forEach var="doctor" items="${allDoctors}" >
        <tr align="center" >
            <td> <c:out value="${doctor.doctorId}"/> </td>
            <td> <c:out value="${doctor.name}"/> </td>
            <td> <c:out value="${doctor.surname}"/> </td>
            <td> <c:out value="${doctor.middlename}"/> </td>
            <td> <c:out value="${doctor.dateOfBirth}"/> </td>
            <td> <c:out value="${doctor.address}"/> </td>
        </tr>
        </c:forEach>
    </table>

     All payment types : </br>
    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <tr align="center" >
            <td> paymentTypeId </td>
            <td> paymentType </td>
        </tr>
        <c:forEach var="paymentType" items="${allPaymentTypes}" >
        <tr align="center" >
            <td> <c:out value="${paymentType.paymentTypeId}"/> </td>
            <td> <c:out value="${paymentType.paymentType}"/> </td>
        </tr>
        </c:forEach>
    </table>
     All visits : </br>
    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <tr align="center" >
            <td> visitId </td>
            <td> patientId </td>
            <td> paymentTypeId </td>
            <td> currentDoctorId </td>
            <td> dateOfVisit </td>
            <td> dentalFormulaId </td>
            <td> cost </td>
            <td> description </td>
            <td> doctorForReplaceId </td>
        </tr>
        <c:forEach var="visit" items="${allVisits}" >
        <tr align="center" >
            <td> <c:out value="${visit.visitId}"/> </td>
            <td> <c:out value="${visit.patientId}"/> </td>
            <td> <c:out value="${visit.paymentTypeId}"/> </td>
            <td> <c:out value="${visit.currentDoctorId}"/> </td>
            <td> <c:out value="${visit.dateOfVisit}"/> </td>
            <td> <c:out value="${visit.dentalFormulaId}"/> </td>
            <td> <c:out value="${visit.cost}"/> </td>
            <td> <c:out value="${visit.description}"/> </td>
            <td> <c:out value="${visit.doctorForReplaceId}"/> </td>
        </tr>
        </c:forEach>
    </table>
     All dental formulas : </br>
    <table border="5" width="100%" cellpadding="4" cellspacing="3" >
        <tr align="center" >
            <td> dentalFormulaId </td>
            <td> tooth11 </td>
            <td> tooth12 </td>
            <td> tooth13 </td>
            <td> tooth14 </td>
            <td> tooth15 </td>
            <td> tooth16 </td>
            <td> tooth17 </td>
            <td> tooth18 </td>
            <td> tooth21 </td>
            <td> tooth22 </td>
            <td> tooth23 </td>
            <td> tooth24 </td>
            <td> tooth25 </td>
            <td> tooth26 </td>
            <td> tooth27 </td>
            <td> tooth28 </td>
            <td> tooth31 </td>
            <td> tooth32 </td>
            <td> tooth33 </td>
            <td> tooth34 </td>
            <td> tooth35 </td>
            <td> tooth36 </td>
            <td> tooth37 </td>
            <td> tooth38 </td>
            <td> tooth41 </td>
            <td> tooth42 </td>
            <td> tooth43 </td>
            <td> tooth44 </td>
            <td> tooth45 </td>
            <td> tooth46 </td>
            <td> tooth47 </td>
            <td> tooth48 </td>
        </tr>
        <c:forEach var="dentalFormula" items="${allDentalFormulas}" >
        <tr align="center" >
            <td> <c:out value="${dentalFormula.dentalFormulaId}"/> </td>
            <td> <c:out value="${dentalFormula.tooth11}"/> </td>
            <td> <c:out value="${dentalFormula.tooth12}"/> </td>
            <td> <c:out value="${dentalFormula.tooth13}"/> </td>
            <td> <c:out value="${dentalFormula.tooth14}"/> </td>
            <td> <c:out value="${dentalFormula.tooth15}"/> </td>
            <td> <c:out value="${dentalFormula.tooth16}"/> </td>
            <td> <c:out value="${dentalFormula.tooth17}"/> </td>
            <td> <c:out value="${dentalFormula.tooth18}"/> </td>
            <td> <c:out value="${dentalFormula.tooth21}"/> </td>
            <td> <c:out value="${dentalFormula.tooth22}"/> </td>
            <td> <c:out value="${dentalFormula.tooth23}"/> </td>
            <td> <c:out value="${dentalFormula.tooth24}"/> </td>
            <td> <c:out value="${dentalFormula.tooth25}"/> </td>
            <td> <c:out value="${dentalFormula.tooth26}"/> </td>
            <td> <c:out value="${dentalFormula.tooth27}"/> </td>
            <td> <c:out value="${dentalFormula.tooth28}"/> </td>
            <td> <c:out value="${dentalFormula.tooth31}"/> </td>
            <td> <c:out value="${dentalFormula.tooth32}"/> </td>
            <td> <c:out value="${dentalFormula.tooth33}"/> </td>
            <td> <c:out value="${dentalFormula.tooth34}"/> </td>
            <td> <c:out value="${dentalFormula.tooth35}"/> </td>
            <td> <c:out value="${dentalFormula.tooth36}"/> </td>
            <td> <c:out value="${dentalFormula.tooth37}"/> </td>
            <td> <c:out value="${dentalFormula.tooth38}"/> </td>
            <td> <c:out value="${dentalFormula.tooth41}"/> </td>
            <td> <c:out value="${dentalFormula.tooth42}"/> </td>
            <td> <c:out value="${dentalFormula.tooth43}"/> </td>
            <td> <c:out value="${dentalFormula.tooth44}"/> </td>
            <td> <c:out value="${dentalFormula.tooth45}"/> </td>
            <td> <c:out value="${dentalFormula.tooth46}"/> </td>
            <td> <c:out value="${dentalFormula.tooth47}"/> </td>
            <td> <c:out value="${dentalFormula.tooth48}"/> </td>
        </tr>
        </c:forEach>
    </table>

</body>
</html>
