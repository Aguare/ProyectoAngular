package ObtenerObjetos;

import Entidades.Cliente;
import Entidades.Etiqueta;
import Entidades.Perfil;
import Entidades.Usuario;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ObGeneral {

    public static final int ETIQUETAS_REVISTA = 1;
    public static final int ETIQUETAS_PERFIL = 2;

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

    /**
     * Se obtiene las etiquetas de un perfil como de una revista
     *
     * @param opcion 1 para revista, 2 para perfil
     * @param id
     * @return
     */
    public ArrayList<Etiqueta> obtenerEtiquetas(int opcion, int id) {
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        String query = opcion == 1 ? "SELECT * FROM Revista_Etiquetas WHERE RE_idRevista = ?;" : "SELECT * FROM Perfil WHERE P_nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, id);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                etiquetas.add(new Etiqueta(r.getString("RE_nombre_etiqueta")));
            }
        } catch (SQLException e) {
        }
        return etiquetas;
    }

    public Perfil obtenerPerfilUsuario(String nombreUsuario) {
        Perfil perfil = null;
        String query = "SELECT * FROM Perfil WHERE P_nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, nombreUsuario);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                perfil = new Perfil(r.getString(2), r.getString(3), obtenerEtiquetas(2, r.getInt(1)));
            }
        } catch (SQLException e) {
        }
        return perfil;
    }

    public Cliente obtenerClienteUsuario(String nombreUsuario) {
        Cliente cliente = null;
        cliente = new Cliente(obtenerUsuario(nombreUsuario), obtenerPerfilUsuario(nombreUsuario));
        return cliente;
    }

}
