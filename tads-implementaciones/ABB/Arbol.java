package clase11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arbol<T> {

	public Nodo<T> raiz;

	public Arbol() {
		raiz = null;
	}

	public void agregar(T elem) {
		Random random = new Random();
		if (raiz == null) {
			raiz = new Nodo<>(elem);
		} else {
			agregarRec(raiz, elem, random);
		}
	}

	private void agregarRec(Nodo<T> nodo, T elem, Random random) {
		if (nodo.izq == null) {
			nodo.izq = new Nodo<>(elem);
		} else if (nodo.der == null) {
			nodo.der = new Nodo<>(elem);
		} else {
			if (random.nextBoolean()) {
				agregarRec(nodo.izq, elem, random);
			} else {
				agregarRec(nodo.der, elem, random);
			}
		}
	}
	public void imprimir() {
		imprimir(raiz);
	}
	
	private void imprimir(Nodo<T> nodo) {
		if(nodo == null) return;
		System.out.println(nodo);
		imprimir(nodo.izq);
		imprimir(nodo.der);
	}
	

	public int cantNodos() {
		return (raiz == null) ? 0 : cantNodos(raiz);
	}

	private int cantNodos(Nodo<T> nodo) {
		int cantIzq = (nodo.izq == null) ? 0 : cantNodos(nodo.izq);
		int cantDer = (nodo.der == null) ? 0 : cantNodos(nodo.der);
		return 1 + cantIzq + cantDer;
	}

	public int altura() {
		return (raiz == null) ? 0 : altura(raiz);
	}

	private int altura(Nodo<T> nodo) {
		int altIzq = (nodo.izq == null) ? 0 : altura(nodo.izq);
		int altDer = (nodo.der == null) ? 0 : altura(nodo.der);
		return 1 + Math.max(altIzq, altDer);
	}

	
	public void imprimirArbolNoEficiente() {
		int altura = altura(raiz);
		for(int i = 0; i < altura; i++) {
			imprimirPorNivel(i);
			System.out.println(" ");
		}
		
	}	
	
	public void imprimirPorNivel(int nivel) {
		for(Nodo<T> nodo: nodosPorNivel(nivel)) {
			System.out.print(nodo + " ");
		}
	}	
	
	private List<Nodo<T>> nodosPorNivel(int nivel) {
		List<Nodo<T>> nodosPorNivel = new ArrayList<>();
		nodosPorNivel(raiz,nivel,0,nodosPorNivel);
		return nodosPorNivel;
	}
	
	public void nodosPorNivel(Nodo<T> nodo, int nivel, int nivelActual, List<Nodo<T>> nodosPorNivel) {
		if(nodo==null) return;
		if(nivel==nivelActual) { nodosPorNivel.add(nodo); return; }
		nodosPorNivel(nodo.izq,nivel,nivelActual+1,nodosPorNivel);
		nodosPorNivel(nodo.der,nivel,nivelActual+1,nodosPorNivel);
	}
	
	
}
