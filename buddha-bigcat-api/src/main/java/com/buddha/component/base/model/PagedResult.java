package com.buddha.component.base.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PagedResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int PageSize;
	private int PageIndex;
	private int TotalCount;
	private int PageCount;
	private List<Map<String, Object>> Data;
	
	public void setData(List<Map<String, Object>> Data)
	{
		this.Data = Data;
	}
	public List<Map<String, Object>> getData()
	{
		return this.Data;
	}
	
	public void setTotalCount(int TotalCount)
	{
		this.TotalCount = TotalCount;
	}
	public int getTotalCount()
	{
		return this.TotalCount;
	}
	
	public void setPageIndex(int PageIndex)
	{
		this.PageIndex = PageIndex;
	}
	public int getPageIndex()
	{
		return this.PageIndex;
	}
	
	public void setPageSize(int PageSize)
	{
		this.PageSize = PageSize;
	}
	public int getPageSize()
	{
		return this.PageSize;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}
}
