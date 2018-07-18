package io.github.shenbinglife.common.base.domain;

/**
 * 字段排序信息
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public class Sorter {

    public static final String ASC = "asc";

    public static final String DESC = "desc";

    private String field;

    private String orderBy;

    public Sorter() {
    }

    public Sorter(String field, String orderBy) {
        this.field = field;
        this.orderBy = orderBy;
    }

    /**
     * 构建默认升序的排序字段
     */
    public static Sorter asc(String field) {
        return new Sorter(field, ASC);
    }

    /**
     * 构建默认降序的排序字段
     */
    public static Sorter desc(String field) {
        return new Sorter(field, DESC);
    }

    /**
     * 是否为升序排序
     */
    public boolean isAsc() {
        return ASC.equals(this.orderBy);
    }

    /**
     * 是否为降序排序
     */
    public boolean isDesc() {
        return DESC.equals(this.orderBy);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    @Override
    public String toString() {
        return "Sorter{" +
                "field='" + field + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
