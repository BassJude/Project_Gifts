<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<header>
    <%@ include file="../fragments/nav.jspf" %>

</header>


<div>



    <h2>Profil użytkownika</h2>
        <h1>Twój email: ${user.email}</h1>
        <h1>Chcesz zmienic hasło? -> <a href="${pageContext.request.contextPath}/user/changePassword" >Kliknij</a> </h1>
        <c:choose>


        <c:when test="${changeSucces==true}">

        <p class="error">Hasło zmienione </p>

        </c:when>


        <c:otherwise>

        </c:otherwise>
        </c:choose>

        <form:form method="post" modelAttribute="user">

        <div style="font-size: 20px" class="form-group">
            Imię: <form:input path="firstName"/>
            <form:errors path="firstName" cssClass="error" /></div>

        <div style="font-size: 20px" class="form-group">
            Nazwisko: <form:input path="lastName"/>
            <form:errors path="lastName" cssClass="error" />
        </div>


        <input type="submit" value="Zapisz">


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
