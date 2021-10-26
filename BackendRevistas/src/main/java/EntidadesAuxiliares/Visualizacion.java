package EntidadesAuxiliares;

import java.sql.Date;

/**
 *
 * @author marco
 */
public class Visualizacion {

    private int idVisualizacion;
    private String url;
    private String fecha;
    private int V_idAnuncio;
    private String V_nombre_anunciante;

    public Visualizacion(int idVisualizacion, String url, String fecha, int V_idAnuncio, String V_nombre_anunciante) {
        this.idVisualizacion = idVisualizacion;
        this.url = url;
        this.fecha = fecha;
        this.V_idAnuncio = V_idAnuncio;
        this.V_nombre_anunciante = V_nombre_anunciante;
    }

    public String getFecha() {
        return fecha;
    }

    public Date getFechaDate() {
        Date f = Date.valueOf(fecha);
        return f;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdVisualizacion() {
        return idVisualizacion;
    }

    public void setIdVisualizacion(int idVisualizacion) {
        this.idVisualizacion = idVisualizacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getV_idAnuncio() {
        return V_idAnuncio;
    }

    public void setV_idAnuncio(int V_idAnuncio) {
        this.V_idAnuncio = V_idAnuncio;
    }

    public String getV_nombre_anunciante() {
        return V_nombre_anunciante;
    }

    public void setV_nombre_anunciante(String V_nombre_anunciante) {
        this.V_nombre_anunciante = V_nombre_anunciante;
    }
}
