package ObtenerObjetos;

import Entidades.Usuario;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author marco
 */
public class ObGeneral {

    public Usuario obtenerUsuario(String nombre) {
        Usuario usuario = null;
        String query = "SELECT * FROM Usuario WHERE nombre_usuario = ?";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, nombre);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                usuario = new Usuario(r.getInt(3), r.getString(1), "");
            }
        } catch (Exception e) {
        }
        return usuario;
    }
}
