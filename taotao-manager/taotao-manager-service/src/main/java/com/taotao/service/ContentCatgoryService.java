package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatgoryService {

	List<EasyUITreeNode> getContentCatList(Long parentId);
	
	//添加节点（内容分类管理，右击添加）
	TaotaoResult insertCatgory(Long parentId,String name);
	
	//更新节点（重命名）
	TaotaoResult updateCatgory(Long id, String name);
	
	//删除节点
	TaotaoResult deleteCatgory(Long id);
}
