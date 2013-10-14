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
import com.sumadga.upload.MediaUploadModel;

@Repository
public class MediaDao  {

	private static final Logger logger = Logger.getLogger(MediaDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(Media entity) {
		logger.info("saving Media instance");
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
	public void delete(Media entity) {
		logger.info("deleting Media instance");
		try {
			entity = entityManager.getReference(Media.class,
					entity.getMediaId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public Media update(Media entity) {
		logger.info("updating Media instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			Media result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public Media findById(Integer id) {
		logger.info("finding Media instance with id: " + id);
		try {
			Media instance = entityManager.find(Media.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Media> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding Media instance with property: " + propertyName
				+ ", value: " + value);
		try {
			 String queryString = "select model from Media model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from Media model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, Media.class);
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
	public List<Media> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all Media instances");
		try {
			final String queryString = "select model from Media model";
			Query query = entityManager
					.createQuery(queryString, Media.class);
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

	public List<Media> search(MediaUploadModel mediaUploadModel) {
		// TODO Auto-generated method stub
		logger.info("search media");
		StringBuffer queryString = new StringBuffer("select model from Media model where 1=1 ");
		if(mediaUploadModel != null){
			if(mediaUploadModel.getLanguageId() != null && mediaUploadModel.getLanguageId() != -1)
				queryString.append(" and model.language="+mediaUploadModel.getLanguageId());
			if(mediaUploadModel.getDescription() != null && !mediaUploadModel.getDescription().equals(""))
				queryString.append(" and model.description like '%"+mediaUploadModel.getDescription()+"%'");
			if(mediaUploadModel.getMediaCycleId() != null && mediaUploadModel.getMediaCycleId() != -1)
				queryString.append(" and model.mediaCycle="+mediaUploadModel.getMediaCycleId());
			if(mediaUploadModel.getMediaName() != null && !mediaUploadModel.getMediaName().equals(""));
				queryString.append(" and model.mediaName like '%"+mediaUploadModel.getMediaName()+"%'");
			if(mediaUploadModel.getMediaTitle() != null && !mediaUploadModel.getMediaTitle().equals(""))
				queryString.append(" and model.mediaTitle like '%"+mediaUploadModel.getMediaTitle()+"%'");
			if(mediaUploadModel.getMediaTypeId() != null && mediaUploadModel.getMediaTypeId() != -1)
				queryString.append(" and model.mediaType="+mediaUploadModel.getMediaTypeId());
		}
		
		Query query = entityManager
				.createQuery(queryString.toString(), Media.class);
		
		return query.getResultList();
	}
}
