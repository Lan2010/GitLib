package com.tianzhixing.common.auth.dao.generic;

import java.io.Serializable;
import java.util.List;

/**
 * DAO 层接口
 *
 * @author jinkai.xie
 * @date 下午12:35:17 @param <T>
 * @date 下午12:35:17 @param <PK>
 */
public abstract interface GenericInfDao<T, PK extends Serializable> {

    /**
     * 查询总条数
     *
     * @return
     */
    abstract long count();

    /**
     * 查询总条数
     *
     * @param sql
     * @return
     */
    abstract long count(String sql);

    /**
     * 根据单条件查询条数
     *
     * @param propertyName
     * @param propertyValue
     * @return
     */
    abstract long count(final String propertyName,
                        final Object propertyValue);

    /**
     * 根据多条件查询条数
     *
     * @param propertyNames
     * @param propertyValues
     * @return
     */
    abstract long count(final String[] propertyNames,
                        final Object[] propertyValues);

    /**
     * 通过id得到实体对象
     *
     * @param id
     * @return
     */
    abstract T get(final PK id);

    /**
     * 根据条件查询实体对象
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    abstract T get(final String[] properties,
                   final Object[] propertyValues);

    /**
     * 根据条件对象获取对象
     *
     * @param property
     * @param obj
     * @return
     */
    abstract T get(final String property, final Object obj);

    /**
     * 根据多条件查询实体列表（排序）
     *
     * @param properties
     * @param propertyValues
     * @param orderBy
     * @param order
     * @return
     */
    abstract List<T> list(final String[] properties,
                          final Object[] propertyValues, final String orderBy,
                          final String order);

    /**
     * 查询实体列表
     *
     * @return
     */
    abstract List<T> list();

    /**
     * 根据多条件查询实体列表（排序）
     *
     * @param from
     * @param size
     * @return
     */
    abstract List<T> list(long from, long size);

    /**
     * 查询实体列表（排序）
     *
     * @param orderBy
     * @param order
     * @return
     */
    abstract List<T> list(String orderBy,
                          String order);

    /**
     * 查询实体列表（排序+分页）
     *
     * @param orderBy
     * @param order
     * @return
     */
    abstract List<T> list(String orderBy,
                          String order, long from, long size);

    /**
     * 根据多条件查询实体列表
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    abstract List<T> list(final String[] properties,
                          final Object[] propertyValues);

    /**
     * 根据多条件查询实体列表（分页）
     *
     * @param properties
     * @param propertyValues
     * @param from
     * @param size
     * @return
     */
    abstract List<T> list(String[] properties,
                          Object[] propertyValues, long from, long size);

    /**
     * 根据多条件查询实体列表（分页）
     *
     * @param properties
     * @param propertyValues
     * @param from
     * @param size
     * @return
     */
    abstract List<T> list(String[] properties,
                          Object[] propertyValues, String orderBy,
                          String order, long from, long size);

    /**
     * 自定义sql查询
     *
     * @param sql
     * @return
     */
    abstract List<T> list(String sql);

    /**
     * 根据多条件获取主键id
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    abstract PK getId(final String[] properties,
                      final Object[] propertyValues);

    /**
     * 根据多条件获取主键列表
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    abstract List<PK> listIds(final String[] properties,
                              final Object[] propertyValues);

    /**
     * 添加对象
     *
     * @param properties
     * @param propertyValues
     * @return
     */
    abstract PK insert(final String[] properties,
                       final Object[] propertyValues);

    /**
     * 添加对象
     *
     * @param obj
     * @return
     */
    abstract PK insert(final Object obj);

    /**
     * 根据主键更新对象部分属性
     *
     * @param id
     * @param properties
     * @param propertyValues
     * @param version
     * @return
     */
    abstract void update(final PK id,
                         final String[] properties, final Object[] propertyValues, int version);

    /**
     * 更新对象
     *
     * @param id
     * @param version
     * @param obj
     */
    abstract void update(final PK id, final int version, final Object obj);

    /**
     * 根据主键删除对象
     *
     * @param id
     * @param version
     * @return int
     * @throws Exception
     */
    @Deprecated
    abstract int deleteById(final PK id, final int version);

    /**
     * 根据主键列表删除对象
     *
     * @param ids
     * @param versions
     * @return int
     * @throws Exception
     */
    @Deprecated
    abstract int deleteByIds(final List<PK> ids, final List<Integer> versions);

    /**
     * 根据条件删除对象
     *
     * @param properties
     * @param propertyValues
     * @return int
     * @throws Exception
     */
    @Deprecated
    abstract int deleteByMap(final String[] properties, final Object[] propertyValues, final List<Integer> versions);


    /**
     * 根据条件对象获取对象
     *
     * @param sql
     * @return
     */
    T get(String sql);

    /**
     * 通过id得到实体某一属性
     *
     * @param property
     * @param id
     * @return
     */
    abstract Object getProperty(final String property, final PK id);

    /**
     * 根据条件查询实体对象某项属性
     *
     * @param property
     * @param properties
     * @param propertyValues
     * @return
     */
    abstract Object getProperty(final String property, final String[] properties, final Object[] propertyValues);

    /**
     * 根据条件对象获取对象某项属性
     *
     * @param property
     * @param propertyName
     * @param obj
     * @return
     */
    abstract Object getProperty(final String property, final String propertyName, final Object obj);

}
