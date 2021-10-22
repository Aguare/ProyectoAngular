package ServletLector;

import Controlador.ControlLector;
import EntidadesAuxiliares.Info;
import EntidadesAuxiliares.Suscripcion;
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
@WebServlet(name = "ObtenerSuscripciones", urlPatterns = {"/ObtenerSuscripciones"})
@MultipartConfig()
public class ObtenerSuscripciones extends HttpServlet {

    private final ObLector obLec = new ObLector();
    private final ControlLector ctlLec = new ControlLector();
    private Convertir c = new Convertir();

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
        try {
            String idTexto = request.getParameter("idRevista");
            int idRevista = Integer.parseInt(idTexto);
            ArrayList<Suscripcion> suscripciones = obLec.obtenerSuscripcionesRevista(idRevista);
            response.getWriter().append(c.obtenerJSON(suscripciones, suscripciones.getClass()));
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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

    }
}
