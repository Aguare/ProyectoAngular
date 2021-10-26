package Reportes;

import SQL.Conexion;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author marco
 */
public class ControlReportes {
    
    public void imprimirReporte(OutputStream targetStream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/jgranados/calcappapi/reports/report1.jasper");
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, Conexion.Conexion());

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }

    public void imprimirReportesConParametros(OutputStream targetStream, Map<String, Object> parametros, String pathReporte) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream(pathReporte);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, parametros, Conexion.Conexion());
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }

    public void imprimirReporteBeans(OutputStream targetStream, String path, JRDataSource parametros) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream(path);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, parametros);
        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
}
