package Controlador;

import EntidadesPrincipales.Anunciante;
import EntidadesAuxiliares.Info;
import EntidadesPrincipales.Revista;
import EntidadesAuxiliares.ValorSistema;
import EntidadesPrincipales.Anuncio;
import SQL.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author marco
 */
public class ControlAdmin {

    private final ModificarError modificar = new ModificarError();
    private String errorGeneral = "No se realizó la operación";

    /**
     * Registra a un anunciante con sus datos
     *
     * @param anunciante
     * @return
     */
    public Info registrarAnunciante(Anunciante anunciante) {
        String query = "INSERT INTO Anunciante(nombre_anunciante, telefono) VALUES(?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, anunciante.getNombreAnunciante());
            prepared.setInt(2, anunciante.getTelefono());
            prepared.executeUpdate();
            return new Info(true, "EXITO", "El anunciante se registró correctamente, puede seguir registrando");
        } catch (SQLException ex) {
            errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
        }
        return new Info(false, "ERROR", errorGeneral);
    }

    /**
     * Se aprueba o rechaza la publicación de una revista
     *
     * @param revista
     * @return
     */
    public Info cambiarEstadoRevista(Revista revista) {
        String query = "UPDATE Revista SET aprobado = ?, precio_costo = ? WHERE idRevista = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setBoolean(1, revista.isAprobado());
            prepared.setDouble(2, revista.getPrecio_costo());
            prepared.setInt(3, revista.getIdRevista());
            prepared.executeUpdate();
            if (revista.isAprobado()) {
                cambiarPrecioCosto(revista);
            }
            return new Info(true, "Exito", "El estado de la revista fue actualizado");
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
            return new Info(false, "Error", errorGeneral);
        }
    }

    /**
     * Cambia el precio de costo de una revista
     *
     * @param revista
     * @return
     */
    public Info cambiarPrecioCosto(Revista revista) {
        String query = "INSERT INTO CambioRevista(precio_costo, fecha_inicio, CR_idRevista, CR_nombre_usuario) VALUES (?,?,?,?);";
        PreparedStatement prepared = null;
        try {
            Conexion.Conexion().setAutoCommit(false);
            prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setDouble(1, revista.getPrecio_costo());
            prepared.setDate(2, revista.getFechaDate());
            prepared.setInt(3, revista.getIdRevista());
            prepared.setString(4, revista.getUsuarioCreador().getNombreUsuario());
            prepared.executeUpdate();
            if (actualizarUltimoCambio(revista)) {
                Conexion.Conexion().commit();
                return new Info(true, "Exito", "El precio de costo ha sido actualizado");
            }
        } catch (SQLException e) {
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
        return new Info(false, "Error", errorGeneral);
    }

    private boolean actualizarUltimoCambio(Revista revista) {
        int idCambio = obtenerUltimoRegisto(revista);
        String query = "UPDATE CambioRevista SET fecha_final = ? WHERE idCambioRevista = ?;";
        if (idCambio != -1) {
            try {
                PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
                prepared.setDate(1, revista.getFechaDate());
                prepared.setInt(2, idCambio);
                prepared.executeUpdate();
                return true;
            } catch (SQLException e) {
                errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
            }
        }
        return false;
    }

    private int obtenerUltimoRegisto(Revista revista) {
        String query = "SELECT idCambioRevista FROM CambioRevista WHERE CR_idRevista = ? ORDER BY fecha_inicio DESC LIMIT 1;";
        int n = -1;
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, revista.getIdRevista());
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                n = r.getInt(1);
            }
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
        }
        return n;
    }

    public Info actualizarComision(ValorSistema valor) {
        String query = "INSERT INTO ValoresSistema(porcentaje_comision, fecha) VALUES(?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setDouble(1, valor.getPorcentaje_comision());
            prepared.setDate(2, valor.getFechaDate());
            prepared.executeUpdate();
            return new Info(true, "EXITO", "El valor ha sido actualizado correctamente");
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
        }
        return new Info(false, "Error", errorGeneral);
    }

    public Info crearAnuncio(Anuncio anuncio, int dias) {
        String query = "INSERT INTO Anuncio(tipo_anuncio,texto,video_url,imagen_path,activo,fecha_inicio,fecha_final,pago,A_nombre_anunciante) VALUES(?,?,?,?,?,?,?,?,?);";
        LocalDate fechaIni = anuncio.getFecha_inicioDate().toLocalDate();
        LocalDate fechaFin = fechaIni.plusDays(dias);
        try {
            PreparedStatement p = Conexion.Conexion().prepareStatement(query);
            p.setInt(1, anuncio.getTipoAnuncio());
            p.setString(2, anuncio.getTexto());
            p.setString(3, anuncio.getVideo_url());
            p.setString(4, anuncio.getImagen_path());
            p.setBoolean(5, anuncio.isActivo());
            p.setDate(6, anuncio.getFecha_inicioDate());
            p.setDate(7, Date.valueOf(fechaFin));
            p.setDouble(8, anuncio.getPago());
            p.setString(9, anuncio.getAnunciante());
            p.executeUpdate();
            return new Info(true, "Exito", "El anuncio se creó correctamente");
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
            return new Info(false, "Error", errorGeneral);
        }
    }

    /**
     * Habilita o deshabilita un anuncio
     *
     * @param anuncio
     * @return
     */
    public Info actualizarAnuncio(Anuncio anuncio) {
        String query = "UPDATE Anuncio SET activo = ? WHERE idAnuncio = ?;";
        try {
            PreparedStatement p = Conexion.Conexion().prepareStatement(query);
            p.setBoolean(1, anuncio.isActivo());
            p.setInt(2, anuncio.getIdAnuncio());
            p.executeUpdate();
            String mensaje = anuncio.isActivo() ? "El anunció se habilitó correctamente" : "El anuncio se deshabilito correctamente";
            return new Info(true, "Exito", mensaje);
        } catch (SQLException e) {
            errorGeneral = modificar.obtenerTipoError(e.getErrorCode());
            return new Info(false, "Error", errorGeneral);
        }
    }

}
