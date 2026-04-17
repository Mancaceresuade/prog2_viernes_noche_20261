import java.util.ArrayList;
import java.util.HashMap;

public class Empresa {
    private String nombre;
    private ArrayList<String> telefonos = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

    private HashMap<Integer,Sucursal> sucursales
            = new HashMap<>();
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public Empresa(String nombre) {
        // Validación del IREP
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El IREP se ha violado: el nombre no puede ser vacío.");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public HashMap<Integer, Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(HashMap<Integer, Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", telefonos=" + telefonos +
                ", empleados=" + empleados +
                ", sucursales=" + sucursales +
                '}';
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
