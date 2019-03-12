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
        <c:when test="${userDelete==true}">

         <p class="error">Usunąłeś uzytkownika!</p>
         <p class="error">Id: ${user.id}, email: ${user.email}, imię: ${user.firstName}, nazwisko: ${user.lastName}</p>
        </c:when>

        <c:when test="${Invalid==true}">

            <p class="error">Nie możesz usunąć użytkownika. Użytkownik jest wskazywany przez rekordy w tabeli z darami!</p>
        </c:when>

        <c:when test="${AdminInvalid==true}">

            <p class="error">Nie możesz usunąć użytkownika, który jest ostatnim adminem</p>
        </c:when>


        <c:otherwise>

        </c:otherwise>
    </c:choose>


    <form action="/admin/search" method="get">
        Wyszukaj użytkownika: <input type="text" name="search" placeholder="nazwisko">

        <input type="submit" value="szukaj" >
    </form>

    <table border="1" >
        <thead>
        <tr>
            <td>Id</td>
            <td>email</td>
            <td>Imię</td>
            <td>Nazwisko</td>
            <td>Dary użytkownika</td>
            <td>Hasło</td>
            <td>Admin</td>
            <td>Edytuj</td>
            <td>Usuń</td>
        </tr>

        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td><a style="color: #309125" href="/admin/gifts/${user.id}">Lista darów</a></td>
                <td>${user.password}</td>
                <td>${user.superUser}</td>

                <td><a style="color: #309125" href="/admin/editUser/${user.id}">Edytuj użytkownika</a></td>
                <td><a style="color: #309125" href="/admin/deleteUser/${user.id}" onclick="return confirm('Czy na pewno skasować pytanie?')">Usuń
                    użytkownika</a></td>


            </tr>

        </c:forEach>

        </tbody>


    </table>


</section>

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
