package ObtenerObjetos;

import EntidadesAuxiliares.Pago;
import EntidadesAuxiliares.Suscripcion;
import EntidadesPrincipales.Comentario;
import EntidadesPrincipales.Reaccion;
import EntidadesPrincipales.Revista;
import EntidadesPrincipales.Usuario;
import ReportEntidades.RevistaReport;
import SQL.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marco
 */
public class ObEditor {

    private ObGeneral obtenerG = new ObGeneral();
    private ObLector obtenerL;

    /**
     * Obtiene las revistas que ha publicado
     *
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
     *
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

    /**
     * Obtiene la lista según los datos
     *
     * @param usuario
     * @param fecha_inicio
     * @param fecha_final
     * @param idRevista
     * @param opcion
     * @return
     */
    public List<RevistaReport> obtenerRevistasReporte(String usuario, String fecha_inicio, String fecha_final, int idRevista, int opcion) {
        obtenerL = new ObLector();
        String query2 = "SELECT idRevista FROM Revista WHERE idRevista = ? AND R_nombre_usuario = ?;";
        return obtenerRevistas(query2, idRevista, usuario, fecha_inicio, fecha_final);
    }

    public List<RevistaReport> obtenerRevistas(String consultaR, int idRevista, String usuario, String fecha1, String fecha2) {
        ArrayList<RevistaReport> revistas = new ArrayList<>();
        String query = "SELECT idRevista FROM Revista WHERE R_nombre_usuario = ?;";
        try {
            PreparedStatement prepared = null;
            if (idRevista == 0) {
                prepared = Conexion.Conexion().prepareStatement(query);
                prepared.setString(1, usuario);
            } else {
                prepared = Conexion.Conexion().prepareStatement(consultaR);
                prepared.setInt(1, idRevista);
                prepared.setString(2, usuario);
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revistas.add(new RevistaReport(obtenerRevistaID(r.getInt(1)), obtenerComentariosConsulta(r.getInt(1), fecha1, fecha2),
                        obtenerReaccionesConsulta(r.getInt(1), fecha1, fecha2), obtenerSuscripcionConsulta(r.getInt(1), fecha1, fecha2)));
            }
        } catch (SQLException e) {
        }
        return revistas;
    }

