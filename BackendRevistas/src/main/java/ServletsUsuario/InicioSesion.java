package ServletsUsuario;

import Controlador.ControlUsuario;
import Entidades.Usuario;
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
@WebServlet(name = "InicioSesion", urlPatterns = {"/InicioSesion"})
public class InicioSesion extends HttpServlet {

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
        BufferedReader lector = request.getReader();
        String body = "";
        String line = lector.readLine();
        while (line != null) {
            body = body + line;
            line = lector.readLine();
        }
        System.out.println("body:");
        System.out.println(body);
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
        Convertir c = new Convertir();
        ControlUsuario control = new ControlUsuario();
        try {
            BufferedReader lector = request.getReader();
            String textoUsuario = c.entradaJSON(lector);
            Usuario usuario = (Usuario) c.obtenerObjeto(textoUsuario, Usuario.class);
            response.getWriter().append(c.obtenerJSON(control.verificarUsuario(usuario.getNombreUsuario(), usuario.getPassword()), Usuario.class));
        } catch (Exception e) {
            response.getWriter().append(c.obtenerJSON(new Usuario(0, "ERROR_404", ""), Usuario.class));
        }
        
    }
}
