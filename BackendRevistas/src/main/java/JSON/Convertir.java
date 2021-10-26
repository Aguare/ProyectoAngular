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

    /**
     * Convierte un JSON a objeto
     *
     * @param datos el string con los datos del objeto
     * @param tipoObjeto el tipo de objeto que se convertirá
     * @return retorna un Object
     */
    public Object obtenerObjeto(String datos, Type tipoObjeto) {
        return this.gson.fromJson(datos, tipoObjeto);
    }

    /**
     * Obtiene el JSON a partir de un objeto
     *
     * @param objeto objeto a convertir
     * @param tipoObjeto el tipo del objeto
     * @return Retorna un string con los datos
     */
    public String obtenerJSON(Object objeto, Type tipoObjeto) {
        return this.gson.toJson(objeto, tipoObjeto);
    }

    /**
     * Convierte un BufferReader en el String estructura JSON
     * @param lector Buffer
     * @return retorna un String con estructura JSON
     */
    public String entradaJSON(BufferedReader lector) {
        String body = "";
        try {
            String line = lector.readLine();
            while (line != null) {
                body = body + line;
                line = lector.readLine();
            }
            System.out.println("body:");
            System.out.println(body);
        } catch (Exception e) {
        }
        return body;
    }
}
