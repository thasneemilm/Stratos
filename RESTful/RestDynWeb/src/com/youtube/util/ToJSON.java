package com.youtube.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.ResultSet;

import org.owasp.esapi.ESAPI;

public class ToJSON {
	
	public JSONArray toJSONArray(ResultSet rs) throws Exception{
		JSONArray json = new JSONArray();
		String temp = null;
		try{
			java.sql.ResultSetMetaData rsmd = rs.getMetaData();
			
			while(rs.next()){
				int numColoumns = rsmd.getColumnCount();
				JSONObject obj = new JSONObject();
				for(int i=1;i<=numColoumns;i++){
					String coloumn_name = rsmd.getColumnName(i);
					
					if(rsmd.getColumnType(i) == java.sql.Types.INTEGER){
						obj.put(coloumn_name, rs.getInt(coloumn_name));
						System.out.println("ToJSON : INTEGER");
					}
					else if(rsmd.getColumnType(i) == java.sql.Types.VARCHAR){
						
						temp = rs.getString(coloumn_name);
						temp = ESAPI.encoder().canonicalize(temp);
						temp = ESAPI.encoder().encodeForHTML(temp);
						obj.put(coloumn_name, temp);
						
						//obj.put(coloumn_name, rs.getString(coloumn_name));
						//System.out.println("ToJSON : VERCHAR");
					}
					else{
						obj.put(coloumn_name, rs.getObject(coloumn_name));
						System.out.println("ToJSON : OBJECT" + coloumn_name);
					}
				}			
				json.put(obj);
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}

}
