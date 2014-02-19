package com.youtube.rest.inventory;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.dao.Mysql_obj;
import com.youtube.util.ToJSON;

@Path("/v1/inventory")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllServices() throws Exception{
		PreparedStatement query = null;
		String returnString = null;
		Connection conn = null;
		
		try{
			conn = Mysql_obj.connectDb();
			String sql = "select * from services";
			query = conn.prepareStatement(sql);
			ResultSet rs = query.executeQuery();
			
			ToJSON convertor = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = convertor.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn != null) conn.close();
		}
		
		return returnString;
	}
	
	@Path("/response")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllServicesAsResponse() throws Exception{
		PreparedStatement query = null;
		String returnString = null;
		Connection conn = null;
		Response rb = null;
		
		try{
			conn = Mysql_obj.connectDb();
			String sql = "select * from services";
			query = conn.prepareStatement(sql);
			ResultSet rs = query.executeQuery();
			
			ToJSON convertor = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = convertor.toJSONArray(rs);
			query.close();
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn != null) conn.close();
		}		
		return rb;
	}

}
