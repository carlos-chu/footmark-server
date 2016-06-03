package com.footmark.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.footmark.common.entity.IDEntity;

/**
 * Common mybatis dao base class, applies add, delete, update, query operations.
 *
cwq
 * @version $ v1.0 Jun 24, 2015 $
 */
@SuppressWarnings("rawtypes")
public interface GenericDao<E extends IDEntity> {

    /**
     * Save the entity.
     * 
     * @param entity physical entity
     * @return effected rows
     */
    int add(E entity);

    /**
     * Update the whole entity.
     * 
     * @param entity physical entity
     * @return effected rows
     */
    int update(E entity);

    /**
     * Remove entity according to the id.
     * 
     * @param id identity
     * @return effected rows
     */
    int delete(Serializable id);

    /**
     * Remove entity which is determined by the parameterized sql.
     * 
     * @param id identity
     * @return effected rows
     */
    int delete(String ql, Object... args);

    /**
     * Remove the entity.
     * 
     * @param entity physical entity
     * @return effected rows
     */
    int delete(E entity);

    /**
     * Get all records.
     * 
     * @return may be a large number of records
     */
    List<E> getAll();

    /**
     * Get the specified entity by id.
     * 
     * @param id identity
     * @return the entity
     */
    E get(Serializable id);

    /**
     * Get the result set by parameterized sql.
     * 
     * @param sql statement
     * @param arg arguments
     * @return {@link List} of <i>Object</i>
     */
    List query(String ql, Object... arg);

    /**
     * Query the single record by parameterized sql.
     *
     * @param sql statements
     * @param arg arguments
     * @return <i>Object</i>
     */
    Object queryOne(String ql, Object... arg);

    /**
     * Execute update sql.
     * 
     * @param sql statement
     * @param arg arguments
     */
    int update(String sql, Object... arg);

    /**
     * Query key:val result set by parameterized sql.
     * 
     * @param sql statement
     * @param arg arguments
     * @return <b>Name:Value</b> results which are wrapped into a map
     */
    Map getMap(String sql, Object... arg);
}
