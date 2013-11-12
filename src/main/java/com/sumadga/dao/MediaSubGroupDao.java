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
import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.dto.MediaSubGroup;
/*import com.sumadga.mediagroup.MediaGroupModel;*/

@Repository
public class MediaSubGroupDao  {

	private static final Logger logger = Logger.getLogger(MediaSubGroupDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(MediaSubGroup entity) {
		logger.info("saving MediaSubGroup instance");
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
	public void delete(MediaSubGroup entity) {
		logger.info("deleting MediaSubGroup instance");
		try {
			entity = entityManager.getReference(MediaSubGroup.class,
					entity.getSubGroupId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}
	/*@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
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
	}*/

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public MediaSubGroup update(MediaSubGroup entity) {
		logger.info("updating MediaSubGroup instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			MediaSubGroup result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public MediaSubGroup findById(Integer id) {
		logger.info("finding MediaSubGroup instance with id: " + id);
		try {
			MediaSubGroup instance = entityManager.find(MediaSubGroup.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MediaSubGroup> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding MediaSubGroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from MediaSubGroup model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from MediaSubGroup model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, MediaSubGroup.class);
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
	public List<MediaSubGroup> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all MediaSubGroup instances");
		try {
			final String queryString = "select model from MediaSubGroup model";
			Query query = entityManager
					.createQuery(queryString, MediaSubGroup.class);
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

	public List<MediaSubGroup> findMediaGroupByOrder(Integer mediaGroupId) {
		// TODO Auto-generated method stub
		try {
			String queryString = "select model from MediaSubGroup model where model.parentMediaGroup.mediaGroupId="
					 + mediaGroupId+" order by model.groupOrder";
			Query query = entityManager
					.createQuery(queryString, MediaSubGroup.class);
			return query.getResultList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

/*	public List<MediaGroup> findRemainingMediaGroup(MediaGroupModel mediaGroupModel) {
		// TODO Auto-generated method stub
		try {
			String queryString = "select model from MediaGroup model where 1=1 "
					 +" and model.mediaGroupId not in(select msb.childMediaGroup.mediaGroupId from MediaSubGroup msb"
					 +" where msb.parentMediaGroup.mediaGroupId="
					 + mediaGroupModel.getParentmgId()+")";
			Query query = entityManager
					.createQuery(queryString, MediaGroup.class);
			return query.getResultList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		//return null;
	}*/
}
