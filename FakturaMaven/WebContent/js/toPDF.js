(function(){
	var exitURL="index.jsp";
    var
        form = $('#content'),
        cache_width = form.width(),
        a4  =[ 595.28,  841.89];  // for a4 size paper width and height

    $('#cmd').on('click',function(){
        $('body').scrollTop(0);
       createPDF();
      
     //  window.print();
      // goToNextURL();
        
    });
//create pdf
    function createPDF(){
        getCanvas().then(function(canvas){
            var
                img = canvas.toDataURL("image/JPEG"),
                doc = new jsPDF({
                    unit:'px',
                    format:'a4'
                });
            doc.addImage(img, 'JPEG', 20, 20);
          
            doc.save('faktura.pdf');
            properties = {type: 'application/pdf'}; // Specify the file's mime-type.

            try {
            	  // Specify the filename using the File constructor, but ...
            	  file = new File(data, "file.pdf", properties);
            	} catch (e) {
            	  // ... fall back to the Blob constructor if that isn't supported.
            	  file = new Blob(data, properties);
            	}
            	url = URL.createObjectURL(file);
            	$('link').html(url);
               
            
            form.width(cache_width);
        });
    	
      
    }
    
  
    
// create canvas object
    function getCanvas(){
        form.width((a4[0]*1.33333) -80).css('max-width','none');
        return html2canvas(form,{
            imageTimeout:2000,
            removeContainer:true
        });
    }
    
    function goToNextURL()  { window.location.href = exitURL; };
   
}());
