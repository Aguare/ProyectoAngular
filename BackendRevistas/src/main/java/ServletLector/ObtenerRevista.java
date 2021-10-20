package ServletLector;

import Entidades.Info;
import Entidades.Revista;
import JSON.Convertir;
import ObtenerObjetos.ObEditor;
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
@WebServlet(name = "ObtenerRevista", urlPatterns = {"/ObtenerRevista"})
public class ObtenerRevista extends HttpServlet {

    private ObEditor obE = new ObEditor();
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
            String idRevista = request.getParameter("idRevista");
            System.out.println(idRevista);
            Revista revista = obE.obtenerRevistaID(Integer.parseInt(idRevista));
            if (revista != null) {
                response.getWriter().append(c.obtenerJSON(revista, Revista.class));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "La revista no existe"), Info.class));
            }
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "La revista no existe"), Info.class));
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
