package com.youtube.rest.inventory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.youtube.dao.Sql_schema;

@Path("/v3/inventory")
public class V3_inventory {
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addServices2(String incomingData) throws Exception{
		
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		Sql_schema dao = new Sql_schema();
		
		try{
			
			JSONObject serviceData = new JSONObject(incomingData);
			System.out.println("Service Data : " + serviceData.toString());
					
			int http_code = dao.insertIntoService(serviceData.optString("name"), serviceData.optString("owner"), serviceData.optString("status"), serviceData.optInt("rank"));
			
			if(http_code == 200){
				
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Service has been entered successfully, Version 2!");
				
				returnString = jsonArray.put(jsonObject).toString();
			}
			else{
				return Response.status(500).entity("Error : Unable to enter service!").build();
			}				
			
			System.out.println("returnString  : " + returnString);
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request!").build();
		}
		return Response.ok(returnString).build();
	}

}
