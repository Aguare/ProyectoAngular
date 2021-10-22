package EntidadesPrincipales;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Revista {

    private int idRevista;
    private String revista;
    private String titulo;
    private String descripcion;
    private int no_version;
    private double precio_costo;
    private boolean aprobado;
    private boolean suscripciones;
    private double precio_suscripcion;
    private boolean es_pago;
    private boolean tiene_comentarios;
    private boolean tiene_reacciones;
    private String fecha;
    private Usuario usuarioCreador;
    private ArrayList<Etiqueta> etiquetas;

    public Revista(int idRevista, String revista, String titulo, String descripcion, int no_version, double precio_costo, boolean aprobado, boolean suscripciones,
            double precio_suscripcion, boolean es_pago, boolean tiene_comentarios, boolean tiene_reacciones, String fecha, Usuario usuarioCreador, ArrayList<Etiqueta> etiquetas) {
        this.idRevista = idRevista;
        this.revista = revista;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.no_version = no_version;
        this.precio_costo = precio_costo;
        this.aprobado = aprobado;
        this.suscripciones = suscripciones;
        this.precio_suscripcion = precio_suscripcion;
        this.es_pago = es_pago;
        this.tiene_comentarios = tiene_comentarios;
        this.tiene_reacciones = tiene_reacciones;
        this.fecha = fecha;
        this.usuarioCreador = usuarioCreador;
        this.etiquetas = etiquetas;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public int getNo_version() {
        return no_version;
    }

    public void setNo_version(int no_version) {
        this.no_version = no_version;
    }

    public double getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getPrecio_suscripcion() {
        return precio_suscripcion;
    }

    public void setPrecio_suscripcion(double precio_suscripcion) {
        this.precio_suscripcion = precio_suscripcion;
    }

    public boolean isEs_pago() {
        return es_pago;
    }

    public void setEs_pago(boolean es_pago) {
        this.es_pago = es_pago;
    }

    public boolean isTiene_comentarios() {
        return tiene_comentarios;
    }

    public void setTiene_comentarios(boolean tiene_comentarios) {
        this.tiene_comentarios = tiene_comentarios;
    }

    public boolean isTiene_reacciones() {
        return tiene_reacciones;
    }

    public void setTiene_reacciones(boolean tiene_reacciones) {
        this.tiene_reacciones = tiene_reacciones;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public boolean isSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(boolean suscripciones) {
        this.suscripciones = suscripciones;
    }

    public String getFecha() {
        return fecha;
    }

    public Date getFechaDate() {
        return Date.valueOf(fecha);
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
