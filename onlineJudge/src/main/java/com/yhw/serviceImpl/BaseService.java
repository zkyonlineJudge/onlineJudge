
package com.yhw.serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yhw.dao.BaseHibernateDao;
import com.yhw.service.IBaseService;

/**
 * Service基类(所有业务Service必须继承该类)
 * @since	2.0
 * 
 */
//对异常进行回滚，如果没有这个声明的话抛出异常后事务是不会回滚的
//子类里就不要再重写事务注解了，除非有特殊要求
@Transactional(rollbackFor=Exception.class)
public class BaseService<T> implements IBaseService<T>{

	private BaseHibernateDao<T> dao;
	
	public void setDao(BaseHibernateDao<T> dao) {
		this.dao = dao;
	}
	
	protected BaseHibernateDao<T> getDao() {
		return this.dao;
	}

	/**	
	   #################################
	   ##########   查询功能   ##########
	   #################################
	*/
	
	/**
	 * 根据hql取得所有数据
	 * @param hql	  hql语句
	 * @param values  hql查询参数值
	 * @return
	 */
	public List<T> findRecordsByHql(String hql,Object ... values){
		return dao.findRecordsByHql(hql,values);
	}
	public List<T> findRecordsByHqlPage(String hql,int offset,int length, Object... values){
		return dao.findRecordsByHql(hql, offset, length, values);
	}
	/**
	 * 取得所有数据
	 * @return
	 */
	public List<T> findAllRecords() {
		return dao.findAllRecords();
	}

	/**
	 * 取得所有未删除数据
	 * @return
	 */
	public List<T> findAllUndeletedRecords() {
		return dao.findAllUndeletedRecords();
	}

	/**
	 * 根据某个属性值取得相应数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return		
	 */
	public List<T> findRecordsByProperty(String property, Object value) {
		return dao.findRecordsByProperty(property,value);
	}
	
	/**
	 * 根据某个属性值取得相应未删除的数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return		
	 */
	public List<T> findUndeletedRecordsByProperty(String property, Object value) {
		return dao.findUndeletedRecordsByProperty(property,value);
	}
	
	/**
	 * 根据某个属性值取得相应已删除的数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return		
	 */
	public List<T> findDeletedRecordsByProperty(String property, Object value) {
		return dao.findDeletedRecordsByProperty(property,value);
	}

	
	/**
	 * 根据某个属性值取得第一条数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public T findRecordByProperty(String property, Object value) {
		return dao.findRecordByProperty(property,value);
	}
	
	
	/**
	 * 根据某个属性值取得第一条未删除的数据
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public T findUndeletedRecordByProperty(String property, Object value) {
		return dao.findUndeletedRecordByProperty(property,value);
	}

	/**
	 * 根据某个属性值得到相应实体数量
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public int countByProperty(String property, Object value) {
		return dao.countByProperty(property,value);
	}
	
	/**
	 * 根据某个属性值得到相应实体数量
	 * @param property	属性名称
	 * @param value		属性值
	 * @return
	 */
	public int countUndeletedByProperty(String property, Object value) {
		return dao.countUndeletedByProperty(property,value);
	}
	
	public Integer countByHql(String hql, Object... values){
		return dao.countByHql(hql, values);
	}
	
	/**	
	   #################################
	   ##########   保存功能   ##########
	   #################################
	*/

	/**
	 * 添加实体
	 * @param entity	实体
	 * @return
	 */
	public T insert(T entity) {
		return dao.insert(entity);
	}
	
	/**
	 * 保存实体
	 * @param entity	实体
	 * @return
	 */
	public Integer save(T entity){
		return dao.save(entity);
	}
	
	/**
	 * 更新某个实体
	 * @param entity	实体
	 * @return
	 */
	public T update(T entity) {
		return dao.update(entity);
	}

	/**
	 * 更新或插入（多个）
	 * @param list	实体列表
	 */
	//public void saveOrUpdateAll(List<T> list) {
	//	dao.saveOrUpdateAll(list);
	//}
	
	public void saveAll(List<T> list){
		dao.saveAll(list);
	}
	
	public void updateAll(List<T> list){
		dao.updateAll(list);
	}
	

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
		return dao.delete(entity);
	}
		
	
	/**
	 *  获取前N条记录
	 * @param n
	 * 前几条
	 * @param hql
	 * 查询所有符合要求的记录的hql
	 * @param orderProperty
	 * 排序的属性
	 * @param isDesc
	 * 排序是否为降序
	 * @return
	 * topN的列表
	 */
	@SuppressWarnings("unchecked")
	public List<T> getTopNByHql(Integer n,String hql, String orderProperty, boolean isDesc ){
		hql += " order by " + orderProperty + " ";
		if(isDesc){
			hql += "desc";
		} else {
			hql += "asc";
		}
		Query query = this.dao.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(n-1);
		return query.list();		
	}
}
