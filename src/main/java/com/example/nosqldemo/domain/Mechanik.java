package com.example.nosqldemo.domain;

import java.util.List;
import org.bson.types.ObjectId;

public class Mechanik {
	
	private ObjectId id;	
	private String imie;
    private Integer liczbaObiektow;
    private List<Bus> busy;
	
	
	
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getImie() {
		return imie;
	}
	
	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public int getLiczbaObiektow() {
		return liczbaObiektow;
	}
	public void setLiczbaObiektow(int liczbaObiektow) {
		this.liczbaObiektow = liczbaObiektow;
	}
	
	public List<Bus> getbusy() {
		return busy;
	}
	public void setbusy(List<Bus> busy) {
		this.busy = busy;
	}
	
}

