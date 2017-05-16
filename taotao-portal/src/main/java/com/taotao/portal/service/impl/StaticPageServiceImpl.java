package com.taotao.portal.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.io.ObjectOutputStream.PutField;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.portal.service.ItemService;
import com.taotao.portal.service.StaticPageService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 生成商品页面的静态网页
 * @author wrx
 *
 */
@Service
public class StaticPageServiceImpl implements StaticPageService {

	@Autowired
	private ItemService itemService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Value("${STATIC_PAGE_PATH}")
	private String STATIC_PAGE_PATH;
	
	@Override
	public TaotaoResult genItemHtml(Long itemId) throws Exception {
		//商品基本信息
		TbItem tbItem = itemService.getItemById(itemId);
		//商品描述
		String itemDesc = itemService.getItemDescById(itemId);
		//规格参数
		String itemParam = itemService.getItemParamById(itemId);
		//生成静态页面
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("item.ftl");
		//创建一个数据集
		Map root = new HashMap<>();
		//向数据集中添加属性
		root.put("item", tbItem);
		root.put("itemDesc", itemDesc);
		root.put("itemParam", itemParam);
		//创建一个Writer对象
		Writer out = new FileWriter(new File(STATIC_PAGE_PATH + itemId + ".html"));
		template.process(root, out);
		out.flush();
		out.close();
		return TaotaoResult.ok();
	}

}
