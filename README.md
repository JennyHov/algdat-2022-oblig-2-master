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

I oppgave 4 tok vi utgangspunkt i kildekoden til EnkeltLenketListe fasit. Først sjekker vi for "null"-verdier og
returnerer -1 vis verdien er "null". Så lager vi en hjelpevariabel p lik hode. Med en for-løkka går vi gjennom hver p
verdi og sjekke om de er lik verdien som er gitt, hvis det er det returnerer vi indeksen. Etter at vi har gått gjennom
hele listen og fant ikke verdien returnerer det -1. For inneholder(T verdi) metoden bruker vi indeksTil(T verdi) metode
og sjekke om der ulik -1, altså at verdien finnes i listen.

I oppgave 5 så gikk vi frem ved å sjekke for "null"-verdier og ulovlig indekser requireNonNull-metode og 
indeksKontroll-metode fra Liste med leggInn verdi lik true. Hvis verdien er lik null(0), altså verdien skal legges 
først i listen så lager vi en ny Node med gitt verdi, forrige peker lik "null" og neste peker lik hode. Hvis hode ikke 
er "null" flytter vi forrige pekeren til nye noden også flytter vi hode pekeren til den nye node. Så sjekker vi om antall
er lik null(0) dvs. at listen er tom så setter vi hale lik hode slik at de peker på samme node. Derimot hvis indeks 
er lik antall, altså verdien skal legges sist lager vi en ny node med forrige peker lik hale og neste peker 
lik "null". Hvis hale ikke er "null" flytter vi neste pekeren til hale til nye noden også flytter vi hale pekeren til 
nye noden. Ellers vis indeks er enten lik null(0) eller antall går vi frem og sette node p lik hode og bruker en
for-løkka å flytte p indeks-1 ganger. Også lager vi node q som er noden foran p, med det lager vi nye noden med
forrige peker lik p og neste peker lik q. Vi flytter p sin neste peker og q sin forrige peker til nye noden. Til slutt
legger vi til en til endringer og antall.

I oppgave 6 for den boolean fjern metode sjekket vi for null-verdier og returnerer false hvis det er det. Neste sjekker
vi om antall er lik én, altså hvis det er kun én verdi i listen så setter vi hode og hale lik null(0). Ellers går vi
gjennom listen for å finne første forekomster av verdi med en while-løkka. q er noden som skal fjernes og p er noden
foran. Hvis q er "null" returneres det false, verdien finnes ikke. Hvis q er lik hode, altså første noden skal fjernes
flytter vi hode til neste noden og forrige pekeren til null. Hvis q er lik hale, altså siste noden skal fjernes flytter
vi hale til neste siste noden p og setter neste pekeren til "null". Hvis q ikke er noen av de nevnte og verdien er
mellom to andre noder lager vi en ny Node r som er noden bak den som skal fjernes. Så flytter vi p sin neste peker til r
og r sin forrige peker lik p slik at ingen noder peker til q. Til sist setter vi q sin verdi og neste til "null", legge
til en til endringer, trekk en fra antall og returnere true.

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

