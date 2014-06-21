package com.sumadga.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
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
import com.sumadga.dto.MediaProvider;
import com.sumadga.mis.MisModel;
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

	public List<Media> search(MediaUploadModel mediaUploadModel, String userName) {
		// TODO Auto-generated method stub
		logger.info("search media");
		String mediaProvList = getMediaProviderFromUser(userName);
		StringBuffer queryString = new StringBuffer("select model from Media model where 1=1 ");
		if(mediaUploadModel != null){
			if(mediaUploadModel.getLanguageId() != null && mediaUploadModel.getLanguageId() != -1)
				queryString.append(" and model.language="+mediaUploadModel.getLanguageId());
			if(mediaUploadModel.getDescription() != null && !mediaUploadModel.getDescription().equals(""))
				queryString.append(" and model.description like '%"+mediaUploadModel.getDescription()+"%'");
			if(mediaUploadModel.getMediaCycleId() != null && mediaUploadModel.getMediaCycleId() != -1)
				queryString.append(" and model.mediaCycle="+mediaUploadModel.getMediaCycleId());
			if(mediaUploadModel.getMediaName() != null && !mediaUploadModel.getMediaName().equals("") && !mediaUploadModel.getMediaName().trim().equals("null"));
				queryString.append(" and model.mediaName like '%"+mediaUploadModel.getMediaName()+"%'");
			if(mediaUploadModel.getMediaTitle() != null && !mediaUploadModel.getMediaTitle().equals(""))
				queryString.append(" and model.mediaTitle like '%"+mediaUploadModel.getMediaTitle()+"%'");
			if(mediaUploadModel.getMediaTypeId() != null && mediaUploadModel.getMediaTypeId() != -1)
				queryString.append(" and model.mediaType="+mediaUploadModel.getMediaTypeId());
		}
		if(mediaProvList != null)
			queryString.append("and model.mediaProvider.mediaProviderId in ("+mediaProvList+")");
		queryString.append(" order by model.createdTime desc, model.modifiedTime desc");
		Query query = entityManager
				.createQuery(queryString.toString(), Media.class);
	//	query.setMaxResults(20);
		return query.getResultList();
	}

	public List<Media> findRemainingMedia(MediaUploadModel mediaUploadModel, String userName) {
		// TODO Auto-generated method stub
		StringBuffer queryString = new StringBuffer("select model from Media model where 1=1 ");
		String mediaProvList = getMediaProviderFromUser(userName);
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
		queryString.append("and model.mediaId not in (select mgm.media.mediaId from MediaGroupMedia mgm where mgm.mediaGroup.mediaGroupId="+mediaUploadModel.getMgid()+")");
		if(mediaProvList != null)
			queryString.append("and model.mediaProvider.mediaProviderId in ("+mediaProvList+")");
		Query query = entityManager
				.createQuery(queryString.toString(), Media.class);
		
		List<Media> media = query.getResultList();
		return media;
	}
	
	public List<MisModel> getMisModel(List<MediaProvider> mediaProviderList) {

		StringBuffer queryString = new StringBuffer("select model.* from RevenueView model where 1=1 ");
			
		if(mediaProviderList!=null)
		{
			String str="";
			Iterator<MediaProvider> itr=mediaProviderList.iterator();
			while(itr.hasNext())
			{
				str=str+itr.next().getMediaProviderId();
				if(itr.hasNext())
					str=str+",";
			}
			if(!str.trim().equals(""))
				queryString.append(" and mediaProviderId in ("+str+")");
		}
		Query query = entityManager
				.createNativeQuery(queryString.toString(), MisModel.class);
		
		return query.getResultList();
	}
	
	public String getMediaProviderFromUser(String userName){
		Query query = entityManager.createNativeQuery("Select ump.media_provider_id from user_media_providers ump " +
				" where ump.user_name='"+userName+"'");
		List<Object> ls = query.getResultList();
		String s = "";
		int x=0;
		for (Object object : ls) {
			if(x!=0)
				s=s+",";
			s = s+object;
			x++;
		}
		return s;
	}
}

