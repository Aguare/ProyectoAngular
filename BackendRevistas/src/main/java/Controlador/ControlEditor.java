package Controlador;

import Entidades.Etiqueta;
import Entidades.Info;
import Entidades.Revista;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class ControlEditor {

    private String errorGeneral = "";
    private ModificarError modificar = new ModificarError();

    public Info registrarEtiqueta(Etiqueta etiqueta) {
        String query = "INSERT INTO Etiqueta(nombre_etiqueta) VALUES(?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, etiqueta.getNombre());
            prepared.executeUpdate();
            return new Info(true, "EXITO", "Ya puedes seleccionar la etiqueta");
        } catch (SQLException ex) {
            errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
        }
        return new Info(false, "ERROR", errorGeneral);
    }

    public Info registrarRevista(Revista revista, String pathArchivo) {
        String query = "INSERT INTO Revista(revista,titulo,descripcion,no_version,precio_costo,aprobado,"
                + "precio_suscripcion,es_pago,tiene_comentarios,tiene_reacciones,R_nombre_usuario) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement prepared = null;
        try {
            Conexion.Conexion().setAutoCommit(false);
            prepared = Conexion.Conexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepared.setString(1, pathArchivo);
            prepared.setString(2, revista.getTitulo());
            prepared.setString(3, revista.getDescripcion());
            prepared.setInt(4, revista.getNo_version());
            prepared.setDouble(5, 0);
            prepared.setBoolean(6, false);
            prepared.setDouble(7, revista.getPrecio_suscripcion());
            prepared.setBoolean(8, revista.isEs_pago());
            prepared.setBoolean(9, revista.isTiene_comentarios());
            prepared.setBoolean(10, revista.isTiene_reacciones());
            prepared.setString(11, revista.getUsuarioCreador().getNombreUsuario());
            prepared.executeUpdate();
            ResultSet resultado = prepared.getGeneratedKeys();
            while (resultado.next()) {
                revista.setIdRevista(resultado.getInt(1));
            }
            registrarEtiquetasRevista(revista);
            Conexion.Conexion().commit();
            return new Info(true, "Exito", "La revista se registró correctamente y está en lista de espera");
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
        } finally {
            try {
                if (prepared.getResultSet() == null) {
                    Conexion.Conexion().rollback();
                }
                Conexion.Conexion().setAutoCommit(true);
            } catch (SQLException ex) {
                errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
            }
        }
        return new Info(false, "ERROR", errorGeneral);
    }

    public void registrarEtiquetasRevista(Revista revista) {
        ArrayList<Etiqueta> etiquetas = revista.getEtiquetas();
        for (Etiqueta etiqueta : etiquetas) {
            registrarEtiqueta(etiqueta.getNombre(), revista.getIdRevista());
        }
    }

    private void registrarEtiqueta(String etiqueta, int idRevista) {
        String query = "INSERT INTO Revista_Etiquetas VALUES(?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idRevista);
            prepared.setString(2, etiqueta);
            prepared.executeUpdate();
        } catch (SQLException ex) {
            errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
        }
    }
}
