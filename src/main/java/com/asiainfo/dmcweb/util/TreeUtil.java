package com.asiainfo.dmcweb.util;

import java.util.ArrayList;
import java.util.List;

import com.asiainfo.dmcweb.entity.SysMenu;
import com.asiainfo.dmcweb.entity.TreeNode;


public class TreeUtil {
	
	private final static String MENU_ID = "menu_";
	
	List<SysMenu> rootMenus;
	List<SysMenu> childMenus;
	
	public TreeUtil(List<SysMenu> rootMenus,List<SysMenu> childMenus){
		this.rootMenus = rootMenus;
		this.childMenus = childMenus;
	}  
	
	public List<TreeNode> getTreeNode(){
		return getRootNodes();
	}
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private TreeNode MenuToNode(SysMenu menu){
		if(menu == null){
			return null;
		}
		TreeNode node = new TreeNode();
		node.setId(MENU_ID+menu.getId());
		node.setDataId(menu.getId());
		node.setText(menu.getName());
		node.setUrl(menu.getUrl());
		node.setParentId(menu.getParentId());
		node.getAttributes().put("type", "0");
		node.getAttributes().put("id", menu.getId());
		return node;
	}
	
	

	private List<TreeNode> getRootNodes(){
		List<TreeNode> rootNodes = new ArrayList<TreeNode>();
		if(rootMenus==null)return rootNodes;
		for(SysMenu menu : rootMenus){
			TreeNode node = MenuToNode(menu);
			if(node != null){
				addChlidNodes(node);
				rootNodes.add(node);
			}
		}
		return rootNodes;
	}
	
	/**
	 * 
	 * @param menu
	 * @return
	 */
	private void addChlidNodes(TreeNode rootNode){
		List<TreeNode> childNodes = new ArrayList<TreeNode>();  
		System.out.println(childMenus==null);
		if(childMenus==null)return;
		for(SysMenu menu : childMenus){
			System.out.println("#########"+rootNode.getDataId()+" "+menu.getParentId());
			if(rootNode.getDataId().equals(menu.getParentId())){
				TreeNode node = MenuToNode(menu);
				childNodes.add(node);
			}
		}
		rootNode.setChildren(childNodes);
	}
	
	
}
