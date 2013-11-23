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

import com.sumadga.dto.TestMobile;

@Repository
public class TestMobileDao {

	private static final Logger logger = Logger.getLogger(TestMobileDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void save(TestMobile entity) {
		logger.info("saving TestMobile instance");
		try {
			entityManager.persist(entity);
			logger.info("save successful");
		} catch (RuntimeException re) {
			logger.error("save failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public void delete(TestMobile entity) {
		logger.info("deleting TestMobile instance");
		try {
			entity = entityManager.getReference(TestMobile.class,
					entity.getMobileNumber());
			entityManager.remove(entity);
			logger.info("delete successful");
		} catch (RuntimeException re) {
			logger.error("delete failed", re);
			throw re;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = DataAccessException.class)
	public TestMobile update(TestMobile entity) {
		logger.info("updating TestMobile instance");
		try {
			TestMobile result = entityManager.merge(entity);
			logger.info("update successful");
			return result;
		} catch (RuntimeException re) {
			logger.error("update failed", re);
			throw re;
		}
	}

	public TestMobile findById(Integer id) {
		logger.info("finding TestMobile instance with id: " + id);
		try {
			TestMobile instance = entityManager.find(TestMobile.class, id);
			return instance;
		} catch (RuntimeException re) {
			logger.error("find failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public TestMobile findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		logger.info("finding TestMobile instance with property: " + propertyName
				+ ", value: " + value);
		try {
						
			String queryString = "select model from TestMobile model where model."
					+ propertyName + "= " + value;
			if(value.getClass().getName().equals("java.lang.String"))
				queryString = "select model from TestMobile model where model."
						+ propertyName + " like '%" + value+"%'";
			Query query = entityManager
					.createQuery(queryString, TestMobile.class);
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
			List<TestMobile> testMobiles = query.getResultList();
			
			if(!testMobiles.isEmpty())
			return testMobiles.get(0);
			else
			return null;
		} catch (RuntimeException re) {
			logger.error("find by property name failed", re);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TestMobile> findAll(final int... rowStartIdxAndCount) {
		logger.info("finding all TestMobile instances");
		try {
			final String queryString = "select model from TestMobile model";
			Query query = entityManager
					.createQuery(queryString, TestMobile.class);
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
