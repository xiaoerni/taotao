package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCatgoryService;


/**
 * 内容分类管理的service
 * @author wrx
 *
 */
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		//根据parentID查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for(TbContentCategory tbContentCategory : list){
			//创建一EasyUITreeNode节点
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到列表
			resultList.add(node);
		}
		return resultList;
	}
	
	/**
	 * 添加节点（内容分类管理，右击添加）
	 */
	@Override
	public TaotaoResult insertCatgory(Long parentId, String name) {
		//创建一个pojo对象
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		//1(正常)  2（删除）
		contentCategory.setStatus(1);
		contentCategory.setIsParent(false);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排序，取值范围：大于零的整数
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入数据
		contentCategoryMapper.insert(contentCategory);
		//取返回的主键
		Long id= contentCategory.getId();
		//判断父节点的isparent属性
		//查询父节点
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()){
			parentNode.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//返回主键
		return TaotaoResult.ok(id);
	}

	/**
	 * 更新节点（重命名）
	 */
	@Override
	public TaotaoResult updateCatgory(Long id, String name) {
		//TbContentCategory contentCategory = new TbContentCategory();
		//根据主键查询节点数据
		TbContentCategory content = contentCategoryMapper.selectByPrimaryKey(id);
		content.setName(name);
		contentCategoryMapper.updateByPrimaryKeySelective(content);
		return TaotaoResult.ok();
	}

	/**
	 * 删除节点
	 */
	@Override
	public TaotaoResult deleteCatgory(Long id) {
		//根据主键查询节点数据
		TbContentCategory contentCategory= contentCategoryMapper.selectByPrimaryKey(id);
		//判断此节点是否为父节点
		if(!contentCategory.getIsParent()){
			//此节点不是父节点
			//查询此节点的父节点，是否存在除此节点以外的其它子节点
			//根据parentid查询子节点列表
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(contentCategory.getParentId());
			//执行查询
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//判断父节点下是否存在其它子节点，即list是否为空
			if(list != null && list.size() > 0){
				//父节点存在其它子节点
				//删除此节点
				contentCategoryMapper.deleteByPrimaryKey(id);
			}else{
				//父节点不存在其它子节点，将父节点的is_parent属性改为0,并删除此节点
				criteria.andIdEqualTo(contentCategory.getParentId());
				TbContentCategory parentCategoryontent = (TbContentCategory) contentCategoryMapper.selectByExample(example);
				parentCategoryontent.setIsParent(false);
				contentCategoryMapper.deleteByPrimaryKey(id);
			}
		}else{
			//此节点是父节点
			//将以此节点为父节点的所有子节点全部删除
			//根据parentid查询子节点列表
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(contentCategory.getId());
			//执行查询
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			//循环删除子节点
			for(int i=0;i<list.size();i++){
				//list.get(i);
				//TODO:
			}
			//判断子节点的父节点是否存在其它子节点
			criteria.andParentIdEqualTo(contentCategory.getParentId());
			//执行查询
			List<TbContentCategory> parentList = contentCategoryMapper.selectByExample(example);
			//判断父节点下是否存在其它子节点，即list是否为空
			if(parentList != null && parentList.size() > 0){
				//父节点存在其它子节点
				//删除此节点
				contentCategoryMapper.deleteByPrimaryKey(id);
			}else{
				//父节点不存在其它子节点，将父节点的is_parent属性改为0,并删除此节点
				criteria.andIdEqualTo(contentCategory.getParentId());
				TbContentCategory parentCategoryontent = (TbContentCategory) contentCategoryMapper.selectByExample(example);
				parentCategoryontent.setIsParent(false);
				contentCategoryMapper.deleteByPrimaryKey(id);
			}
		}
		return TaotaoResult.ok();
	}

}
