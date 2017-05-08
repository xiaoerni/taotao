package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

/**
 * 内容分类管理的controller
 * @author wrx
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCatgoryService contentCatgoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EasyUITreeNode> list = contentCatgoryService.getContentCatList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult createNode(Long parentId,String name){
		TaotaoResult result = contentCatgoryService.insertCatgory(parentId, name);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateCatgory(Long id,String name){
		TaotaoResult result = contentCatgoryService.updateCatgory(id, name);
		return  result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteCatgory( Long id,Long parentId){
		TaotaoResult result = contentCatgoryService.deleteCatgory(id);
		return result;
	}
}
