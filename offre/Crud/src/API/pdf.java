/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 

/**
 *
 * @author myriam
 */
public class pdf {
        public static void main(String args[]) throws Exception 
    {
        Document document=new Document();
  PdfWriter.getInstance(document,new FileOutputStream(""));
       document.open();
       PdfPTable table=new PdfPTable(2);
       table.addCell("domaine");
       table.addCell("Id");
      


       
       
       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/khedmtech", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from Recruteur order by domaine ");
       while(rs.next()){
       table.addCell(rs.getString("domaine"));
       table.addCell(rs.getString("Id"));
      

       }
       document.add(table);
       document.close();
   }
    }
