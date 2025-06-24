package com.xy.persitent.extension.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.xy.persitent.extension.methods.InsertIgnoreBatchAllColumn;

import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-24 22:26
 */
public class CustomerSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new InsertIgnoreBatchAllColumn());
        return methodList;
    }
}
