package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传的controller
 * @author wrx
 *
 */
@Controller
public class PictureController {

	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	
	@Autowired
	private PictureService pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) {
		System.out.println("图片服务器的url" + IMAGE_SERVER_BASE_URL);
		PictureResult result = pictureService.uploadPic(uploadFile);
		//需要把Java对象手工转换成json数据
		String json = JsonUtils.objectToJson(result);
		return json;
	}
}


