package com.baolishang.springboot.beans;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by hello on 2016/9/18.
 */
public class Page<T> {
    protected int pageNo = 1; // 当前页, 默认为第1页
    protected int pageSize = 10; // 每页记录数
    protected long totalRecord; // 总记录数, 默认为-1, 表示需要查询
    protected int totalPage; // 总页数, 默认为-1, 表示需要计算

    protected List<T> results; // 当前页记录List形式

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        computeTotalPage();
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        computeTotalPage();
    }

    protected void computeTotalPage() {
        if (getPageSize() > 0 && getTotalRecord() > -1) {
            this.totalPage = (int) (getTotalRecord() % getPageSize() == 0 ? getTotalRecord() / getPageSize() : getTotalRecord() / getPageSize() + 1);
        }
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public static Pageable toPageable(Page page, Sort sort){
        return new PageRequest(page.getPageNo()-1, page.getPageSize(), sort);
    }

    public static Pageable toPageable(Page page){
        return new PageRequest(page.getPageNo()-1, page.getPageSize());
    }

}
