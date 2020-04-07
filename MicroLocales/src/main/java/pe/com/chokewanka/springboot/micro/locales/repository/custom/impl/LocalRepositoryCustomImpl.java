package pe.com.chokewanka.springboot.micro.locales.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import pe.com.chokewanka.springboot.micro.locales.filter.LocalFilter;
import pe.com.chokewanka.springboot.micro.locales.model.Local;
import pe.com.chokewanka.springboot.micro.locales.repository.custom.LocalRepositoryCustom;
import pe.com.chokewanka.springboot.micro.locales.utils.ModelConstants;
import pe.com.chokewanka.springboot.micro.locales.utils.UtilConstants;

public class LocalRepositoryCustomImpl implements LocalRepositoryCustom {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Local> findCustom(LocalFilter filter) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		List<Local> resultado = new ArrayList<Local>();
		
		CriteriaQuery<Local> query = cb.createQuery(Local.class);
		Root<Local> local = query.from(Local.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(filter.getNombre() != null && !filter.getNombre().isEmpty()) {
			predicates.add(cb.like(local.get(ModelConstants.LOCAL_NOMBRE), UtilConstants.bothSidesLike(filter.getNombre())));
		}
		
		query
			.select(local)
			.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		resultado = entityManager.createQuery(query).getResultList();
		
		return resultado;
		
	}

}
