package ServletsControl;

import Controlador.ControlArchivos;
import Entidades.Info;
import Entidades.Usuario;
import JSON.Convertir;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
@MultipartConfig()
public class SubirArchivo extends HttpServlet {

    private Convertir c = new Convertir();
    private ControlArchivos ctlArch = new ControlArchivos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getParameter("path");
        String opcion = request.getParameter("opcion");
        obtenerArchivo(response, path);
    }

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
        Part filePart = request.getPart("Archivo");
        String usuarioJSON = request.getParameter("Usuario");
        Usuario usuario = (Usuario) c.obtenerObjeto(usuarioJSON, Usuario.class);
        try {
            String path = ctlArch.guardarArchivo(filePart, "Foto" + usuario.getNombreUsuario(), ControlArchivos.IMG);
            response.getWriter().append(c.obtenerJSON(new Info(true, path, path), Info.class));
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Archivo", "La imágen es demasiado grande o no es una imágen"), Info.class));
        }
    }

    /**
     * Para mostrar un archivo en debe ir en un get y devuelve el pdf
     *
     * @param response
     * @param path
     */
    private void obtenerArchivo(HttpServletResponse response, String path) {
        response.setCharacterEncoding("UTF-8");
        try (BufferedInputStream fileStream = new BufferedInputStream(new FileInputStream(path))) {
            response.setContentType("application/pdf");
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void descargarArchivo(HttpServletResponse response, String path) throws FileNotFoundException, IOException {
        FileInputStream inputStream = new FileInputStream(path);
        String filename = "Revista.pdf";
        descargar(response, inputStream, filename);
    }

    private void descargar(HttpServletResponse response, InputStream inputStream, String fileName) throws IOException {
        try (BufferedInputStream fileStream = new BufferedInputStream(inputStream)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            int data = fileStream.read();
            while (data > -1) {
                response.getOutputStream().write(data);
                data = fileStream.read();
            }
        }
    }
}
