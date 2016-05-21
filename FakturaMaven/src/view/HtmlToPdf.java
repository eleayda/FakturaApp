package view;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import javax.activation.DataSource;
import javax.activation.FileDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import model.Company;
import model.Customer;
import model.Invoice;
import model.Invoice.Row;

@SuppressWarnings("deprecation")
public class HtmlToPdf {
private	Invoice invoice;
 

  public HtmlToPdf(Invoice invoice) {
	this.invoice=invoice;
}
  public DataSource getSource2() throws Exception {
	  File f=new File("faktura.pdf");
	  try {
		    String k = getHtmlString();
		    OutputStream file = new FileOutputStream(f);
		    Document document = new Document();
		    PdfWriter writer = PdfWriter.getInstance(document, file);
		    document.open();
		    InputStream is = new ByteArrayInputStream(k.getBytes());
		    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	  
	  return new FileDataSource (f);
  }


public DataSource getSource() throws Exception {
	 
	File file=new File("faktura.pdf");
	    try {
	      Document document = new Document(PageSize.LETTER);
	      PdfWriter.getInstance(document, new FileOutputStream(file));
	      document.open();
	      
	      document.addCreationDate();
	     

	      HTMLWorker htmlWorker = new HTMLWorker(document);
	      String str = getHtmlString();
	      htmlWorker.parse(new BufferedReader(new StringReader(str)));
	      document.close();
	      System.out.println("Done");
	      }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
		return new FileDataSource (file);
	  }



  private String getHtmlString() {
	  Company company=invoice.getCompany();
	  Customer customer=invoice.getCustomer();
	
	  String str="<head>"+
		    "<meta charset='utf-8'/>"+
		    "<link rel='stylesheet' type='text/css' href='WebContent/css/semantic.min.css'/>"+
		    "<title>"+"Faktura"+"</title>"+

		"</head>"+
		"<div class='ui divider hidden'>"+"</div>"+
		"<body style='margin: 5% 20% 5% 20%'>"+
		

		"<div class='ui divider hidden'>"+"</div>"+
		"<div class='ui segment' id='content' >"+


		      "<table name='customer-info' class='table' align='center' width='100%'>"+
		            "<tr   align='left'>"+
		                "<td colspan='4'>"+
		                    "<h1>"+ company.getCompanyName()+"</h1>"+

		                "</td>"+
		                "<td colspan='4'>"+
		                    "<h1>"+ "Faktura "+"</h1>"+
		                "</td>"+
		            "</tr>"+
		          "<tr   align='left'>"+
		              "<th colspan='6'>"+

		              "</th>"+
		              "<th>"+"Faktura datum"+"</th>"+
		              "<th>"+
		                  "Faktura nummer"+
		              "</th>"+
		              "</tr>"+
		          "<tr align='left'>"+
		              "<td colspan='6'>"+

		              "</td>"+
		              "<td>"+
		                  invoice.getDate()+

		              "</td>"+

		              "<td>"+
		                 invoice.getNumber()+
		              "</td>"+

		          "</tr>"+
		          "<tr>"+"<td>"+"<br>"+"</td>"+"</tr>"+
		          "<tr   align='left'>"+
		              "<td colspan='6'>"+
		              "</td>"+

		              "<td>"+
		                  "Köpare"+
		              "</td>"+
		                "<td>"+"Betalningsvillkor 20 dagar"+"</td>"+
		          "</tr>"+
		          "<tr   align='left'>"+
		              "<td colspan='6'>"+
		              "</td>"+
		              "<td>"+
		                  customer.getFirstName()+
		              "</td>"+

		          "</tr>"+
		          "<tr   align='left'>"+
		              "<td colspan='6'>"+
		              "</td>"+
		              "<td>"+
		                  customer.getLastName()+
		              "</td>"+

		          "</tr>"+
		          "<tr   align='left'>"+
		              "<td colspan='6'>"+
		              "</td>"+
		              "<td>"+
		                  customer.getStreet()+
		              "</td>"+

		          "</tr>"+
		          "<tr   align='left'>"+
		              "<td colspan='6'>"+
		              "</td>"+
		              "<td>"+
		                 customer.getSity()+"<br>"+
		              "</td>"+

		          "</tr>"+
		        "</table>"+


		

		    "<div class='ui divider hidden'>"+"</div>"+

		"<table class='ui table'>"+
		    "<tr>"+
		        "<th >"+
		            "<h3>"+"Beskrivning"+"</h3>"+
		        "</th>"+
		        "<th>"+
		            "<h3>"+"RUT/ROT"+"</h3>"+
		        "</th>"+
		        "<th>"+
		            "<h3>"+"tim/antal"+"</h3>"+
		        "</th>"+
		        "<th>"+
		            "<h3>"+"a-pris"+"</h3>"+
		        "</th>"+
		        "<th>"+
		            "<h2>"+"summa"+"</h2>"+
		        "</th>"+
		    "</tr>"+
		   getRowsAsString()+
	
		    "<tr align='right'>"+
		        "<td colspan='3'>"+"</td>"+
		        "<td>"+"summa:"+"</td>"+
		        "<td>"+"<label id='sum-total'>"+"0"+"</label>"+"</td>"+
		    "</tr>"+
		    "<tr align='right'>"+
		        "<td colspan='3'>"+"</td>"+
		        "<td>"+"moms 25%:"+"</td>"+
		        "<td>"+"<label id='vat'>"+"0"+"</label>"+"</td>"+
		    "</tr>"+
		    "<tr align='right'>"+
		        "<td colspan='3'>"+"</td>"+
		        "<td>"+"ROT 30%:"+"</td>"+
		        "<td>"+"<label id='discount30'>"+"0"+"</label>"+"</td>"+
		    "</tr>"+
		    "<tr align='right'>"+
		        "<td colspan='3'>"+"</td>"+
		        "<td>"+"RUT 50%:"+"</td>"+
		        "<td>"+"<label id='discount50'>"+"0"+"</label>"+"</td>"+
		    "</tr>"+
		    "<tr align='right'>"+
		        "<td colspan='3'>"+"</td>"+
		        "<td>"+"<h3>"+"Att betala:"+"</h3>"+"</td>"+
		        "<td>"+"<h3>"+"<label id='to-pay'>"+"0"+"</label>"+"</h3>"+"</td>"+
		    "</tr>"+

		"</table>"+
		    "<div class='ui divider hidden'>"+"</div>"+
		    "<table class='ui table'>"+
		        "<tr>"+
		            "<td>"+
		            company.getCompanyName()+				 
		            "</td>"+
		            "<td>"+
		                "<label>"+"telefon :"+"</label>"+company.getPhone()+
		            "</td>"+
		            "<td>"+
		                "<label>"+"org nummer :"+"</label>"+company.getVatNumber()+
		            "</td>"+

		            "<td>"+
		                "<label>"+"Bank Giro :"+"</label>"+company.getBank()+
		            "</td>"+

		        "</tr>"+

		        "<tr>"+

		            "<td>"+
		                "<label>"+"Adress :"+"</label>"+ company.getAdddress()+
		            "</td>"+
		            "<td>"+
		                "<label>"+ "Post Nummer :"+"</label>"+ company.getZipCode()+
		            "</td>"+
		            "<td>"+
		                "<label>"+ "Ort :"+"</label>"+company.getZipName()+
		            "</td>"+
		            "<td>"+
		                "<label>"+"Mail :"+"</label>"+company.getEmail()+
		            "</td>"+
		        "</tr>"+
		    "</table>"+
		    "<div class='ui divider hidden'>"+"</div>"+
		"</div>"+
		"<div class='ui divider hidden'>"+"</div>"+
		

		    "<h4 align='center'>"+"Copyright © 2016 FakturaApp.com"+"</h4>"+

		
		"</body>";
	return str;
	}

	private String getRowsAsString() {
	
	Row[]rows=invoice.getRows();
	StringBuilder sb=new StringBuilder();
	
	for(int i=0;i<rows.length;i++){
		String row= "<tr class='row'>"+
		        "<td >"+
		        "<label class='sum'>"+invoice.getRows()[i].getDescriprion()+"</label>"+
		        "</td>"+
		        "<td>"+"<select class='discount'>"+
		        "<label class='sum'>"+invoice.getRows()[i].getDiscount()+"</label>"+
		        
		        "</select>"+"</td>"+
		        "<td >"+
		        "<label class='sum'>"+invoice.getRows()[i].getQuantity()+"</label>"+
		        "</td>"+
		        "<td >"+
		        "<label class='sum'>"+invoice.getRows()[i].getPrice()+"</label>"+
		        "</td>"+
		        "<td >"+
		        "<label class='sum'>"+invoice.getRows()[i].getSum()+"</label>"+
		        "</td>"+
		    "</tr>";
		sb.append(row);
		}
	
	return sb.toString();
	}
}
