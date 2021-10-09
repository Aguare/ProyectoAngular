package Entidades;

/**
 *
 * @author marco
 */
public class Usuario {

    private String tipoUsuario;
    private String nombreUsuario;
    private String password;

    public Usuario(int tipoUsuario, String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        verificarTipo(tipoUsuario);
    }

    private void verificarTipo(int tipoUsuario) {
        switch (tipoUsuario) {
            case 1:
                this.tipoUsuario = "Administrador";
                break;
            case 2:
                this.tipoUsuario = "Lector";
                break;
            case 3:
                this.tipoUsuario = "Editor";
                break;
            default:
                this.tipoUsuario = "ERROR_404";
                break;
        }
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
