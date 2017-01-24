package com.example.nosqldemo.repository;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.nosqldemo.domain.Mechanik;


public interface MechanikRepository extends CrudRepository<Mechanik, ObjectId> {
	
	<S extends Mechanik> S save(S entity); 
	
	Mechanik findById(ObjectId id);
	
	List<Mechanik> findByLiczbaObiektow(int liczbaObiektow);
	
	List<Mechanik> findAll();
	
	@Query(value = "{ 'imie' : ?0, 'liczbaObiektow' : ?1 }" )
	List<Mechanik> znajdzMechanik(String imie, int liczbaObiektow);
	
}