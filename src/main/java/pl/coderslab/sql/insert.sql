
INSERT INTO institutions (name,missionTarget,needs,address,email,phone,location) VALUES
("Fundacja \"Nadzieja\"",
"Pomoc dzieciom z ubogich rodzin.",
"ubrania, jedzenie, sprzęt AGD, meble, zabawki",
"ul. Mysia 25, 04-081 Warszawa",
"kontakt@nadzieja.pl",
"+48 956 321 457",
"mazowieckie"),

("Fundacja \"Wioski dziecięce\"",
"Pomoc dzieciom w domach dziecka",
"ubrania, jedzenie, sprzęt AGD, meble, zabawki",
"ul. Krucza 15, 04-081 Łódź",
"kontakt@wioski.pl",
"+48 321 321 457",
"łódzkie"),

("Fundacja \"Dla dzieci\"",
"Pomoc osobom znajdującym się w trudnej sytuacji życiowej.",
"ubrania, meble, zabawki",
"ul. Dolna 55, 04-081 Kraków",
"kontakt@dzieci.pl",
"+48 321 957 457",
"małopolskie"),

("Fundacja \"Wyborcza\"",
"Pomoc biednym dziennikarzom",
"ubrania, jedzenie, sprzęt AGD, meble, zabawki",
"ul. Jakaś 17, 04-081 Wrocław",
"kontakt@wyborcza.pl",
"+48 321 951 457",
"dolnośląskie");


INSERT INTO users (email,password,firstName,lastName,admin) VALUES
("sowa@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Adam","Pierzchała",true ),
("antek@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Adam","Pierzchała",true ),
("mowa@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Ewelina","Musiał",false ),
("krowa@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Anetka","Kowalczuk",true ),
("kotek@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Amelka","Pisera",false ),
("piesek@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Kamil","Magiera",false ),
("kura@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Piotr","Sucha",false ),
("bura@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Michał","Dzięcioł",false );

INSERT INTO gifts (institution_id, user_id,bags,decsription, phone, city,zip_code,street,number_of_home, courier_decsription,pickup_time,send_time) VALUES
(1,1,5,"ubrania, zabawki, AGD","+48 654 897 264","Wrocław","65-478","Drukarska","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00"),
(1,1,5,"ubrania, zabawki, AGD","+48 654 897 264","Wrocław","65-478","Drukarska","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00"),
(1,1,5,"ubrania, zabawki, AGD","+48 654 897 264","Wrocław","65-478","Drukarska","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00"),
(1,1,5,"ubrania, zabawki, AGD","+48 654 897 264","Wrocław","65-478","Drukarska","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00"),
(1,1,5,"ubrania, zabawki, AGD","+48 654 897 264","Wrocław","65-478","Drukarska","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00");


-- create - tworzy tabele na podstawie adnotacji encji, nie nadpisuje zmian
--           none - nie wykonuje żadnych operacji
--           drop-and-create - usunie a następnie utworzy, przy pracy uzywamy tego
--           drop - usunie elementy bazy danych zgodne z adnotacjami
--           update - !!! nie zawsze poprawnie zmieni