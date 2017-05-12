package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.HttpClientUtil;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;


/**
 * 内容管理controller
 * @author wrx
 *
 */
@Controller
@RequestMapping("/content/")
public class ContentController {

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	
	@Value("${REST_CONTENT_STNC_URL}")
	private String REST_CONTENT_STNC_URL;
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		TaotaoResult result = contentService.insertContent(content);
		//调用taotao-rest发布的服务，同步缓存
		HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_STNC_URL + content.getCategoryId());
		return result;
	}
}
