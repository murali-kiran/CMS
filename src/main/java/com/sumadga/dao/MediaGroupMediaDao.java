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

import com.sumadga.dto.Media;
import com.sumadga.dto.MediaGroupMedia;

@Repository
public class MediaGroupMediaDao  {

	private static final Logger logger = Logger.getLogger(MediaGroupMediaDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(MediaGroupMedia entity) {
		logger.info("saving MediaGroupMedia instance");
		try {
			entity.setCreatedTime(new Date());
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			entityManager.persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(MediaGroupMedia entity) {
		logger.info("deleting MediaGroupMedia instance");
		try {
			entity = entityManager.getReference(MediaGroupMedia.class,
					entity.getMediaGroupMediaId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public List<MediaGroupMedia> findMappedMedia(Integer mediaId, Integer mediaGroupId) {
		logger.info("find MediaGroupMedia instance");
		List<MediaGroupMedia> mediagm = null;
		try {
			String strquery = "from MediaGroupMedia where media.mediaId="+mediaId+" and mediaGroup.mediaGroupId="+mediaGroupId;
			
			Query query = entityManager
					.createQuery(strquery, MediaGroupMedia.class);
			mediagm = (List<MediaGroupMedia>) query.getResultList();
			logger.info("find successful");
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
		return mediagm;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public List<MediaGroupMedia> findMappedMedia(Integer mediaGroupId) {
		logger.info("find MediaGroupMedia instance");
		List<MediaGroupMedia> mediagm = null;
		try {
			String strquery = "from MediaGroupMedia where mediaGroup.mediaGroupId="+mediaGroupId;
			
			Query query = entityManager
					.createQuery(strquery, MediaGroupMedia.class);
			mediagm = (List<MediaGroupMedia>) query.getResultList();
			logger.info("find successful");
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
		return mediagm;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public MediaGroupMedia update(MediaGroupMedia entity) {
		logger.info("updating MediaGroupMedia instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			MediaGroupMedia result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public MediaGroupMedia findById(Integer id) {
		logger.info("finding MediaGroupMedia instance with id: " + id);
		try {
			MediaGroupMedia instance = entityManager.find(MediaGroupMedia.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MediaGroupMedia> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding MediaGroupMedia instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from MediaGroupMedia model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from MediaGroupMedia model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, MediaGroupMedia.class);
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
	public List<MediaGroupMedia> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all MediaGroupMedia instances");
		try {
			final String queryString = "select model from MediaGroupMedia model";
			Query query = entityManager
					.createQuery(queryString, MediaGroupMedia.class);
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
