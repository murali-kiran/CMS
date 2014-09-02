package com.sumadga.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sumadga.dto.Purchas;
import com.sumadga.wap.model.Bean;

@Repository
public class PurchaseAndDownloadDao {
	
	private static final Logger logger = Logger.getLogger(PurchaseAndDownloadDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	public Purchas checkPurchaseRecordExistForToday(Long msiddn,int mediaId,String identifier){
		
		
		logger.info("Finding mediaTypeInfo of mediaGroup");
		try {
		//	final String queryString = "SELECT * FROM purchases WHERE date(expiry_time) = curdate() and msisdn = ?;";
			
			//final String queryString ="SELECT p.* FROM purchases p join media_downloads md  on  md.`purchase_id` = p.`purchase_id` and date(expiry_time) = curdate() and p.msisdn = ? and md.`media_id` = ? ";
			 String queryString ="" ;
			 if(msiddn==null  || msiddn==0)
			 queryString ="SELECT p.* FROM purchases p join media_downloads md  on " +
					" md.`purchase_id` = p.`purchase_id` and date(expiry_time) = curdate() and p.identifier = ? and md.`media_id` = ? ";
			 else
				 queryString ="SELECT p.* FROM purchases p join media_downloads md  on " +
							" md.`purchase_id` = p.`purchase_id` and date(expiry_time) = curdate() and p.msisdn = ? and md.`media_id` = ? ";
			
			Query query = entityManager.createNativeQuery(queryString);
			if(msiddn!=null && msiddn!=0)
			query.setParameter(1,msiddn);
			else
				query.setParameter(1,identifier);
			query.setParameter(2,mediaId);
			
			List<Object[]> list = query.getResultList();
			
			if(!list.isEmpty()){
				Object [] obj = list.get(0);
				Purchas purchas = new Purchas();
				purchas.setPurchaseId((Integer)obj[0]);
				
				return purchas;
			}else{
				return null;
			}
					
		} catch (RuntimeException re) {
			return null;
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


	public Purchas checkPurchaseRecordOfMediaGroupExistForToday(Long msisdn,String mediaIdsStr, String identifier) {
		
		logger.info("Finding mediaTypeInfo of mediaGroup");
		try {
			 String queryString ="" ;			 
			 if(msisdn==null  || msisdn==0)
			 queryString ="SELECT p.* FROM purchases p join media_downloads md  on " +
					" md.`purchase_id` = p.`purchase_id` and date(expiry_time) = curdate() and p.identifier = ? and md.`media_id` in ("+mediaIdsStr+")";
			 else
				 queryString ="SELECT p.* FROM purchases p join media_downloads md  on " +
							" md.`purchase_id` = p.`purchase_id` and date(expiry_time) = curdate() and p.msisdn = ? and md.`media_id` in ("+mediaIdsStr+")";
			 
			 
			
			Query query = entityManager.createNativeQuery(queryString);
			
			if(msisdn!=null && msisdn!=0)
			query.setParameter(1,msisdn);
			else
				query.setParameter(1,identifier);
			
			
			List<Object[]> list = query.getResultList();
			
			if(!list.isEmpty()){
				Object [] obj = list.get(0);
				Purchas purchas = new Purchas();
				purchas.setPurchaseId((Integer)obj[0]);
				
				return purchas;
			}else{
				return null;
			}
					
		} catch (RuntimeException re) {
			re.printStackTrace();
			return null;
		}
		
	}


}
