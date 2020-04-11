package com.bes.common.vo;


import lombok.Data;

import java.util.List;

/**
 * 返回给页面的分页查询信息
 */
@Data
public class PageResult<T> {
    private Long total;          // 总数据数
    private Integer totalPage;  // 总页数
    private List<T> items;      // 当前页的数据

    public PageResult(){
    }

    public PageResult (Long total, Integer totalPage, List<T> items) {
        this.total =  total;
        this.totalPage = totalPage;
        this.items = items;
    }

    public PageResult (Long total, List<T> items) {
        this.total =total;
        this.items = items;
    }
}
