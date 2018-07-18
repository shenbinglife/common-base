package io.github.shenbinglife.common.base.dao;

import java.io.Serializable;
import java.util.List;

/**
 * BaseDAO
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public interface BaseDAO<T> {

    List<T> selectAll();

    T selectById(Serializable id);

    void insert(T entity);

    void update(T entity);

    void delete(Serializable id);
}
