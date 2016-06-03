package com.footmark.framework.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.footmark.common.entity.EntityVersion;
import com.footmark.common.entity.IDEntity;
import com.footmark.common.util.ClassUtil;

/**
 * Common mybatis dao base class, concrete dao should extend it.
 *
cwq
 * @version $ v1.0 Jun 24, 2015 $
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericDaoDefault<E extends IDEntity> extends SqlSessionDaoSupport implements GenericDao<E> {
    // Constants pool for names of mybatis statements.
    /** save. */
    public static final String INSERT_STATEMENT = "insert";
    /** update */
    public static final String UPDATE_STATEMENT = "update";
    /** remove */
    public static final String DELETE_STATEMENT = "delete";
    /** get single result */
    public static final String GET_STATEMENT = "get";
    /** get all results */
    public static final String GET_ALL_STATEMENT = "getAll";

    /** Generic type of the entity */
    protected Class entityClass;
    private String simpleClassName;

    /**
     * Default constructor.
     */
    public GenericDaoDefault() {
        this.entityClass = ClassUtil.getGenericClass(this.getClass());
        this.simpleClassName = this.entityClass.getSimpleName();
    }

    @Autowired(required = false)
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public int delete(Serializable id) {
        return super.getSqlSession().delete(getStatementId(DELETE_STATEMENT), id);
    }

    public int delete(String ql, Object... args) {
        int rows = 0;
        if (args == null || args.length == 0) {
            rows = super.getSqlSession().delete(getStatementId(ql));
        } else if (args.length == 1) {
            rows = super.getSqlSession().delete(getStatementId(ql), args[0]);
        } else {
            Map map = new HashMap();
            for (int i = 0; i < args.length; i++) {
                map.put(i + "", args[i]);
            }
            rows = super.getSqlSession().delete(getStatementId(ql), map);
        }
        return rows;
    }

    public int delete(E entity) {
        return delete(entity.getId());
    }

    public E get(Serializable id) {
        return (E) super.getSqlSession().selectOne(getStatementId(GET_STATEMENT), id);
    }

    public List<E> getAll() {
        return super.getSqlSession().selectList(getStatementId(GET_ALL_STATEMENT));
    }

    public List query(String ql, Object... arg) {
        List result = null;
        if (arg == null || arg.length == 0) {
            result = super.getSqlSession().selectList(getStatementId(ql));
        } else if (arg.length == 1) {
            result = super.getSqlSession().selectList(getStatementId(ql), arg[0]);
        } else {
            Map map = new HashMap();
            for (int i = 0; i < arg.length; i++) {
                map.put(i + "", arg[i]);
            }
            result = super.getSqlSession().selectList(getStatementId(ql), map);
        }
        return result != null ? result : new ArrayList();
    }

    public Object queryOne(String ql, Object... arg) {
        Object ob = null;
        if (arg == null || arg.length == 0) {
            ob = super.getSqlSession().selectOne(getStatementId(ql));
        } else if (arg.length == 1) {
            ob = super.getSqlSession().selectOne(getStatementId(ql), arg[0]);
        } else {
            Map map = new HashMap();
            for (int i = 0; i < arg.length; i++) {
                map.put(i + "", arg[i]);
            }
            ob = super.getSqlSession().selectOne(getStatementId(ql), map);
        }
        return ob;
    }

    public int update(E entity) {
        int rows = super.getSqlSession().update(getStatementId(UPDATE_STATEMENT), entity);
        if (entity instanceof EntityVersion) {
            if (rows == 0) {
                throw new RuntimeException("Optimistic lock err, zero row is affected.");
            }
        }
        return rows;
    }

    public int add(E entity) {
        return super.getSqlSession().insert(getStatementId(INSERT_STATEMENT), entity);
    }

    public int update(String ql, Object... arg) {
        int rows = 0;
        if (arg == null || arg.length == 0) {
            rows = super.getSqlSession().update(getStatementId(ql));
        } else if (arg.length == 1) {
            rows = super.getSqlSession().update(getStatementId(ql), arg[0]);
        } else {
            Map map = new HashMap();
            for (int i = 0; i < arg.length; i++) {
                map.put(i + "", arg[i]);
            }
            rows = super.getSqlSession().update(getStatementId(ql), map);
        }
        return rows;
    }

    public Map getMap(String ql, Object... arg) {
        Map result = null;
        if (arg == null || arg.length == 0) {
            result = (Map) super.getSqlSession().selectOne(getStatementId(ql));
        } else if (arg.length == 1) {
            result = (Map) super.getSqlSession().selectOne(getStatementId(ql), arg[0]);
        } else {
            Map map = new HashMap();
            for (int i = 0; i < arg.length; i++) {
                map.put(i + "", arg[i]);
            }
            result = (Map) super.getSqlSession().selectOne(getStatementId(ql), map);
        }
        return result;
    }

    protected String getStatementId(String postfix) {
        return this.simpleClassName + "." + postfix;
    }
}
