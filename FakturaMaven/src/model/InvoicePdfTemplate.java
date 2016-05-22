package model;


public class InvoicePdfTemplate extends Invoice {
	
	private String fileName="invoice.pdf";
	private final String MIME="application/pdf";
	private String content="";
	
	
	
	public InvoicePdfTemplate() {
		super();
		
	}

	public InvoicePdfTemplate(String fileName) {
		this();
		this.fileName = fileName;
		
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	
	public void setContent(String content) {
		this.content = content;
	}

	public String getMIME() {
		return MIME;
	}

	public String getContent(){
		 Company company=this.getCompany();
		  Customer customer=this.getCustomer();
			
		  this. content=
				"<head>"+
			    "<meta charset='utf-8'/>"+
			    "<link rel='stylesheet' type='text/css' href='./WebContent/css/semantic.min.css'/>"+
			  
			    "</head>"+
			
			   
			    "<body style='margin: 5% 20% 5% 20%'>"+
			

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
			      "</table >"+     
			      "<table >"+
			          "<tr   align='left'>"+
			              "<th colspan='6'>"+

			              "</th>"+
			              "<th>"+"datum"+"</th>"+
			              "<th>"+
			                  "nummer"+
			              "</th>"+
			              "</tr>"+
			          "<tr align='left'>"+
			              "<td colspan='6'>"+

			              "</td>"+
			              "<td>"+
			                  getDate()+

			              "</td>"+

			              "<td>"+
			                 getNumber()+
			              "</td>"+

			          "</tr>"+
			          "</table >"+
			          "<table >"+   
			          "<tr   align='left'>"+
			              "<th colspan='6'>"+
			              "Betalningsvillkor 20 dagar"+
			              "</th>"+

			              "<th>"+
			                  "Köpare"+
			              "</th>"+
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
			        "<td>"+"<label id='sum-total'>"+this.getSumTotal()+"</label>"+"</td>"+
			    "</tr>"+
			    "<tr align='right'>"+
			        "<td colspan='3'>"+"</td>"+
			        "<td>"+"moms 25%:"+"</td>"+
			        "<td>"+"<label id='vat'>"+this.getVat()+"</label>"+"</td>"+
			    "</tr>"+
			    "<tr align='right'>"+
			        "<td colspan='3'>"+"</td>"+
			        "<td>"+"ROT 30%:"+"</td>"+
			        "<td>"+"<label id='discount30'>"+this.getDiscount30()+"</label>"+"</td>"+
			    "</tr>"+
			    "<tr align='right'>"+
			        "<td colspan='3'>"+"</td>"+
			        "<td>"+"RUT 50%:"+"</td>"+
			        "<td>"+"<label id='discount50'>"+this.getDiscount50()+"</label>"+"</td>"+
			    "</tr>"+
			    "<tr align='right'>"+
			        "<td colspan='3'>"+"</td>"+
			        "<td>"+"<h3>"+"Att betala:"+"</h3>"+"</td>"+
			        "<td>"+"<h3>"+"<label id='to-pay'>"+this.getSumToPay()+"</label>"+"</h3>"+"</td>"+
			    "</tr>"+

			"</table>"+
			    "<div class='ui divider hidden'>"+"</div>"+
			    "<table class='ui table'>"+
			    "<tr>"+
			    	"<th>"+
			    	"företag :"+
			    	"</th>"+
			 		 "<th >"+
			 		 "telefon :"+
			 		 "</th>"+
			 		 "<th>"+
		            "org nummer :"+
		            "</th>"+
		            "<th>"+
		            "Bank Giro"+
		            "</th>"+
		    	"</tr>"+
			     "<tr>"+
			         "<td>"+
			          company.getCompanyName()+				 
			          "</td>"+
			          "<td>"+
			          company.getPhone()+
			          "</td>"+
			          "<td>"+
			         company.getVatNumber()+
			          "</td>"+
			          "<td>"+
			          company.getBank()+
			            "</td>"+

			        "</tr>"+
"</table>"+
"<table class='ui table'>"+
			        "<tr>"+
			    	"<th>"+
			    	"Adress :"+
			    	"</th>"+
			 		 "<th >"+
			 		 "Post Nummer :"+
			 		 "</th>"+
			 		 "<th>"+
		            "Ort :"+
		            "</th>"+
		            "<th>"+
		            "Email :"+
		            "</th>"+
		    	"</tr>"+
			        "<tr>"+

			            "<td>"+
			                company.getAdddress()+
			            "</td>"+
			            "<td>"+
			                company.getZipCode()+
			            "</td>"+
			            "<td>"+
			            company.getZipName()+
			            "</td>"+
			            "<td>"+
			               company.getEmail()+
			            "</td>"+
			        "</tr>"+
			  
			  
			"</div>"+
			
  "</table>"+
 
			    "<h4 align='center'>"+"Copyright © 2016 FakturaApp.com"+"</h4>"+

			
			"</body>";
		 
		return content;
	}
	
	private String getRowsAsString() {
		
		Row[]rows=getRows();
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<rows.length;i++){
			String row= "<tr class='row'>"+
			        "<td >"+
			        "<label class='sum'>"+getRows()[i].getDescriprion()+"</label>"+
			        "</td>"+
			        "<td>"+
			        "<label class='sum'>"+getRows()[i].getDiscount()+"</label>"+
			        "</td>"+
			        "<td >"+
			        "<label class='sum'>"+getRows()[i].getQuantity()+"</label>"+
			        "</td>"+
			        "<td >"+
			        "<label class='sum'>"+getRows()[i].getPrice()+"</label>"+
			        "</td>"+
			        "<td >"+
			        "<label class='sum'>"+getRows()[i].getSum()+"</label>"+
			        "</td>"+
			    "</tr>";
			sb.append(row);
			}
		return sb.toString();
	
	}

}
