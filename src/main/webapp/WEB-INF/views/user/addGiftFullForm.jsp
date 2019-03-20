<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<%@ include file="../fragments/head.jspf" %>
<body>

<header class="header--form-page">

    <%@ include file="../fragments/nav.jspf" %>


    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Jeśli wiesz komu chcesz pomóc, możesz wpisać nazwę tej organizacji w
                wyszukiwarce. Możesz też filtrować organizacje po ich lokalizacji
                bądź celu ich pomocy.
            </p>
            <p data-step="4">
                Na podstawie Twoich kryteriów oraz rzeczy, które masz do oddania
                wybraliśmy organizacje, którym możesz pomóc. Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="5">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">

        <form:form method="post" modelAttribute="gift">

            <h3>Napisz co chcesz oddać:</h3>

            <div style="font-size: 20px" class="form-group">
                Co oddajesz <form:textarea rows="2" cols="200" path="decsription"/>
                <form:errors path="decsription" cssClass="error"/></div>


            <br><br>

            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>
            <div style="font-size: 20px" class="form-group">
                Liczba 60l worków: <form:input path="bags" placeholder="worki" type="number"/>
                <form:errors path="bags" cssClass="error"/>
            </div>


            <h3>Wybierz organizacje, której chcesz pomóc:</h3>
            <p><form:errors path="institution" cssClass="error"/></p>

            <br><br>

            <c:forEach items="${institutionsUser}" var="institution">

                <div class="form-group form-group--checkbox">
                    <label>

                        <form:radiobutton path="institution.id" value="${institution.id}"/>


                        <span class="checkbox radio"></span>
                        <span class="description">
                  <div class="title">${institution.name}</div>
                  <div class="subtitle">
                    Cel i misja: ${institution.missionTarget}
                  </div>
                </span>
                    </label>
                </div>


            </c:forEach>


            <h3>Podaj adres oraz termin odbioru rzeczy przez kuriera:</h3>

            <div class="form-section form-section--columns">
                <div class="form-section--column">
                    <h4>Adres odbioru</h4>
                    <div class="form-group form-group--inline">
                        <label> Ulica
                            <form:input path="street" placeholder="ulica"/>
                            <form:errors path="street" cssClass="error"/>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Numer domu
                            <form:input path="homeNumber" placeholder="numer domu"/>
                            <form:errors path="homeNumber" cssClass="error"/>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Miasto
                            <form:input path="city" placeholder="miasto"/>
                            <form:errors path="city" cssClass="error"/>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Kod pocztowy
                            <form:input path="zipCode" placeholder="kod pocztowy"/>
                            <form:errors path="zipCode" cssClass="error"/>
                        </label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label>
                            Numer telefonu
                            <form:input path="phone" placeholder="numer telefonu"/>
                            <form:errors path="phone" cssClass="error"/>
                        </label>
                    </div>
                </div>

                <div class="form-section--column">
                    <h4>Termin odbioru</h4>
                    <div class="form-group form-group--inline">
                        <label> Data
                            <form:input path="pickUpTime" placeholder="data" type="datetime-local" style="width: 200px;height: 40px; font-size: 20px"/>
                            <form:errors path="pickUpTime" cssClass="error"/>
                        </label>
                    </div>


                    <div class="form-group form-group--inline">
                        <label>
                            Uwagi dla kuriera

                            <form:textarea rows="5" cols="50" path="courierDecsription"/>
                            <form:errors path="courierDecsription" cssClass="error"/>
                        </label>
                    </div>
                </div>
            </div>


            <br><br>
            <div style="font-size: 20px" class="form-group">
                <input type="submit" value="Zapisz"></div>
        </form:form>
    </div>
</section>

<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form class="form--contact">
            <div class="form-group form-group--50">
                <input type="text" name="name" placeholder="Imię"/>
            </div>
            <div class="form-group form-group--50">
                <input type="text" name="surname" placeholder="Nazwisko"/>
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

<script src="/js/app.js"></script>
</body>
</html>
