package Entidades;

import java.sql.Date;

/**
 *
 * @author marco
 */
public class Suscripcion {

    private int idSuscripcion;
    private String fechaInicio;
    private String fechaFinal;
    private int meses;
    private boolean esta_anulado;
    private Usuario lector;
    private int idRevista;
    private Usuario editor;
    private Pago pago;

    public Suscripcion(int idSuscripcion, String fechaInicio, String fechaFinal, int meses, boolean esta_anulado, Usuario lector, int idRevista, Usuario editor, Pago pago) {
        this.idSuscripcion = idSuscripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.meses = meses;
        this.esta_anulado = esta_anulado;
        this.lector = lector;
        this.idRevista = idRevista;
        this.editor = editor;
        this.pago = pago;
    }

    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getMeses() {
        return meses;
    }

    public void setMeses(int meses) {
        this.meses = meses;
    }

    public boolean isEsta_anulado() {
        return esta_anulado;
    }

    public void setEsta_anulado(boolean esta_anulado) {
        this.esta_anulado = esta_anulado;
    }

    public Usuario getLector() {
        return lector;
    }

    public void setLector(Usuario lector) {
        this.lector = lector;
    }

    public Usuario getEditor() {
        return editor;
    }

    public void setEditor(Usuario editor) {
        this.editor = editor;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }
    
    public Date getFechaInicioDate(){
        return Date.valueOf(fechaInicio);
    }
    
    public Date getFechaFinalDate(){
        return Date.valueOf(fechaFinal);
    }
}
