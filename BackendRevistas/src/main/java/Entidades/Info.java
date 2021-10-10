package Entidades;

/**
 *
 * @author marco
 */
public class Info {

    private boolean operacion;
    private String tipoError;
    private String mensaje;

    public Info(boolean operacion, String tipoError, String mensaje) {
        this.operacion = operacion;
        this.tipoError = tipoError;
        this.mensaje = mensaje;
    }

    public boolean isOperacion() {
        return operacion;
    }

    public void setOperacion(boolean operacion) {
        this.operacion = operacion;
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
