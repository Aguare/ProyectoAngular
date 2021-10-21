package Controlador;

import Entidades.Anunciante;
import Entidades.Info;
import SQL.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author marco
 */
public class ControlAdmin {

    private ModificarError modificar = new ModificarError();
    private String errorGeneral = "No se realizó la operación";

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

}
