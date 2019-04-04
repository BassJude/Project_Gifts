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
    <div><span class="error"><c:if test="${emailSent==true}">${messageSent}</c:if></span></div>
    <form action="${pageContext.request.contextPath}/recoverPassword" method="post">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" value="${email}"/>
            <span class="error"><c:if test="${emailInvalid==true}">${messageEmail}</c:if></span></div>
        </div>
        <%--<div class="form-group">--%>
            <%--<input type="password" name="pass" placeholder="Hasło" value="${pass}"/>--%>
            <%--<span class="error" ><c:if test="${passInvalid==true}">${messagePass}</c:if></span>--%>

            <%--<a href="${pageContext.request.contextPath}/recoverPassword" class="btn btn--small btn--without-border reset-password">Odzyskiwanie hasła</a>--%>
        <%--</div>--%>

        <div class="form-group form-group--buttons">
            <%--<a href="${pageContext.request.contextPath}/registration" class="btn btn--without-border">Załóż konto</a>--%>
            <button class="btn" type="submit">Wyślij</button>
        </div>
    </form>
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
