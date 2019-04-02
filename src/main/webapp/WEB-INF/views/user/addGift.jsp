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
                        Oddaj rzeczy, których już nie chcesz<br />
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

<div>
    <c:choose>
        <c:when test="${validator==true}">

            <c:forEach items="${errors}" var="error">
                <p class="error">
                    ${error}
                </p>
            </c:forEach>

        </c:when>




        <c:otherwise>

        </c:otherwise>
    </c:choose>

</div>
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
                <div class="form--steps-counter">Krok <span>1</span>/5</div>

                <form action="${pageContext.request.contextPath}/user/addGiftJs" method="post">
                    <!-- STEP 1: class .active is switching steps -->
                    <div data-step="1" class="active">
                        <h3>Zaznacz co chcesz oddać:</h3>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input
                                        type="checkbox"
                                        name="products[]"
                                        value="ubrania, które nadają się do ponownego użycia"
                                        class="firstStep"
                                />
                                <span class="checkbox"></span>
                                <span class="description"
                                >ubrania, które nadają się do ponownego użycia</span
                                >
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input
                                        type="checkbox"
                                        name="products[]"
                                        value="ubrania, do wyrzucenia"
                                        class="firstStep"
                                />
                                <span class="checkbox"></span>
                                <span class="description">ubrania, do wyrzucenia</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="products[]" value="zabawki" class="firstStep" />
                                <span class="checkbox"></span>
                                <span class="description">zabawki</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="products[]" value="książki" class="firstStep" />
                                <span class="checkbox"></span>
                                <span class="description">książki</span>
                            </label>
                        </div>

                        <div class="form-group form-group--checkbox">
                            <label>
                                <input type="checkbox" name="products[]" value="inne" class="firstStep" />
                                <span class="checkbox"></span>
                                <span class="description">inne</span>
                            </label>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn next-step one">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 2 -->
                    <div data-step="2">
                        <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy (max 100):</h3>

                        <div class="form-group form-group--inline">
                            <label>
                                Liczba 60l worków:
                                <input type="number" name="bags" step="1" min="1" max="100" class="secondStep" />
                            </label>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step two">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 3 -->
                    <div data-step="3">

                        <h3>Lokalizacja:</h3>

                        <div class="form-group form-group--dropdown" >
                            <select name="localization" >
                                <option value="0">- wybierz -</option>
                                <option value="warsaw">Warszawa</option>
                                <option value="wroclaw">Wrocław</option>
                                <option value="poznan">Poznań</option>
                                <option value="gdansk">Gdańsk</option>
                            </select>
                        </div>

                        <div class="form-section">
                            <h4>Komu chcesz pomóc?</h4>
                            <div class="form-section--checkboxes">
                                <div class="form-group form-group--checkbox">
                                    <label>
                                        <input type="checkbox" name="help[]" value="children" />
                                        <span class="checkbox">dzieciom</span>
                                    </label>
                                </div>

                                <div class="form-group form-group--checkbox">
                                    <label>
                                        <input type="checkbox" name="help[]" value="mothers" />
                                        <span class="checkbox">samotnym matkom</span>
                                    </label>
                                </div>

                                <div class="form-group form-group--checkbox">
                                    <label>
                                        <input type="checkbox" name="help[]" value="homeless" />
                                        <span class="checkbox">bezdomnym</span>
                                    </label>
                                </div>

                                <div class="form-group form-group--checkbox">
                                    <label>
                                        <input type="checkbox" name="help[]" value="disabled" />
                                        <span class="checkbox">niepełnosprawnym</span>
                                    </label>
                                </div>

                                <div class="form-group form-group--checkbox">
                                    <label>
                                        <input type="checkbox" name="help[]" value="old" />
                                        <span class="checkbox">osobom starszym</span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-section">
                            <h4>Wpisz nazwę konkretnej organizacji (opcjonalnie)</h4>
                            <div class="form-group">
                                <textarea rows="4" name="organization_search"></textarea>
                            </div>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step">Szukaj</button>
                        </div>
                    </div>

                    <!-- STEP 4 -->
                    <div data-step="4">
                        <h3>Wybierz organizacje, której chcesz pomóc:</h3>

                        <c:forEach items="${institutionsUser}" var="institution">

                            <div class="form-group form-group--checkbox">
                                <label>
                                    <input type="radio" name="organization" value="${institution.id}" class="fourthStep"/>
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



                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step four">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 5 -->
                    <div data-step="5">
                        <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                        <div class="form-section form-section--columns">
                            <div class="form-section--column">
                                <h4>Adres odbioru</h4>
                                <div class="form-group form-group--inline">
                                    <label> Ulica* <input type="text" name="street" id="street" /> </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label> Numer domu* <input type="text" name="homeNumber" id="homeNumber" /> </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label> Miasto* <input type="text" name="city" id="city"/> </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        Kod pocztowy* <input type="text" name="postcode" id="postcode" pattern="\d{2}-\d{3}" placeholder="xx-xxx" />
                                    </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        Numer telefonu* <input type="phone" name="phone" id="phone" />
                                    </label>
                                </div>
                            </div>

                            <div class="form-section--column">
                                <h4>Termin odbioru</h4>
                                <div class="form-group form-group--inline">
                                    <label> Data <input type="date" name="data" id="data"/> </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label> Godzina <input type="time" name="time" id="time" /> </label>
                                </div>

                                <div class="form-group form-group--inline">
                                    <label>
                                        Uwagi dla kuriera
                                        <textarea name="more_info" rows="5" id="infoForCourier"></textarea>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <button type="button" class="btn next-step five">Dalej</button>
                        </div>
                    </div>

                    <!-- STEP 6 -->
                    <div data-step="6">
                        <h3>Podsumowanie Twojej darowizny</h3>

                        <div class="summary">
                            <div class="form-section">
                                <h4>Oddajesz:</h4>
                                <ul>
                                    <li>
                                        <span class="icon icon-bag"></span>
                                        <span class="summary--text formBags" >
                                            Ilość worków: 1</span>
                                        <li class="toGiveAway"></li>

                                    </li>

                                    <li>
                                        <span class="icon icon-hand"></span>
                                        <span class="summary--text formOrganization"
                                        >Dla fundacji "Mam marzenie" w Warszawie</span
                                        >
                                    </li>
                                </ul>
                            </div>

                            <div class="form-section form-section--columns">
                                <div class="form-section--column">
                                    <h4>Adres odbioru:</h4>
                                    <ul>
                                        <li id="summary1">Prosta 51</li>
                                        <li>Warszawa</li>
                                        <li>99-098</li>
                                        <li>123 456 789</li>
                                    </ul>
                                </div>

                                <div class="form-section--column">
                                    <h4>Termin odbioru:</h4>
                                    <ul>
                                        <li id="summary2">13/12/2018</li>
                                        <li>15:40</li>
                                        <li>Brak uwag</li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="form-group form-group--buttons">
                            <button type="button" class="btn prev-step">Wstecz</button>
                            <%--<button type="submit" class="btn">Potwierdzam</button>--%>
                            <%--<input type="submit" value="Potwierdzam">--%>
                            <input type="submit" value="Zapisz">
                        </div>
                    </div>

                    <!-- STEP 7 -->
                    <div data-step="7">
                        <h2>
                            Dziękujemy za przesłanie formularza Na maila prześlemy wszelkie
                            informacje o odbiorze.
                        </h2>
                    </div>
                </form>
            </div>
        </section>

        <footer>
            <div class="contact">
                <h2>Skontaktuj się z nami</h2>
                <h3>Formularz kontaktowy</h3>
                <form class="form--contact">
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
                    ><img src="${pageContext.request.contextPath}/images/icon-facebook.svg"
                    /></a>
                    <a href="#" class="btn btn--small"
                    ><img src="${pageContext.request.contextPath}/images/icon-instagram.svg"
                    /></a>
                </div>
            </div>
        </footer>


</body>
</html>
