package Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.Part;

/**
 *
 * @author marco
 */
public class ControlArchivos {

    public static final String PATH = "C:\\Users\\marco\\Documents\\ServerSources\\";
    public static final String PDF = ".pdf";
    public static final String IMG = ".png";

    public String guardarArchivo(Part parte, String nombreArchivo, String extension) throws IOException {
        String path = "";
        InputStream fileStream = parte.getInputStream();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(fileStream))) {
            String line = in.readLine();
            while (line != null) {
                line = in.readLine();
            }
            path = PATH + nombreArchivo + extension;
            parte.write(path);
        } catch (Exception ex) {
        }
        return path;
    }
}
