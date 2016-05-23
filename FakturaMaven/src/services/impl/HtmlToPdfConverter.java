package services.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@SuppressWarnings("deprecation")
public class HtmlToPdfConverter {
	
	
	private String content="<h1>default content</h1>";
	private String fileName="defaultFileName.pdf";
	
	
	public HtmlToPdfConverter(String content,String fileName) {
		super();
		this.content = content;
		this.fileName=fileName;
	}


	//TODO call this method instead of getSource. But first prepare HTML string -> validate HTML5
	public File getSourceFile2() throws Exception {
		File file=new File(fileName);
	
	
//		  try {
//			    String k = this.content;
//			    OutputStream os = new FileOutputStream(file);
//			    Document document = new Document();
//			    PdfWriter writer = PdfWriter.getInstance(document, os);
//			    document.open();
//			    InputStream is = new ByteArrayInputStream(k.getBytes());
//			    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
//			    document.close();
//			    os.close();
//			} catch (Exception e) {
//			
//			    e.printStackTrace();
//			}
		
		  return file;
	  }


	public File getSourceFile() throws Exception {
	

		File file=new File( fileName);
		    try {
		      Document document = new Document(PageSize.LETTER);
		      PdfWriter.getInstance(document, new FileOutputStream(file));
		      document.open();
		      
		      document.addCreationDate();
		     

		      HTMLWorker htmlWorker = new HTMLWorker(document);
	     
		      String str=content;
		     // String str="<h1>TestString</>"+this.getCompany().getCompanyName();
		      htmlWorker.parse(new BufferedReader(new StringReader(str)));
		      document.close();
		      System.out.println("Done");
		      }
		    catch (Exception e) {
		   
		      e.printStackTrace();
		    }
		
			return file;
		  }

}
