package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import javax.naming.NoPermissionException;
import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a,"Tabellen a er null!");
        hode = hale = null;                 // initialiserser null-verdier
        for (T t : a) {
            if (t != null) {                // unngår å ta med null-verdier fra tabell a
                Node node = new Node(t);    // oppretter ny node
                if (hode == null) {
                    hode = hale = node;     // peker til første node i listen
                } else {
                    hale.neste = node;      // peker mot ny node
                    node.forrige = hale;    // peker tilbake til forrige node
                    hale = node;            // halen flyttes til ny node
                }
                antall++;                   // økes med 1 for hver nye node som blir opprettet
            }
        }
        endringer = 0;
    }

    // Programkode 1.2.3 a) fra kompendiet
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    public Liste<T> subliste(int fra, int til) {
        fratilKontroll(antall, fra, til);
        endringer = 0;
        Liste<T> sub = new DobbeltLenketListe<>();

        for (int i = fra; i < til; i++) {
            T verdi = hent(i);
            sub.leggInn(verdi);
        }
        return sub;
    }

    @Override
    public int antall() {
        return antall;          // returnerer antall fra for-løkken til DobbeltLenketListe(T[] a)
    }

    @Override
    public boolean tom() {
        if (hode == null) {
            return true;
        } else {
            return false;
        }    // dersom listen er tom, så returnerer metoden true
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Null-verdier er ikke tillatt"); // sjekker om verdi ikke er null
        Node node = new Node(verdi);    // ny node
        if (tom()) {
            hode = hale = node; // peker på første node
            node.forrige = node.neste = null;
        } else {
            hale.neste = node;  //  setter pekere
            node.forrige = hale;
            hale = node;
        }
        antall++;       // økes med 1
        endringer++;    // økes med 1
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");

        indeksKontroll(indeks, true); // true: indeks = antall er lovlig

        if (indeks == 0) { // nye verdien skal legges først i listen
            Node nyNode = new Node<>(verdi, null, hode); // Lager ny node

            if (hode != null) {
                hode.forrige = nyNode; // Endrer forrige peker av hode til den nye noden
            }
            hode = nyNode; // Bytter hode-pekeren til den nye noden
        }
        else if (indeks == antall) { // nye verdien skal legges bakerst
            Node nyNode = new Node<>(verdi, hale, null); // Lager ny node

            if (hale != null) {
                hale.neste = nyNode;
            }
            hale = nyNode; // Bytter hale-pekeren til den nye noden
        }
        else { // nye verdien skal legges mellom to noder
            Node<T> p = hode;
            for (int i = 1; i < indeks; i++) p = p.neste; // p flyttes (indeks-1) ganger

            Node<T> q = p.neste; // Lagrer noden etter p med en hjelpevaribel
            Node nyNode = new Node<>(verdi,p,q); // Lager ny node med p og q som forrige og neste pekerne
            p.neste = nyNode; // bytter p sin neste peker fra q til nye noden
            q.forrige = nyNode; // bytter q sin forrige peker fra p til nye noden
        }

        endringer++;
        antall++;
    }

    private Node<T> finnNode(int indeks) {
        if (indeks < antall/2) {
            if (indeks == 0) return hode;
            Node midlertidig = hode;
            int pos = 0;
            while (pos < indeks) {
                midlertidig = midlertidig.neste;
                pos++;
            }
            return midlertidig;
        } else {
            if (indeks == antall-1) return hale;
            Node midlertidig = hale;
            int posisjon = antall-1;
            while (posisjon > indeks) {
                midlertidig = midlertidig.forrige;
                posisjon--;
            }
            return midlertidig;
        }
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) return -1;

        Node<T> p = hode;       // Lagrer hode Node med en hjelpevariabel

        int indeks = 0;         // Lagrer posisjonen/indeksen til verdien i dobbeltlenketlistet

        while (p.verdi != verdi && p.neste != null) {
            indeks++;           // Oppdatere indeksen
            p = p.neste;        // Oppdatere hjelpevariablen
        }

        if (p.verdi != verdi) return -1; // Hvis verdien finnes ikke returneres det -1

        return indeks; // Hvis verdien finnes i listen returneres det indeksen
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi);
        T temp = hent(indeks);
        finnNode(indeks).verdi = nyverdi;
        endringer++;
        return temp;
    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) return false;
        Node<T> q = hode, p = null; // q er noden som skal fjernes og p er noden foran q

        if (antall == 1) {          // Hvis det er kun én verdi i listen
            hode = null;
            hale = null;
        } else {
            // Går gjennom listen for å finne første forekomster av verdi
            while (q != null) {
                if (q.verdi.equals(verdi)) break;
                p = q; q = q.neste;
            }

            if (q == null) {
                return false;           // verdien finnes ikke i listen
            }
            else if (q == hode) {       // Hvis verdien er første noden
                hode = hode.neste;      // Flytter hode-pekeren til den neste i listen
                hode.forrige = null;    // Kobler fra den første node fra listen
            }
            else if (q == hale) {       // Hvis verdien er siste noden
                hale = p;               // Flytter hale-pekeren til nest siste node
                hale.neste = null;      // Kobler fra den siste node fra listen
            }
            else { // Hvis verdien er mellom to andre noder
                Node<T> r = q.neste;    // r er noden bak den som skal fjernes

                // Flytter neste og forrige pekerne av de to andre noder slik at ingen peker til q
                p.neste = r;
                r.forrige = p;
            }
        }

        q.verdi = null;
        q.neste = null;

        endringer++;
        antall--;

        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false); // false: indeks = antall er ulovlig

        T temp;

        if (indeks == 0) {                // hvis første verdien skal fjernes
            temp = hode.verdi;            // lagrer verdien som skal fjernes med en hjelpevariabel
            hode = hode.neste;            // flytter hode-pekeren til neste node
            hode.forrige = null;          // kobler fra første noden

            if (antall == 1) hale = null; // hvis det er kun én verdi i listen
        }
        else {
            Node<T> p = hode; // p er noden foran den som skal fjernes
            for (int i = 0; i < indeks; i++) p = p.neste;

            Node<T> q = p.neste;               // q er noden som skal fjernes
            temp = q.verdi;                    // lagrer verdien som skal fjernes med en hjelpevariabel
            if (q == hale) hale = p;           // hvis q er siste node

            Node<T> r = q.neste;               // r er noden bak den som skal fjernes

            // "hopper over" q
            p.neste = r;
            r.forrige = p;
        }

        endringer++;
        antall--;

        return temp;
    }

    @Override
    public void nullstill() {

        //Metode 1
        Node<T> t = hode;
        Node<T> q;
        while(t != null){
            q = t.neste;
            t.neste = null;
            t.verdi = null;
            t = q;
        }
        hode = hale = null;
        endringer ++;
        antall = 0;

        /*
        Metode 2, kjører saktere enn første metode og beholder den derfor ikke
        for (Node<T> t = hode; t != null; t = t.neste) {
            fjern(0);
        }
         */

    }


    @Override
    public String toString() {
        Node midlertidig = hode;                        // begynner med hode     
        StringBuilder ut = new StringBuilder("[");      // lager ut variabel gjennom stringBuilder
        if (tom()) {                                
            return "[]";    // returnerer følgende når det er tomme lister
        } else {
            while (midlertidig.neste != null) {         // looper gjennom til siste node
                ut.append(midlertidig.verdi + ", ");    // legger til verdi i ut variabelen
                midlertidig = midlertidig.neste;        // går videre til neste node 
            }
            ut.append(midlertidig.verdi + "]");         // føyer til når listen er ferdig gjennomgått
            return ut.toString();                       // returnerer ut variabelen eller listen som streng
        }
    }

    public String omvendtString() {
        Node midlertidig = hale;                        // begynner med hale
        StringBuilder ut = new StringBuilder("[");      // lager ut variable gjennom stringBuilder
        if (tom()) {
            return "[]";    // returner følgende når det er tomme lister
        } else {
            while (midlertidig.forrige != null) {       // looper gjennom til den første variabelen
                ut.append(midlertidig.verdi + ", ");    // legger til verdi i ut variabelen
                midlertidig = midlertidig.forrige;          // går videre til forrige node
            }
            ut.append(midlertidig.verdi + "]");
            return ut.toString();                       // returnerer ut variabelen eller listen som streng (baklengs)
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, true);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            //trenger node-navn fra tidligere oppgaver for å kunne legge inn peker
            fjernOK = false; //Blir sann når next() kalles
            iteratorendringer = endringer; //Teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (endringer != iteratorendringer){
                throw new ConcurrentModificationException("Det er gjort endringer.");
            }
            if(!hasNext()){
                throw new NoSuchElementException("Det er ikke flere igjen i listen.");
            }
            fjernOK = true;
            T temp2 = denne.verdi;
            denne = denne.neste;
            return temp2;
        }

        @Override
        public void remove() {
            if(!fjernOK){
                throw new IllegalStateException("Det er ikke tillat å fjerne elementer nå!");
            }
            if(endringer != iteratorendringer){
                throw new ConcurrentModificationException("Det er gjort endringer.");
            }
            fjernOK = false;

            if (antall == 1){
                hode = null;
                hale = null;
            } else if (denne == null){
                hale = hale.forrige;
                hale.neste = null;
            } else if (denne.forrige == hode){
                hode = denne;
                denne.forrige = null;
            } else {
                denne.forrige = denne.forrige.forrige;
                denne.forrige.neste = denne;
            }
            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        if (liste == null){
            throw new NullPointerException("Mangler verdier.");
        }

        for(int i = 0; i < liste.antall() - 1; i++){
            int min_index = i;
            for (int j = i +1; j < liste.antall(); j++){
                if((c.compare(liste.hent(j), liste.hent(min_index))) < 0){
                    min_index = j;
                }
                T temp3 = liste.hent(min_index);
                liste.oppdater(min_index, liste.hent(j));
                liste.oppdater(j, temp3);
            }
        }
    }

} // class DobbeltLenketListe


