package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import javax.servlet.http.Part;

/**
 *
 * @author marco
 */
public class ControlArchivos {

    public static final String PATH = "DatosServer\\";
    public static final String PDF = ".pdf";
    public static final String IMG = ".png";

    public String guardarArchivo(Part parte, String nombreArchivo, String extension, String servidor) throws IOException {
        LocalDateTime fecha = LocalDateTime.now();
        String hora = "" + fecha.getHour() + fecha.getMinute() + fecha.getSecond();
        String path = "";
        InputStream fileStream = parte.getInputStream();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(fileStream))) {
            String line = in.readLine();
            while (line != null) {
                line = in.readLine();
            }
            path = servidor + PATH + nombreArchivo + hora + extension;
            parte.write(path);
        } catch (Exception ex) {
            System.out.println("ERROR AL GUARDAR EL ARCHIVO");
        }
        return path;
    }

    public File obtenerArchivo(String path) {
        File archivo = new File(path);
        return archivo;
    }

}
