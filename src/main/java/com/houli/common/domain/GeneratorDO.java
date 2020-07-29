package com.houli.common.domain;

import java.io.Serializable;

public class GeneratorDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String tableName;
	private String engine;
	private String tableComment;
	private String createTime;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	

}
