$(document).ready(function() {
	
  $('#ghsubmitbtn').on('click', function(e){
	
	console.log("####### button clicked ###########");
    e.preventDefault();
    $('#ghapidata').html('<div id="loader"><img src="css/loader.gif" alt="loading..."></div>');
    
    var projectkey = $('#ghusername').val();
    var requri   = 'http://localhost:9000/api/resources?resource='+projectkey+'&metrics=ncloc&format=json';
    
    if(projectkey == ''){
    	$('#ghapidata').html('<h2><span style="color: #ff0000">No Project Info Found</span></h2>');
    }
    
    /*function processData(json){
      
    	  var projectname   = json[0].name;
    	  var ncloc   = json[0].msr[0].frmt_val;  
    	  var outhtml = '<div class="repolist clearfix"><p>Name of the project : <span style="color: #ff0000"><strong>'+projectname+'</strong></span></p><p>Number of code lines in the project : <span style="color: #0000A0"><strong>'+ncloc+'</strong></span></p></div>';       
          $('#ghapidata').html(outhtml);
    }*/ // end requestJSON Ajax call
    
    
    var req = $.ajax({

		type: "GET",
		//cache: true,
		//jsonp: "callback",
		url: requri,
		//success: processData,	
		dataType : "jsonp",
		//async : false,
		//context: document.body,
		timeout : 5000
	}); //end of $.ajax
    
    
    req.success(function(json) {
    	var projectname   = json[0].name;
  	  	var ncloc   = json[0].msr[0].frmt_val;  
  	  	var outhtml = '<div class="repolist clearfix"><p>Name of the project : <span style="color: #ff0000"><strong>'+projectname+'</strong></span></p><p>Number of code lines in the project : <span style="color: #0000A0"><strong>'+ncloc+'</strong></span></p></div>';       
        $('#ghapidata').html(outhtml);
    });
    
    req.error(function() {
        console.log('Oh noes!');
        $('#ghapidata').html('<h2><span style="color: #ff0000">No Project Info Found</span></h2>');
    });
    
    
  }); // end click event handler
  

});
