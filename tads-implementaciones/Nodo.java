package clase10;

public class Nodo<V> {
	V elemento;

	Nodo(V elemento) {
		this.elemento = elemento;
	}

	Nodo izq;
	Nodo der;

	@Override
	public String toString() {
		return elemento.toString();
	}
}
