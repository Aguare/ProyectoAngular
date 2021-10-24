package ObtenerObjetos;

import EntidadesPrincipales.Revista;
import EntidadesPrincipales.Usuario;
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
    
    /**
     * Obtiene las revistas que ha publicado
     * @param usuario
     * @return 
     */
    public ArrayList<Revista> obtenerRevistasEditor(Usuario usuario) {
        ArrayList<Revista> revistas = new ArrayList<>();
        String query = "SELECT * FROM Revista WHERE R_nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, usuario.getNombreUsuario());
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revistas.add(new Revista(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                        r.getInt(5), r.getDouble(6), r.getBoolean(7), r.getBoolean(8), r.getDouble(9), r.getBoolean(10), r.getBoolean(11), r.getBoolean(12),
                        r.getDate(13).toString(), usuario, obtenerG.obtenerEtiquetas(1, r.getInt(1))));
            }
        } catch (SQLException e) {
        }
        return revistas;
    }
    
    /**
     * Obtiene una revista según su ID
     * @param idRevista
     * @return 
     */
    public Revista obtenerRevistaID(int idRevista) {
        Revista revista = null;
        String query = "SELECT * FROM Revista WHERE idRevista = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idRevista);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revista = new Revista(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                        r.getInt(5), r.getDouble(6), r.getBoolean(7), r.getBoolean(8), r.getDouble(9), r.getBoolean(10), r.getBoolean(11), r.getBoolean(12),
                        r.getDate(13).toString(), obtenerG.obtenerUsuario(r.getString(14)), obtenerG.obtenerEtiquetas(1, r.getInt(1)));
            }
        } catch (SQLException e) {
        }
        return revista;
    }
    
    
}
