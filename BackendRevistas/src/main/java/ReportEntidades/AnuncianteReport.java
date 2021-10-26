package ReportEntidades;

import EntidadesPrincipales.Anunciante;
import EntidadesPrincipales.Anuncio;
import java.util.List;

/**
 *
 * @author marco
 */
public class AnuncianteReport {
    
    private Anunciante anunciante;
    private List<Anuncio> anuncios;

    public AnuncianteReport(Anunciante anunciante, List<Anuncio> anuncios) {
        this.anunciante = anunciante;
        this.anuncios = anuncios;
    }

    public Anunciante getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Anunciante anunciante) {
        this.anunciante = anunciante;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    
}
