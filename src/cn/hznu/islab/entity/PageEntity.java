package cn.hznu.islab.entity;

import java.util.List;

/**
 * @ClassName PageEntity
 * @Description
 * @Author GYJ
 * @Date 2018/11/6 11:25
 * @Version 1.0
 **/
public class PageEntity<T> {
    private Integer currentPage;//当前页记录数
    private Integer perPageRows;//每页记录数
    private Integer totalRows;//总记录数
    private Integer totalPages;//总页数
    private List<T> list;//显示数据的集合

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPageRows() {
        return perPageRows;
    }

    public void setPerPageRows(Integer perPageRows) {
        this.perPageRows = perPageRows;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
