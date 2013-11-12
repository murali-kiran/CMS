package com.sumadga.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import com.sumadga.dto.MediaGroupMedia;
import com.sumadga.wap.model.Bean;
import com.sumadga.wap.model.MediaBean;

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
	public List<MediaGroupMedia> findMediaByOrder(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding MediaGroupMedia instance with property: " + propertyName
				+ ", value: " + value);
		try {
			
			
			String queryString = "select model from MediaGroupMedia model where model."
					+ propertyName + "= " + value+" order by model.mediaOrder";
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
	
		
	@SuppressWarnings("unchecked")
	public Integer getCountOfMediaInMediaGroupOfSpecificMediaType(int mediaGroupId,int mediaTypeId) {
		logger.info("Finding media info of mediaGroup");
		try {
			final String queryString = "SELECT count(*) FROM  media_group_media cat JOIN  media m JOIN  media_contents mc ON  media_group_id = ? AND cat.media_id = m.media_id AND mc.media_id = cat.media_id  and m.media_type_id = ?";
			
			Query query = entityManager.createNativeQuery(queryString);
			query.setParameter(1, mediaGroupId);
			query.setParameter(2, mediaTypeId);
			
			Object count = query.getSingleResult();
			
			return  ((BigInteger) count).intValue();
			
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<MediaBean> getMediaInfoOfMediaGroup(int mediaGroupId,final int... rowStartIdxAndCount) {
		logger.info("Finding media info of mediaGroup");
		try {
			final String queryString = "SELECT m.media_type_id , m.media_id , m.media_title , mc.storage_path "
					+"FROM  media_group_media cat JOIN  media m JOIN  media_contents mc ON  media_group_id =? "
					+"AND cat.media_id = m.media_id AND mc.media_id = cat.media_id group by m.media_id , m.media_title , mc.storage_path ";
			
			Query query = entityManager.createNativeQuery(queryString);
			query.setParameter(1, mediaGroupId);
			
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
				bean.setMediaTypeId((Integer)obj[0]);
				bean.setMediaId((Integer)obj[1]);
				bean.setMediaName((String)obj[2]);
				bean.setStoragePath((String)obj[3]);
				bean.setIsSubMediaGroup(false);
				mediaBeans.add(bean);
			}
			
			return mediaBeans;
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
	
	
	
			@SuppressWarnings("unchecked")
			public MediaBean getMediaInfoOfMediaSubGroup(int mediaGroupId,final int... rowStartIdxAndCount) {
				logger.info("Finding media info of mediaGroup");
				try {
					final String queryString = "SELECT m.media_type_id , m.media_id , m.media_title , mc.storage_path FROM  media_groups cat JOIN  media m JOIN  media_contents mc ON  media_group_id = ? AND cat.media_group_preview_id = m.media_id AND mc.media_id = cat.media_group_preview_id group by m.media_id , m.media_title , mc.storage_path";
					
					Query query = entityManager.createNativeQuery(queryString);
					query.setParameter(1, mediaGroupId);
					
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
						bean.setMediaTypeId((Integer)obj[0]);
						bean.setMediaId((Integer)obj[1]);
						bean.setMediaName((String)obj[2]);
						bean.setStoragePath((String)obj[3]);
						bean.setIsSubMediaGroup(true);
						bean.setMediagroupId(mediaGroupId);
						mediaBeans.add(bean);
					}
					
					return mediaBeans.get(0);
				} catch (RuntimeException re) {
					logger.error("find all failed", re);
					throw re;
				}
			}
			

	
	
	@SuppressWarnings("unchecked")
	public List<MediaBean> getMediaInfoOfMediaGroupOfSpecificMediaType(int mediaGroupId,int mediaTypeId,final int... rowStartIdxAndCount) {
		logger.info("Finding media info of mediaGroup");
		try {
			final String queryString = "SELECT m.media_type_id , m.media_id , m.media_title , mc.storage_path "
					+"FROM  media_group_media cat JOIN  media m JOIN  media_contents mc ON  media_group_id =? "
					+"AND cat.media_id = m.media_id AND mc.media_id = cat.media_id  and m.`media_type_id` = ? group by  m.media_type_id , m.media_id , m.media_title , mc.storage_path ";
			
			Query query = entityManager.createNativeQuery(queryString);
			query.setParameter(1, mediaGroupId);
			query.setParameter(2, mediaTypeId);
			
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
				bean.setMediaTypeId((Integer)obj[0]);
				bean.setMediaId((Integer)obj[1]);
				bean.setMediaName((String)obj[2]);
				bean.setStoragePath((String)obj[3]);
				mediaBeans.add(bean);
			}
			
			return mediaBeans;
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Bean<Integer,String>> getMediaTypeOfMediaGroup(int mediaGroupId,final int... rowStartIdxAndCount) {
		
		logger.info("Finding mediaTypeInfo of mediaGroup");
		try {
			final String queryString = "SELECT  Distinct mt.media_type_id,mt.media_type_name FROM media_group_media cat join media m join media_types mt on cat.media_id  = m.media_id and m.media_type_id = mt.media_type_id and cat.media_group_id = ?";
			
			Query query = entityManager.createNativeQuery(queryString);
			query.setParameter(1,mediaGroupId);
			
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
			List<Bean<Integer,String>> mediaTypes = new ArrayList<Bean<Integer,String>>();
	
			for(Object[] obj : list){
				
				Bean<Integer,String> bean = new Bean<Integer,String>();
				bean.setId((Integer)obj[0]);
				bean.setName((String)obj[1]);
				
				mediaTypes.add(bean);
			}
			
			return mediaTypes;
		} catch (RuntimeException re) {
			logger.error("find all failed", re);
			throw re;
		}
	}
	
	
	
}
