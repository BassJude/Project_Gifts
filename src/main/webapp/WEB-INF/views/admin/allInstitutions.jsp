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

    <c:choose>
        <c:when test="${institutionDelete==true}">

            <p class="error">Usunąłeś instytucje!</p>
            <p class="error">Id: ${institution.id}, nazwa ${institution.name}</p>
        </c:when>

        <c:when test="${Invalid==true}">

            <p class="error">Nie możesz usunąć instytucji. Instytucja jest wskazywana przez rekordy w tabeli z darami!</p>
        </c:when>

        <c:when test="${registration==true}">

            <p class="error">Dodałeś nową instytucje</p>
        </c:when>




        <c:otherwise>

        </c:otherwise>
    </c:choose>


    <form action="${pageContext.request.contextPath}/admin/searchInstitution" method="get">
        Wyszukaj instytucje: <input type="text" name="search" placeholder="instytucja">

        <input type="submit" value="szukaj" >
    </form>

    <table border="1" >
        <thead>
        <tr>
            <td>Id</td>
            <td>Nazwa</td>
            <td>Cel i misja</td>
            <td>Co potrzebujemy</td>
            <td>Adres</td>
            <td>Email</td>
            <td>Telefon</td>
            <td>Lokalizacja</td>
            <td>Lista darów od użytkowników</td>
            <td>Edytuj</td>
            <td>Usuń</td>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${institutions}" var="institution">
            <tr>
                <td>${institution.id}</td>
                <td>${institution.name}</td>
                <td>${institution.missionTarget}</td>
                <td>${institution.needs}</td>
                <td>${institution.address}</td>
                <td>${institution.email}</td>
                <td>${institution.phone}</td>
                <td>${institution.location}</td>
                <td><a style="color: #309125" href="${pageContext.request.contextPath}/admin/giftsFromUsers/${institution.id}">Lista darów</a></td>


                <td><a style="color: #309125" href="${pageContext.request.contextPath}/admin/editInstitution/${institution.id}">Edytuj instytucje</a></td>
                <td><a style="color: #309125" href="${pageContext.request.contextPath}/admin/deleteInstitution/${institution.id}" onclick="return confirm('Czy na pewno skasować instytucje?')">Usuń
                    instytucje</a></td>


            </tr>

        </c:forEach>

        </tbody>


    </table>


</section>

<footer style="margin-top: 40px;">
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

            <button class="btn" type="submit">Usługa niedostępna</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <%--<div class="bottom-line--icons">--%>
            <%--<a href="#" class="btn btn--small"><img src="images/icon-facebook.svg"/></a>--%>
            <%--<a href="#" class="btn btn--small"><img src="images/icon-instagram.svg"/></a>--%>
        <%--</div>--%>
    </div>
</footer>

</body>
</html>
