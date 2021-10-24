package ReportEntidades;

import EntidadesAuxiliares.Suscripcion;
import EntidadesPrincipales.Comentario;
import EntidadesPrincipales.Reaccion;
import EntidadesPrincipales.Revista;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class RevistaReport {

    private Revista revista;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Reaccion> reacciones;
    private ArrayList<Suscripcion> suscripciones;

    public RevistaReport(Revista revista, ArrayList<Comentario> comentarios, ArrayList<Reaccion> reacciones, ArrayList<Suscripcion> suscripciones) {
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

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Reaccion> getReacciones() {
        return reacciones;
    }

    public void setReacciones(ArrayList<Reaccion> reacciones) {
        this.reacciones = reacciones;
    }

    public ArrayList<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(ArrayList<Suscripcion> suscripciones) {
        this.suscripciones = suscripciones;
    }
}
