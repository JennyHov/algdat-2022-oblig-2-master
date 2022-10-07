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
        throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        throw new UnsupportedOperationException();
    }

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tom() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
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

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null) return -1;

        Node<T> p = hode; // Lagrer hode Node med en hjelpevariabel

        int indeks = 0; // Lagrer posisjonen/indeksen til verdien i dobbeltlenketlistet

        while (p.verdi != verdi && p.neste != null) {
            indeks++; // Oppdatere indeksen
            p = p.neste; // Oppdatere hjelpevariablen
        }

        if (p.verdi != verdi) return -1; // Hvis verdien finnes ikke returneres det -1

        return indeks; // Hvis verdien finnes i listen returneres det indeksen
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {

        //Metode 1
        //Må legge til en temp variabel for å kunne gjøe for løkken om igjen, hvis ikke så kjører den kun

        //temp
        for(Node<T> t = hode; t != null; t = t.neste){
            t.verdi = null;
            t.forrige = t.neste = null;
        }
        hode = hale = null;
        endringer ++;
        antall = 0;

        //Metode 2, kjører saktere enn første metode og beholder den derfor ikke
        /*
        for (Node<T> t = hode; t != null; t = t.neste) {
            fjern(0);
        }
         */

    }


    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


