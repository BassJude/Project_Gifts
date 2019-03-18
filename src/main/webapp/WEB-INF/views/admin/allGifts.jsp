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
<div>

    <c:choose>
        <c:when test="${deleteGift==true}">

            <p class="error">Usunąłeś dar!</p>

        </c:when>


        <c:when test="${showUserGifts==true}">

            <p class="error">Lista darów wybranego użytkownika. Id: ${user.id}, Imię i nazwisko: ${user.fullName}</p>
            <p class="error">Ilość pozycji: ${quantity}</p>

        </c:when>

        <c:when test="${showInstitutionGifts==true}">

            <p class="error">Lista darów wybranej instytucji. Id: ${institution.id}, Nazwa: ${institution.name}</p>
            <p class="error">Ilość pozycji: ${quantity}</p>

        </c:when>

        <%--<c:when test="${Invalid==true}">--%>

            <%--<p class="error">Nie możesz usunąć instytucji. Instytucja jest wskazywana przez rekordy w tabeli z darami!</p>--%>
        <%--</c:when>--%>

        <%--<c:when test="${registration==true}">--%>

            <%--<p class="error">Dodałeś nową instytucje</p>--%>
        <%--</c:when>--%>




        <c:otherwise>

        </c:otherwise>
    </c:choose>


    <%--<form action="/admin/searchGifts" method="get">--%>
        <%--Wyszukaj dary: <input type="text" name="search" placeholder="wyszukaj dary">--%>

        <%--<input type="submit" value="szukaj" >--%>
    <%--</form>--%>

    <table border="1" >
        <thead>
        <tr>
            <td>Id</td>
            <td>Organizacja</td>
            <td>Darczyńca</td>
            <td>Ile worków</td>
            <td>Dary użytkownika</td>
            <td>Kontakt z darczyńcą</td>
            <td>Miasto</td>
            <td>Kod pocztowy</td>
            <td>Ulica</td>
            <td>Numer domu</td>
            <td>Informacja dla kuriera</td>
            <td>Data odbioru paczki</td>
            <td>Przekazanie paczki</td>
            <td>Status</td>
            <td>Edytuj</td>
            <td>Usuń</td>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${gifts}" var="gift">
            <tr>
                <td>${gift.id}</td>
                <td>${gift.institution.name}</td>
                <td>${gift.user.fullName}</td>
                <td>${gift.bags}</td>
                <td>${gift.decsription}</td>
                <td>${gift.phone}</td>
                <td>${gift.city}</td>
                <td>${gift.zipCode}</td>
                <td>${gift.street}</td>
                <td>${gift.homeNumber}</td>
                <td>${gift.courierDecsription}</td>
                <td>${gift.pickUpTime}</td>
                <td>${gift.sendTime}</td>
                <td>${gift.status}</td>
                <td><a style="color: #309125" href="/admin/editGift/${gift.id}">Edytuj dar</a></td>
                <td><a style="color: #309125" href="/admin/deleteGift/${gift.id}" onclick="return confirm('Czy na pewno skasować?')">Usuń
                    dar</a></td>


            </tr>

        </c:forEach>

        </tbody>


    </table>


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
