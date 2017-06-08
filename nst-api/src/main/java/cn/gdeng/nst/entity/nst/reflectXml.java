package cn.gdeng.nst.entity.nst;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class reflectXml {

	private List<String> methodNames = new ArrayList<String>();

	private String tableName;
	
	private String idName;
	
	public Class clazz =SourceShipperEntity.class ;

	public static void main(String[] args) throws Exception {

		reflectXml r = new reflectXml();
//		r.clazz = ProOperateEntity.class;
		r.getTableName();
		r.getClassId();
		r.getClassProperties();
		r.createXML();
	}

	public void getTableName() throws Exception {
		Annotation a = this.clazz.getAnnotation(Entity.class);
		Object c = a.annotationType().getMethod("name", null).invoke(a, null);
		tableName = c.toString();
	}
	
	public void getClassId() {
		java.lang.reflect.Method[] a = this.clazz.getMethods();

		for (int i = 0; i < a.length; i++) {
			if (a[i].getAnnotation(Id.class) != null) {
				String methodName = a[i].getName();
				methodName = methodName.substring(3, 4).toLowerCase()
						+ methodName.substring(4);
				//System.out.println(methodName);
				idName = methodName;

			}
		}

	}

	public void getClassProperties() {
		java.lang.reflect.Method[] a = this.clazz.getMethods();

		for (int i = 0; i < a.length; i++) {
			if (a[i].getAnnotation(Column.class) != null) {
				String methodName = a[i].getName();
				methodName = methodName.substring(3, 4).toLowerCase()
						+ methodName.substring(4);
				//System.out.println(methodName);
				methodNames.add(methodName);

			}
		}

	}

	public void createXML() {
		System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		System.out.println("<sqlMap namespace=\""+clazz.getSimpleName().substring(0,clazz.getSimpleName().length()-6)+"\">");
		
		this.createInsert();
		System.out.println();
		
		if(this.idName!=null) {
			this.createUpdate();
		}
		createQuery();
		createQueryPage();
		if(this.idName!=null) {
			this.createDelete();
		}
		
		System.out.println("</sqlMap>");
		
	}
	
	public void createInsert() {
		System.out.println("<sql id=\"insert\">");
		System.out.println("<![CDATA[");
		System.out.println("	INSERT INTO");
		
		System.out.println(tableName + "(");
		
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println(methodName);
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.print(methodName+",");
		}
		
		System.out.println(") VALUES (");
		
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println(":"+methodName);
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.print(":"+methodName+",");
		}
		
		System.out.println(")");
		System.out.println("]]>");
		System.out.println("</sql>");
	}
	
	public void createUpdate() {
		System.out.println("<sql id=\"update\">");
		System.out.println("<![CDATA[");
		System.out.println("	UPDATE " +tableName);
		
		System.out.println(" SET ");
		
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
				System.out.println(methodName + "=:"+methodName);
				System.out.println("</#if>");
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
			System.out.println(methodName + "=:"+methodName+",");
			System.out.println("</#if>");
			
		}
		
		System.out.println(" WHERE " + idName + "=:"+idName);
		
		/*for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
				System.out.println("AND " + methodName + "=:"+methodName);
				System.out.println("</#if>");
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
			System.out.println("AND " + methodName + "=:"+methodName);
			System.out.println("</#if>");
		}*/
		
		System.out.println(")");
		System.out.println("]]>");
		System.out.println("</sql>");
	}
	
	public void createQuery() {
		System.out.println("<sql id=\"queryByCondition\">");
		System.out.println("<![CDATA[");
		System.out.println("	SELECT ");
		
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println(methodName);
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.print(methodName+",");
		}
		
		System.out.println("	FROM ");
		
		System.out.println(tableName + " WHERE 1=1");
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
				System.out.println("AND " + methodName + "=:"+methodName);
				System.out.println("</#if>");
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
			System.out.println("AND " + methodName + "=:"+methodName);
			System.out.println("</#if>");
		}
		
		System.out.println("]]>");
		System.out.println("</sql>");
	}
	
	public void createQueryPage() {
		System.out.println("<sql id=\"queryByConditionPage\">");
		System.out.println("<![CDATA[");
		System.out.println("	SELECT ");
		
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println(methodName);
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.print(methodName+",");
		}
		
		System.out.println("	FROM ");
		
		System.out.println(tableName + " WHERE 1=1");
		for (int i = 0; i < methodNames.size(); i++) {
			String methodName = methodNames.get(i);
			if(i == methodNames.size() -1) {
				System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
				System.out.println("AND " + methodName + "=:"+methodName);
				System.out.println("</#if>");
				break;
			}
			if(i%8==0) {
				System.out.println();
			}
			System.out.println("<#if "+methodName+"?exists && "+methodName+"!=\"\" >");
			System.out.println("AND " + methodName + "=:"+methodName);
			System.out.println("</#if>");
		}
		System.out.println("LIMIT :startRow,:endRow");
		System.out.println("]]>");
		System.out.println("</sql>");
	}
	
	public void createDelete() {
		System.out.println("<sql id=\"deleteById\">");
		System.out.println("<![CDATA[");
		System.out.println("	DELETE " +tableName);
		System.out.println(" WHERE " + idName + "=:"+idName);
		System.out.println("]]>");
		System.out.println("</sql>");
	}
	
}
