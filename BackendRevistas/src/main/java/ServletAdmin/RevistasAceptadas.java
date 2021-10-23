package ServletAdmin;

import Controlador.ControlAdmin;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Revista;
import JSON.Convertir;
import ObtenerObjetos.ObAdmin;
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
@WebServlet(name = "RevistasAceptadas", urlPatterns = {"/RevistasAceptadas"})
@MultipartConfig()
public class RevistasAceptadas extends HttpServlet {

    private ObAdmin obAd = new ObAdmin();
    private Convertir c = new Convertir();
    private ControlAdmin ctlAd = new ControlAdmin();

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
        try {
            ArrayList<Revista> revistas = obAd.obtenerRevistaPendiente(2);
            response.getWriter().append(c.obtenerJSON(revistas, revistas.getClass()));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "Error al obtener los parámetros"), Info.class));
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
    }
}
