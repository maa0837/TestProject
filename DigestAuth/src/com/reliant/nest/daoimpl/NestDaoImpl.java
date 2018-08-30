package com.reliant.nest.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reliant.nest.dao.NestDao;
import com.reliant.nest.entity.NestEntity;
import com.reliant.nest.hibernateutil.NestHibernateUtil;

@Repository
public class NestDaoImpl implements NestDao {

	@Autowired
	NestHibernateUtil nesthibernateutil;

	@Override
	public NestEntity saveList(NestEntity entity) {
		try {
			System.out.println("NestDaoImpl.saveList() || li : " + entity);
			nesthibernateutil.saveBatchId(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
