package com.xy.persitent.repository.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.xy.internal.model.BaseModel;
import com.xy.internal.query.BaseQuery;
import com.xy.internal.result.PageResult;
import com.xy.persitent.adapter.EnvironmentAdapter;
import com.xy.persitent.converter.PersistentConverter;
import com.xy.persitent.dataobject.BaseDO;
import com.xy.persitent.extension.mapper.CommonMapper;
import com.xy.respository.BaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-23 22:10
 */
@Slf4j
@RequiredArgsConstructor
public abstract class BaseRepositoryImpl<D extends BaseDO, T extends BaseModel,
        Q extends BaseQuery, C extends PersistentConverter<T, D>, M extends CommonMapper<D>> implements BaseRepository<T, Q> {

    private static final int BATCH_INSERT_SIZE = 100;

    protected final M mapper;

    protected final C converter;

    protected final EnvironmentAdapter env;

    /**
     * 构造查询方法
     *
     * @param q
     * @return
     */
    protected abstract Wrapper<D> buildQueryWrapper(Q q);

    @Override
    public T insert(T t) {
        D d = converter.modelToDo(t);
        if (StringUtils.isEmpty(d.getEnv())) {
            d.setEnv(env.currentEnvironment());
        }
        mapper.insert(d);
        return converter.doToModel(d);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<T> insertList(List<T> list) {
        List<D> doList = converter.modelListToDoList(list);
        int i = 0;
        String sqlStatement = sqlStatement();
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (D d : doList) {
                if (StringUtils.isEmpty(d.getEnv())) {
                    d.setEnv(env.currentEnvironment());
                }
                batchSqlSession.insert(sqlStatement, d);
                if (i >= 1 && i % BATCH_INSERT_SIZE == 0) {
                    batchSqlSession.flushStatements();
                }
                i++;
            }
            batchSqlSession.flushStatements();
        }
        return converter.doListToModelList(doList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertIgnoreList(List<T> list) {
        List<D> doList = converter.modelListToDoList(list);
        for (D d : doList) {
            if (StringUtils.isEmpty(d.getEnv())) {
                d.setEnv(env.currentEnvironment());
                d.setGmtModified(new Date());
                d.setGmtCreate(new Date());
            }
        }
        if (doList.size() <= BATCH_INSERT_SIZE) {
            return mapper.insertIgnoreBatchAllColumn(doList);
        }
        int rows = 0;
        for (int fromIdx = 0, endIdx = BATCH_INSERT_SIZE; ; fromIdx += BATCH_INSERT_SIZE, endIdx += BATCH_INSERT_SIZE) {
            if (endIdx > doList.size()) {
                endIdx = doList.size();
            }
            rows += mapper.insertIgnoreBatchAllColumn(doList.subList(fromIdx, endIdx));
            if (endIdx == list.size()) {
                return rows;
            }
        }
    }

    @Override
    public int update(T t, Q q) {
        D d = converter.modelToDo(t);
        d.setGmtModified(new Date());
        return mapper.update(d, buildQueryWrapper(q));
    }

    @Override
    public T selectOne(Q q) {
        D d = mapper.selectOne(buildQueryWrapper(q));
        return converter.doToModel(d);
    }

    @Override
    public List<T> select(Q q) {
        List<D> list = mapper.selectList(buildQueryWrapper(q));
        return converter.doListToModelList(list);
    }

    @Override
    public PageResult<T> selectPage(Q q) {
        Page<D> page = mapper.selectPage(
                new Page<>(q.getPageIndex(), q.getPageSize()),
                buildQueryWrapper(q)
        );
        return PageResult.of(
                converter.doListToModelList(page.getRecords()),
                page.getTotal(),
                page.getSize(),
                page.getCurrent()
        );
    }

    /**
     * 获取SqlStatement
     *
     * @return
     */
    protected String sqlStatement() {
        return SqlHelper.table(currentModelClass()).getSqlStatement(SqlMethod.INSERT_ONE.getMethod());
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    /**
     * 批量操作 SqlSession
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }
}
