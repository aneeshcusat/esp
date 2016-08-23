package com.chathan.famstack.dataaccess.entity.manager;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chathan.famstack.BaseFamStackService;

@Repository
@Transactional
public class FamStackEntityManager<T> extends BaseFamStackService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<T> getAllItems(Class<T> entity) 
	{
		List<T> users = (List<T>) entityManager.createQuery("Select a From "+ entity.getSimpleName() +" a", entity).getResultList();
        return users;
	}
	
	public List<T> queyItems(Map<String, String> entityParameters, Class<T> entity) 
	{
		String qlString = "SELECT p FROM "+ entity.getSimpleName() +" p WHERE p.userName = ?1 and p.password = ?2";
		TypedQuery<T> query = entityManager.createQuery(qlString, entity);		
		query.setParameter(1, entityParameters.get("userName"));
		query.setParameter(2, entityParameters.get("password"));
		List<T> users = query.getResultList();
        return users;
	}
	
	public T queyItem(Map<String, String> entityParameters, Class<T> entity) 
	{
		String qlString = "SELECT p FROM "+ entity.getSimpleName() +" p WHERE p.userName = ?1 and p.password = ?2";
		TypedQuery<T> query = entityManager.createQuery(qlString, entity);		
		query.setParameter(1, entityParameters.get("userName"));
		query.setParameter(2, entityParameters.get("password"));
		T user = query.getSingleResult();
        return user;
	}

}
