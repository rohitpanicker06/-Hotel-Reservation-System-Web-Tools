/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotel_reservation_system.view;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.document.AbstractPdfView;

/**
 *
 * @author rohitpanicker
 */
public class PdfViewImpl extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
         
     Paragraph p1 =  new Paragraph("Paragraph 1");
     Paragraph p2 =  new Paragraph("Paragraph 2");
     doc.add(p1);
     doc.add(p2);
     doc.close();
    }
    
}
