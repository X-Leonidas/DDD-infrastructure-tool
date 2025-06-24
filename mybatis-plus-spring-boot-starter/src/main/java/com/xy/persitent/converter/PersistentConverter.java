package com.xy.persitent.converter;

import com.xy.internal.model.BaseModel;
import com.xy.persitent.dataobject.BaseDO;

import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-23 22:18
 */
public interface PersistentConverter<T extends BaseModel, D extends BaseDO> {
    /**
     * DO 转 model
     *
     * @param d DO
     * @return Model
     */
    T doToModel(D d);

    /**
     * model 转 DO
     *
     * @param t Model
     * @return DO
     */
    D modelToDo(T t);

    /**
     * DO list 转 model list
     *
     * @param list do list
     * @return model list
     */
    List<T> doListToModelList(List<D> list);

    /**
     * model list 转 DO list
     *
     * @param list list
     * @return do list
     */
    List<D> modelListToDoList(List<T> list);
}
