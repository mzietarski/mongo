package com.example.nosqldemo.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Bus;
import com.example.nosqldemo.repository.BusRepository;

@Component
public class BusManager {

	@Autowired
	private  BusRepository busRepository;
	
	public void addBus(Bus bus){
		busRepository.save(bus);
	}
	
	public void deletebus(Bus bus){
		busRepository.delete(bus);
	}
	
	public void deleteAllbusy(){
		busRepository.deleteAll();
	}
	
	public void updatebus(Bus bus){
		busRepository.save(bus);
	}
	
	public List<Bus> getAllbusy(){
		return busRepository.findAll();
	}
	
	public List<Bus> getBusyByNrlini(int nrlini){
		return busRepository.findByNrlini(nrlini);
	}
	
	public Bus getBusById(ObjectId id){
		return busRepository.findById(id);
	}
	
	
	
}
