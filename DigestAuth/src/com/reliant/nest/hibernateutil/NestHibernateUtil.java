package com.reliant.nest.hibernateutil;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reliant.nest.entity.NestEntity;

@Repository
public class NestHibernateUtil {

	@Autowired
	SessionFactory sessionFactory;
	
	public void saveBatchId(NestEntity entity) {
		try {
			System.out.println("NestHibernateUtil.saveBatchId() || entity : " + entity.getFIRST_NAME());
			sessionFactory.getCurrentSession().save(entity);
			System.out.println("NestHibernateUtil.saveBatchId() || Done..");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
