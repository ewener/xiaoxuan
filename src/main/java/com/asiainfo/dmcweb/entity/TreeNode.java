package com.asiainfo.dmcweb.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeNode {

	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", dataId=" + dataId + ", text=" + text + ", url=" + url + ", state=" + state
				+ ", checked=" + checked + ", parentId=" + parentId + ", attributes=" + attributes + ", children="
				+ children + "]";
	}

	private String id;
	
	private String dataId;
	
	private String text;
	
	private String url;
	
	private String state;//value closed,open
	
	private boolean checked; //
	
	private String parentId;
	
	private Map<String,Object> attributes = new HashMap<String, Object>();
	
	private List<TreeNode>  children;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public  String getParentId() {
		return parentId;
	}

	public void setParentId( String parentId) {
		this.parentId = parentId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	
}
