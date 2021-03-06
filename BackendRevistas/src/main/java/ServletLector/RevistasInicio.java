package ServletLector;

import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Etiqueta;
import EntidadesPrincipales.Revista;
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
@WebServlet(name = "RevistasInicio", urlPatterns = {"/RevistasInicio"})
@MultipartConfig()
public class RevistasInicio extends HttpServlet {

    private final Convertir c = new Convertir();
    private final ObLector obLec = new ObLector();
    private final ObGeneral obtenerG = new ObGeneral();

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
            Usuario usuario = obtenerG.obtenerUsuario(nombreUsuario);
            ArrayList<Revista> revistasLector = obLec.obtenerRevistasLector(usuario, 1);
            response.getWriter().append(c.obtenerJSON(revistasLector, revistasLector.getClass()));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petici??n"), Info.class));
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
            String etiquetasText = request.getParameter("Etiquetas");
            Etiqueta[] et = new Etiqueta[1];
            Etiqueta[] etiquetas = (Etiqueta[]) c.obtenerObjeto(etiquetasText, et.getClass());
            ArrayList<Etiqueta> nuevasEtiquetas = new ArrayList<>();
            for (Etiqueta etiqueta : etiquetas) {
                nuevasEtiquetas.add(new Etiqueta(etiqueta.getNombre()));
            }
            ArrayList<Revista> revistasLector = obLec.obtenerBusquedaRevista(nuevasEtiquetas);
            response.getWriter().append(c.obtenerJSON(revistasLector, revistasLector.getClass()));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petici??n"), Info.class));
        }
    }
}
