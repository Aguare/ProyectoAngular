package ServletAdmin;

import Controlador.ControlAdmin;
import Entidades.Anunciante;
import Entidades.Info;
import JSON.Convertir;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marco
 */
@WebServlet(name = "RegistrarAnunciante", urlPatterns = {"/RegistrarAnunciante"})
public class RegistrarAnunciante extends HttpServlet {

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
        Convertir c = new Convertir();
        ControlAdmin control = new ControlAdmin();
        try {
            BufferedReader lector = request.getReader();
            String textoEntrada = c.entradaJSON(lector);
            Anunciante anunciante = (Anunciante) c.obtenerObjeto(textoEntrada, Anunciante.class);
            Info status = control.registrarAnunciante(anunciante);
            response.getWriter().append(c.obtenerJSON(status, Info.class));
        } catch (Exception e) {
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
        }
    }
}
