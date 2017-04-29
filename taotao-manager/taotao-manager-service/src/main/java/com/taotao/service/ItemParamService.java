package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {

	TaotaoResult getItemParamByCid(Long cid);
	//保存规格参数模板
	TaotaoResult insertItemParam(Long cid,String paramData);
	//删除
	TaotaoResult deleteItemParem(Long cid);
	
}
