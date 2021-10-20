package Entidades;

/**
 *
 * @author marco
 */
public class Comentario {
    
    private int idComentario;
    private String comentario;
    private String fecha;
    private String nombreUsuario;
    private int idRevista;

    public Comentario(int idComentario, String comentario, String fecha, String nombreUsuario, int idRevista) {
        this.idComentario = idComentario;
        this.comentario = comentario;
        this.fecha = fecha;
        this.nombreUsuario = nombreUsuario;
        this.idRevista = idRevista;
    }

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
