package ObtenerObjetos;

import Entidades.Anunciante;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ObAdmin {

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
}
