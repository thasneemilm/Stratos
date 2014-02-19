package com.youtube.dao;
import java.sql.Connection;
import java.sql.DriverManager;




public class Mysql_obj {
	private static Connection conn = null;
	
	public static Connection connectDb() throws Exception{
		
		try{			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/restful","root","");
			//return conn;
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

}
