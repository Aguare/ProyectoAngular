package Entidades;

/**
 *
 * @author marco
 */
public class Anunciante {

    private String nombreAnunciante;
    private int telefono;

    public Anunciante(String nombreAnunciante, int telefono) {
        this.nombreAnunciante = nombreAnunciante;
        this.telefono = telefono;
    }

    public String getNombreAnunciante() {
        return nombreAnunciante;
    }

    public void setNombreAnunciante(String nombreAnunciante) {
        this.nombreAnunciante = nombreAnunciante;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
