package com.wanghws.codegen.main;

import java.util.ArrayList;
import java.util.List;

import com.wanghws.codegen.db.DataBase;
import com.wanghws.codegen.db.MySql;
import com.wanghws.codegen.db.SqlServer;
import com.wanghws.codegen.template.VelocityTemplate;
import com.wanghws.codegen.utils.Tools;

public class CodeGenerate {
	private static DataBase db;
	public static DataBase getDataBase(){
		return db;
	}
	public static DataBase getMySQL(String userName,String password)throws Exception{
		return db = new MySql(userName,password);
	}
	public static DataBase getSqlServer(String userName,String password)throws Exception{
		return db = new SqlServer(userName,password);
	}
	
	
	public static void make(String database,String outPath,String packages,Object[] objs,String type)throws Exception{
		List<String> tables = new ArrayList<String>();
		for(Object o:objs){
			tables.add(String.valueOf(o));
		}
		System.out.println(outPath+" "+packages);
		String filePath = outPath+"\\"+packages.replace(".", "\\");
		
		Tools.makeDir(filePath);
		VelocityTemplate.make(filePath,packages,db.getTable(database,tables),type);
	}
}
