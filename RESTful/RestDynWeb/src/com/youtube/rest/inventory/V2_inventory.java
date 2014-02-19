package com.youtube.rest.inventory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Sql_schema;

@Path("/v2/inventory")
public class V2_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnFineServicesAsResponse(@QueryParam("status") String status) throws Exception{
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			System.out.println(status);
			if(status == null){
				return Response.status(400).entity("Error : Please specify the status for this!").build();
			}
			
			Sql_schema dao = new Sql_schema();
			
			json = dao.returnFineServices(status);
			returnString = json.toString();
							
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request!").build();
		}
		return Response.ok(returnString).build();
	}
	
	/*
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnErrorOnService() throws Exception{
		return Response.status(400).entity("Error : Please specify the status for this!").build();
	}
	*/
	
	@Path("/{status}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnFineServicesUsingPathParam(@PathParam("status") String status) throws Exception{
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			if(status == null){
				return Response.status(400).entity("Error : Please specify the status for this!").build();
			}
			
			Sql_schema dao = new Sql_schema();
			
			json = dao.returnFineServices(status);
			returnString = json.toString();
							
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request!").build();
		}
		return Response.ok(returnString).build();
	}
	
	@Path("/{status}/{rank}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnFineServicesUsingPathParam(@PathParam("status") String status, @PathParam("rank") int rank) throws Exception{
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			if(status == null){
				return Response.status(400).entity("Error : Please specify the status for this!").build();
			}
			
			Sql_schema dao = new Sql_schema();
			
			json = dao.returnFineServicesWithRank(status, rank);
			returnString = json.toString();
							
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request!").build();
		}
		return Response.ok(returnString).build();
	}
		
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response addServices(String incomingData) throws Exception{
		
		String returnString = null;
		Sql_schema dao = new Sql_schema();
		
		try{
			
			System.out.println("Incomng Data : " + incomingData);
			
			ObjectMapper mapper = new ObjectMapper();
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			
			int http_code = dao.insertIntoService(itemEntry.name, itemEntry.owner, itemEntry.status, itemEntry.rank);
			
			if(http_code == 200){
				returnString = "Service inserted.";
			}
			else{
				return Response.status(500).entity("Error : Please specify the status for this!").build();
			}							
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request!").build();
		}
		return Response.ok(returnString).build();
	}

}

class ItemEntry{
	public String name;
	public String owner;
	public String status;
	public int rank;
}
