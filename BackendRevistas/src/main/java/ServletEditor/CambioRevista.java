package ServletEditor;

import Controlador.ControlEditor;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Revista;
import JSON.Convertir;
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
@WebServlet(name = "CambioRevista", urlPatterns = {"/CambioRevista"})
@MultipartConfig()
public class CambioRevista extends HttpServlet {

    private final Convertir c = new Convertir();
    private final ControlEditor ctlE = new ControlEditor();

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
        try {
            String texto = request.getParameter("Revista");
            String op = request.getParameter("Opcion");
            int n = Integer.parseInt(op);
            Revista revista = (Revista) c.obtenerObjeto(texto, Revista.class);
            Info info = ctlE.cambiarEstadoRevista(revista, n);
            response.getWriter().append(c.obtenerJSON(info, Info.class));
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "Error en los parámetros"), Info.class));
        }
    }
}
