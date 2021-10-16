package Controlador;

import Entidades.Etiqueta;
import Entidades.Info;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
