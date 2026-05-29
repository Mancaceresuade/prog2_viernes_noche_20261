package dinamico;
public class NodoArista {
    int etiqueta;
    NodoGrafo  NodoGrafo ;
    NodoArista sigArista;

    public NodoArista() {
        this.etiqueta = 0;
        this.NodoGrafo  = null;
        this.sigArista = null;
    }
    public NodoArista(int etiqueta, NodoGrafo  NodoGrafo ) {
        this.etiqueta = etiqueta;
        this.NodoGrafo  = NodoGrafo ;
        this.sigArista = null;
    }
    public int getEtiqueta() {
        return etiqueta;
    }     
    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }
    public NodoGrafo  getNodoGrafo () {
        return NodoGrafo ;
    }
    public void setNodoGrafo (NodoGrafo  NodoGrafo ) {
        this.NodoGrafo  = NodoGrafo ;
    }
    public NodoArista getSigArista() {
        return sigArista;
    }
    public void setSigArista(NodoArista sigArista) {
        this.sigArista = sigArista;
    }
    @Override
    public String toString() {
        return "NodoArista{" +
                "etiqueta=" + etiqueta +
                ", NodoGrafo =" + NodoGrafo  +
                ", sigArista=" + sigArista +
                '}';
    }
}
