
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

("Fundacja \"Olimpia\"",
"Pomoc dla sportowców, którym kontuzja zniszczyła życie.",
"ubrania, meble, AGD",
"ul. Dolna 55, 04-081 Kraków",
"kontakt@olimpia.pl",
"+48 957 943 713",
"małopolskie"),

("Fundacja \"Aviva\"",
"Pomoc osobom chorym na raka",
"ubrania, jedzenie, sprzęt AGD, meble, zabawki",
"ul. Jakaś 17, 04-081 Wrocław",
"kontakt@wyborcza.pl",
"+48 321 951 457",
"dolnośląskie");


INSERT INTO users (email,password,firstName,lastName,admin,can_log_in,token) VALUES
("sowa@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Adam","Pierzchała",true,true,0 ),
("antek@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Adam","Pierzchała",true,true,0 ),
("mowa@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Ewelina","Musiał",false,true,0 ),
("krowa@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Anetka","Kowalczuk",true,true,0 ),
("kotek@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Amelka","Pisera",false,true ,0),
("piesek@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Kamil","Magiera",false,true ,0),
("kura@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Piotr","Sucha",false,true ,0),
("bura@coders.pl","$2a$10$OLUmD1O93EAS9/jzWzKA7OflshzwXnQT.Ix4AizFrqg16oe4cMwq6","Michał","Dzięcioł",false,true,0 );

INSERT INTO gifts (institution_id, user_id,bags,decsription, phone, city,zip_code,street,number_of_home, courier_decsription,pickup_time,send_time,status) VALUES
(1,1,5,"ubrania, zabawki, AGD","+48 953 897 666","Wrocław","65-478","Drukarska","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00",null,"Kurier"),
(2,2,2,"ubrania, zabawki, AGD","+48 627 897 555","Kraków","65-478","Wiktorka","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00",null,"Odebrana"),
(3,3,6,"ubrania, zabawki, AGD","+48 654 897 444","Warszawa","65-478","Duża","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00","Przekazana"),
(4,4,3,"ubrania, zabawki, AGD","+48 957 897 866","Katowice","65-478","Mała","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00",null,"Kurier"),
(1,3,4,"ubrania, zabawki, AGD","+48 627 897 555","Kraków","65-478","Wiktorka","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00",null,"Odebrana"),
(2,2,5,"ubrania, zabawki, AGD","+48 654 897 444","Warszawa","65-478","Duża","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00","Przekazana"),
(3,1,3,"ubrania, zabawki, AGD","+48 957 897 866","Katowice","65-478","Mała","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00",null,"Kurier"),
(1,1,6,"ubrania, zabawki, AGD","+48 432 897 549","Szczecin","65-478","Wiśni","65/45","Proszę dzwonić przed przyjazdem","2019-03-24 00:00:00","2019-04-24 00:00:00","Przekazana");


-- create - tworzy tabele na podstawie adnotacji encji, nie nadpisuje zmian
--           none - nie wykonuje żadnych operacji
--           drop-and-create - usunie a następnie utworzy, przy pracy uzywamy tego
--           drop - usunie elementy bazy danych zgodne z adnotacjami
--           update - !!! nie zawsze poprawnie zmieni