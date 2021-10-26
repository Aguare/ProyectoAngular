package ServletAdmin;

import EntidadesAuxiliares.Info;
import JSON.Convertir;
import Reportes.GenerarRE;
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
@WebServlet(name = "AdminReportes", urlPatterns = {"/AdminReportes"})
public class AdminReportes extends HttpServlet {

    private final GenerarRE generar = new GenerarRE();
    private final Convertir c = new Convertir();

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/pdf");
        String descarga = request.getParameter("descarga");
        try {
            String opcionReporte = request.getParameter("opcionReporte");
            String fecha_inicio = request.getParameter("fecha_inicio");
            String fecha_final = request.getParameter("fecha_final");
            String idRevista = request.getParameter("idRevista");
            String anun = request.getParameter("anunciante");
            int op = Integer.parseInt(opcionReporte);
            int id = Integer.parseInt(idRevista);
            generar.generarReporteAdmin(fecha_inicio, fecha_final, id, op, response.getOutputStream(), anun);
        } catch (IOException | NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.getWriter().append(c.obtenerJSON(new Info(false, "Error del Servidor", "No se pudo generar el reporte"), Info.class));
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
