package Reportes;

import ObtenerObjetos.ObEditor;
import ReportEntidades.RevistaReport;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author marco
 */
public class GenerarRE {
    
    private final ObEditor obE = new ObEditor();
    private final ControlReportes reporte = new ControlReportes();
    private final String R1 = "Reportes/EditorR/R1.jasper";
    private final String R2 = "";
    private final String R3 = "";
    private final String R4 = "";

    public void generarReporte(String usuario, String fecha_inicio, String fecha_final, int idRevista, int opcion, OutputStream out) {
        try {
            switch (opcion) {
                case 1:
                    List<RevistaReport> revistas = obE.obtenerRevistasReporte(usuario, fecha_inicio, fecha_final, idRevista, opcion);
                    JRDataSource source = new JRBeanCollectionDataSource(revistas);
                    reporte.imprimirReporteBeans(out, R1, source);
                    break;
                default:
                    break;
            }
        } catch (JRException e) {
            System.out.println(e.getMessage());
        }
    }

    private Date castearFecha(String fecha) {
        try {
            Date fechaDate = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            return fechaDate;
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return null;
    }
}
