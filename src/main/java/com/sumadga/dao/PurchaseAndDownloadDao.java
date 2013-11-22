package com.sumadga.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sumadga.wap.model.Bean;

@Repository
public class PurchaseAndDownloadDao {
	
	private static final Logger logger = Logger.getLogger(PurchaseAndDownloadDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	public Boolean checkPurchaseRecordExistForToday(Long msiddn){
		
		
		logger.info("Finding mediaTypeInfo of mediaGroup");
		try {
			final String queryString = "SELECT * FROM purchases WHERE date(last_purchase_time) = curdate() and msisdn = ?;";
			
			Query query = entityManager.createNativeQuery(queryString);
			query.setParameter(1,msiddn);
			
			List<Object[]> list = query.getResultList();
			
			return(list.size() > 0);
					
		} catch (RuntimeException re) {
			return false;
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
