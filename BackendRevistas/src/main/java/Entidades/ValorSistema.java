package Entidades;

import java.sql.Date;

/**
 *
 * @author marco
 */
public class ValorSistema {
    
    private Double porcentaje_comision;
    private String fecha;
    
    public ValorSistema(Double porcentaje_comision, String fecha) {
        this.porcentaje_comision = porcentaje_comision;
        this.fecha = fecha;
    }
    
    public Double getPorcentaje_comision() {
        return porcentaje_comision;
    }
    
    public void setPorcentaje_comision(Double porcentaje_comision) {
        this.porcentaje_comision = porcentaje_comision;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public Date getFechaDate() {
        return Date.valueOf(this.fecha);
    }
    
}
