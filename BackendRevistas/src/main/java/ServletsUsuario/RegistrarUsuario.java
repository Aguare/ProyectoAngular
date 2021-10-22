package ServletsUsuario;

import Controlador.ControlArchivos;
import Controlador.ControlUsuario;
import EntidadesAuxiliares.Cliente;
import EntidadesAuxiliares.Info;
import JSON.Convertir;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author marco
 */
@WebServlet(name = "RegistrarUsuario", urlPatterns = {"/RegistrarUsuario"})
@MultipartConfig()
public class RegistrarUsuario extends HttpServlet {

    private ControlArchivos ctlArch = new ControlArchivos();

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
        Convertir c = new Convertir();
        ControlUsuario control = new ControlUsuario();
        Info status = new Info(false, "Error", "El usuario no pudo registrarse");
        try {
            String textoEntrada = request.getParameter("Cliente");
            Part foto = request.getPart("Foto");
            Cliente cliente = (Cliente) c.obtenerObjeto(textoEntrada, Cliente.class);
            String pathFoto = ctlArch.guardarArchivo(foto, "foto" + cliente.getUsuario().getNombreUsuario(), ControlArchivos.IMG);
            cliente.getPerfil().setFoto(pathFoto);
            status = control.regitrarCliente(cliente);
            response.getWriter().append(c.obtenerJSON(status, Info.class));
        } catch (IOException | ServletException e) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.getWriter().append(c.obtenerJSON(status, Info.class));
        }
    }
}
