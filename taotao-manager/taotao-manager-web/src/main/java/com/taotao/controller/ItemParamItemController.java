package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.service.ItemParamItemService;

/**
 * 查询商品参数的controller
 * @author wrx
 *
 */
@Controller
public class ItemParamItemController {

	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamItemList(Integer page, Integer rows){
		EasyUIDataGridResult result = itemParamItemService.getItemParamItemList(page, rows);
		return result;
	}
}
