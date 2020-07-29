package com.houli.system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @Description:    部门管理
 * @author: jxl     
 * @date:   2018年11月9日 下午4:14:32   
 * @version V1.0
 */
public class DeptDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long deptId;
	//上级部门ID，一级部门为0
	private Long parentId;
	//部门名称
	private String name;
	//排序
	private Integer orderNum;
	//是否删除  -1：已删除  0：正常
	private Integer delFlag;
	
	// easyui-treegrid使用属性，用于默认树状态为关闭状态
	private String state = "closed";
	// easyui-treegrid使用属性，用于构建树结构
	private List<DeptDO> children = new ArrayList<DeptDO>();


	/**
	 * 设置：
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取：
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 * 设置：上级部门ID，一级部门为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级部门ID，一级部门为0
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getOrderNum() {
		return orderNum;
	}
	/**
	 * 设置：是否删除  -1：已删除  0：正常
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：是否删除  -1：已删除  0：正常
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	public List<DeptDO> getChildren() {
		return children;
	}
	public void setChildren(List<DeptDO> children) {
		this.children = children;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "DeptDO [deptId=" + deptId + ", parentId=" + parentId + ", name=" + name + ", orderNum=" + orderNum
				+ ", delFlag=" + delFlag + ", state=" + state + ", children=" + children + "]";
	}
}
