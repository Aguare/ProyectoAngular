package ServletAdmin;

import Controlador.ControlAdmin;
import EntidadesAuxiliares.Info;
import EntidadesAuxiliares.Visualizacion;
import JSON.Convertir;
import ObtenerObjetos.ObAdmin;
import java.io.IOException;
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
@WebServlet(name = "RegistrarVisualizacion", urlPatterns = {"/RegistrarVisualizacion"})
@MultipartConfig()
public class RegistrarVisualizacion extends HttpServlet {

    private final ObAdmin obAd = new ObAdmin();
    private final Convertir c = new Convertir();
    private final ControlAdmin ctlAd = new ControlAdmin();

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
        try {
            String visual = request.getParameter("visual");
            Visualizacion v = (Visualizacion) c.obtenerObjeto(visual, Visualizacion.class);
            Info info = ctlAd.registrarVisualizacion(v);
            response.getWriter().append(c.obtenerJSON(new Info(true, "Exito", "Se registró la visualización"), Info.class));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "Error al obtener los anuncios"), Info.class));
        }
    }
}
