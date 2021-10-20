package Controlador;

import Entidades.Reaccion;
import ObtenerObjetos.ObLector;
import SQL.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class ControlLector {

    private ObLector obLec = new ObLector();

    public void darMG(Reaccion reaccion) {
        Reaccion nueva = obLec.verificarMG(reaccion);
        if (nueva != null) {
            cambiarValorMG(reaccion);
        } else {
            int generado = registrarMG(reaccion);
            reaccion.setIdReaccion(generado);
            registrarMGRevista(reaccion);
        }
    }

    private int registrarMG(Reaccion reaccion) {
        String query = "INSERT INTO Reaccion(reaccion, fecha, RM_nombre_usuario) VALUES(?,?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepared.setBoolean(1, true);
            Date fechaNueva = Date.valueOf(reaccion.getFecha());
            prepared.setDate(2, fechaNueva);
            prepared.setString(3, reaccion.getNombreUsuario());
            prepared.executeUpdate();
            ResultSet r = prepared.getGeneratedKeys();
            int n = -1;
            while (r.next()) {
                n = r.getInt(1);
            }
            return n;
        } catch (SQLException ex) {
        }
        return -1;
    }

    private void registrarMGRevista(Reaccion reaccion) {
        String query = "INSERT INTO Reaccion_Revista(RR_idReaccion, RR_nombre_usuario, RR_idRevista, fecha) VALUES(?,?,?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepared.setInt(1, reaccion.getIdReaccion());
            Date fechaNueva = Date.valueOf(reaccion.getFecha());
            prepared.setString(2, reaccion.getNombreUsuario());
            prepared.setInt(3, reaccion.getIdRevista());
            prepared.setDate(4, fechaNueva);
            prepared.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    private void cambiarValorMG(Reaccion reaccion) {
        String query = "UPDATE Reaccion SET reaccion = ? WHERE idReaccion=?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setBoolean(1, reaccion.isReaccion());
            prepared.setInt(2, reaccion.getIdReaccion());
            prepared.executeUpdate();
        } catch (SQLException ex) {
        }
    }
    
    
}
