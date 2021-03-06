package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {

	//根据itemId查询信息
	TbItem getItemById(Long itemId);
	
	//进行分页查询
	EasyUIDataGridResult getItemList(int page,int rows);
	
	TaotaoResult createItem(TbItem item,String desc,String itemParam);
	
	String getItemParamHtml(Long itemId);
}
