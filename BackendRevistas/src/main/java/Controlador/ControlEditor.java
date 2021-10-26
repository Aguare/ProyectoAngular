package Controlador;

import EntidadesPrincipales.Etiqueta;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Revista;
import SQL.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String query = "INSERT INTO Revista(revista,titulo,descripcion,no_version,precio_costo,aprobado,suscripciones,"
                + "precio_suscripcion,es_pago,tiene_comentarios,tiene_reacciones,fecha,R_nombre_usuario) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
            prepared.setBoolean(7, revista.isSuscripciones());
            prepared.setDouble(8, revista.getPrecio_suscripcion());
            prepared.setBoolean(9, revista.isEs_pago());
            prepared.setBoolean(10, revista.isTiene_comentarios());
            prepared.setBoolean(11, revista.isTiene_reacciones());
            Date nuevaFecha = Date.valueOf(revista.getFecha());
            prepared.setDate(12, nuevaFecha);
            prepared.setString(13, revista.getUsuarioCreador().getNombreUsuario());
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

    /**
     * Actualiza el estado de las revistas
     *
     * @param revista
     * @param opcion 1 = tiene_comentarios, 2 = me gusta, 3 = suscripciones
     * @return
     */
    public Info cambiarEstadoRevista(Revista revista, int opcion) {
        String query = "";
        try {
            PreparedStatement p = null;
            switch (opcion) {
                case 1:
                    query = "UPDATE Revista SET tiene_comentarios = ? WHERE idRevista = ?;";
                    p = Conexion.Conexion().prepareStatement(query);
                    p.setBoolean(1, revista.isTiene_comentarios());
                    break;
                case 2:
                    query = "UPDATE Revista SET tiene_reacciones = ? WHERE idRevista = ?;";
                    p = Conexion.Conexion().prepareStatement(query);
                    p.setBoolean(1, revista.isTiene_reacciones());
                    break;
                case 3:
                    query = "UPDATE Revista SET suscripciones = ? WHERE idRevista = ?;";
                    p = Conexion.Conexion().prepareStatement(query);
                    p.setBoolean(1, revista.isSuscripciones());
                    break;
                default:
                    break;
            }
            p.setInt(2, revista.getIdRevista());
            p.executeUpdate();
            return new Info(true, "Exito", "Se actualizaron los valores");
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
            return new Info(false, "Error", "No se actualizaron los valores" + errorGeneral);
        }
    }

}
