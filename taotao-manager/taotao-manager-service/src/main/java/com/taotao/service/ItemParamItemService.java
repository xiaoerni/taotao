package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

public interface ItemParamItemService {

	//查询商品参数
	EasyUIDataGridResult getItemParamItemList(int page,int rows);
}
