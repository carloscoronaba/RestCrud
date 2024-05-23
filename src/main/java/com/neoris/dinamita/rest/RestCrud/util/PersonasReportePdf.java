package com.neoris.dinamita.rest.RestCrud.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//Genera el reporte en formato PDF
@Service
public class PersonasReportePdf {

    @Autowired
    private DataSource dataSource;

    //Genera el reporte en formato pdf.
    public byte[] exportPdf() throws JRException, SQLException, FileNotFoundException {
        //Exporta un JasperReport a un array de bytes que representa el reporte en formato PDF
        return JasperExportManager.exportReportToPdf(getReport());
    }

    //Crea y llena el objeto JasperPrint que es necesario para crear el reporte
    private JasperPrint getReport() throws JRException, SQLException, FileNotFoundException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection(); //Obtiene una conexión de BD a traves del objeto DataSource
            Map<String, Object> params = new HashMap<>(); //Mapa de parametros (personalizar el reporte)

            //Compila el archivo .jrxml Y llena el reporte con los datos recopilados.
            JasperPrint reporte = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                    ResourceUtils.getFile("C:\\Users\\marco.aguirre\\JaspersoftWorkspace\\MyReports\\ConsultaJasper.jrxml")
                            .getAbsolutePath()), params, conn);
                    //Recibe por parámetro el archivo compilado, los parametros y la conexión a la base de datos
            return reporte;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

}
