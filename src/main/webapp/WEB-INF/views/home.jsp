<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="fragments/head.jspf" %>
<body>

<header class="header--main-page" style="background-image: url(${pageContext.request.contextPath}/images/header-bg.jpg); background-repeat: no-repeat;">

    <%@ include file="fragments/nav.jspf" %>

        <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <c:choose>
                    <c:when test="${registration==true}">

                        <p style="color: #2350a5">Dziękujemy za rejestracje!</p> Zaloguj się i zacznij pomagać!<br />
                        Oddaj niechciane rzeczy w zaufane ręce
                    </c:when>

                    <c:when test="${login==true}">

                        <p style="color: #2350a5">Zalogowałeś się.</p>  Zacznij pomagać!<br />
                        Oddaj niechciane rzeczy w zaufane ręce
                    </c:when>

                    <c:when test="${logout==true}">

                        <p style="color: #2350a5">Wylogowałeś się.</p> Dziękujemy za wizyte!<br />
                        Miłego dnia.

                    </c:when>

                    <c:otherwise>
                        Zacznij pomagać!<br />
                        Oddaj niechciane rzeczy w zaufane ręce
                    </c:otherwise>
                </c:choose>

            </h1>

            <ul class="slogan--buttons">
                <li><a href="${pageContext.request.contextPath}/user/addGift" class="btn btn--large">Oddaj rzeczy</a></li>
                <%--<li><a href="#" class="btn btn--large">Zorganizuj zbiórkę</a></li>--%>
            </ul>
        </div>
    </div>
</header>

<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${numberOfBags}</em>
            <h3>Oddanych worków</h3>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius est beatae, quod accusamus illum tempora!</p>
        </div>

        <div class="stats--item">
            <em>${numberOfOrganisations}</em>
            <h3>Wspartych organizacji</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Laboriosam magnam, sint nihil cupiditate quas quam.</p>
        </div>

        <div class="stats--item">
            <em>${numberOfGifts}</em>
            <h3>Zorganizowanych zbiórek</h3>
            <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quos dolores commodi error. Natus, officiis vitae?</p>
        </div>
    </div>
</section>

<section class="steps">
    <h2 id="steps1">Wystarczą 4 proste kroki</h2>

    <div class="steps--container">
        <div class="steps--item">
            <span class="icon icon--hands"></span>
            <h3>Wybierz rzeczy</h3>
            <p>ubrania, zabawki, sprzęt i inne</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--arrow"></span>
            <h3>Spakuj je</h3>
            <p>skorzystaj z worków na śmieci</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--glasses"></span>
            <h3>Zdecyduj komu chcesz pomóc</h3>
            <p>wybierz zaufane miejsce</p>
        </div>
        <div class="steps--item">
            <span class="icon icon--courier"></span>
            <h3>Zamów kuriera</h3>
            <p>kurier przyjedzie w dogodnym terminie</p>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/registration" class="btn btn--large">Załóż konto</a>
</section>

<section class="about-us">
    <div class="about-us--text" id="aboutUs">
        <h2>O nas</h2>
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptas vitae animi rem pariatur incidunt libero optio esse quisquam illo omnis.</p>
        <img src="${pageContext.request.contextPath}/images/signature.svg" class="about-us--text-signature" alt="Signature" />
    </div>
    <div class="about-us--image"><img src="${pageContext.request.contextPath}/images/about-us.jpg" alt="People in circle" /></div>
</section>

<section class="help">
    <h2 id="whoWeHelp">Komu pomagamy?</h2>



    <div class="help--slides active" data-id="1">
        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy. Możesz sprawdzić czym się zajmują, komu pomagają i czego potrzebują.</p>

        <ul class="help--slides-items">
            <c:forEach items="${institutionsHome}" var="institution" begin="0" varStatus="theCount">
            <li data-organisation="${theCount.count}">
                <div class="col">
                    <div class="title">${institution.name}</div>
                    <div class="subtitle">Cel i misja: ${institution.missionTarget}</div>
                </div>

                <div class="col"><div class="text">${institution.needs}</div></div>
            </li>

            </c:forEach>
        </ul>


    </div>


    <%--<ul class="help--buttons">--%>
        <%--<li id="previous"><a href="#" class="btn btn--without-border">Poprzednie</a></li>--%>
        <%--<li id="next"><a href="#" class="btn btn--without-border">Następne</a></li>--%>

    <%--</ul>--%>

</section>

<footer>
    <div class="contact" id="navContact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact">
            <div class="form-group form-group--50"><input type="text" name="name" placeholder="Imię" /></div>
            <div class="form-group form-group--50"><input type="text" name="surname" placeholder="Nazwisko" /></div>

            <div class="form-group"><textarea name="message" placeholder="Wiadomość" rows="1"></textarea></div>

            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <%--<div class="bottom-line--icons">--%>
            <%--<a href="#" class="btn btn--small"><img src="${pageContext.request.contextPath}/images/icon-facebook.svg"/></a> <a href="#" class="btn btn--small"><img src="${pageContext.request.contextPath}/images/icon-instagram.svg"/></a>--%>
        <%--</div>--%>
    </div>
</footer>



<%--<%@ include file="fragments/footer.jspf" %>--%>
</body>
</html>
