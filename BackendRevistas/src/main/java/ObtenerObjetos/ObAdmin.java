package ObtenerObjetos;

import EntidadesPrincipales.Anunciante;
import EntidadesPrincipales.Revista;
import EntidadesAuxiliares.ValorSistema;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        } catch (Exception e) {
        }
        return anunciantes;
    }

    public ValorSistema obtenerComision() {
        String query = "SELECT * FROM ValoresSistema ORDER BY idValores DESC;";
        ValorSistema valor = null;
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet r = prepared.executeQuery();
            while (r.next()) {
                valor = new ValorSistema(r.getDouble(2), r.getDate(3).toString());
            }
        } catch (Exception e) {
        }

        return valor;
    }

    /**
     * Obtiene las revistas
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
}
