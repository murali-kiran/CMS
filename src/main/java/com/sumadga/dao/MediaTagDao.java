package com.sumadga.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sumadga.dto.MediaTag;
import com.sumadga.utils.ApplicationProperties;
import com.sumadga.utils.CommonUtils;
import com.sumadga.wap.model.MediaBean;

@Repository
public class MediaTagDao {

	private static final Logger logger = Logger.getLogger(MediaTagDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ApplicationProperties applicationProperties;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(MediaTag entity) {
		logger.info("saving MediaTag instance");
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
	public void delete(MediaTag entity) {
		logger.info("deleting MediaTag instance");
		try {
			entity = entityManager.getReference(MediaTag.class,
					entity.getMediaTagId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public MediaTag update(MediaTag entity) {
		logger.info("updating MediaTag instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			MediaTag result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public MediaTag findById(Integer id) {
		logger.info("finding MediaTag instance with id: " + id);
		try {
			MediaTag instance = entityManager.find(MediaTag.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MediaTag> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding MediaTag instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "select model from MediaTag model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from MediaTag model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, MediaTag.class);
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
	public List<MediaTag> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all MediaTag instances");
		try {
			final String queryString = "select model from MediaTag model";
			Query query = entityManager
					.createQuery(queryString, MediaTag.class);
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
	public List<MediaBean> getMediaInfoUsingTag(String tagName,int serviceKeyId,int mediaContentPurposeId,int width,int height,final int... rowStartIdxAndCount) {
		logger.info("Finding media info of Using");
		try {
			
			
			final String queryString = "select distinct media.id,media.title,media.path,media.servicekeyId,media.serviceKeyPriceId,media.price,media.serviceKeyPriceKey  "
					+ "from (SELECT distinct m.media_id as id, m.media_title as title , mc.storage_path as path , skp.service_key_id as servicekeyId, skp.service_key_price_id as serviceKeyPriceId, "
					+ "skp.price as price, skp.service_key_price_key as serviceKeyPriceKey FROM media_group_media mgm join service_media_groups smg join service_key_prices skp   JOIN  media m  JOIN  media_contents mc JOIN "
					+ "media_specifications ms on smg.service_id = :serviceId and  mgm.`media_group_id` = smg.`media_group_id`  and mgm.media_id  = m.media_id and skp.service_key_id = smg.service_key_id  AND  mc.media_id = mgm.media_id AND mc.media_specification_id = ms.media_specification_id  "
					+ "AND ms.width = :width  AND ms.height = :height  AND  ms.media_content_purpose_id = :purposeId) as media join (SELECT  distinct mt.media_id  as mediaId FROM tags t  join media_tags mt on t.tag_name like :tagName and t.tag_id = mt.tag_id ) as k on k.mediaId = media.id group by media.id,media.title,"
					+ "media.path,media.servicekeyId,media.serviceKeyPriceId,media.price,media.serviceKeyPriceKey order by media.id";
			Query query = entityManager.createNativeQuery(queryString);
			
			query.setParameter("serviceId", serviceKeyId);
			query.setParameter("width", width);
			query.setParameter("height", height);
			query.setParameter("purposeId", mediaContentPurposeId);
			query.setParameter("tagName", "%" +tagName+"%" );
			
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
			
			List<Object[]> list = query.getResultList();
			List<MediaBean> mediaBeans = new ArrayList<MediaBean>();
			for(Object[] obj : list){
				MediaBean bean = new MediaBean();
				bean.setMediaId((Integer)obj[0]);
				bean.setMediaName((String)obj[1]);
				
				if(mediaContentPurposeId == CommonUtils.MEDIA_CONTENT_PRIVIEW)
				bean.setStoragePath(applicationProperties.getMediaAbsolutePath()+(String)obj[2]);
				else
				bean.setStoragePath((String)obj[2]);	
				
				bean.setServiceKeyId((Integer)obj[3]);
				bean.setServiceKeypriceId((Integer)obj[4]);
				bean.setPrice((Double)obj[5]);
				bean.setServiceKeypriceKey((String)obj[6]);
			
				bean.setIsSubMediaGroup(false);
				mediaBeans.add(bean);
			}
			
			return mediaBeans;
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}

	
}

