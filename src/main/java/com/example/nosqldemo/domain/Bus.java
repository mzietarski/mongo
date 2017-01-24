package com.example.nosqldemo.domain;

import org.bson.types.ObjectId;

public class Bus {
	
	private ObjectId id;
	private int nrlini;
	private String marka;
	
	
	
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public String getMarka() {
		return marka;
	}
	
	public void setMarka(String marka) {
		this.marka = marka;
	}
	
	public int getnrlini() {
		return nrlini;
	}
	
	public void setnrlini(int nrlini) {
		this.nrlini = nrlini;
	}
	
}
