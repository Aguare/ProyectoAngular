package EntidadesPrincipales;

/**
 *
 * @author marco
 */
public class Reaccion {

    private int idReaccion;
    private boolean reaccion;
    private String fecha;
    private String nombreUsuario;
    private int idRevista;

    public Reaccion(int idReaccion, boolean reaccion, String fecha, String nombreUsuario, int idRevista) {
        this.idReaccion = idReaccion;
        this.reaccion = reaccion;
        this.fecha = fecha;
        this.nombreUsuario = nombreUsuario;
        this.idRevista = idRevista;
    }

    public int getIdReaccion() {
        return idReaccion;
    }

    public void setIdReaccion(int idReaccion) {
        this.idReaccion = idReaccion;
    }

    public boolean isReaccion() {
        return reaccion;
    }

    public void setReaccion(boolean reaccion) {
        this.reaccion = reaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

}
