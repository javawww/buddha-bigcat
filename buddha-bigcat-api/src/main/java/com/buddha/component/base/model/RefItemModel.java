package com.buddha.component.base.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.buddha.component.base.pubdef.RouteType;

@Table(name="RefItemModelxxxx")
public class RefItemModel extends EntityBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="KEY")
	private String Key;
	@Column(name="VALUE")
	private String Value;
	@Column(name="ORDNO")
	private Integer SortOrder;
	
	@Column(name="STATUS")
	private String Status;
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Integer getSortOrder() {
		return SortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		SortOrder = sortOrder;
	}
	public void setKey(String Key)
	{
		this.Key = Key;
	}
	public String getKey()
	{
		return this.Key;
	}

	public void setValue(String Value)
	{
		this.Value = Value;
	}
	public String getValue()
	{
		return this.Value;
	}
	public String getLimitFldVal(String LimitFld)
	{
		if(LimitFld.toUpperCase()=="KEY")
		{
			return this.getKey();
		}
		if(LimitFld.toUpperCase()=="VALUE")
		{
			return this.getValue();
		}
		if(LimitFld.toUpperCase()=="STATUS")
		{
			return this.getStatus();
		}
		return this.getOtherValues().get(LimitFld.toUpperCase());
	}
	
	@Override
	public RouteType getModelRouteType() {
		return RouteType.ENT;
	}

	@Override
	public Integer getModelRouteId() {
		return 0;
	}
}
