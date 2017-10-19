package com.yhw.dao;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.yhw.util.Constants;
import com.yhw.util.ReflectionUtils;
import com.yhw.util.SystemUtil;

/**
 * Dao基类(所有业务Dao必须继承该类)
 * @since	2.0
 * 
 */
public class BaseHibernateDao<T>{

	private Class<T> entityClass;
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	public BaseHibernateDao() {
		entityClass = SystemUtil.getClassGenricType(getClass());
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public Session openSession() {
		session = sessionFactory.openSession();
		return session;
	}
	
	public void closeSession() {
		if (session != null && session.isOpen()) {
			session.close(); //关闭session  
		}
	}

	/**
	 * 取得所有数据
	 * @return
	 */
	public List<T> findAllRecords() {
		return findByCriteria();
	}

	/**
	 * 取得所有没有删除的数据
	 * @return
	 */
	public List<T> findAllUndeletedRecords(){
		return findByCriteria(Restrictions.eq("deleted", Constants.DELETED_NO));
	}
	
	/**
	 * 根据ID得到唯一实体
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		return (T) getSession().get(c, id);
	}
	
	/**
	 * 根据某个属性值取得相应数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return		
	 */
	@SuppressWarnings("unchecked")
	public List<T> findRecordsByProperty(String property, Object value) {
		return createCriteria(Restrictions.eq(property, value)).list();
	}

	/**
	 * 根据某个属性值取得第一条数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findRecordByProperty(String property, Object value) {
		List<T> list = createCriteria(Restrictions.eq(property, value)).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据某个属性值取得相应有效数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return		
	 */
	@SuppressWarnings("unchecked")
	public List<T> findUndeletedRecordsByProperty(String property, Object value) {
		Field field = ReflectionUtils.getDeclaredField(this.entityClass, "deleted");
		if(field != null){
			return createCriteria(Restrictions.eq(property, value),Restrictions.eq("deleted", Constants.NO)).list();
		}else{
			return createCriteria(Restrictions.eq(property, value)).list();
		}
		
	}
	
	/**
	 * 根据某个属性值取得相应已删除数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return		
	 */
	@SuppressWarnings("unchecked")
	public List<T> findDeletedRecordsByProperty(String property, Object value) {
		Field field = ReflectionUtils.getDeclaredField(this.entityClass, "deleted");
		if(field != null){
			return createCriteria(Restrictions.eq(property, value),Restrictions.eq("deleted", Constants.YES)).list();
		}else{
			return createCriteria(Restrictions.eq(property, value)).list();
		}
		
	}

	/**
	 * 根据某个属性值取得第一条有效数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public T findUndeletedRecordByProperty(String property, Object value) {
		List<T> list = findUndeletedRecordsByProperty(property, value);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据hql取得数据
	 * @param hql		hql
	 * @param values	参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findRecordsByHql(String hql, Object... values) {
		return createQuery(hql, values).list();
	}
	
	/**
	 * 根据hql取得Dto数据
	 * @param hql		hql
	 * @param values	参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> findDtoRecordsByHql(Class<E> dtoClass, String hql, Object... values) {
		
		return (List<E>) createQuery(hql, values).setResultTransformer(new AliasToBeanResultTransformer(dtoClass)).list();
	}

	/**
	 *  
	 * 据hql取得数据  (DTO分页显示)
	 * @Title: findDtoRecordsByHql   
	 * @Description:   
	 * @param @param <E>
	 * @param @param dtoClass  class
	 * @param @param hql   hql
	 * @param @param offset 开始取数据的下标
	 * @param @param length 读取数据记录数
	 * @param @param values 参数数组
	 * @param @return      
	 * @return List<E>     
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public <E> List<E> findDtoRecordsByHql(Class<E> dtoClass, String hql,int offset,int length, Object... values) {
			return createQuery(hql, values).setResultTransformer(new AliasToBeanResultTransformer(dtoClass)).setFirstResult(offset).setMaxResults(length).list();
	}
	/**
	 * 根据hql取得数据
	 * @param hql		hql
	 * @param offset	开始取数据的下标
	 * @param length	读取数据记录数
	 * @param values	参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findRecordsByHql(String hql,int offset,int length, Object... values) {
		return createQuery(hql, values).setFirstResult(offset).setMaxResults(length).list();
	}
	/**
	 * 根据hql取得第一条数据
	 * @param hql		hql
	 * @param values	参数数组
	 * @return
	 */
	public T findRecordByHql(String hql,Object...values) {
		List<T> list = findRecordsByHql(hql,values);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据sql取得数据
	 * @param sql		sql
	 * @param values	参数数组
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<T> findRecordsBySql(String sql, Object... values) {
		return createSQLQuery(sql, values).addEntity(entityClass).list();
	}
	
	/**
	 * 根据Sql取得第一条数据
	 * @param Sql		Sql
	 * @param values	参数数组
	 * @return
	 */
	public T findRecordBySql(String hql,Object...values) {
		List<T> list = findRecordsBySql(hql,values);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据sql取得数据 (lik)
	 * @param className
	 * @param sql
	 * @param values
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	public List findRecordsBySql(Class className, String sql, Object... values) {
		return createSQLQuery(sql, values).setResultTransformer(Transformers.aliasToBean(className)).list();
	}
	
	/**
	 * 根据sql取得数据 (lik)
	 * @param sql		sql
	 * @param values	参数数组
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findRecordsBySql(Class<T> className,String sql, int offset,int pageSize,Object...params) {
		return createSQLQuery(sql,params).setResultTransformer(Transformers.aliasToBean(className)).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	/**
	 * 根据某个属性值得到相应实体数量
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public int countByProperty(String property, Object value) {
		return ((Number) (createCriteria(Restrictions.eq(property, value))
				.setProjection(Projections.rowCount()).uniqueResult()))
				.intValue();
	}
	
	/**
	 * 根据某个属性值得到相应未删除的实体数量
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public int countUndeletedByProperty(String property, Object value) {
		Field field = ReflectionUtils.getDeclaredField(this.entityClass, "deleted");
		if(field != null){
			return ((Number) (createCriteria(Restrictions.eq(property, value),Restrictions.eq("deleted", Constants.NO))
					.setProjection(Projections.rowCount()).uniqueResult()))
					.intValue();
		}else{
			return countByProperty(property, value);
		}
	}
	
	public Integer countUndeletedRecords(){
		Field field = ReflectionUtils.getDeclaredField(this.entityClass, "deleted");
		if(field != null){
			return ((Number) (createCriteria(Restrictions.eq("deleted", Constants.NO))
					.setProjection(Projections.rowCount()).uniqueResult()))
					.intValue();
		}
		return null;
	}

	/**
	 * 根据HQL取得实体数量
	 * @param hql		hql
	 * @param values	参数数组
	 * @return
	 */
	public int countByHql(String hql,Object...values) {
		List<?> list = createQuery(hql, values).list();
		if (list != null && list.size() > 0) {
			return ((Integer) list.size());
		} else
			return 0;
	}
	
	
	
	/**
	 * 根据SQL取得实体数量
	 * @param sql		sql
	 * @param values	参数数组
	 * @return
	 */
	public int countBySql(String sql) {
		return (this.getSession().
						createSQLQuery(sql).list().size());
	}
	
	/**
	 * 根据SQL取得实体数量
	 * @param sql		sql
	 * @param values	参数数组
	 * @return
	 */
	public int countBySql(String sql, Object...values) {
		return ((BigInteger) createSQLQuery(sql,values).list().get(0)). intValue();
	}

	/**
	 * 通过动态查询条件进行查询
	 * @param criterion
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Criterion... criterion) {
		return createCriteria(criterion).list();
	}

	/**
	 * 创建Query对象
	 * @param queryString	查询字符串
	 * @param values		参数数组
	 * @return
	 */
	public Query createQuery(String queryString, Object... values) {
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
	
	/**
	 * 创建Query对象
	 * @param hql 查询字符串
	 * @return
	 */
	public Query createQuery(String hql){
		Query query = getSession().createQuery(hql);
		return query;
	}

	/**
	 * 创建SQLQuery对象
	 * @param queryString	查询字符串
	 * @param values		参数数组
	 * @return
	 */
	public SQLQuery createSQLQuery(String queryString, Object... values) {
		SQLQuery queryObject = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		return queryObject;
	}
	
	/**
	 * 创建SQLQuery对象
	 * @param queryString	查询字符串
	 * @return
	 */
	public SQLQuery createSQLQuery(String queryString){
		return getSession().createSQLQuery(queryString);
	}

	/**
	 * 创建检索条件
	 * @return
	 */
	protected Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**	
	   #################################
	   ##########   保存功能   ##########
	   #################################
	*/

	/**
	 * 根据ID加载实体
	 * @param id
	 * @return
	 */
	public T load(Serializable id) {
		return load(id, false);
	}
	
	/**
	 * 根据ID加载实体
	 * @param id	id
	 * @param lock	是否上锁
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public T load(Serializable id, boolean lock) {
		T entity = null;
		if (lock) {
			entity = (T) getSession().load(entityClass, id,LockMode.UPGRADE);
		} else {
			entity = (T) getSession().load(entityClass, id);
		}
		return entity;
	}

	/**
	 * 添加实体
	 * @param entity	实体
	 * @return
	 */
	public T insert(T entity) {
		getSession().persist(entity);
		return entity;
	}

	/**
	 * 保存一个实体(新）
	 */
	public Integer save(T entity){
		return (Integer) getSession().save(entity);
	}
	
	/**
	 * 更新某个实体
	 * @param entity	实体
	 * @return
	 */
	public T update(T entity) {
		getSession().merge(entity);
		return entity;
	}
	
	/**
	 * 更新或插入（多个）
	 * @param list	实体列表
	 */
	public void saveOrUpdateAll(List<T> list) {
		for (T t : list) {
			getSession().saveOrUpdate(t);
		}
	}
	
	public Integer saveAll(List<T> list){
		Integer count = 0;
		for(T t : list) {
			if (getSession().save(t) != null)
				count ++;
		}
		return count;
	}
	
	public void updateAll(List<T> list){
		for(T t :list)
			getSession().update(t);
	}
	
	

	/**
	 * 更新1个实体
	 * @param entity	实体
	 * @return
	 */
	//public T saveOrUpdate(T entity) {
	//	getSession().saveOrUpdate(entity);
	//	return entity;
	//}

	/**	
	   #################################
	   ##########   删除功能   ##########
	   #################################
	*/
	
	/**
	 * 删除某个实体
	 * @param entity	实体
	 * @return
	 */
	public T delete(T entity) {
		getSession().delete(entity);
		return entity;
	}
	
	
	/**
	 * 根据id数组删除数据
	 * @param ids	id数组
	 */
	public void deleteAllByIds(List<Integer> ids) {
		
		Method method = ReflectionUtils.getDeclaredMethod(this.entityClass, "setDeleted");
		try {
			if(method != null){
				for (Integer id : ids) {
					T entity = load(id);
					method.invoke(entity, Constants.DELETED_YES);
					getSession().update(entity);
				}
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据id假删除数据
	 * @param id id主键
	 */
	public void delete(Integer id){
		Method method = ReflectionUtils.getDeclaredMethod(this.entityClass, "setDeleted");
		if(method != null){
			T entity = load(id);
			try {
				method.invoke(entity, Constants.DELETED_YES);
				getSession().update(entity);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
