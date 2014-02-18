package com.youtube.dao;

import java.sql.*;

import org.codehaus.jettison.json.JSONArray;

import com.youtube.util.ToJSON;

public class Sql_schema {
	
	public int insertIntoService(String name, String owner, String status, int rank) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;

		try{
			conn = Mysql_obj.connectDb();
			String sql = "insert into services(name,owner,status,rank) values (?, ?, ?, ?)";
			query = conn.prepareStatement(sql);
			
			query.setString(1, name);
			query.setString(2, owner);
			query.setString(3, status);
			query.setInt(4, rank);
			
			query.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
			return 500;
		}
		finally{
			if(conn != null) conn.close();
		}		
		return 200;
		
	}
	
	public JSONArray returnFineServices(String status) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;

		JSONArray json = new JSONArray();
		ToJSON convertor = new ToJSON();
		
		try{
			conn = Mysql_obj.connectDb();
			String sql = "select * from services where status = ?";
			query = conn.prepareStatement(sql);
			query.setString(1, status.toUpperCase());
			
			ResultSet rs = query.executeQuery();
			
			json = convertor.toJSONArray(rs);
			query.close();			
		}
		catch(SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e){
			e.printStackTrace();
			return json;
		}
		finally{
			if(conn != null) conn.close();
		}		
		return json;
	}
	
	public JSONArray returnFineServicesWithRank(String status, int rank) throws Exception{
		
		PreparedStatement query = null;
		Connection conn = null;

		JSONArray json = new JSONArray();
		ToJSON convertor = new ToJSON();
		
		try{
			conn = Mysql_obj.connectDb();
			String sql = "select * from services where status = ? and rank = ?";
			query = conn.prepareStatement(sql);
			query.setString(1, status);
			query.setInt(2, rank);
			
			ResultSet rs = query.executeQuery();
			
			json = convertor.toJSONArray(rs);
			query.close();			
		}
		catch(SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e){
			e.printStackTrace();
			return json;
		}
		finally{
			if(conn != null) conn.close();
		}		
		return json;
	}

}
