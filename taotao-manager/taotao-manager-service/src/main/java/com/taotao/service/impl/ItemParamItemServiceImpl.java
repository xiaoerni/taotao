package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.service.ItemParamItemService;

/**
 * 查询商品参数的serviceimpl
 * @author wrx
 *
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public EasyUIDataGridResult getItemParamItemList(int page, int rows) {

		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemParamItemExample example = new TbItemParamItemExample();
		List<TbItemParamItem> list = itemParamItemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItemParamItem> pageInfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}

}
