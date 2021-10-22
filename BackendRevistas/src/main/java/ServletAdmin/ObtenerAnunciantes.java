package ServletAdmin;

import Controlador.ControlAdmin;
import EntidadesPrincipales.Anunciante;
import JSON.Convertir;
import ObtenerObjetos.ObAdmin;
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
@WebServlet(name = "ObtenerAnunciantes", urlPatterns = {"/ObtenerAnunciantes"})
public class ObtenerAnunciantes extends HttpServlet {
    
    private ControlAdmin control = new ControlAdmin();
    private ObAdmin obtener = new ObAdmin();
    private Convertir convertir = new Convertir();

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
        ArrayList<Anunciante> anunciantes = obtener.obtenerAnunciates();
        response.getWriter().append(convertir.obtenerJSON(anunciantes, anunciantes.getClass()));
    }
}
