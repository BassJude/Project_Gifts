
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



/*
INSERT INTO `plan` (`id`, `name`, `description`, `created`, `admin_id`) VALUES
(1, 'Plan Jarski', NULL, '2018-10-17 14:27:05', 1);

INSERT INTO `recipe` (`id`, `name`, `ingredients`, `description`, `created`, `updated`, `admin_id`, `preparation_time`, `preparation`) VALUES
(1, 'Przepis 1', 'sałata', 'Opis przepisu 1', '2018-10-17 00:00:00', '2018-10-17 14:24:44', 1, 30,'Sałaty dokładnie myję i jeszcze dokładniej suszę - nie powinno być na liściach żadnych kropelek wody.'),
(2, 'Przepis 2', 'sałata', 'Opis przepisu 2', '2018-10-16 00:00:00', '2018-10-17 14:24:44', 1, 30,'Sałaty dokładnie myję i jeszcze dokładniej suszę - nie powinno być na liściach żadnych kropelek wody.'),
(3, 'Przepis 3', 'sałata', 'Opis przepisu 3', '2018-10-24 00:00:00', '2018-10-17 14:24:44', 1, 30,'Sałaty dokładnie myję i jeszcze dokładniej suszę - nie powinno być na liściach żadnych kropelek wody.');
*/
-- create - tworzy tabele na podstawie adnotacji encji, nie nadpisuje zmian
--           none - nie wykonuje żadnych operacji
--           drop-and-create - usunie a następnie utworzy, przy pracy uzywamy tego
--           drop - usunie elementy bazy danych zgodne z adnotacjami
--           update - !!! nie zawsze poprawnie zmieni