package Reportes;

import EntidadesPrincipales.Etiqueta;
import SQL.Conexion;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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

    public void imprimirReportesConParametros(OutputStream targetStream, LocalDate startDate, LocalDate endDate) throws JRException {
        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/jgranados/calcappapi/reports/report2Params.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("StartDate", start);
        params.put("EndDate", end);
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, params, Conexion.Conexion());

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }

    public void imprimirReporteBeans(OutputStream targetStream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/jgranados/calcappapi/reports/report3ListBeans.jasper");

        ArrayList<Etiqueta> estudiantes = new ArrayList<>();
        estudiantes.add(new Etiqueta("Hola"));

        JRDataSource source = new JRBeanCollectionDataSource(estudiantes);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
    /*
    public void printReportWithComplexBeans(OutputStream targetStream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/jgranados/calcappapi/reports/report4ListComplexBeans.jasper");

        List<AsignacionSinCurso> asignaciones = new ArrayList<>();
        asignaciones.add(new AsignacionSinCurso(new Date(), false, new Estudiante("123456", "Mario", "Perez", new Date())));
        asignaciones.add(new AsignacionSinCurso(new Date(), false, new Estudiante("789012", "Silvia", "Hernandez", new Date())));
        asignaciones.add(new AsignacionSinCurso(new Date(), false, new Estudiante("345678", "Ana Lucia", "Fernandez", new Date())));
        asignaciones.add(new AsignacionSinCurso(new Date(), false, new Estudiante("901234", "Juan Luis", "Guerra", new Date())));

        JRDataSource source = new JRBeanCollectionDataSource(asignaciones);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }*/

 /*
    public void printMasterDetailReportWithComplexBeans(OutputStream targetStream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("com/jgranados/calcappapi/reports/report5MasterDetail.jasper");

        List<CursoReporte> cursos = new ArrayList<>();
        cursos.add(new CursoReporte("Mate B 1", "001", Arrays.asList(
                new Estudiante("123456", "Mario", "Perez", new Date()),
                new Estudiante("789012", "Silvia", "Hernandez", new Date())
        )));
        cursos.add(new CursoReporte("Fisica 1", "010", Arrays.asList(
                new Estudiante("789012", "Silvia", "Hernandez", new Date()),
                new Estudiante("123456", "Mario", "Perez", new Date()),
                new Estudiante("854651", "Jose", "Granados", new Date()),
                new Estudiante("8915616", "Magda", "Acevedo", new Date()),
                new Estudiante("86796156", "Juana", "Lainez", new Date()),
                new Estudiante("52315", "Fernando", "Gomez", new Date()),
                new Estudiante("8975", "Mauricio", "Guevara", new Date()),
                new Estudiante("98480", "Rosa", "Hernandez", new Date())
        )));
        cursos.add(new CursoReporte("IPC2", "100", Arrays.asList(
                new Estudiante("789012", "Silvia", "Hernandez", new Date()),
                new Estudiante("345678", "Ana Lucia", "Fernandez", new Date()),
                new Estudiante("901234", "Juan Luis", "Guerra", new Date()),
                new Estudiante("123456", "Mario", "Perez", new Date())
        )));

        JRDataSource source = new JRBeanCollectionDataSource(cursos);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }*/
}
