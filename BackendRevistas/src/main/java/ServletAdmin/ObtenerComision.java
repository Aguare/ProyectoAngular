package ServletAdmin;

import Controlador.ControlAdmin;
import EntidadesAuxiliares.Info;
import EntidadesAuxiliares.ValorSistema;
import JSON.Convertir;
import ObtenerObjetos.ObAdmin;
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
@WebServlet(name = "ObtenerComision", urlPatterns = {"/ObtenerComision"})
@MultipartConfig()
public class ObtenerComision extends HttpServlet {

    private Convertir c = new Convertir();
    private ControlAdmin ctlAd = new ControlAdmin();
    private ObAdmin obAd = new ObAdmin();

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
            String op = request.getParameter("opcion");
            int opcion = Integer.parseInt(op);
            System.out.println(opcion);
            switch (opcion) {
                case 1:
                    ArrayList<ValorSistema> valores = obAd.obtenerComisiones();
                    response.getWriter().append(c.obtenerJSON(valores, valores.getClass()));
                    break;
                case 2:
                    ValorSistema valor = obAd.obtenerComision();
                    response.getWriter().append(c.obtenerJSON(valor, ValorSistema.class));
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "El servidor no pudo resolver la petición"), Info.class));
                    break;
            }
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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
            String texto = request.getParameter("Comision");
            ValorSistema valor = (ValorSistema) c.obtenerObjeto(texto, ValorSistema.class);
            Info info = ctlAd.actualizarComision(valor);
            response.getWriter().append(c.obtenerJSON(info, Info.class));
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error", "No se pudo actualizar el valor"), Info.class));
        }
    }
}
