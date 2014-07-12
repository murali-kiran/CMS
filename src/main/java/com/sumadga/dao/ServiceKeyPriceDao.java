package com.sumadga.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sumadga.dto.ServiceKeyPrice;

@Repository
public class ServiceKeyPriceDao {

	private static final Logger logger = Logger.getLogger(ServiceKeyPriceDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(ServiceKeyPrice entity) {
		logger.info("saving ServiceKeyPrice instance");
		try {
			entityManager.persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(ServiceKeyPrice entity) {
		logger.info("deleting ServiceKeyPrice instance");
		try {
			entity = entityManager.getReference(ServiceKeyPrice.class,
					entity.getServiceKeyPriceId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public ServiceKeyPrice update(ServiceKeyPrice entity) {
		logger.info("updating ServiceKeyPrice instance");
		try {
			ServiceKeyPrice result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public ServiceKeyPrice findById(Integer id) {
		logger.info("finding ServiceKeyPrice instance with id: " + id);
		try {
			ServiceKeyPrice instance = entityManager.find(ServiceKeyPrice.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServiceKeyPrice> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding ServiceKeyPrice instance with property: " + propertyName
				+ ", value: " + value);
		try {
						
			String queryString = "select model from ServiceKeyPrice model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from ServiceKeyPrice model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, ServiceKeyPrice.class);
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
			logger.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServiceKeyPrice> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all ServiceKeyPrice instances");
		try {
			final String queryString = "select model from ServiceKeyPrice model";
			Query query = entityManager
					.createQuery(queryString, ServiceKeyPrice.class);
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
	
	@SuppressWarnings("unchecked")
	public List<ServiceKeyPrice> findByServiceIdAndServiceKey(Integer serviceId,String serviceKeyPrice) {
		logger.info("finding all ServiceKeyPrice instances");
		try {
			final String queryString = "select model from ServiceKeyPrice model where model.serviceKeyPriceKey='"+serviceKeyPrice+"' and model.serviceKey = "+serviceId;
			Query query = entityManager
					.createQuery(queryString, ServiceKeyPrice.class);
			
			return query.getResultList();
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
}
