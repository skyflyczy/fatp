package com.fatp.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.fatp.po.sys.SysMenuPo;

/**
 * 菜单树节点
 * @author zhiya.chai
 * 2015年7月1日 上午10:40:01
 */
public class MenuTreeNode {

	//自身id
	private Integer id;
	//父id
	private Integer pid;
	//显示的名字
	private String name;
	//url
	private String url;
	//子节点
	private List<MenuTreeNode> children = new ArrayList<MenuTreeNode>();
	
	private Integer isButton;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeNode> children) {
		this.children = children;
	}
	
	public static List<MenuTreeNode> convertToNodes(List<SysMenuPo> sysMenus, boolean isLeftTree, boolean hasUrl) {
		if(CollectionUtils.isEmpty(sysMenus))
			return Collections.emptyList();
		
		/**
		 * 转换为TreeNode
		 */
		Map<Integer, List<MenuTreeNode>> pidMap = new HashMap<Integer, List<MenuTreeNode>>();
		Map<Integer, MenuTreeNode> idMap = new HashMap<Integer, MenuTreeNode>();
		for(SysMenuPo sysMenu : sysMenus){
			if(isLeftTree && sysMenu.getIsButton() != null && sysMenu.getIsButton().intValue() == 2) //树不包括按钮
				continue;
			MenuTreeNode node = new MenuTreeNode();
			node.setId(sysMenu.getId());
			node.setName(sysMenu.getMenuName());
			node.setPid(sysMenu.getParentId());
			node.setIsButton(sysMenu.getIsButton());
			if(hasUrl) //需要链接的
				node.setUrl(sysMenu.getMenuUrl());
			
			idMap.put(node.getId(), node);
			List<MenuTreeNode> list = pidMap.get(node.getPid());
			if(list == null) {
				list = new ArrayList<MenuTreeNode>();
				pidMap.put(node.getPid(), list);
			}
			list.add(node);
		}
		
		/**
		 * 处理TreeNode层次结构
		 */
		List<MenuTreeNode> treeNodes = new ArrayList<MenuTreeNode>();
		List<Integer> keyList = new ArrayList<Integer>(pidMap.keySet());
		Collections.sort(keyList, new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		for(Integer key : keyList){
			if(key.intValue() == 0) {
				treeNodes.addAll(pidMap.get(key));
			}else {
				if(idMap.containsKey(key))
					idMap.get(key).getChildren().addAll(pidMap.get(key));
			}
		}
		
		return treeNodes;
	}
	
	public String getChildJson() {
		return this.children.size() >0 ? JSONObject.toJSONString(this.children): null;
	}
	public Integer getIsButton() {
		return isButton;
	}
	public void setIsButton(Integer isButton) {
		this.isButton = isButton;
	}
	
	
}
