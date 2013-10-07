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

import com.sumadga.dto.MediaServiceOwnerSpecfication;

@Repository
public class MediaServiceOwnerSpecficationDao implements GenericDao<MediaServiceOwnerSpecfication>{

	private static final Logger logger = Logger.getLogger(MediaServiceOwnerSpecficationDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(MediaServiceOwnerSpecfication entity) {
		logger.info("saving MediaServiceOwnerSpecfication instance");
		try {
			entityManager.persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(MediaServiceOwnerSpecfication entity) {
		logger.info("deleting MediaServiceOwnerSpecfication instance");
		try {
			entity = entityManager.getReference(MediaServiceOwnerSpecfication.class,
					entity.getMediaServiceOwnerSpecficationId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public MediaServiceOwnerSpecfication update(MediaServiceOwnerSpecfication entity) {
		logger.info("updating MediaServiceOwnerSpecfication instance");
		try {
			MediaServiceOwnerSpecfication result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public MediaServiceOwnerSpecfication findById(Integer id) {
		logger.info("finding MediaServiceOwnerSpecfication instance with id: " + id);
		try {
			MediaServiceOwnerSpecfication instance = entityManager.find(MediaServiceOwnerSpecfication.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MediaServiceOwnerSpecfication> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding MediaServiceOwnerSpecfication instance with property: " + propertyName
				+ ", value: " + value);
		try {
			final String queryString = "select model from MediaServiceOwnerSpecfication model where model."
					+ propertyName + "= " + value;
			Query query = entityManager
					.createQuery(queryString, MediaServiceOwnerSpecfication.class);
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
	public List<MediaServiceOwnerSpecfication> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all MediaServiceOwnerSpecfication instances");
		try {
			final String queryString = "select model from MediaServiceOwnerSpecfication model";
			Query query = entityManager
					.createQuery(queryString, MediaServiceOwnerSpecfication.class);
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
