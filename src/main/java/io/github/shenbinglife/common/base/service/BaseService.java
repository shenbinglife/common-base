package io.github.shenbinglife.common.base.service;

import java.io.Serializable;
import java.util.List;

/**
 * BaseService
 *
 * @author shenbing
 * @version 2018/1/9
 * @since since
 */
public interface BaseService<T> {

    List<T> getAll();

    T getById(Serializable id);

    void save(T entity);

    void modify(T entity);

    void remove(Serializable id);
}
