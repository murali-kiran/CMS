package com.sumadga.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sumadga.dto.ServiceProperty;

@Repository
public class ServicePropertyDao {

	private static final Logger logger = Logger.getLogger(ServicePropertyDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(ServiceProperty entity) {
		logger.info("saving ServiceProperty instance");
		try {
			entityManager.persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(ServiceProperty entity) {
		logger.info("deleting ServiceProperty instance");
		try {
			entity = entityManager.getReference(ServiceProperty.class,
					entity.getServicePropertyId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public ServiceProperty update(ServiceProperty entity) {
		logger.info("updating ServiceProperty instance");
		try {
			ServiceProperty result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public ServiceProperty findById(Integer id) {
		logger.info("finding ServiceProperty instance with id: " + id);
		try {
			ServiceProperty instance = entityManager.find(ServiceProperty.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public ServiceProperty findByProperty(int serviceId,String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding ServiceProperty instance with property: " + propertyName
				+ ", value: " + value);
		try {
						
			String queryString = "select model from ServiceProperty model where model."
					+ propertyName + "= " + value+" and serviceId = "+serviceId;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from ServiceProperty model where model."
						+ propertyName + "= '" + value+"' and serviceId = "+serviceId;
			Query query = entityManager
					.createQuery(queryString, ServiceProperty.class);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			
			List<ServiceProperty> serviceProperties = query.getResultList();
			
			if(!serviceProperties.isEmpty())
				return serviceProperties.get(0);
			else
				return null;
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			return null;
		}
	}
	
	

	@SuppressWarnings("unchecked")
	public List<ServiceProperty> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all ServiceProperty instances");
		try {
			final String queryString = "select model from ServiceProperty model";
			Query query = entityManager
					.createQuery(queryString, ServiceProperty.class);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
}
