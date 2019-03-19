<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<header>
    <%@ include file="../fragments/nav.jspf" %>
    <%@ include file="../fragments/navAdmin.jspf" %>
</header>

<%--<section class="login-page">--%>
<section>


    <h2>Edycja daru</h2>

    <p style="font-size: 20px">Id zmienianego daru: ${gift.id}.</p>
    <p style="font-size: 20px"> Dla organizacji: ${gift.institution.name}, ID instytucji: ${gift.institution.id}</p>
    <p style="font-size: 20px"> Darczyńca: ${gift.user.fullName}, ID darczyńcy: ${gift.user.id}</p>

    <form:form method="post" modelAttribute="gift">

        <div><input type="submit" value="Zapisz" style="width: 200px;height: 40px; font-size: 20px"></div>

        <div style="font-size: 20px" class="form-group">
            Ilość worków: <form:input path="bags"/>
            <form:errors path="bags" cssClass="error"/></div>

        <div style="font-size: 20px" class="form-group">
            Dary użytkownika: <form:textarea rows="3" cols="200" path="decsription"/>
            <form:errors path="decsription" cssClass="error"/></div>

        <div style="font-size: 20px" class="form-group">
            Telefon darczyńcy: <form:input path="phone"/>
            <form:errors path="phone" cssClass="error"/>
        </div>

        <div style="font-size: 20px" class="form-group">
            Miasto: <form:input path="city"/>
            <form:errors path="city" cssClass="error"/>
        </div>

        <div style="font-size: 20px" class="form-group">
            Kod pocztowy: <form:input path="zipCode"/>
            <form:errors path="zipCode" cssClass="error"/>
        </div>

        <div style="font-size: 20px" class="form-group">
            Ulica: <form:input path="street"/>
            <form:errors path="street" cssClass="error"/>
        </div>

        <div style="font-size: 20px" class="form-group">
            Numer domu: <form:input path="homeNumber"/>
            <form:errors path="homeNumber" cssClass="error"/>
        </div>

        <div style="font-size: 20px" class="form-group">
            Informacja dla kuriera: <form:textarea rows="3" cols="200" path="courierDecsription"/>
            <form:errors path="courierDecsription" cssClass="error"/>
        </div>

        <c:choose>


            <c:when test="${gift.status=='Courier'}">

                <p style="font-size: 20px;color: #912824">Kurier odbierze przesyłkę od darczyńcy : ${gift.pickUpTime}</p>
                <div style="font-size: 20px" class="form-group">
                    Status: <form:select path="status" items="${giftStatus}"/>
                    <form:errors path="status" cssClass="error"/></div>

            </c:when>

            <c:when test="${gift.status=='PickUp'}">

                <p style="font-size: 20px; color: #2350a5">Kurier odebrał przesyłkę od darczyńcy : ${gift.pickUpTime}</p>
                <div style="font-size: 20px" class="form-group">
                    Status: <form:select path="status" items="${giftStatus}"/>
                    <form:errors path="status" cssClass="error"/></div>

            </c:when>

            <c:when test="${gift.status=='Sent'}">

                <p style="font-size: 20px;color: green">Paczka przekazana instytucji dnia: ${gift.sendTime}</p>
                <form:hidden path="status"></form:hidden>

            </c:when>


            <c:otherwise>

            </c:otherwise>
        </c:choose>





    </form:form>


</section>

<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form>
            <div class="form-group form-group--50">
                <input type="text" name="name" placeholder="Imię"/>
            </div>
            <div class="form-group form-group--50">
                <input type="text" name="surname" placeholder="Nazwisko"/>
            </div>

            <div class="form-group">
                <textarea name="message" placeholder="Wiadomość" rows="1"></textarea>
            </div>

            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="images/icon-facebook.svg"/></a>
            <a href="#" class="btn btn--small"><img src="images/icon-instagram.svg"/></a>
        </div>
    </div>
</footer>

</body>
</html>
