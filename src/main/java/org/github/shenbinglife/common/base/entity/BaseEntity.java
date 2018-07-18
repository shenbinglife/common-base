package org.github.shenbinglife.common.base.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 类名
 *
 * @author shenbing
 * @version 2018/5/4
 * @since since
 */
public class BaseEntity implements Serializable {

    private String id;

    private Date createTime;

    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "BaseEntity{" + "id='" + id + '\'' + ", createTime=" + createTime + ", updateTime=" + updateTime + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
