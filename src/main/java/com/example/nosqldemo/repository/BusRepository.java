package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Bus;

public interface BusRepository extends CrudRepository<Bus, ObjectId>{
	
	<S extends Bus> S save(S entity);
	
	Bus findById(ObjectId id);
	
	List<Bus> findByNrlini(int nrlini);
	
	List<Bus> findAll();
	
	@Query(value = "{ 'nrlini' : ?0 }" )
	List<Bus> znajdzBus(int nrlini);
	

}

