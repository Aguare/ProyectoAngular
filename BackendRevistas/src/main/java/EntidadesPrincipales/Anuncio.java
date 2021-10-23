package EntidadesPrincipales;

import java.sql.Date;

/**
 *
 * @author marco
 */
public class Anuncio {

    private int idAnuncio;
    private int tipo_anuncio;
    private String texto;
    private String video_url;
    private String imagen_path;
    private boolean activo;
    private String fecha_inicio;
    private String fecha_final;
    private Double pago;
    private String A_nombre_anunciante;

    public Anuncio(int idAnuncio, int tipoAnuncio, String texto, String video_url, String imagen_path, boolean activo, String fecha_inicio, String fecha_final, Double pago, String anunciante) {
        this.idAnuncio = idAnuncio;
        this.tipo_anuncio = tipoAnuncio;
        this.texto = texto;
        this.video_url = video_url;
        this.imagen_path = imagen_path;
        this.activo = activo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.pago = pago;
        this.A_nombre_anunciante = anunciante;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_inicioDate() {
        return Date.valueOf(fecha_inicio);
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public Date getFecha_finalDate() {
        return Date.valueOf(fecha_final);
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public int getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(int idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public int getTipoAnuncio() {
        return tipo_anuncio;
    }

    public void setTipoAnuncio(int tipoAnuncio) {
        this.tipo_anuncio = tipoAnuncio;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getImagen_path() {
        return imagen_path;
    }

    public void setImagen_path(String imagen_path) {
        this.imagen_path = imagen_path;
    }

    public String getAnunciante() {
        return A_nombre_anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.A_nombre_anunciante = anunciante;
    }

    public Double getPago() {
        return pago;
    }

    public void setPago(Double pago) {
        this.pago = pago;
    }

}
