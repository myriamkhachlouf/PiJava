/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.services;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.DefaultTagWorkerFactory;
import com.itextpdf.html2pdf.css.apply.impl.DefaultCssApplierFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Mohamed
 */
public class PDFLib {
    public  void htmltopdf (String htmlSource, String pdfDest) throws FileNotFoundException, IOException 
    {
         // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), 
        new FileOutputStream(pdfDest), converterProperties);
        
    }
}
