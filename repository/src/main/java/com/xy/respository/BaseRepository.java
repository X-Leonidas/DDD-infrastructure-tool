package com.xy.respository;

import com.xy.internal.model.BaseModel;
import com.xy.internal.query.BaseQuery;
import com.xy.internal.result.PageResult;

import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-23 21:56
 */
public interface BaseRepository<T extends BaseModel, Q extends BaseQuery> {
    /**
     * insert row
     *
     * @param t model
     * @return model
     */
    T insert(T t);

    /**
     * batch insert rows
     *
     * @param list models
     * @return models
     */
    List<T> insertList(List<T> list);

    /**
     * batch insert rows
     *
     * @param list models
     * @return models
     */
    int insertIgnoreList(List<T> list);

    /**
     * update row
     *
     * @param t model
     * @param q filter
     * @return affected rows
     */
    int update(T t, Q q);

    /**
     * select one
     *
     * @param q query
     * @return model
     */
    T selectOne(Q q);

    /**
     * select list
     *
     * @param q query
     * @return models
     */
    List<T> select(Q q);

    /**
     * select page
     *
     * @param q query
     * @return page result
     */
    PageResult<T> selectPage(Q q);
}
