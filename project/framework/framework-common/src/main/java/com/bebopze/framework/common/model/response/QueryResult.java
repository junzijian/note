package com.bebopze.framework.common.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author bebopze
 * @date 2018/11/13
 */
@Data
public class QueryResult<T> {
    /**
     * 数据列表
     */
    private List<T> list;
    /**
     * 数据总数
     */
    private long total;
}
