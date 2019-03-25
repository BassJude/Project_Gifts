document.addEventListener("DOMContentLoaded", function() {


    ///////////////// my code
// step 1
    var checkboxSlide1 = document.querySelectorAll("input.firstStep");
    var toGiveAway = document.querySelector(".toGiveAway");
    var oneButton = document.querySelector(".one");
    oneButton.disabled=true;
    oneButton.innerHTML="Wybierz minimum jedną rzecz";


    for (var i = 0; i < checkboxSlide1.length; i++) {
        checkboxSlide1[i].addEventListener("click", function () {
            toGiveAway.innerHTML = "";

            for (var i = 0; i < checkboxSlide1.length; i++) {
                if (checkboxSlide1[i].checked) {

                    toGiveAway.innerHTML += checkboxSlide1[i].nextElementSibling.nextElementSibling.innerHTML + ";<br> ";

                }
            }
         if (toGiveAway.innerHTML.length!==0) {
               oneButton.disabled=false;
             oneButton.innerHTML="Dalej";

         } else {
               oneButton.disabled=true;
             oneButton.innerHTML="Wybierz minimum jedną rzecz";

         }
        })
    }


    // step 2
    var numberBags = document.querySelector("input.secondStep");
    numberBags.value=1;
    var numberBagsToSummary = document.querySelector(".formBags")
    numberBags.addEventListener("input", function () {
        console.log(this.value); // after enter into the tag
        numberBagsToSummary.innerHTML = "Ilość worków: "+this.value;

    })


// step 4
    var radioSlide4 = document.querySelectorAll("input.fourthStep");
    var organizationToSummary = document.querySelector(".formOrganization");
    var fourButton = document.querySelector(".four");
    fourButton.disabled=true;
    fourButton.innerHTML="Wybierz organizacje";

    // console.log(organizationToSummary);
    for (var i = 0; i < radioSlide4.length; i++) {
        radioSlide4[i].addEventListener("click", function () {

            organizationToSummary.innerHTML = "Dar dla: " + this.nextElementSibling.nextElementSibling.firstElementChild.innerHTML;
            console.log(this.nextElementSibling.nextElementSibling.firstElementChild.innerHTML);

            for(var i=0; i<radioSlide4.length; i++) {
                if (radioSlide4[i].checked) {
                    fourButton.disabled=false;
                    fourButton.innerHTML="Dalej";
                }
            }


        })
    }

    // step 5
    var summary1 = document.getElementById("summary1");
    var summary2 = document.getElementById("summary2");

    var street = document.getElementById("street");
    var homeNumber = document.getElementById("homeNumber");
    var city = document.getElementById("city");
    var postcode = document.getElementById("postcode");
    var phone = document.getElementById("phone");
    var date = document.getElementById("data");

    var dateToInput = new Date();
    var yearToInput=dateToInput.getFullYear();
    var monthToInput = dateToInput.getMonth()+1; // +1


    // pick up Date = date.now + 3 days, so:
    var dayToInput = dateToInput.getDate()+3;
     if (dayToInput>28) { // because February has 28 day....
        dayToInput = (dateToInput.getDate()+3)%28+1;

        monthToInput++;

        if(monthToInput>12) {
            monthToInput=monthToInput%12;
            yearToInput++;
        }
    }


// ifs must be here !!! not higher, because of day setting
    if (monthToInput<10) monthToInput="0"+monthToInput;
    if (dayToInput<10) dayToInput="0"+dayToInput;
    date.value=yearToInput+"-"+monthToInput+"-"+dayToInput;
    summary2.innerText= dayToInput+"-"+monthToInput+"-"+yearToInput;
    console.log(dayToInput+"-"+monthToInput+"-"+yearToInput);

    var time = document.getElementById("time");
    time.value="10:00";
    summary2.nextElementSibling.innerHTML="10:00";
    var infoForCourier = document.getElementById("infoForCourier");


    var fiveButton = document.querySelector(".five");
    fiveButton.disabled=true;
    fiveButton.innerHTML="\"*\" - wypełnij pola obowiązkowe";

    function checkInputs() {
        if ((street.value.length===0||homeNumber.value.length===0||city.value.length===0||postcode.value.length===0||phone.value.length===0)) {
            fiveButton.disabled=true;
            fiveButton.innerHTML="\"*\" - wypełnij pola obowiązkowe";
        } else {
            fiveButton.disabled=false;
            fiveButton.innerHTML="Dalej";
        }
    }

    street.addEventListener("input", function () {
        street.maxLength=100;
        summary1.innerText=this.value;
        checkInputs();


    })
    homeNumber.addEventListener("input",function () {
        homeNumber.maxLength=50;
        summary1.innerText=street.value+" "+this.value;
        checkInputs();
    })

    city.addEventListener("input", function () {
        city.maxLength=100;
        summary1.nextElementSibling.innerHTML=this.value;
        checkInputs();

    })

    postcode.addEventListener("input", function () {
        postcode.maxLength=10;
        summary1.nextElementSibling.nextElementSibling.innerHTML=this.value;
        checkInputs();

    })
    phone.addEventListener("input",function () {
        phone.maxLength=20;
        summary1.nextElementSibling.nextElementSibling.nextElementSibling.innerHTML=this.value;
        checkInputs();

    })
    date.addEventListener("input", function () {
        summary2.innerText= this.value;
    })
    time.addEventListener("input",function () {
        summary2.nextElementSibling.innerHTML=this.value;

    })
    infoForCourier.addEventListener("input", function () {
        infoForCourier.maxLength=200;
        summary2.nextElementSibling.nextElementSibling.innerHTML=this.value;
    })






    /**
     * HomePage - Help section
     */
    class Help {
        constructor($el) {
            this.$el = $el;
            this.$buttonsContainer = $el.querySelector(".help--buttons");
            this.$slidesContainers = $el.querySelectorAll(".help--slides");
            this.currentSlide = this.$buttonsContainer.querySelector(".active").parentElement.dataset.id;
            this.init();
        }

        init() {
            this.events();
        }

        events() {
            /**
             * Slide buttons
             */
            this.$buttonsContainer.addEventListener("click", e => {
                if (e.target.classList.contains("btn")) {
                this.changeSlide(e);
            }
        });

            /**
             * Pagination buttons
             */
            this.$el.addEventListener("click", e => {
                if (e.target.classList.contains("btn") && e.target.parentElement.parentElement.classList.contains("help--slides-pagination")) {
                this.changePage(e);
            }
        });
        }

        changeSlide(e) {
            e.preventDefault();
            const $btn = e.target;

            // Buttons Active class change
            [...this.$buttonsContainer.children].forEach(btn => btn.firstElementChild.classList.remove("active"));
            $btn.classList.add("active");

            // Current slide
            this.currentSlide = $btn.parentElement.dataset.id;

            // Slides active class change
            this.$slidesContainers.forEach(el => {
                el.classList.remove("active");

            if (el.dataset.id === this.currentSlide) {
                el.classList.add("active");
            }
        });
        }

        /**
         * TODO: callback to page change event
         */
        changePage(e) {
            e.preventDefault();
            const page = e.target.dataset.page;

            console.log(page);
        }
    }
    const helpSection = document.querySelector(".help");
    if (helpSection !== null) {
        new Help(helpSection);
    }

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
            li.dataset.value = el.value;
            li.innerText = el.innerText;

            if (i === 0) {
                // First clickable option
                this.current = document.createElement("div");
                this.current.innerText = el.innerText;
                this.dropdown.appendChild(this.current);
                this.valueInput.value = el.value;
                li.classList.add("selected");
            }

            this.ul.appendChild(li);
        });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
            this.dropdown.classList.toggle("selecting");

            // Save new value only when clicked on li
            if (target.tagName === "LI") {
                this.valueInput.value = target.dataset.value;
                this.current.innerText = target.innerText;
            }
        });
        }
    }
    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
});

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function(e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
    });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
            this.currentStep++;
            this.updateForm();
        });
        });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
            this.currentStep--;
            this.updateForm();
        });
        });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

            if (slide.dataset.step == this.currentStep) {
                slide.classList.add("active");
            }
        });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 6;
            this.$step.parentElement.hidden = this.currentStep >= 6;

            // TODO: get data from inputs and show them in summary
        }

        /**
         * Submit form
         *
         * TODO: validation, send data to server
         */
        submit(e) {
            // TODO zmiana prevent
            // e.preventDefault();
            this.currentStep++;
            this.updateForm();
        }
    }
    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});