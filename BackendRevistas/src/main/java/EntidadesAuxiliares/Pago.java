package EntidadesAuxiliares;

/**
 *
 * @author marco
 */
public class Pago {

    private int idPago;
    private Double parte_obtenido;
    private Double parte_editor;
    private Double total;

    public Pago(int idPago, Double parte_obtenido, Double parte_editor, Double total) {
        this.idPago = idPago;
        this.parte_obtenido = parte_obtenido;
        this.parte_editor = parte_editor;
        this.total = total;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public Double getParte_obtenido() {
        return parte_obtenido;
    }

    public void setParte_obtenido(Double parte_obtenido) {
        this.parte_obtenido = parte_obtenido;
    }

    public Double getParte_editor() {
        return parte_editor;
    }

    public void setParte_editor(Double parte_editor) {
        this.parte_editor = parte_editor;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
