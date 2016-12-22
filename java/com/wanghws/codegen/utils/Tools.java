package com.wanghws.codegen.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Tools {
	public static void saveJavaFile(String name,String template){
		try {
            FileOutputStream fos=new FileOutputStream(new File(name));
            OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");
            osw.write(template);
            osw.flush();
            fos.flush();
            osw.close();
            fos.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}
	public static String getType(String type) {
		if (type.equals("java.lang.Double") || type.equals("java.lang.Float")) {
			type = "Double";
		} else if (type.equals("java.lang.Long")) {
			type = "Long";
		} else if (type.equals("java.lang.Integer")) {
			type = "Integer";
		} else if (type.equals("java.lang.String")) {
			type = "String";
		} else if (type.equals("java.lang.Boolean")) {
			type = "Boolean";
		} else if (type.equals("java.sql.Timestamp")) {
			type = "Date";
		} else if(type.equals("java.math.BigDecimal")){
			type = "Number";
		}
		return type;
	}
	public static String formatPropertieName(String columnName){
		String[] names = columnName.split("_");
		if(names.length<=1)return columnName;
		String allName = names[0];
		for(int i=1;i<names.length;i++){
			allName+= names[i].replaceFirst(names[i].substring(0, 1),names[i].substring(0, 1).toUpperCase());
		}
		return allName;
	}
	public static String formatTableName(String tableName){
		String[] names = tableName.split("_");
		if(names.length<=1)return tableName.toLowerCase();
		String allName = "";
		for(int i=0;i<names.length;i++){
			allName+= names[i].toLowerCase();
		}
		return allName;
	}
	public static String formatMethodName(String columnName){
		String[] names = columnName.split("_");
		String allName = "";
		for(int i=0;i<names.length;i++){
			allName+= names[i].replaceFirst(names[i].substring(0, 1),names[i].substring(0, 1).toUpperCase());
		}
		return allName;
	}
	public static String toFirstUpper(String columnName){
		return columnName.replaceFirst(columnName.substring(0, 1),columnName.substring(0, 1).toUpperCase());
    }
	public static void makeDir(String dir)throws Exception{
		File file = new File(dir);
		file.mkdirs();
	}
}
