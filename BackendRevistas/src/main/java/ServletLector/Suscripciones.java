package ServletLector;

import Controlador.ControlLector;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Revista;
import EntidadesAuxiliares.Suscripcion;
import EntidadesPrincipales.Usuario;
import JSON.Convertir;
import ObtenerObjetos.ObGeneral;
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
@WebServlet(name = "Suscripciones", urlPatterns = {"/Suscripciones"})
@MultipartConfig()
public class Suscripciones extends HttpServlet {

    private final ObLector obLec = new ObLector();
    private final ControlLector ctlLec = new ControlLector();
    private final ObGeneral obtenerG = new ObGeneral();
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
            String nombreUsuario = request.getParameter("usuario");
            System.out.println(nombreUsuario);
            Usuario usuario = obtenerG.obtenerUsuario(nombreUsuario);
            ArrayList<Revista> revistasLector = obLec.obtenerRevistasLector(usuario, 2);
            response.getWriter().append(c.obtenerJSON(revistasLector, revistasLector.getClass()));
        } catch (Exception e) {
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String susTexto = request.getParameter("Suscripcion");
            System.out.println(susTexto);
            Suscripcion suscripcion = (Suscripcion) c.obtenerObjeto(susTexto, Suscripcion.class);
            Info status = ctlLec.registrarSuscripcionLog(suscripcion);
            if (status.isOperacion()) {
                response.getWriter().append(c.obtenerJSON(status, Info.class));
            } else {
                response.getWriter().append(c.obtenerJSON(status, Info.class));
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
