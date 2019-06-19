package com.buddha.component.base;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddha.component.base.model.RefItemModel;
import com.buddha.component.base.service.BaseRefDefService;
import com.buddha.component.base.service.RefItemModelService;
import com.buddha.component.common.utils.ReferenceItem;


@Service
public class ReferenceManage {
	@Autowired
	private RefItemModelService _RefItemModelService;

	@Autowired
	private BaseRefDefService _BaseRefDefService;
	private Map<Integer,ReferenceItem> _RefItemList = null;
	ReferenceManage()
	{
		_RefItemList = new HashMap<Integer, ReferenceItem>();
	}
	public void refresh()
	{
		this._RefItemList.clear();
	}
	public void refresh(int routeId)
	{
		this._RefItemList.get(routeId).refresh();
	}
	public void refresh(int routeId, String RefCode)
	{
		this._RefItemList.get(routeId).refresh(RefCode);
	}
	private ReferenceItem getRefItem(Integer routeId)
	{
		ReferenceItem theItem = _RefItemList.get(routeId);
		if(theItem==null)
		{
			theItem = new ReferenceItem(routeId,_RefItemModelService,_BaseRefDefService);
			_RefItemList.put(routeId,theItem);
		}
		return theItem;
	}
	public String getRefValue(int routeId,String RefCode,String Key,String DefaultValue)
	{
		return  getRefItem(routeId).getRefValue(RefCode,Key,DefaultValue);
	}
	public String getRefValue(Integer routeId,String RefCode,String Key)
	{
		return  this.getRefItem(routeId).getRefValue(RefCode, Key,Key);
	}
	public List<RefItemModel> getRefValueList(Integer routeId,String RefCode,String LimitFld,String LimitVal)
	{
		return this.getRefItem(routeId).getRefValueList(RefCode,LimitFld,LimitVal);
	}
	public Map<String,RefItemModel> getRefValueMap(Integer routeId,String RefCode)
	{
		return this.getRefItem(routeId).getRefValueMap(RefCode);
	}
	private static volatile ReferenceManage _Instantce = null;
	public static ReferenceManage GetInstantce()
	{
		if(_Instantce==null)
		{
			synchronized (ReferenceManage.class){
				if(_Instantce == null){
					_Instantce = JRRuntimeHelper.getCurrentAppContext().getBean(ReferenceManage.class);;
				}
			}
		}
		return _Instantce;
	}
}
