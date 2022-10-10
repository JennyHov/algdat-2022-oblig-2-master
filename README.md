# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Jenny Hovland, s364545, s364545@oslomet.no
* Joachim Ivo Di Mattia Martinsen, s358995, s358995@oslomet.no
* Jan Nicole Yao, s362049, s362049@oslomet.no

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Joachim har hatt hovedansvar for oppgave 1, 2 og 3. 
* Nicole har hatt hovedansvar for oppgave 4, 5 og 6. 
* Jenny har hatt hovedansvar for oppgave 7, 8 og 9.
* Vi har i fellesskap løst oppgave 10.  

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å ...

I oppgave 2 så brukte vi en ... til å ...

I oppgave 3 så brukte vi en ... til å ...

I oppgave 4 begynte vi med å sjekke om verdien er null og returnerer -1 vis det er det. Så gikk vi frem med å lage
en hjelpevariabel lik hode-noden og int objekt for indeksen. Vi går gjennom listen med en while løkka opp til slutten
av listen eller p.verdi er lik verdi. Vi legger til en til indeksen hver iterasjon av while-løkka. Hvis p.verdi er 
ulik verdi, som mener at verdien finnes ikke i listen, så returnerer vi -1 ellers returnerer vi indeksen.

I oppgave 5

I oppgave 6

I oppgave 7 brukte vi først en if, if else og else. Fant ut av at det ikke fungerte fordi vi ikke fikk fjernet 
elementene i midten. Derfor gikk vi over til å bruke en for løkke, kunne også evt brukt en while løkke. 
Etter å ha laget en for løkke innså vi at vi måtte inkludere en temp variabel utenfor for at løkken kunne fortsette å
kjøre. Hvis ikke så hadde den ikke kjørt flere ganger siden man setter neste variabel til null som ødelegger for løkken.
Lagde også en 2 metode med samme for løkke og kjører tidsberegninger på begge. Metode 2 er treigere og derfor velger å
legge den i kildekommentar, metode 1 blir stående. Endte opp med en while løkke og en for løkke for de forskjellige
metodene. Disse løper da gjennom hele listen og nullstiller alle verdiene for at de skal kunne fjernes.

I oppgave 8 bruker man next til å kunne flytte den nåværende noden en node framover. Startet med å kaste forskjellige
feilemeldinger etter det oppgaven spurte om. Returnerer det lagde iterator objektet som oppgaven spør om. Dette gjøres
under både iterator og iterator(indeks). Bortsett fra at under iterator(indeks) starter den med en gitt indeks. 

I oppgave 9 lagde vi først if setninger for å kunne kaste feilmeldinger som oppgaven spør om. Deretter  lages det enda
en if setning med flere else if for å kunne fjerne noden til venstre for den daværende noden p. Disse if setningene 
sjekker de forskjellige tilstandene som oppgaven ber om og returnerer de nye verdiene for nodene.

I oppgave 10 lagde vi en for løkke med en inklusiv for løkke inni for å kunne sortere listen slik at vi kan finne det 
minste tallet slik at man kan bytte på verdiene. Til slutt brukes metodene liste.hent og liste.oppdater for å kunne 
fullføre oppgaven. 

