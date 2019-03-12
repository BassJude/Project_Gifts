<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<header>
    <%@ include file="../fragments/nav.jspf" %>
    <%@ include file="../fragments/navAdmin.jspf" %>
</header>


<div>

    <h1>Edycja użytkownika</h1>

    <p>Id zmienianego usera: ${user.id}. Mail zmienianego użytkownika: ${user.email}</p>
    <div>div</div>
    <div>div</div>
    <div>div</div>

    <form:form method="post" modelAttribute="user">
        <div>div</div>
        <div>div</div>
        <div>div</div>

        <p>Imię: <form:input path="firstName"/>
            <form:errors path="firstName" cssClass="error" /></p>

        <p>Nazwisko: <form:input path="lastName"/>
            <form:errors path="lastName" cssClass="error" /></p>

        <p>Czy jest superUserem <form:checkbox path="superUser"/>
            <form:errors path="superUser" cssClass="error" /></p>


        <input type="submit" value="Zapisz">


    </form:form>

    <div>div</div>
    <div>div</div>
    <div>div</div>



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
