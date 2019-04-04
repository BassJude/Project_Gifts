<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="fragments/head.jspf" %>
<body>

<header>
    <%@ include file="fragments/nav.jspf" %>
</header>

<section class="login-page">
    <h2>Resetowanie hasła</h2>

    <c:if test="${passInvalid==true}">
        <p class="error">${messagePass}</p>
    </c:if>

    <c:choose>
        <c:when test="${errorToken==true}">

            <p class="error">${messageToken}</p>

        </c:when>

        <c:when test="${correctToken==true}">

            <form action="${pageContext.request.contextPath}/token/${token}/${id}" method="post">
               <p> <input style="width: 200px;height: 40px; font-size: 20px" type="password" name="password1" placeholder="nowe hasło"></p>
                <p> <input style="width: 200px;height: 40px; font-size: 20px" type="password" name="password2" placeholder="powtórz nowe hasło"></p>
                <p> <input style="width: 200px;height: 40px; font-size: 20px" type="submit" value="Zmień hasło"></p>
            </form>


        </c:when>

        <c:when test="${newPass==true}">

            <p style="font-size: 20px; color: #2350a5" >Ustawiłeś nowe hasło. Zaloguj się używając nowego hasła</p>

        </c:when>



        <c:otherwise>

        </c:otherwise>
    </c:choose>



    <%--<div><span class="error"><c:if test="${emailSent==true}">${messageSent}</c:if></span></div>--%>
    <%--<form action="${pageContext.request.contextPath}/recoverPassword" method="post">--%>
        <%--<div class="form-group">--%>
            <%--<input type="email" name="email" placeholder="Email" value="${email}"/>--%>
            <%--<span class="error"><c:if test="${emailInvalid==true}">${messageEmail}</c:if></span></div>--%>
        <%--</div>--%>
        <%----%>
        <%--<div class="form-group form-group--buttons">--%>
        <%----%>
            <%--<button class="btn" type="submit">Wyślij</button>--%>
        <%--</div>--%>
    <%--</form>--%>
    <%----%>

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
