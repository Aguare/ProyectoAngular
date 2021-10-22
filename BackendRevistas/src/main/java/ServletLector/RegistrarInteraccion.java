package ServletLector;

import Controlador.ControlLector;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Reaccion;
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
@WebServlet(name = "RegistrarInteraccion", urlPatterns = {"/RegistrarInteraccion"})
@MultipartConfig()
public class RegistrarInteraccion extends HttpServlet {

    private Convertir c = new Convertir();
    private ControlLector ctlL = new ControlLector();
    private ObLector obLec = new ObLector();

    /**
     * Este método devuelve los me gustas de una revista
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("idRevista");
        try {
            ArrayList<Reaccion> reacciones = obLec.obtenerReaccionesRevista(Integer.parseInt(id));
            response.getWriter().append(c.obtenerJSON(reacciones, reacciones.getClass()));
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
        String reac = request.getParameter("Reaccion");
        try {
            Reaccion reaccion = (Reaccion) c.obtenerObjeto(reac, Reaccion.class);
            ctlL.darMG(reaccion);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));

        }
    }
}
