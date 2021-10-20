package ServletEditor;

import Controlador.ControlArchivos;
import Controlador.ControlEditor;
import Entidades.Info;
import Entidades.Revista;
import Entidades.Usuario;
import JSON.Convertir;
import ObtenerObjetos.ObEditor;
import ObtenerObjetos.ObGeneral;
import java.io.IOException;
import java.time.LocalDateTime;
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
@MultipartConfig()
public class RegistrarRevista extends HttpServlet {

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
            response.getWriter().append(c.obtenerJSON(revistasEditor, revistasEditor.getClass()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
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
        LocalDateTime fecha = LocalDateTime.now();
        try {
            Revista nueva = (Revista) c.obtenerObjeto(revista, Revista.class);
            String hora = "" + fecha.getHour() + fecha.getMinute() + fecha.getSecond();
            String pathArchivo = controlArch.guardarArchivo(archivo, "Revista_" + nueva.getUsuarioCreador().getNombreUsuario() + hora, ControlArchivos.PDF);
            Info info = controlEditor.registrarRevista(nueva, pathArchivo);
            response.getWriter().append(c.obtenerJSON(info, Info.class));
        } catch (IOException e) {
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
        }
    }
}
