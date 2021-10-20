package ServletLector;

import Controlador.ControlLector;
import Entidades.Comentario;
import Entidades.Info;
import JSON.Convertir;
import ObtenerObjetos.ObLector;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marco
 */
@WebServlet(name = "RegistrarComentario", urlPatterns = {"/RegistrarComentario"})
@MultipartConfig()
public class RegistrarComentario extends HttpServlet {

    private Convertir c = new Convertir();
    private ControlLector ctlLec = new ControlLector();
    private ObLector obLec = new ObLector();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("idRevista");
        try {
            ArrayList<Comentario> comentarios = obLec.obtenerComentariosRevista(Integer.parseInt(id));
            response.getWriter().append(c.obtenerJSON(comentarios, comentarios.getClass()));
        } catch (NumberFormatException e) {
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
        String com = request.getParameter("Comentario");
        try {
            Comentario comentario = (Comentario) c.obtenerObjeto(com, Comentario.class);
            ctlLec.registrarComentarioGeneral(comentario);
            response.getWriter().append(c.obtenerJSON(new Info(true, "Éxito", "El comentario se registró"), Info.class));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));

        }
    }
}
