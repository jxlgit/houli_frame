package com.houli.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description:    分页工具类  
 * @author: jxl     
 * @date:   2018年11月12日 上午9:25:19   
 * @version V1.0
 */
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
