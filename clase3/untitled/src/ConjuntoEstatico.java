public class ConjuntoEstatico<T> implements iConjunto<T> {

    private T[] datos;
    private int cantidadActual;

    public ConjuntoEstatico(int cantidad) {
        this.datos = (T[]) new Object[cantidad];
        this.cantidadActual = 0;
    }

    @Override
    public void agregar(T elemento) {
        if (this.cantidadActual < datos.length) {
            if (!pertenece(elemento)) {
                this.datos[cantidadActual] = elemento;
                this.cantidadActual++;
            }
        }
    }

    @Override
    public void sacar(T elemento) {
        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].equals(elemento)) {
                datos[i] = datos[cantidadActual - 1]; // Reemplazar por el último
                datos[cantidadActual - 1] = null; // Limpiar última posición
                cantidadActual--;
                return;
            }
        }
    }

    @Override
    public boolean pertenece(T elemento) {
        for (int i = 0; i < cantidadActual; i++) {
            if (datos[i].equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estaVacio() {
        return cantidadActual == 0;
    }

    @Override
    public void imprimir() {
        for (int i = 0; i < cantidadActual; i++) {
            System.out.print(datos[i] + " ");
        }
        System.out.println();
    }
}
