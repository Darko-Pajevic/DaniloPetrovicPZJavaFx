Sistem za Rezervaciju Hotela

Sadržaj:

-Uvod
-Funkcionalnosti
-Sistemski Zahtevi
-Instalacija
-Korisničko Uputstvo
-Prijava
-Komandna Tabla
-Upravljanje Hotelima
-Upravljanje Sobicama
-Upravljanje Zaposlenima
-Upravljanje Rezervacijama
-Rešavanje Problema

-Uvod
Sistem za Rezervaciju Hotela je JavaFX aplikacija dizajnirana za upravljanje hotelskim operacijama. Omogućava administratorima da efikasno upravljaju hotelima, sobama, zaposlenima i rezervacijama. Ovaj dokument pruža pregled funkcionalnosti aplikacije i detaljno korisničko uputstvo.

Funkcionalnosti
-JavaFX Korisnički Interfejs: Grafički korisnički interfejs za jednostavnu interakciju.
-Integracija sa Bazom Podataka: Čuva podatke u SQLite bazi podataka.
-CRUD Operacije: Kreiranje, čitanje, ažuriranje i brisanje za hotele, sobe, zaposlene i rezervacije.
-Autentifikacija: Funkcionalnost prijave korisnika.
-Modularni Dizajn: Razdvajanje odgovornosti putem različitih kontrolera i objekata za pristup podacima (DAO).
-Java Kolekcije: Koristi liste za upravljanje i prikaz podataka.
-Obrada Grešaka: Sveobuhvatna obrada izuzetaka u celoj aplikaciji.
-JUnit Testovi: Jedinični testovi za svu logiku kontrolera kako bi se osigurala pouzdanost koda.

Sistemski Zahtevi
-Java Development Kit (JDK): Verzija 11 ili viša
-SQLite: Verzija 3.36.0 ili viša
-Maven: Za upravljanje zavisnostima i izgradnju projekta
-Operativni Sistem: Windows 10 ili viši, macOS, ili Linux

Instalacija:

Klonirajte Repozitorijum:
git clone https://github.com/Darko-Pajevic/DaniloPetrovicPZJavaFx.git

Pređite u Direktorijum Projekta:
cd DaniloPetrovicPZJavaFx

Izgradite Projekat:
mvn clean install

Pokrenite Aplikaciju:
mvn javafx:run

Korisničko Uputstvo
Prijava
Otvorite Aplikaciju:
Pokrenite aplikaciju koristeći zadatu komandu ili izvršnu datoteku.

Ekran za Prijavu:

Unesite vaše korisničko ime i lozinku.
Kliknite na dugme "Prijava".
Ako su kredencijali ispravni, bićete preusmereni na komandnu tablu.
Komandna Tabla
Komandna tabla služi kao centralno mesto aplikacije. Odavde možete navigirati do različitih sekcija za upravljanje:

Upravljanje Hotelima
Upravljanje Sobicama
Upravljanje Zaposlenima
Upravljanje Rezervacijama
Odjava
Upravljanje Hotelima
Dodajte Hotel:

Kliknite na "Upravljanje Hotelima" na komandnoj tabli.
Popunite detalje o hotelu (Ime, Adresa, Ocena).
Kliknite na "Dodaj Hotel" da sačuvate novi hotel.
Pregled Hotela:

Tabela prikazuje sve hotele koji su pohranjeni u bazi podataka.
Možete videti detalje kao što su ID, Ime, Adresa i Ocena.
Upravljanje Sobicama
Dodajte Sobu:

Kliknite na "Upravljanje Sobicama" na komandnoj tabli.
Popunite detalje o sobi (ID Hotela, Broj Sobe, Tip, Cena po Noćenju).
Kliknite na "Dodaj Sobu" da sačuvate novu sobu.
Pregled Soba:

Tabela prikazuje sve sobe koje su pohranjene u bazi podataka.
Možete videti detalje kao što su ID, ID Hotela, Broj Sobe, Tip i Cena po Noćenju.
Upravljanje Zaposlenima
Dodajte Zaposlenog:

Kliknite na "Upravljanje Zaposlenima" na komandnoj tabli.
Popunite detalje o zaposlenom (Ime, Pozicija, Plata).
Kliknite na "Dodaj Zaposlenog" da sačuvate novog zaposlenog.
Pregled Zaposlenih:

Tabela prikazuje sve zaposlene koji su pohranjeni u bazi podataka.
Možete videti detalje kao što su ID, Ime, Pozicija i Plata.
Upravljanje Rezervacijama
Dodajte Rezervaciju:

Kliknite na "Upravljanje Rezervacijama" na komandnoj tabli.
Popunite detalje o rezervaciji (ID Korisnika, ID Sobe, Datum Početka, Datum Kraja, Ukupna Cena).
Kliknite na "Dodaj Rezervaciju" da sačuvate novu rezervaciju.
Pregled Rezervacija:

Tabela prikazuje sve rezervacije koje su pohranjene u bazi podataka.
Možete videti detalje kao što su ID, ID Korisnika, ID Sobe, Datum Početka, Datum Kraja i Ukupna Cena.
Rešavanje Problema
Problemi sa Prijavom:

Uverite se da su korisničko ime i lozinka tačni.
Proverite vezu sa bazom podataka.
Veza sa Bazom Podataka:

Uverite se da SQLite datoteka baze podataka postoji i da je dostupna.
Proverite niz za povezivanje sa bazom podataka u klasi DatabaseConnection.
Greške u Aplikaciji:

Proverite konzolu za poruke o greškama.
Uverite se da su sve zavisnosti pravilno instalirane i ažurirane.
