package Entidades;

import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Perfil {

    private String descripcion;
    private ArrayList<Etiqueta> etiquetasInteres;

    public Perfil(String descripcion, ArrayList<Etiqueta> etiquetasInteres) {
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

}
