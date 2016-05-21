
(function(){

    var qList=$('.quantity');
    var pList= $('.price');
    var dList=$('.discount')
    var sumList= $('.sum');
    
    var fNameTop=$('#fNameTop');
    var sumTotalView=$('#sum-total');
    var vatView= $('#vat');
    var discSum30View=$('#discount30');
    var discSum50View=$('#discount50'); 
    var toPayView= $('#to-pay');
    
 
   
    var discSum30,discSum50,sumTotal;
	   
  
    // company name on the top & on the bottom of the document must be the same
    fNameTop.change(function(){
     
       var fNameBottom =$('#fNameBottom');
        fNameBottom.html(fNameTop.val());

    });
    // listen to the user input of quantity and change the results
    qList.change(function(){

        for (var i=0;i<qList.length;i++) {
          var  q= qList.eq(i);
          var  p=q.closest('tr').find('.price');
            updateSum(p,q);

        }
        updateTotal();

    });
    // listen to the user input of price and change the results
     pList.change(function(){

         for (var i=0;i<pList.length;i++) {
           var  p= pList.eq(i);
           var  q=p.closest('tr').find('.quantity');
             updateSum(p,q);

         }
         updateTotal();

     });
     
     //listen to the user input of discount and change sums
     dList.change(function(){

    	 updateTotal();

     });

        //update row-sum
      function updateSum(p,q){
     
      var sum=p.closest('tr').find('.sum');
        sum.html(q.val()* p.val());
    }

        //update total results
      function updateTotal(){
      
        updateDiscounts();
        updateTotalView();
}

    function updateDiscounts() {
    	
        var discount=0;
        var discVal; 
        	discSum30=0;
	   	 	discSum50=0;
	   	 	sumTotal=0;
	   	 	
        for (var i=0;i<sumList.length;i++) {
            var sum=sumList.eq(i);
            discVal=sum.closest('tr').find('.discount');
            sum=parseInt(sum.text());
            sumTotal+=sum;
                
            if(discVal.val()==='RUT'){
                    discount=0.5;
                    discSum50+=sum*1.25*discount*(-1)
                   
                    }
                if(discVal.val()==='ROT'){
                    discount=0.3;
                    discSum30+=sum*1.25*discount*(-1)
                  
            	}
           
                  
              }
			       

    }

    function  updateTotalView(){
  
    //update sum total
 	sumTotalView.html(sumTotal);
    //update vat
    vatView.html(sumTotal*0.25);
    discSum30View.html(discSum30);		
    discSum50View.html(discSum50);
    //update to pay
    toPayView.html(sumTotal+sumTotal*0.25+discSum30+discSum50);
}

}());
