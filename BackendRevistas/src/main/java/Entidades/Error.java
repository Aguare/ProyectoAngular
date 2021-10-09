package Entidades;

/**
 *
 * @author marco
 */
public class Error {

    private String tipoError;
    private String mensaje;

    public Error(String tipoError, String mensaje) {
        this.tipoError = tipoError;
        this.mensaje = mensaje;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
