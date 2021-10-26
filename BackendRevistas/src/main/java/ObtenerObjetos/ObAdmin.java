package ObtenerObjetos;

import EntidadesPrincipales.Anunciante;
import EntidadesPrincipales.Revista;
import EntidadesAuxiliares.ValorSistema;
import EntidadesPrincipales.Anuncio;
import EntidadesPrincipales.Etiqueta;
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
public class ObAdmin {

    private final ObGeneral obtenerG = new ObGeneral();

    public ArrayList<Anunciante> obtenerAnunciates() {
        ArrayList<Anunciante> anunciantes = new ArrayList<>();
        String query = "SELECT * FROM Anunciante;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet resultado = prepared.executeQuery();
            while (resultado.next()) {
                anunciantes.add(new Anunciante(resultado.getString("nombre_anunciante"), resultado.getInt("telefono")));
            }
        } catch (SQLException e) {
        }
        return anunciantes;
    }

    public ValorSistema obtenerComision() {
        String query = "SELECT * FROM ValoresSistema ORDER BY idValores DESC LIMIT 1;";
        ValorSistema valor = null;
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                valor = new ValorSistema(r.getDouble(2), r.getDate(3).toString());
            }
        } catch (SQLException e) {
        }
        return valor;
    }

    public ArrayList<ValorSistema> obtenerComisiones() {
        ArrayList<ValorSistema> valores = new ArrayList<>();
        String query = "SELECT * FROM ValoresSistema ORDER BY idValores ASC;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                valores.add(new ValorSistema(r.getDouble(2), r.getDate(3).toString()));
            }
        } catch (SQLException e) {
        }
        return valores;
    }

    /**
     * Obtiene las revistas
     *
     * @param opcion 1 -> pendientes de aceptar, 2 -> Ya aprobadas
     * @return
     */
    public ArrayList<Revista> obtenerRevistaPendiente(int opcion) {
        ArrayList<Revista> revistas = new ArrayList<>();
        String query = opcion == 1 ? "SELECT * FROM Revista WHERE precio_costo = 0 AND aprobado = 0;" : "SELECT * FROM Revista WHERE aprobado = 1;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revistas.add(new Revista(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                        r.getInt(5), r.getDouble(6), r.getBoolean(7), r.getBoolean(8), r.getDouble(9), r.getBoolean(10), r.getBoolean(11), r.getBoolean(12),
                        r.getDate(13).toString(), obtenerG.obtenerUsuario(r.getString(14)), obtenerG.obtenerEtiquetas(1, r.getInt(1))));
            }
        } catch (SQLException e) {
        }
        return revistas;
    }

    public ArrayList<Anuncio> obtenerAnuncios() {
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        String query = "SELECT * FROM Anuncio;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                anuncios.add(new Anuncio(r.getInt(1), r.getInt(2), r.getString(3),
                        r.getString(4), r.getString(5), r.getBoolean(6), r.getString(7),
                        r.getString(8), r.getDouble(9), r.getString(10), etiquetasAnuncio(r.getInt(1))));
            }
        } catch (SQLException e) {
        }
        return anuncios;
    }

    /**
     * Devuelve las etiquetas relacionadas con el anuncio
     *
     * @param idAnuncio
     * @return
     */
    private ArrayList<Etiqueta> etiquetasAnuncio(int idAnuncio) {
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        String query = "SELECT * FROM Anuncio_Etiquetas WHERE AE_idAnuncio = ?";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idAnuncio);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                etiquetas.add(new Etiqueta(r.getString(2)));
            }
        } catch (SQLException e) {
        }
        return etiquetas;
    }

    /**
     * Obtiene la lista según los datos
     *
     * @param fecha_inicio
     * @param fecha_final
     * @param idRevista
     * @param opcion
     * @return
     */
    public List<RevistaReport> obtenerRevistasReporte(String fecha_inicio, String fecha_final, int idRevista, int opcion) {
        String query2 = "SELECT idRevista FROM Revista WHERE idRevista = ?;";
        return obtenerRevistas(query2, idRevista, fecha_inicio, fecha_final);
    }

    private List<RevistaReport> obtenerRevistas(String consultaR, int idRevista, String fecha1, String fecha2) {
        ObEditor ob = new ObEditor();
        ArrayList<RevistaReport> revistas = new ArrayList<>();
        String query = "SELECT idRevista FROM Revista;";
        try {
            PreparedStatement prepared = null;
            if (idRevista == 0) {
                prepared = Conexion.Conexion().prepareStatement(query);
            } else {
                prepared = Conexion.Conexion().prepareStatement(consultaR);
                prepared.setInt(1, idRevista);
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revistas.add(new RevistaReport(ob.obtenerRevistaID(r.getInt(1)), ob.obtenerComentariosConsulta(r.getInt(1), fecha1, fecha2),
                        ob.obtenerReaccionesConsulta(r.getInt(1), fecha1, fecha2), ob.obtenerSuscripcionConsulta(r.getInt(1), fecha1, fecha2)));
            }
        } catch (SQLException e) {
        }
        return revistas;
    }

    /**
     * Devuelve las revistas segun la opcion
     *
     * @param opcion = 1 mas suscripcions, 2 más comentarios
     * @param fecha1
     * @param fecha2
     * @return
     */
    public List<RevistaReport> obtenerRevistasPopulares(int opcion, String fecha1, String fecha2) {
        ObEditor ob = new ObEditor();
        ArrayList<RevistaReport> revistas = new ArrayList<>();
        String query = "SELECT S_idRevista,COUNT(S_idRevista) As veces FROM Suscripcion GROUP BY S_idRevista ORDER BY veces DESC LIMIT 5;";
        String query2 = "SELECT CR_idRevista, COUNT(CR_idRevista) As veces FROM Comentario_Revista GROUP BY CR_idRevista ORDER BY veces DESC LIMIT 5;";
        String query3 = "SELECT S_idRevista,COUNT(S_idRevista) As veces FROM Suscripcion WHERE fecha_inicio BETWEEN ? AND ? GROUP BY S_idRevista ORDER BY veces DESC LIMIT 5;";
        String query4 = "SELECT CR_idRevista, COUNT(CR_idRevista) As veces FROM Comentario_Revista WHERE fecha BETWEEN ? AND ? GROUP BY CR_idRevista ORDER BY veces DESC LIMIT 5;";
        try {
            PreparedStatement prepared = null;
            
            if (opcion == 1 && fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query);
            } else if( opcion == 2 && fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query2);
            }else if (opcion == 1 && !fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query3);
            }else if (opcion == 2 && !fechasVacias(fecha1, fecha2)) {
                prepared = Conexion.Conexion().prepareStatement(query4);
            }
            if (!fechasVacias(fecha1, fecha2)) {
                prepared.setDate(1, getDate(fecha1));
                prepared.setDate(2, getDate(fecha2));
            }
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                revistas.add(new RevistaReport(ob.obtenerRevistaID(r.getInt(1)), ob.obtenerComentariosConsulta(r.getInt(1), fecha1, fecha2),
                        ob.obtenerReaccionesConsulta(r.getInt(1), fecha1, fecha2), ob.obtenerSuscripcionConsulta(r.getInt(1), fecha1, fecha2)));
            }
        } catch (SQLException e) {
        }
        return revistas;
    }

    private boolean fechasVacias(String fecha1, String fecha2) {
        return fecha1.equalsIgnoreCase("Vacio") && fecha2.equalsIgnoreCase("vacio");
    }

    private Date getDate(String fecha) {
        Date f = Date.valueOf(fecha);
        return f;
    }
}
