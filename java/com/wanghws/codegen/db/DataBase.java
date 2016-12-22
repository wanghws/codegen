package com.wanghws.codegen.db;

import java.util.List;


public interface DataBase {
	public void close();
	public List<Table> getTable(String database,List<String> tables);
	public List<String> getDBList();
	public List<String> getTableList(String database);
}
