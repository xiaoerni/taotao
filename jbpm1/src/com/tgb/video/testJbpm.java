package com.tgb.video;

import junit.framework.TestCase;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;

public class testJbpm extends TestCase{

	public void deploy(){
		ProcessEngine processEngine = Configuration.getProcessEngine();
		//专门负责部署流程的服务
		RepositoryService repositoryService = processEngine.getRepositoryService();
		//部署流程
		repositoryService.createDeployment().addResourceFromClasspath("test1.jpdl.xml").deploy();
		
	}
	
	//发起流程
	public void createInstance(){
		ProcessEngine processEngine = Configuration.getProcessEngine();
		//流程服务定义
		ExecutionService executionService = processEngine.getExecutionService();
		ProcessInstance processInstance = executionService.startProcessInstanceByKey("test");
		System.out.println("流程实例ID======" + processInstance.getId());
	}
}
