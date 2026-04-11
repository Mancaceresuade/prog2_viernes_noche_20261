import java.util.Arrays;

public class Conjunto<T> implements ConjuntoTDA<T>{
    T[] elementos;
    int pos;
    Conjunto(int cantidadElemento) {
        this.elementos = (T[]) new Object[cantidadElemento];
        this.pos = 0;
    }
    @Override
    public void agregar(T elemento) {
        if((pos+1) >= elementos.length) return;
        if(existe(elemento)) return;
        this.elementos[pos] = elemento;
        pos++;
    }
    @Override
    public void listar() {
        for (int i = 0; i < this.elementos.length; i++) {
            System.out.println(this.elementos[i]);
        }
    }
    @Override
    public boolean existe(T elemento) {
        for (int i = 0; i < this.elementos.length; i++) {
            if( this.elementos[i] != null &&
                    this.elementos[i].equals(elemento)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Conjunto{" +
                "elementos=" + Arrays.toString(elementos) +
                ", pos=" + pos +
                '}';
    }
}
