package semisemior;

public class ListaDinamica implements ListaTDA {
    private TuplaTDA[] elementos;
    private int cantidad;

    public ListaDinamica() {
        this.elementos = new TuplaTDA[4];
        this.cantidad = 0;
    }

    @Override
    public void agregar(TuplaTDA tupla) {
        if (tupla == null) return;
        if (cantidad == elementos.length) {
            ampliarCapacidad();
        }
        elementos[cantidad] = tupla;
        cantidad++;
    }

    @Override
    public TuplaTDA obtener(int indice) {
        validarIndice(indice);
        return elementos[indice];
    }

    @Override
    public void eliminar(int indice) {
        validarIndice(indice);
        for (int i = indice; i < cantidad - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[cantidad - 1] = null;
        cantidad--;
    }

    @Override
    public int tamanio() {
        return cantidad;
    }

    @Override
    public boolean estaVacia() {
        return cantidad == 0;
    }

    private void ampliarCapacidad() {
        TuplaTDA[] nuevo = new TuplaTDA[elementos.length * 2];
        for (int i = 0; i < cantidad; i++) {
            nuevo[i] = elementos[i];
        }
        elementos = nuevo;
    }

    private void validarIndice(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Indice invalido: " + indice);
        }
    }
}
