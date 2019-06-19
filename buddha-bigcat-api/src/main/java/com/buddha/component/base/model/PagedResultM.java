package com.buddha.component.base.model;

import java.util.List;

public class PagedResultM<T> {
	private static final long serialVersionUID = 1L;
	private int PageSize;
	private int PageIndex;
	private int TotalCount;
	private int PageCount;
	private List<T> Data;
	
	public void setData(List<T> Data)
	{
		this.Data = Data;
	}
	public List<T> getData()
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
