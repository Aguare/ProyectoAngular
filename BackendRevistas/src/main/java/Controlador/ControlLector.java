package Controlador;

import Entidades.Comentario;
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
        String query = "UPDATE Reaccion SET reaccion = ?, fecha = ? WHERE idReaccion=?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setBoolean(1, reaccion.isReaccion());
            Date fechaNueva = Date.valueOf(reaccion.getFecha());
            prepared.setDate(2, fechaNueva);
            prepared.setInt(3, reaccion.getIdReaccion());
            prepared.executeUpdate();
            cambiarFechaMG(reaccion);
        } catch (SQLException ex) {
        }
    }

    private void cambiarFechaMG(Reaccion reaccion) {
        String query = "UPDATE Reaccion_Revista SET fecha = ? WHERE RR_idReaccion=?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            Date fechaNueva = Date.valueOf(reaccion.getFecha());
            prepared.setDate(1, fechaNueva);
            prepared.setInt(2, reaccion.getIdReaccion());
            prepared.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void registrarComentarioGeneral(Comentario comentario) {
        int idComentario = registrarComentario(comentario);
        comentario.setIdComentario(idComentario);
        registrarComentarioRevista(comentario);
    }

    private int registrarComentario(Comentario comentario) {
        String query = "INSERT INTO Comentario(comentario, fecha, C_nombre_usuario) VALUES(?,?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepared.setString(1, comentario.getComentario());
            Date fechaNueva = Date.valueOf(comentario.getFecha());
            prepared.setDate(2, fechaNueva);
            prepared.setString(3, comentario.getNombreUsuario());
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

    private void registrarComentarioRevista(Comentario comentario) {
        String query = "INSERT INTO Comentario_Revista(CR_idComentario, CR_nombre_usuario, CR_idRevista, fecha) VALUES(?,?,?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepared.setInt(1, comentario.getIdComentario());
            Date fechaNueva = Date.valueOf(comentario.getFecha());
            prepared.setString(2, comentario.getNombreUsuario());
            prepared.setInt(3, comentario.getIdRevista());
            prepared.setDate(4, fechaNueva);
            prepared.executeUpdate();
        } catch (SQLException ex) {
        }
    }
}
