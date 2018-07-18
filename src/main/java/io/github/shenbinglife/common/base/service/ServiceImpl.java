package io.github.shenbinglife.common.base.service;

import java.io.Serializable;
import java.util.List;

import io.github.shenbinglife.common.base.dao.BaseDAO;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 类名
 *
 * @author shenbing
 * @version 2018/5/4
 * @since since
 */
public class ServiceImpl<T, M extends BaseDAO<T>> implements BaseService<T> {

    @Autowired
    protected M baseDAO;

    @Override
    public List<T> getAll() {
        return baseDAO.selectAll();
    }

    @Override
    public T getById(Serializable id) {
        return baseDAO.selectById(id);
    }

    @Override
    public void save(T entity) {
        baseDAO.insert(entity);
    }

    @Override
    public void modify(T entity) {
        baseDAO.update(entity);
    }

    @Override
    public void remove(Serializable id) {
        baseDAO.delete(id);
    }

    public M getBaseDAO() {
        return baseDAO;
    }

    public void setBaseDAO(M baseDAO) {
        this.baseDAO = baseDAO;
    }
}
