package com.entity;

public class Page {
    private int pageIndex;
    private int countRows;
    private int startRows;
    private int totalPages;
    private int pageSize;

    public Page(int pageIndex) {
        this(pageIndex,8);
    }
    public Page(int pageIndex,int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.startRows = (pageIndex-1)*pageSize;
    }

    public void setCountRows(int countRows) {
        this.countRows = countRows;
        this.totalPages = countRows % pageSize == 0?countRows/pageSize:(countRows/pageSize) +1;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getCountRows() {
        return countRows;
    }

    public int getStartRows() {
        return startRows;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }
}
