import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("datos.json");
            Empresa empresa = gson.fromJson(reader,Empresa.class);
            System.out.println(empresa);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Termina ok");
    }
}