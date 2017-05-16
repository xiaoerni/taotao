package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;


public class FreeMarkerTest {
	public class student{
		private int id;
		private String name;
		private String address;
		
		public student(int id,String name,String address){
			this.id = id;
			this.name = name;
			this.address = address;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		
	}

	@Test
	public void testFreeMarker() throws Exception{
		//第一步：把freemarker的jar包添加到工程中
		//第二步：freemarker的运行不依赖web容器，可以在java工程中运行。创建一个测试方法进行测试。
		//第三步：创建一个Configration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//第四步：告诉config对象模板文件存放的路径。
		configuration.setDirectoryForTemplateLoading(new File("D:\\share\\JavaEE18\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl")); 
		//第五步：设置config的默认字符集。一般是utf-8
		configuration.setDefaultEncoding("utf-8");
		//第六步：从config对象中获得模板对象。需要制定一个模板文件的名字。
		Template template = configuration.getTemplate("second.ftl");
		//第七步：创建模板需要的数据集。可以是一个map对象也可以是一个pojo，把模板需要的数据都放入数据集。
		Map root = new HashMap<>();
		root.put("hello", "first  hello");
		//root.put("title", "hello freemarker");
		root.put("student", new student(1, "sherry", "beijing"));
		List<student> stuList = new ArrayList<>();
		stuList.add(new student(1, "sherry", "beijing"));
		stuList.add(new student(2, "sherry2", "beijing2"));
		stuList.add(new student(3, "sherry3", "beijing3"));
		stuList.add(new student(4, "sherry4", "beijing4"));
		stuList.add(new student(5, "sherry5", "beijing5"));
		root.put("students", stuList);
		root.put("curdate", new Date());
		//第八步：创建一个Writer对象，指定生成的文件保存的路径及文件名。
		Writer out = new FileWriter(new File("G:\\temp\\html\\second.html"));
		//第九步：调用模板对象的process方法生成静态文件。需要两个参数数据集和writer对象。
		template.process(root, out);
		//第十步：关闭writer对象
		out.flush();
		out.close();

	}
}
