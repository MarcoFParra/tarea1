package mp.tareas.tarea1;

import java.util.ArrayList;

public class MiLista {

    class Nodo {
        Contacto contaco;
        Nodo nodoAterior, nodoSiguiente;

        public Nodo(Contacto contacto)
        {
            this.contaco = contacto;
            nodoAterior = null;
            nodoSiguiente = null;
        }
    }

    Nodo raiz;

    public  MiLista()
    {
        raiz = null;
    }

    public int size()
    {
        int cont = 0;
        Nodo aux = raiz;
        while (aux != null) {
            aux = aux.nodoSiguiente;
            cont++;
        }
        return cont;
    }

    void add(Contacto contacto)
    {
        if (raiz == null) {
            raiz = new Nodo(contacto);
        }
        else {
            Nodo nuevo = new Nodo(contacto);
            Nodo aux = raiz;
            while (aux.nodoSiguiente != null) {
                aux = aux.nodoSiguiente;
            }
            aux.nodoSiguiente = nuevo;
            nuevo.nodoAterior = aux;
            nuevo.nodoSiguiente = null;
        }
    }

    public Contacto get(int pos) {
        Contacto contacto = null;
        Nodo aux =  raiz;
        if (pos < size()) {
            if (pos > 0)
                for (int i = 0; i < pos && aux != null; i++) {
                    aux = aux.nodoSiguiente;
                }
            contacto = aux.contaco;
        }
        return contacto;
    }

    public void remove(int pos) {
        if (pos < size()) {
            if (pos == 0) {
                raiz = raiz.nodoSiguiente;
                if (raiz != null)
                    raiz.nodoAterior = null;
            } else {
                Nodo aux;
                aux = raiz;
                for (int i = 1; i < pos; i++)
                    aux = aux.nodoSiguiente;
                Nodo prox = aux.nodoSiguiente;
                prox = prox.nodoSiguiente;
                aux.nodoSiguiente = prox;
                if (prox != null)
                    prox.nodoAterior = aux;
            }
        }
    }

    public ArrayList<Object> toArrayList()
    {
        int i =0;
        ArrayList<Object> list = new ArrayList<>();
        while (i < size())
        {
            list.add(get(i));
            i++;
        }
        return list;
    }
}
