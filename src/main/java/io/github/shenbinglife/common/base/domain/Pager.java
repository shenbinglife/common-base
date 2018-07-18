package io.github.shenbinglife.common.base.domain;

import java.util.List;

/**
 * 分页对象
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public class Pager<T> {

    public static final int DEFAULT_PAGE = 1;

    public static final int DEFAULT_SIZE = 10;

    public static final int MAX_SIZE = 1000;

    private int page = DEFAULT_PAGE;

    private int size = DEFAULT_SIZE;

    private int totalCount;

    private List<T> records;

    private int totalPage;

    private Sorter sorter;

    public Pager() {

    }

    public Pager(int page, int size) {
        setPage(page);
        setSize(size);
    }

    public Pager(int page, int size, Sorter sorter) {
        this(page, size);
        this.sorter = sorter;
    }

    /**
     * 根据pageSize和totalCount计算总页数
     * @return totalPage
     */
    public Pager<T> calcTotalPage() {
        this.totalPage = getSize() == 0 ? 1 : (int) Math.ceil((double) this.totalCount / (double) getSize());
        return this;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page < DEFAULT_PAGE) {
            this.page = DEFAULT_PAGE;
        } else {
            this.page = page;
        }
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if(size > MAX_SIZE) {
            this.size = MAX_SIZE;
        } else {
            this.size = size;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "page=" + page +
                ", size=" + size +
                ", totalCount=" + totalCount +
                ", records=" + records +
                ", totalPage=" + totalPage +
                ", sorter=" + sorter +
                '}';
    }
}
