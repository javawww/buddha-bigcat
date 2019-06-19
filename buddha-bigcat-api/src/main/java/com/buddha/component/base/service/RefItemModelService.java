package com.buddha.component.base.service;
import org.springframework.stereotype.Service;

import com.buddha.component.base.model.JRSimpleModelWrapper;
import com.buddha.component.base.model.RefItemModel;
@Service
public class RefItemModelService extends JRBusinessBaseService<RefItemModel> {
	@Override
	public String getDefaultSelectSQL() {
		return "";
	}

	@Override
	public void setModelReference(RefItemModel Model) {
	}

	@Override
	public void setModelStatusPrvData(RefItemModel Model) {

	}

	@Override
	public void setWrappedMapReference(JRSimpleModelWrapper Model) {

	}

	@Override
	public void setWrappedMapStatusPrvData(JRSimpleModelWrapper Model) {

	}

}
