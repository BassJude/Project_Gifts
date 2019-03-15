<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="fragments/head.jspf" %>
<body>
<header>

    <%@ include file="fragments/nav.jspf" %>

</header>

<section class="login-page">
    <%--<h2>Załóż konto</h2>--%>
    <%--<form>--%>
        <%--<div class="form-group">--%>
            <%--<input type="email" name="email" placeholder="Email" />--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input type="password" name="password" placeholder="Hasło" />--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input type="password" name="password2" placeholder="Powtórz hasło" />--%>
        <%--</div>--%>

        <%--<div class="form-group form-group--buttons">--%>
            <%--<a href="login.html" class="btn btn--without-border">Zaloguj się</a>--%>
            <%--<button class="btn" type="submit">Załóż konto</button>--%>
        <%--</div>--%>
    <%--</form>--%>

    <h2>Załóż konto</h2>
    <span class="error">
        <c:if test="${invalid==true}">
            ${message}
        </c:if></span>
<form:form method="post" modelAttribute="user">
        <div style="font-size: 20px" class="form-group">
            <form:input path="email"  placeholder="email" />
            <form:errors path="email" cssClass="error"/>
        </div>
        <div style="font-size: 20px" class="form-group">
            <form:password path="password" placeholder="hasło"/>
            <form:errors path="password" cssClass="error"/>
        </div>
        <div style="font-size: 20px" class="form-group">
            <form:password path="password2" placeholder="powtórz hasło"/>
            <form:errors path="password2" cssClass="error"/>
        </div>

    <div style="font-size: 20px" class="form-group">
        <form:input path="firstName" placeholder="imię"/>
        <form:errors path="firstName" cssClass="error"/>
    </div>

    <div style="font-size: 20px" class="form-group">
        <form:input path="lastName" placeholder="nazwisko"/>
        <form:errors path="lastName" cssClass="error"/>
    </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
</form:form>
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
            <textarea
                    name="message"
                    placeholder="Wiadomość"
                    rows="1"
            ></textarea>
            </div>

            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"
            ><img src="images/icon-facebook.svg"
            /></a>
            <a href="#" class="btn btn--small"
            ><img src="images/icon-instagram.svg"
            /></a>
        </div>
    </div>
</footer>

</body>
</html>
