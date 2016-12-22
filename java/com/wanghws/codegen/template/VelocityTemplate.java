package com.wanghws.codegen.template;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import com.wanghws.codegen.db.Table;
import com.wanghws.codegen.utils.Tools;



public class VelocityTemplate {
	
	public static List<Map<String,String>> getTemplates(String type){
		
		Map<String,List<Map<String,String>>> templateList = new HashMap<String,List<Map<String,String>>>();
		
		List<Map<String,String>> wf_templates = new ArrayList<Map<String,String>>();
		Map<String,String> row = new HashMap<String,String>();
		row.put("name", "");
		row.put("vm", "bean.vm");
		row.put("dir", "bean");
		wf_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "Dao");
		row.put("vm", "dao.vm");
		row.put("dir", "dao");
		wf_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "DaoFactory");
		row.put("vm", "daofactory.vm");
		row.put("dir", "dao");
		wf_templates.add(row);
		
		templateList.put("WF", wf_templates);
		
		List<Map<String,String>> dbhelper_templates = new ArrayList<Map<String,String>>();
		row = new HashMap<String,String>();
		row.put("name", "");
		row.put("vm", "bean.vm");
		row.put("dir", "entity");
		dbhelper_templates.add(row);
		
		templateList.put("DBHelper", dbhelper_templates);
		
		List<Map<String,String>> ssh_templates = new ArrayList<Map<String,String>>();
		row = new HashMap<String,String>();
		row.put("name", "Action.java");
		row.put("vm", "action.vm");
		row.put("dir", "action");
		ssh_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "Dao.java");
		row.put("vm", "dao.vm");
		row.put("dir", "dao");
		ssh_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "Service.java");
		row.put("vm", "service.vm");
		row.put("dir", "service");
		ssh_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", ".java");
		row.put("vm", "pojo.vm");
		row.put("dir", "pojo");
		ssh_templates.add(row);
		
		/*row = new HashMap<String,String>();
		row.put("name", ".hbm.xml");
		row.put("vm", "xml_hbm.vm");
		row.put("dir", "pojo");
		ssh_templates.add(row);
		//---------   XML CONFIG FILE ---------------------
		row = new HashMap<String,String>();
		row.put("name", "-action.xml");
		row.put("vm", "xml_action.vm");
		row.put("dir", "config");
		ssh_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "-service.xml");
		row.put("vm", "xml_service.vm");
		row.put("dir", "config");
		ssh_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "-dao.xml");
		row.put("vm", "xml_dao.vm");
		row.put("dir", "config");
		ssh_templates.add(row);
		
		row = new HashMap<String,String>();
		row.put("name", "-struts.xml");
		row.put("vm", "xml_struts.vm");
		row.put("dir", "config");
		ssh_templates.add(row);*/
		
		templateList.put("SSH", ssh_templates);
		
		return templateList.get(type);
	}
	private static VelocityEngine  velocity = new VelocityEngine(); 
	static{
		try{
			Properties properties = new Properties();
			properties.setProperty("resource.loader", "class");
	        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			velocity.init(properties);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	public static void make(String path,String packages,List<Table> tables,String type)throws Exception{
		VelocityContext context = new VelocityContext();
		StringWriter sw = null;
		Template t = null;
		List<Map<String,String>> templates = getTemplates(type);
		String dir = "";
		for(Table table:tables){
			table.setPackages(packages);
			context.put("table", table);
			for (Map<String, String> row : templates) {
				System.out.println(row.get("vm"));
				sw = new StringWriter();
				t = velocity.getTemplate("/templates/"+type+"/"+row.get("vm"));
				t.merge(context, sw);
				dir = path + "/"+table.getLowTableName()+"/"+row.get("dir")+"/";
				Tools.makeDir(dir);
				Tools.saveJavaFile(dir+"/" + table.getObjectName() + row.get("name"),sw.toString());
			}

		}
	}
}
