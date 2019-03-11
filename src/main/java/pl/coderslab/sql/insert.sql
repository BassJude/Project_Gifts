
INSERT INTO questions (answer1,answer2,answer3,answer4,good_answer,question) VALUES
("zgłaszać pracodawcy, przełożonemu lub służbie BHP sytuacje zagrożenia na terenie zakładu pracy;",
"dbać o ład i porządek na stanowisku pracy;",
"znać przepisy i zasady bhp oraz wykonywać pracę w sposób zgodny z przepisami i zasadami bhp oraz stosować się do wydawanych w tym zakresie poleceń i wskazówek przełożonych;",
"organizować, przygotowywać i prowadzić prace, w oparciu o obowiązujące przepisy BHP oraz wewnętrzne instrukcje bezpiecznej pracy.",
"A",
"Co nie należ do podstawowych obowiązków pracownika?"),

("w Ustawie o Państwowej Inspekcji Pracy z 2007 r.;",
"w Dziale Dziesiątym Kodeksu pracy i Regulaminie pracy;",
"w Ustawie o ubezpieczeniu społecznym z tytułu wypadków przy pracy i chorób zawodowych z 2002;",
 "Rozporządzenie Ministra Pracy i Polityki Socjalnej z 1997 r. w sprawie ogólnych przepisów bezpieczeństwa i higieny pracy.",
"A",
"Obowiązki pracodawcy w zakresie BHP zostały szczególnie określone w:"),


INSERT INTO users (login,password,firstName,lastName,city, street,number_of_home,email,last_test,passed,admin) VALUES
("sowa","$2a$10$i7g/groj108JXj0qrTVpGO/FdPFBCO4i98fUhnsD0n7bFHafVsHWe","Adam","Pierzchała","Warszawa","Czapelska","40/20","email@email.pl",now(),false,false ),
("krowa","$2a$10$i7g/groj108JXj0qrTVpGO/FdPFBCO4i98fUhnsD0n7bFHafVsHWe","Michał","Kowalski","Kraków","Wiktorska","10/95","michal@email.pl",null,false ,true ),

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