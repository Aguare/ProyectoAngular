package ObtenerObjetos;

import Entidades.Etiqueta;
import Entidades.Revista;
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
public class ObEditor {

    private ObGeneral obtenerG = new ObGeneral();

    public ArrayList<Revista> obtenerRevistasEditor(Usuario usuario) {
        ArrayList<Revista> revistas = new ArrayList<>();
        String query = "SELECT * FROM Revista WHERE R_nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, usuario.getNombreUsuario());
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revistas.add(new Revista(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                        r.getInt(5), r.getDouble(6), r.getDouble(7), r.getBoolean(8), r.getBoolean(9), r.getBoolean(10),
                        usuario, obtenerEtiquetasRevista(r.getInt(1))));
            }
        } catch (SQLException e) {
        }
        return revistas;
    }

    public ArrayList<Etiqueta> obtenerEtiquetasRevista(int idRevista) {
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        String query = "SELECT * FROM Revista_Etiquetas WHERE RE_idRevista = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idRevista);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                etiquetas.add(new Etiqueta(r.getString("RE_nombre_etiqueta")));
            }
        } catch (SQLException e) {
        }
        return etiquetas;
    }
}
