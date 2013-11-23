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

import com.sumadga.dto.FailPurchas;

@Repository
public class FailPurchasDao {

	private static final Logger logger = Logger.getLogger(FailPurchasDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public FailPurchas save(FailPurchas entity) {
		logger.info("saving FailPurchas instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			return entityManager.merge(entity);
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			return null;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(FailPurchas entity) {
		logger.info("deleting FailPurchas instance");
		try {
			entity = entityManager.getReference(FailPurchas.class,
					entity.getFailPurchaseId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public FailPurchas update(FailPurchas entity) {
		logger.info("updating FailPurchas instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			FailPurchas result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public FailPurchas findById(Integer id) {
		logger.info("finding FailPurchas instance with id: " + id);
		try {
			FailPurchas instance = entityManager.find(FailPurchas.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<FailPurchas> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding FailPurchas instance with property: " + propertyName
				+ ", value: " + value);
		try {
						
			String queryString = "select model from FailPurchas model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from FailPurchas model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, FailPurchas.class);
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
	public List<FailPurchas> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all FailPurchas instances");
		try {
			final String queryString = "select model from FailPurchas model";
			Query query = entityManager
					.createQuery(queryString, FailPurchas.class);
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
