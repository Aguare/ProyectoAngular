package ServletsControl;

import Controlador.ControlArchivos;
import Entidades.Info;
import JSON.Convertir;
import static ServletsControl.SubirArchivo.PATH;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author marco
 */
@WebServlet(name = "SubirArchivo", urlPatterns = {"/SubirArchivo"})
@MultipartConfig(location = PATH)
public class SubirArchivo extends HttpServlet {

    public static final String PATH = "C:\\Users\\marco\\Documents\\ServerSources\\";
    private Convertir c = new Convertir();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Part filePart = request.getPart("datafile");
        String usuario = request.getParameter("usuario");
        System.out.println("************************");
        System.out.println(usuario);
        ControlArchivos con = new ControlArchivos();
        
        InputStream fileStream = filePart.getInputStream();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(fileStream))) {
            String line = in.readLine();
            while (line != null) {
                line = in.readLine();
            }
            String filePath = PATH + "archivoPrueba.pdf";
            filePart.write(filePath);
            response.getWriter().append(c.obtenerJSON(new Info(true, "Archivo Subido", "El archivo se subió correctamente"), Info.class));
        } catch (Exception ex) {
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
        }
    }

}
