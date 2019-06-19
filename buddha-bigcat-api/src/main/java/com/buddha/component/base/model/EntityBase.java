package com.buddha.component.base.model;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.buddha.component.base.pubdef.RouteType;

public abstract class EntityBase implements Serializable {
	@Transient
	private String KeyValue;
	@Transient
	private String StatusValue;
	@Transient
	private  EntityState entityState;
	public String getKeyValue()
	{
		return KeyValue;
	}
	public String getStatusValue()
	{
		return StatusValue;
	}
	public void setStatusValue(String Value)
	{
		this.StatusValue=Value;
	}
	public void setKeyValue(String Value)
	{
		this.KeyValue=Value;
	}

	public EntityState getEntityState() {
		return entityState;
	}

	public abstract RouteType getModelRouteType();
	public abstract Integer getModelRouteId();

	public void setEntityState(EntityState entityState) {
		this.entityState = entityState;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	@Transient
	private HashMap<String,String> RefValues;
	
	@Transient
	private HashMap<String,String> OtherValues;

	@Transient
	private JRStatusData statusData;
	
	public Map<String, String> getRefValues() {
		return RefValues;
	}
	public Map<String, String> getOtherValues() {
		return OtherValues;
	}


	
	public EntityBase()
	{
		RefValues = new HashMap<String,String>();
		OtherValues = new HashMap<String,String>();
	}
    public  String writeMapValue(String Field,String Value)
    {
    	return this.RefValues.put(Field.toUpperCase(),Value);
    }
    public String  readMapValue(String Field)
    {
    	return this.RefValues.get(Field.toUpperCase());
    }
    
    public  String writeOtherValue(String Field,String Value)
    {
    	return this.OtherValues.put(Field.toUpperCase(),Value);
    }
    public String  readOtherValue(String Field)
    {
    	return this.OtherValues.get(Field.toUpperCase());
    }

	public JRStatusData getStatusData() {
		return statusData;
	}
	public void setStatusData(JRStatusData statusData) {
		this.statusData = statusData;
	}
}
