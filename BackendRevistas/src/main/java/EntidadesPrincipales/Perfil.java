package EntidadesPrincipales;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Perfil {

    private String foto;
    private String descripcion;
    private ArrayList<Etiqueta> etiquetasInteres;

    public Perfil(String foto, String descripcion, ArrayList<Etiqueta> etiquetasInteres) {
        this.foto = foto;
        this.descripcion = descripcion;
        this.etiquetasInteres = etiquetasInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Etiqueta> getEtiquetasInteres() {
        return etiquetasInteres;
    }

    public void setEtiquetasInteres(ArrayList<Etiqueta> etiquetasInteres) {
        this.etiquetasInteres = etiquetasInteres;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
