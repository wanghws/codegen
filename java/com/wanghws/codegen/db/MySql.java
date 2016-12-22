package com.wanghws.codegen.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanghws.codegen.utils.Tools;


public class MySql implements DataBase{
	String driver = "com.mysql.jdbc.Driver";
	Connection conn;
	Statement stmt;
	public MySql(String userName,String password)throws Exception{
		System.out.println("connection > > > ");
		String url = "jdbc:mysql://192.168.1.101:3306/?useUnicode=true&characterEncoding=UTF-8&"+
		"user="+userName+"&password="+password;
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url);
		stmt = conn.createStatement();
	}
	public void close(){
		System.out.println("db close > > > ");
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<String> getDBList(){
		List<String> list = new ArrayList<String>();
		try{
			ResultSet rs = stmt.executeQuery("SHOW databases");
			while(rs.next()){
				list.add(rs.getString(1));
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
		System.out.println(list);
		return list;
	}
	public List<String> getTableList(String database){
		List<String> list = new ArrayList<String>();
		try{
			ResultSet rs = stmt.executeQuery("SHOW TABLES FROM "+database);
			while(rs.next()){
			   list.add(rs.getString(1));
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			return list;
		}
		System.out.println(list);
		return list;
	}
	public List<Table> getTable(String database,List<String> tables){
		List<Table> tableList = new ArrayList<Table>();
		Table table = null;
		String sql = "";
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		Map<String,String> row = null;
		List<Map<String,String>> methods = null;
		for(String tableName:tables){
			methods = new ArrayList<Map<String,String>>();
			table = new Table();
			table.setTableName(tableName);
			table.setObjectName(Tools.formatMethodName(tableName));
			sql = "select * from "+database+"."+tableName+" limit 0";
			try{
				rs = stmt.executeQuery(sql);
				meta = rs.getMetaData();
				System.out.println(database+"."+tableName+":"+meta.getColumnCount());
				for(int i=1;i<=meta.getColumnCount();i++){
					System.out.println(meta.getColumnName(i));
					row = new HashMap<String,String>();
					row.put("tableName", tableName);
					row.put("propertie", Tools.formatPropertieName(meta.getColumnName(i)));
					row.put("name", Tools.formatMethodName(meta.getColumnName(i)));
					row.put("type", Tools.getType(meta.getColumnClassName(i)));
					row.put("lowerType", Tools.getType(meta.getColumnClassName(i)).toLowerCase());
					row.put("column", meta.getColumnName(i));
					row.put("size", String.valueOf(meta.getColumnDisplaySize(i)));
					methods.add(row);
					
				}
				rs.close();
			}catch(Exception e){
				System.err.println(sql);
				e.printStackTrace();
				continue;
			}
			table.setMethods(methods);
			tableList.add(table);
		}
		return tableList;
	}
	public static void main(String[] args)throws Exception{
		new MySql("root", "123456");
	}

}
