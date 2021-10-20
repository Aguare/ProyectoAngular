package ObtenerObjetos;

import Entidades.Reaccion;
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
public class ObLector {

    private ObGeneral obtenerG = new ObGeneral();

    public ArrayList<Revista> obtenerRevistasLector(Usuario usuario) {
        ArrayList<Revista> revistas = new ArrayList<>();
        String query = "SELECT * FROM Revista;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
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

    public Reaccion verificarMG(Reaccion reaccion) {
        Reaccion reac = null;
        String query = "SELECT * FROM Reaccion_Revista WHERE RR_idRevista = ? AND RR_nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, reaccion.getIdRevista());
            prepared.setString(2, reaccion.getNombreUsuario());
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                reac = new Reaccion(r.getInt(1), obtenerReaccion(reaccion.getIdReaccion()), r.getDate(4).toString(), r.getString(2), r.getInt(3));
            }
        } catch (SQLException e) {
        }
        return reac;
    }

    public boolean obtenerReaccion(int idReaccion) {
        String query = "SELECT * FROM Reaccion WHERE idReaccion = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idReaccion);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                return r.getBoolean("reaccion");
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public ArrayList<Reaccion> obtenerReaccionesRevista(int idRevista) {
        ArrayList<Reaccion> reacciones = new ArrayList<>();
        String query = "SELECT * FROM Reaccion_Revista WHERE RR_idRevista = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idRevista);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                reacciones.add(new Reaccion(r.getInt(1), obtenerReaccion(r.getInt(1)), r.getDate(4).toString(), r.getString(2), r.getInt(3)));
            }
        } catch (SQLException e) {
        }
        return reacciones;
    }
}
