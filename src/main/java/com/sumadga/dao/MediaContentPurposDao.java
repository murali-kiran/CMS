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

import com.sumadga.dto.MediaContentPurpos;

@Repository
public class MediaContentPurposDao implements GenericDao<MediaContentPurpos>{

	private static final Logger logger = Logger.getLogger(MediaContentPurposDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(MediaContentPurpos entity) {
		logger.info("saving MediaContentPurpos instance");
		try {
			entityManager.persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(MediaContentPurpos entity) {
		logger.info("deleting MediaContentPurpos instance");
		try {
			entity = entityManager.getReference(MediaContentPurpos.class,
					entity.getMediaContentPurposeId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public MediaContentPurpos update(MediaContentPurpos entity) {
		logger.info("updating MediaContentPurpos instance");
		try {
			MediaContentPurpos result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public MediaContentPurpos findById(Integer id) {
		logger.info("finding MediaContentPurpos instance with id: " + id);
		try {
			MediaContentPurpos instance = entityManager.find(MediaContentPurpos.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MediaContentPurpos> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding MediaContentPurpos instance with property: " + propertyName
				+ ", value: " + value);
		try {
			final String queryString = "select model from MediaContentPurpos model where model."
					+ propertyName + "= " + value;
			Query query = entityManager
					.createQuery(queryString, MediaContentPurpos.class);
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
	public List<MediaContentPurpos> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all MediaContentPurpos instances");
		try {
			final String queryString = "select model from MediaContentPurpos model";
			Query query = entityManager
					.createQuery(queryString, MediaContentPurpos.class);
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