    public List<RevistaReport> obtenerRevistasMasGustadas(int idRevista, String usuario, String fecha1, String fecha2) {
        ArrayList<RevistaReport> revistas = new ArrayList<>();
        String query = "SELECT RR_idRevista,COUNT(RR_idRevista) as total  FROM Reaccion_Revista group by RR_idRevista ORDER BY total desc;";
        String consultaR = "SELECT RR_idRevista,COUNT(RR_idRevista) as total  FROM Reaccion_Revista WHERE fecha BETWEEN ? AND ? group by RR_idRevista ORDER BY total desc;";
        try {
            PreparedStatement prepared = null;
            if (fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query);
            } else {
                prepared = Conexion.Conexion().prepareStatement(consultaR);
                prepared.setDate(1, getDate(fecha1));
                prepared.setDate(2, getDate(fecha2));
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                Revista revista = obtenerRevistaID(r.getInt(1));
                if (revista.getUsuarioCreador().getNombreUsuario().equalsIgnoreCase(usuario)) {
                    revistas.add(new RevistaReport(revista, obtenerComentariosConsulta(r.getInt(1), fecha1, fecha2),
                            obtenerReaccionesConsulta(r.getInt(1), fecha1, fecha2), obtenerSuscripcionConsulta(r.getInt(1), fecha1, fecha2)));
                }
            }
        } catch (SQLException e) {
        }
        return revistas;
    }

    private ArrayList<Comentario> obtenerComentariosConsulta(int idRevista, String fecha_inicio, String fecha_final) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        String query = "SELECT * FROM Comentario_Revista WHERE CR_idRevista = ? ORDER BY fecha;";
        String com2 = "SELECT * FROM Comentario_Revista WHERE CR_idRevista = ? AND fecha BETWEEN ? AND ?";
        try {
            PreparedStatement prepared = null;
            if (fechasVacias(fecha_final, fecha_final)) {
                prepared = Conexion.Conexion().prepareStatement(query);
                prepared.setInt(1, idRevista);
            } else {
                prepared = Conexion.Conexion().prepareStatement(com2);
                prepared.setInt(1, idRevista);
                Date f = Date.valueOf(fecha_inicio);
                prepared.setDate(2, f);
                Date i = Date.valueOf(fecha_final);
                prepared.setDate(3, i);
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                comentarios.add(new Comentario(r.getInt(1), obtenerComentario(r.getInt(1)), r.getDate(4).toString(), r.getString(2), r.getInt(3)));
            }
        } catch (SQLException e) {
        }
        return comentarios;
    }

    private ArrayList<Reaccion> obtenerReaccionesConsulta(int idRevista, String fecha1, String fecha2) {
        ArrayList<Reaccion> reacciones = new ArrayList<>();
        String query = "SELECT * FROM Reaccion_Revista WHERE RR_idRevista = ?;";
        String query2 = "SELECT * FROM Reaccion_Revista WHERE RR_idRevista = ? AND fecha BETWEEN ? AND ?;";
        try {
            PreparedStatement prepared = null;
            if (fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query);
                prepared.setInt(1, idRevista);
            } else {
                prepared = Conexion.Conexion().prepareStatement(query2);
                prepared.setInt(1, idRevista);
                Date f = Date.valueOf(fecha1);
                prepared.setDate(2, f);
                Date i = Date.valueOf(fecha2);
                prepared.setDate(3, i);
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                if (obtenerReaccion(r.getInt(1))) {
                    reacciones.add(new Reaccion(r.getInt(1), true, r.getDate(4).toString(), r.getString(2), r.getInt(3)));
                }
            }
        } catch (SQLException e) {
        }
        return reacciones;
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

    private ArrayList<Suscripcion> obtenerSuscripcionConsulta(int idRevista, String fecha1, String fecha2) {
        ArrayList<Suscripcion> sus = new ArrayList<>();
        String query = "SELECT * FROM Suscripcion WHERE S_idRevista = ?;";
        String query2 = "SELECT * FROM Suscripcion WHERE S_idRevista = ? AND fecha_inicio BETWEEN ? AND ?;";
        try {
            PreparedStatement prepared = null;
            if (fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query);
                prepared.setInt(1, idRevista);
            } else {
                prepared = Conexion.Conexion().prepareStatement(query2);
                prepared.setInt(1, idRevista);
                Date f = Date.valueOf(fecha1);
                prepared.setDate(2, f);
                Date i = Date.valueOf(fecha2);
                prepared.setDate(3, i);
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                sus.add(new Suscripcion(r.getInt(1), r.getString(2), r.getString(3), r.getInt(4),
                        r.getBoolean(5), obtenerG.obtenerUsuario(r.getString(6)), r.getInt(7),
                        obtenerG.obtenerUsuario(r.getString(8)), obtenerPago(r.getInt(1))));
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        }
        return sus;
    }

    private Pago obtenerPago(int idSuscripcion) {
        Pago pago = null;
        String query = "SELECT * FROM Pago WHERE P_idSuscripcion = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idSuscripcion);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                pago = new Pago(r.getInt(1), r.getDouble(3), r.getDouble(4), r.getDouble(5));
            }
        } catch (SQLException e) {
        }
        return pago;
    }

    private String obtenerComentario(int idComentario) {
        String comentario = "";
        String query = "SELECT * FROM Comentario WHERE idComentario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idComentario);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                return r.getString("comentario");
            }
        } catch (SQLException e) {
        }
        return comentario;
    }

    private boolean fechasVacias(String fecha1, String fecha2) {
        return fecha1.equalsIgnoreCase("Vacio") && fecha2.equalsIgnoreCase("vacio");
    }

    private Date getDate(String fecha) {
        Date f = Date.valueOf(fecha);
        return f;
    }
}
