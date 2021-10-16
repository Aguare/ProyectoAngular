package ServletsUsuario;

import Controlador.ControlUsuario;
import Entidades.Etiqueta;
import JSON.Convertir;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marco
 */
@WebServlet(name = "ObtenerEtiquetas", urlPatterns = {"/ObtenerEtiquetas"})
public class ObtenerEtiquetas extends HttpServlet {

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
        String etiqueta = (String) request.getAttribute("Etiqueta");
        if (etiqueta != null) {
            Etiqueta nueva = new Etiqueta(etiqueta);
            
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
        ControlUsuario controlU = new ControlUsuario();
        Convertir convertir = new Convertir();
        ArrayList<Etiqueta> etiquetas = controlU.obtenerEtiquetas();
        response.getWriter().append(convertir.obtenerJSON(etiquetas, etiquetas.getClass()));
    }
}
