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

import com.sumadga.dto.MediaGroup;
import com.sumadga.dto.MediaSubGroup;
import com.sumadga.dto.ServiceMediaGroup;
import com.sumadga.mediagroup.MediaGroupModel;

@Repository
public class ServiceMediaGroupDao {

	private static final Logger logger = Logger.getLogger(ServiceMediaGroupDao.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(ServiceMediaGroup entity) {
		logger.info("saving ServiceMediaGroup instance");
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
	public void delete(ServiceMediaGroup entity) {
		logger.info("deleting ServiceMediaGroup instance");
		try {
			entity = entityManager.getReference(ServiceMediaGroup.class,
					entity.getServiceMediaGroupId());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public ServiceMediaGroup update(ServiceMediaGroup entity) {
		logger.info("updating ServiceMediaGroup instance");
		try {
			entity.setModifiedTime(new Timestamp(new Date().getTime()));
			ServiceMediaGroup result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public ServiceMediaGroup findById(Integer id) {
		logger.info("finding ServiceMediaGroup instance with id: " + id);
		try {
			ServiceMediaGroup instance = entityManager.find(ServiceMediaGroup.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<ServiceMediaGroup> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding ServiceMediaGroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			 String queryString = "select model from ServiceMediaGroup model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from ServiceMediaGroup model where model."
						+ propertyName + "= '" + value+"'";
			Query query = entityManager
					.createQuery(queryString, ServiceMediaGroup.class);
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
	public List<ServiceMediaGroup> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all ServiceMediaGroup instances");
		try {
			final String queryString = "select model from ServiceMediaGroup model";
			Query query = entityManager
					.createQuery(queryString, ServiceMediaGroup.class);
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

	public List<ServiceMediaGroup> findByServiceId(Integer serviceId) {
			// TODO Auto-generated method stub
			try {
				String queryString = "select model from ServiceMediaGroup model where model.service.serviceId="
						 + serviceId+" order by model.groupOrder";
				Query query = entityManager
						.createQuery(queryString, ServiceMediaGroup.class);
				return query.getResultList();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
	}

	public List<MediaGroup> findRemainingMediaGroup(
			MediaGroupModel mediaGroupModel) {
		try {
			String queryString = "select model from MediaGroup model where 1=1 "
					 +" and model.mediaGroupId not in(select smg.mediaGroup.mediaGroupId from ServiceMediaGroup smg"
					 +" where smg.service.serviceId="
					 + mediaGroupModel.getServiceId()+")";
			Query query = entityManager
					.createQuery(queryString, MediaGroup.class);
			return query.getResultList();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
	
}
