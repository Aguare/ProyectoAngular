package ServletsControl;

import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Anuncio;
import EntidadesPrincipales.Perfil;
import JSON.Convertir;
import ObtenerObjetos.ObGeneral;
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
@WebServlet(name = "ObtenerAnuncios", urlPatterns = {"/ObtenerAnuncios"})
public class ObtenerAnuncios extends HttpServlet {

    private Convertir c = new Convertir();
    private ObGeneral obG = new ObGeneral();

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
            String u = request.getParameter("Usuario");
            Perfil perfil = obG.obtenerPerfilUsuario(u);
            ArrayList<Anuncio> anun = obG.obtenerAnunciosParecidos(perfil.getEtiquetasInteres());
            response.getWriter().append(c.obtenerJSON(anun, anun.getClass()));
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
    }
}
