package ReportEntidades;

import EntidadesAuxiliares.Suscripcion;
import EntidadesPrincipales.Comentario;
import EntidadesPrincipales.Reaccion;
import EntidadesPrincipales.Revista;
import java.util.List;

/**
 *
 * @author marco
 */
public class RevistaReport {

    private Revista revista;
    private List<Comentario> comentarios;
    private List<Reaccion> reacciones;
    private List<Suscripcion> suscripciones;

    public RevistaReport(Revista revista, List<Comentario> comentarios, List<Reaccion> reacciones, List<Suscripcion> suscripciones) {
        this.revista = revista;
        this.comentarios = comentarios;
        this.reacciones = reacciones;
        this.suscripciones = suscripciones;
    }

    public Revista getRevista() {
        return revista;
    }

    public void setRevista(Revista revista) {
        this.revista = revista;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(List<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public List<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(List<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }
}
