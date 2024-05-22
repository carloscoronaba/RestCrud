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
@Service
public class PersonasReportePdf {

    @Autowired
    private DataSource dataSource; // Suponiendo que tienes un DataSource configurado en tu aplicación

    public byte[] exportPdf() throws JRException, SQLException, FileNotFoundException {
        return JasperExportManager.exportReportToPdf(getReport());
    }

    private JasperPrint getReport() throws JRException, SQLException, FileNotFoundException {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            Map<String, Object> params = new HashMap<>();
            JasperPrint reporte = JasperFillManager.fillReport(JasperCompileManager.compileReport(
                    ResourceUtils.getFile("C:\\Users\\carlos.corona\\JaspersoftWorkspace\\MyReports\\PeticionOracle.jrxml")
                            .getAbsolutePath()), params, conn);
            return reporte;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
