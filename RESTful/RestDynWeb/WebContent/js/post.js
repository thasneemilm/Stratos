$(document).ready(function() {

	$("#service_submit2").click(function(e) {

		//alert("###### Button is clicked ######");
		e.preventDefault();
		function processData(data) {
			alert("Successfully inserted!");
			//if(data[0].HTTP_CODE == 200){
				//$('#ajax_Response').text(data[0].MSG);
			//}
		}

		var ajaxObj = {};
		
		var name = $("#service_name").val();
		var owner = $("#service_owner").val();
		var status = $("#service_status").val();
		var rank = $("#service_rank").val();
		
		var jsObj = { "name" : name, "owner" : owner, "status" : status, "rank" : rank };
				
		ajaxObj = {

			type : "POST",
			url : "http://localhost:8080/RestDynWeb/api/v2/inventory/",
			data : JSON.stringify(jsObj),
			success : processData,
			error : function(jqXHR, textStatus, errorThrown){
				console.log("Error  : " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			contentType : "application/json; charset=utf-8",
			dataType : "json",
		};
		
		$.ajax(ajaxObj);
	});
	
	
	$("#service_submit3").click(function(e) {

		alert("###### Button is clicked ######");
		e.preventDefault();
		function processData(data) {
			alert("Successfully inserted!");
			if(data[0].HTTP_CODE == 200){
				//$('#ajax_Response').text(data[0].MSG);
				alert("######" + data[0].MSG + "######");
			}
		}

		var ajaxObj = {};
		
		var name = $("#service_name").val();
		var owner = $("#service_owner").val();
		var status = $("#service_status").val();
		var rank = $("#service_rank").val();
		
		var jsObj = { "name" : name, "owner" : owner, "status" : status, "rank" : rank };
				
		ajaxObj = {

			type : "POST",
			url : "http://localhost:8080/RestDynWeb/api/v3/inventory/",
			data : JSON.stringify(jsObj),
			success : processData,
			error : function(jqXHR, textStatus, errorThrown){
				console.log("Error  : " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
			},
			contentType : "application/json; charset=utf-8",
			dataType : "json",
		};
		
		$.ajax(ajaxObj);
	});
	
});