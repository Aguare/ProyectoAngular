package Controlador;

import Entidades.Cliente;
import Entidades.Etiqueta;
import Entidades.Info;
import Entidades.Perfil;
import Entidades.Usuario;
import SQL.Conexion;
import SQL.Encriptar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class ControlUsuario {

    private Encriptar desencriptar = new Encriptar();
    private ModificarError modificar = new ModificarError();
    private String errorGeneral = "No se realizó la operación";

    /**
     * Verifica la contraseña con el método de encriptación
     *
     * @param usuario
     * @param password
     * @return
     */
    public Usuario verificarUsuario(String usuario, String password) {
        String query = "SELECT * FROM Usuario WHERE nombre_usuario = ?;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, usuario);
            ResultSet resultado = prepared.executeQuery();

            String pass = "";
            String nombreUsuario = "";
            int tipoUsuario = 0;
            while (resultado.next()) {
                pass = resultado.getString("password");
                nombreUsuario = resultado.getString("nombre_usuario");
                tipoUsuario = resultado.getInt("U_idTipoUsuario");
            }
            pass = desencriptar.desencriptarPass(pass, "ipc");
            if (pass.equals(password)) {
                return new Usuario(tipoUsuario, nombreUsuario, "");
            } else {
                return new Usuario(0, "ERROR_404", "");
            }
        } catch (SQLException e) {
        }
        return new Usuario(0, "ERROR_404", "");
    }

    public ArrayList<Etiqueta> obtenerEtiquetas() {
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        String query = "SELECT * FROM Etiqueta;";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            ResultSet resultado = prepared.executeQuery();
            while (resultado.next()) {
                etiquetas.add(new Etiqueta(resultado.getString("nombre_etiqueta")));
            }
        } catch (Exception e) {
        }
        return etiquetas;
    }

    public Info regitrarCliente(Cliente cliente) {
        if (registrarUsuario(cliente.getUsuario())) {
            int idPerfil = registrarPerfil(cliente.getPerfil(), cliente.getUsuario().getNombreUsuario());
            ArrayList<Etiqueta> etiquetasSelec = cliente.getPerfil().getEtiquetasInteres();
            for (Etiqueta etiqueta : etiquetasSelec) {
                registrarEtiqueta(idPerfil, cliente.getUsuario().getNombreUsuario(), etiqueta);
            }
            return new Info(true, "EXITO", "Se ingresó correctamente, ya puede iniciar sesión");
        } else {
            return new Info(false, "ERROR", errorGeneral);
        }
    }

    public boolean registrarUsuario(Usuario usuario) {
        String query = "INSERT INTO Usuario(nombre_usuario, password, U_idTipoUsuario) VALUES(?,?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setString(1, usuario.getNombreUsuario());
            prepared.setString(2, desencriptar.encriptarPass(usuario.getPassword()));
            prepared.setInt(3, Integer.parseInt(usuario.getTipoUsuario()));
            prepared.executeUpdate();
            return true;
        } catch (SQLException ex) {
            errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
        }
        return false;
    }

    public int registrarPerfil(Perfil perfil, String nombreUsuario) {
        String query = "INSERT INTO Perfil(foto, descripcion, P_nombre_usuario) VALUES(?,?,?);";
        int idPerfil = -1;
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            prepared.setString(1, perfil.getFoto());
            prepared.setString(2, perfil.getDescripcion());
            prepared.setString(3, nombreUsuario);
            prepared.executeUpdate();
            ResultSet resultado = prepared.getGeneratedKeys();
            while (resultado.next()) {
                idPerfil = resultado.getInt(1);
            }
            return idPerfil;
        } catch (SQLException ex) {
            errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
        }
        return idPerfil;
    }

    public void registrarEtiqueta(int idPerfil, String nombreUsuario, Etiqueta etiqueta) {
        String query = "INSERT INTO Perfil_Etiquetas(PE_idPerfil, PE_nombre_Usuario, PE_nombre_Etiqueta) VALUES(?,?,?);";
        try {
            PreparedStatement prepared = Conexion.Conexion().prepareStatement(query);
            prepared.setInt(1, idPerfil);
            prepared.setString(2, nombreUsuario);
            prepared.setString(3, etiqueta.getNombre());
            prepared.executeUpdate();
        } catch (SQLException ex) {
            errorGeneral = modificar.obtenerTipoError(ex.getErrorCode());
        }
    }
}
