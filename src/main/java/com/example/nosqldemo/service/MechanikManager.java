package com.example.nosqldemo.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.nosqldemo.domain.Bus;
import com.example.nosqldemo.domain.Mechanik;
import com.example.nosqldemo.repository.BusRepository;
import com.example.nosqldemo.repository.MechanikRepository;

@Component
public class MechanikManager {

	@Autowired
	private  MechanikRepository mechanikRepository;
	
	@Autowired
	private  BusRepository busRepository;
	
	public void addNewMechanik(Mechanik uklad){
		mechanikRepository.save(uklad);
	}
	
	public void deleteMechanik(Mechanik uklad){
		for (Bus bus : uklad.getbusy()) {
			busRepository.delete(bus);
		}
		mechanikRepository.delete(uklad);
	}
	
	public void deleteAllMechanik(){
		mechanikRepository.deleteAll();
	}
	
	public void updateMechanik(Mechanik uklad){
		mechanikRepository.save(uklad);
	}
	
	public List<Mechanik> getAllMechanik(){
		return mechanikRepository.findAll();
	}
	
	public List<Mechanik> getMechanikByLiczbaObiektow(int liczbaObiektow){
		return mechanikRepository.findByLiczbaObiektow(liczbaObiektow);
	}
	
	public List<Mechanik> getMechanikByImieLiczbaObiektow(String imie, int liczbaObiektow){
		return mechanikRepository.znajdzMechanik(imie, liczbaObiektow);
	}
	
	public Mechanik getMechanikById(ObjectId id){
		return mechanikRepository.findById(id);
	}
	
	public List<Bus> getbusyInMechanik(Mechanik uklad){
		List<Bus> busy = new ArrayList<Bus>(uklad.getbusy());
		return busy;
	}
	
	public void deleteBusyWMechanikByNrlini(Mechanik uklad, int kryterium){
	
		for (Bus bus : uklad.getbusy()) {
			if(bus.getnrlini() > kryterium){
				busRepository.delete(bus);
			}
		}
	}
	
	
}
