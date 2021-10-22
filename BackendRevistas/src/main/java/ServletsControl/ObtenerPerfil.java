package ServletsControl;

import EntidadesAuxiliares.Cliente;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Perfil;
import JSON.Convertir;
import ObtenerObjetos.ObGeneral;
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
@WebServlet(name = "ObtenerPerfil", urlPatterns = {"/ObtenerPerfil"})
@MultipartConfig()
public class ObtenerPerfil extends HttpServlet {

    private ObGeneral obG = new ObGeneral();
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
            Perfil perfil = obG.obtenerPerfilUsuario(nombreUsuario);
            response.getWriter().append(c.obtenerJSON(perfil, Perfil.class));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "El usuario no existe"), Info.class));
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
        Info error = new Info(false, "Error", "El usuario no existe");
        try {
            String nombreUsuario = request.getParameter("Usuario");
            Cliente cliente = obG.obtenerClienteUsuario(nombreUsuario);
            if (cliente != null) {
                response.getWriter().append(c.obtenerJSON(cliente, Cliente.class));
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                response.getWriter().append(c.obtenerJSON(error, Info.class));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.getWriter().append(c.obtenerJSON(error, Info.class));

        }
    }
}
