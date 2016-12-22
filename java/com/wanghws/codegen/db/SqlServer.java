package com.wanghws.codegen.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanghws.codegen.utils.Tools;


public class SqlServer implements DataBase{
	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	Connection conn;
	Statement stmt;
	public SqlServer(String userName,String password)throws Exception{
		System.out.println("connection > > > ");
		String url = "jdbc:sqlserver://192.168.100.251:1433";
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url,userName,password);
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
			ResultSet rs = stmt.executeQuery("select   *   from   sysdatabases");
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
			ResultSet rs = stmt.executeQuery("use "+database+";select name from sysobjects where xtype='u'");
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
			sql = "use "+database+";select top 1 * from "+tableName;
			try{
				rs = stmt.executeQuery(sql);
				/*while(rs.next()){
					row = new HashMap<String,String>();
					row.put("tableName", tableName);
					row.put("propertie", Tools.formatPropertieName(rs.getString(1)));
					row.put("name", Tools.formatMethodName(rs.getString(1)));
					row.put("type", Tools.getType(rs.getString(1)));
					row.put("column", rs.getString(1));
					methods.add(row);
					System.out.println(rs.getString(1));
				}*/
				meta = rs.getMetaData();
				for(int i=1;i<meta.getColumnCount();i++){
					row = new HashMap<String,String>();
					row.put("tableName", tableName);
					row.put("propertie", Tools.formatPropertieName(meta.getColumnName(i)));
					row.put("name", Tools.formatMethodName(meta.getColumnName(i)));
					row.put("type", Tools.getType(meta.getColumnClassName(i)));
					row.put("column", meta.getColumnName(i));
					methods.add(row);
					System.out.println(meta.getColumnName(i));
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
	

}
