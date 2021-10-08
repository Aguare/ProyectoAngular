package Controlador;

import Entidades.Usuario;
import SQL.Conexion;
import SQL.Encriptar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class ControlUsuario {

    /**
     * Verifica la contraseña con el método de encriptación
     *
     * @param usuario
     * @param password
     * @return
     */
    public Usuario verificarUsuario(String usuario, String password) {
        String query = "SELECT password FROM Usuario WHERE nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, usuario);
            ResultSet resultado = prepared.executeQuery();
            Encriptar desencriptar = new Encriptar();
            String pass = "";
            String nombreUsuario = "";
            int tipoUsuario = 0;
            while (resultado.next()) {
                pass = resultado.getString("password");
                nombreUsuario = resultado.getString("nombre_usuario");
                tipoUsuario = resultado.getInt("U_idTipoUsuario");
            }
            pass = desencriptar.desencriptarPass(pass, "ipc");
            if (pass.equals(password)) {
                return new Usuario(tipoUsuario, "helado");
            }
        } catch (SQLException e) {
        }
        return null;
    }
}
