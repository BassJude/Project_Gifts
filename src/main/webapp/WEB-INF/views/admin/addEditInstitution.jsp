<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<header>
    <%@ include file="../fragments/nav.jspf" %>
    <%@ include file="../fragments/navAdmin.jspf" %>
</header>

<%--<section class="login-page">--%>
<div>

    <h1>Edycja instytucji</h1>

    <p>Id zmienianej instytucji: ${institution.id} </p>

    <form:form method="post" modelAttribute="institution">

        <p>Nazwa: <form:textarea rows="5" cols="80" path="name"/>
            <form:errors path="name" cssClass="error" /></p>

        <p>Cel i misja <form:textarea rows="5" cols="80" path="missionTarget"/>
            <form:errors path="missionTarget" cssClass="error" /></p>

        <p>Nasze potrzeby: <form:textarea rows="5" cols="80" path="needs"/>
            <form:errors path="needs" cssClass="error" /></p>



        <div>Adres: <form:textarea rows="5" cols="80" path="address"/>
            <form:errors path="address" cssClass="error" /></div>

        <div>Email: <form:textarea rows="5" cols="80" path="email"/>
            <form:errors path="email" cssClass="error" /></div>

        <div>Telefon:<form:textarea rows="5" cols="80" path="phone"/>
            <form:errors path="phone" cssClass="error" /></div>

        <div> Województwo: <form:select path="location" items="${locations}" />
            <form:errors path="location" cssClass="error" /></div>


        <div><input type="submit" value="Zapisz"></div>


    </form:form>



</div>

<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form>
            <div class="form-group form-group--50">
                <input type="text" name="name" placeholder="Imię" />
            </div>
            <div class="form-group form-group--50">
                <input type="text" name="surname" placeholder="Nazwisko" />
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
