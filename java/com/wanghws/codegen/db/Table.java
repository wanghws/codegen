package com.wanghws.codegen.db;

import java.util.List;
import java.util.Map;

import com.wanghws.codegen.utils.Tools;

public class Table {
	private String objectName;
	private String tableName;
	private String packages;
	private List<Map<String,String>> methods;
	
	
	
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Map<String, String>> getMethods() {
		return methods;
	}
	public void setMethods(List<Map<String, String>> methods) {
		this.methods = methods;
	}
	public String getVarObjName(){
		return Tools.formatPropertieName(this.getTableName());
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getLowTableName(){
		return Tools.formatTableName(this.tableName);
	}
	
}
