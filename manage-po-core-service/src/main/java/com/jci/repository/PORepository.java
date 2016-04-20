package com.jci.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jci.domain.PO;

@Repository
public interface PORepository extends CrudRepository<PO,Long>{
	
	Iterable<PO> findByIdIn(Collection<Long> ids);
	
	//Iterable<PO> findByPoNumIn(Collection<Long> ids);
	
	List<PO> findByPoNumIn(List<Integer> poNum);
	
	List<PO> findByStatusIn(List<Integer> status);
	
}
