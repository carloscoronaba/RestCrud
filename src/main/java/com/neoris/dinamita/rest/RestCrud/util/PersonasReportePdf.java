package com.neoris.dinamita.rest.RestCrud.util;

import com.neoris.dinamita.rest.RestCrud.model.Persona;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonasReportePdf {

    public byte[] exportPdf(List<Persona> listaPersonas) throws JRException, FileNotFoundException{
        return JasperExportManager.exportReportToPdf(getReport(listaPersonas));
    }

    private JasperPrint getReport(List<Persona> listaPersonas) throws FileNotFoundException, JRException{
        Map<String, Object> params = new HashMap<>();
        params.put("personasData", new JRBeanCollectionDataSource(listaPersonas));

        JasperPrint reporte = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                ResourceUtils.getFile("C:\\Users\\marco.aguirre\\JaspersoftWorkspace\\MyReports\\personasReport.jrxml")
                        .getAbsolutePath()), params, new JREmptyDataSource());

        return reporte;
    }

    //Para convertir a excel
        /*public byte[] exportToXls(List<Persona> listaPersonas) throws JRException, FileNotFoundException{
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
        JRXlsExporter exporter = new JRXlsExporter();
        exporter.setExporterInput(new SimpleExporterInput(getReport(listaPersonas)));
        exporter.setExporterOutput(output);
        exporter.exportReport();
        output.close();
        return byteArray.toByteArray();
    }*/

}
