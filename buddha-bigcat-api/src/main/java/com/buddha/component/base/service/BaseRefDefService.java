package com.buddha.component.base.service;
import org.springframework.stereotype.Service;

import com.buddha.component.base.ReferenceManage;
import com.buddha.component.base.model.JRSimpleModelWrapper;
import com.buddha.component.base.model.Ss19ReferenceDef;

/**
*参照定义表(BASE_REF_DEF)服务层
*/
@Service
public class BaseRefDefService extends JRBusinessBaseService<Ss19ReferenceDef> {

    @Override
    public String getDefaultSelectSQL() {
        return "SELECT * FROM SS19_REFERENCE_DEF WHERE 1=1";
    }

    @Override
    public void setModelReference(Ss19ReferenceDef Model) {
        if (Model != null){
            Model.writeMapValue("SS19_STATUS", ReferenceManage.GetInstantce().getRefValue(null,"BD09_STD_STATUS_3", Model.getStatusValue()));
        }
    }

    @Override
    public void setModelStatusPrvData(Ss19ReferenceDef Model) {

    }

    @Override
    public void setWrappedMapReference(JRSimpleModelWrapper Model) {

    }

    @Override
    public void setWrappedMapStatusPrvData(JRSimpleModelWrapper Model) {

    }
}
