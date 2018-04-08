package com.yun.security.server.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Permission", discriminator = "权限资源po", subTypes = {Permission.class})
public class Permission extends BaseEntity<String> {

	private static final long serialVersionUID = 6180869216498363919L;

	@ApiModelProperty(value = "父id" )
	private String parentId;
	
	@ApiModelProperty(value = "名称" )
	private String name;
	
	@ApiModelProperty(value = "css样式" )
	private String css;
	
	@ApiModelProperty(value = "菜单url对应的路径" )
	private String href;
	
	@ApiModelProperty(value = "权限类型：1：菜单 2：按钮" )
	private Integer type;
	
	@ApiModelProperty(value = "权限的路径" )
	private String permission;
	
	@ApiModelProperty(value = "排序" )
	private Integer sort;

	private List<Permission> child;


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Permission> getChild() {
		return child;
	}

	public void setChild(List<Permission> child) {
		this.child = child;
	}
}
