package ServletAdmin;

import Controlador.ControlAdmin;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Anuncio;
import JSON.Convertir;
import ObtenerObjetos.ObAdmin;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RegistrarAnuncio", urlPatterns = {"/RegistrarAnuncio"})
@MultipartConfig()
public class RegistrarAnuncio extends HttpServlet {

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
        try {
            ArrayList<Anuncio> anuncios = obAd.obtenerAnuncios();
            response.getWriter().append(c.obtenerJSON(anuncios, anuncios.getClass()));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "Error al obtener los anuncios"), Info.class));
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
            String texto = request.getParameter("Anuncio");
            String dias = request.getParameter("Dias");
            int d = Integer.parseInt(dias);
            Anuncio anuncio = (Anuncio) c.obtenerObjeto(texto, Anuncio.class);
            Info info = ctlAd.crearAnuncio(anuncio, d);
            response.getWriter().append(c.obtenerJSON(info, Info.class));
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "Error en los parámetros"), Info.class));
        }
    }
}
