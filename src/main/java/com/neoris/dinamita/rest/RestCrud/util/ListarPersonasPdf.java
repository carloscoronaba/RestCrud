package com.neoris.dinamita.rest.RestCrud.util;

import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.neoris.dinamita.rest.RestCrud.model.Persona;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Component
public class ListarPersonasPdf  {

    public void generarPdf(List<Persona> personas, OutputStream outputStream) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20,-20,40,20);
        document.open();

        PdfPTable tablaTitulo = new PdfPTable(1);
        PdfPCell celda = null;

        //Fuentes
        Font fuenteTitulo = FontFactory.getFont("Helvetica",16,Color.WHITE);
        Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12, Color.BLACK);
        Font fuenteCeldas = FontFactory.getFont(FontFactory.COURIER,10,Color.BLACK);

        //Estilos Titulo
        celda = new PdfPCell(new Phrase("Listado de personas", fuenteTitulo));
        celda.setBorder(0);
        celda.setBackgroundColor(new Color(74,122,212));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(30);
        tablaTitulo.addCell(celda);

        tablaTitulo.setSpacingAfter(30);

        //Lista de personas
        PdfPTable tablaPersonas = new PdfPTable(5);

        //Estilos para el listado de personas
        tablaPersonas.setWidths(new float[]{0.8f,2f,2f,0.8f,3.5f});

        celda.setPhrase(new Phrase("ID", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonas.addCell(celda);

        celda.setPhrase(new Phrase("Nombre", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonas.addCell(celda);

        celda.setPhrase(new Phrase("Apellido", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonas.addCell(celda);

        celda.setPhrase(new Phrase("Edad", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonas.addCell(celda);

        celda.setPhrase(new Phrase("Email", fuenteTituloColumnas));
        celda.setBackgroundColor(Color.lightGray);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_CENTER);
        celda.setPadding(10);
        tablaPersonas.addCell(celda);


        //Llenado de la tabla con personas
        for (Persona persona: personas) {
            celda = new PdfPCell(new Phrase(persona.getId().toString(), fuenteCeldas));
            celda.setPadding(5);
            tablaPersonas.addCell(celda);

            celda = new PdfPCell(new Phrase(persona.getNombre(), fuenteCeldas));
            celda.setPadding(5);
            tablaPersonas.addCell(celda);

            celda = new PdfPCell(new Phrase(persona.getApellido(), fuenteCeldas));
            celda.setPadding(5);
            tablaPersonas.addCell(celda);

            celda = new PdfPCell(new Phrase(String.valueOf(persona.getEdad()), fuenteCeldas));
            celda.setPadding(5);
            tablaPersonas.addCell(celda);

            celda = new PdfPCell(new Phrase(persona.getEmail(), fuenteCeldas));
            celda.setPadding(5);
            tablaPersonas.addCell(celda);
        };

        document.add(tablaTitulo);
        document.add(tablaPersonas);
        document.close();
    }


}
