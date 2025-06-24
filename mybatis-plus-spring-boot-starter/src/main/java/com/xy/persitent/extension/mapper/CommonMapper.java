package com.xy.persitent.extension.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xiangyu
 * @date 2025-06-23 22:23
 */
public interface CommonMapper<D> extends BaseMapper<D> {
    /**
     * 全量插入,等价于insert，忽略唯一索引冲突的行
     *
     * @param dataList list
     * @return rows
     */
    int insertIgnoreBatchAllColumn(List<D> dataList);
}
