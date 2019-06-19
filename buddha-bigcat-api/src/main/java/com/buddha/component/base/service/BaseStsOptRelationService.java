package com.buddha.component.base.service;
import org.springframework.stereotype.Service;

import com.buddha.component.base.model.JRSimpleModelWrapper;
import com.buddha.component.base.model.Ss29StatusOptRelation;
/**
*状态操作关系设置表(BASE_STS_OPT_RELATION)服务层
*/
@Service
public class BaseStsOptRelationService extends JRBusinessBaseService<Ss29StatusOptRelation> {


    @Override
    public String getDefaultSelectSQL() {
        return "SELECT * FROM SS29_STATUS_OPT_RELATION WHERE 1=1";
    }

    @Override
    public void setModelReference(Ss29StatusOptRelation Model) {
        if (Model != null){
                   }
    }
    @Override
    public void setModelStatusPrvData(Ss29StatusOptRelation Model) {
        if (Model != null){
        }
    }

    @Override
    public void setWrappedMapReference(JRSimpleModelWrapper Model) {

    }

    @Override
    public void setWrappedMapStatusPrvData(JRSimpleModelWrapper Model) {

    }
}
