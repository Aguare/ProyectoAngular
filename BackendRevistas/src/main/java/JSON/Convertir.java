package JSON;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.lang.reflect.Type;

/**
 *
 * @author marco
 */
public class Convertir {

    private Gson gson;

    public Convertir() {
        gson = new Gson();
    }

    public Object obtenerObjeto(String jsonString, Type type) {
        return this.gson.fromJson(jsonString, type);
    }

    public String obtenerJSON(Object src, Type typeOfSrc) {
        return this.gson.toJson(src, typeOfSrc);
    }

    public String entradaJSON(BufferedReader lector) {
        String line = "";
        try {
            String body = "";
            line = lector.readLine();
            while (line != null) {
                body = body + line;
                line = lector.readLine();
            }
            System.out.println("body:");
            System.out.println(body);
        } catch (Exception e) {
        }
        return line;
    }
}
