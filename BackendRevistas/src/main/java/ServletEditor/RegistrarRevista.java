package ServletEditor;

import Controlador.ControlArchivos;
import Controlador.ControlEditor;
import Entidades.Info;
import Entidades.Revista;
import Entidades.Usuario;
import JSON.Convertir;
import ObtenerObjetos.ObEditor;
import ObtenerObjetos.ObGeneral;
import static ServletsControl.SubirArchivo.PATH;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "RegistrarRevista", urlPatterns = {"/RegistrarRevista"})
@MultipartConfig(location = PATH)
public class RegistrarRevista extends HttpServlet {
    
    public static final String PATH = "C:\\Users\\marco\\Documents\\ServerSources\\";
    private final Convertir c = new Convertir();
    private final ControlArchivos controlArch = new ControlArchivos();
    private final ControlEditor controlEditor = new ControlEditor();
    private final ObEditor obEdit = new ObEditor();
    private final ObGeneral obtenerG = new ObGeneral();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String nombreUsuario = request.getParameter("usuario");
            Usuario usuario = obtenerG.obtenerUsuario(nombreUsuario);
            ArrayList<Revista> revistasEditor = obEdit.obtenerRevistasEditor(usuario);
            response.getWriter().append(c.obtenerJSON(revistasEditor, Info.class));
        } catch (Exception e) {
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
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
        Part archivo = request.getPart("archivo");
        String revista = request.getParameter("revista");
        Revista nueva = (Revista) c.obtenerObjeto(revista, Revista.class);
        String pathArchivo = controlArch.guardarArchivo(archivo, nueva.getNo_version() + nueva.getTitulo() + "Revista", ControlArchivos.PDF);
        Info info = controlEditor.registrarRevista(nueva, pathArchivo);
        try {
            response.getWriter().append(c.obtenerJSON(info, Info.class));
        } catch (Exception e) {
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
        }
    }
}
