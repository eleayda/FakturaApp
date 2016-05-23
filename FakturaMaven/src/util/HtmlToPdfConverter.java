package util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

@SuppressWarnings("deprecation")
public class HtmlToPdfConverter {

	private String content = "<h1>default content</h1>";
	private String fileName = "defaultFileName.pdf";

	public HtmlToPdfConverter(String content, String fileName) {
		super();
		this.content = content;
		this.fileName = fileName;
	}

	public File getSourceFile() throws Exception {
		File file = new File(fileName);

		try {
			String k = this.content;
			OutputStream os = new FileOutputStream(file);
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, os);
			document.open();
			InputStream is = new ByteArrayInputStream(k.getBytes());
			XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
			document.close();
			os.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return file;
	}

}
