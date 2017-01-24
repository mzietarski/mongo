package com.example.nosqldemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.service.BusManager;
import com.example.nosqldemo.domain.Bus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class BusTest {
	
	@Autowired
	BusManager managerbusy;
	
	private final String marka1 = "Solaris";
	private final String marka2 = "Daewoo";
	
	private final int nrlini1 = 5;
	private final int nrlini2 = 7;
	
	
	private int nrliniUnique = 9;
	
	private final int nrliniUnique1 = 2;
	private final int nrliniUnique2 = 8;
	
	@Test
	public void checkAdding(){
		Bus bus = new Bus();
		bus.setMarka(marka1);
		bus.setnrlini(nrlini1);
		
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedDodaniem = listaBusow.size();
		
		
		managerbusy.addBus(bus);
		
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoDodaniu = listaBusow2.size();
		
		assertEquals(liczbaBusowprzedDodaniem+1, liczbaBusowpoDodaniu);
		managerbusy.deleteAllbusy();
	}
	
	
	@Test
	public void deletebusCheck() {
		
		Bus bus = new Bus();
		bus.setMarka(marka1);
		bus.setnrlini(nrlini1);
		managerbusy.addBus(bus);
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedUsunieciem = listaBusow.size();
		
		managerbusy.deletebus(bus);
		
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoUsunieciu = listaBusow2.size();
		
		assertEquals(liczbaBusowprzedUsunieciem, liczbaBusowpoUsunieciu+1);
		
		
		assertEquals(null, managerbusy.getBusById(bus.getId()));
		managerbusy.deleteAllbusy();
	}
	
	
	@Test
	public void deleteAllbusyCheck() {
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedDodaniem = listaBusow.size();
		
		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);
	
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
	
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
	
		assertNotNull(listaBusow);
		
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoDodaniu = listaBusow2.size();
	
		assertEquals(liczbaBusowpoDodaniu, liczbaBusowprzedDodaniem+2);
		
		managerbusy.deleteAllbusy();
		
		List<Bus> listaBusow3 = managerbusy.getAllbusy();
		int liczbaBusowpoUsunieciu = listaBusow3.size();
		assertEquals(0, liczbaBusowpoUsunieciu);
	}
	
	
	@Test
	public void findbusByIdCheck() {
	
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedDodaniem = listaBusow.size();
		
		Bus bus = new Bus();
		bus.setMarka(marka1);
		bus.setnrlini(nrlini1);
		
		managerbusy.addBus(bus);
		assertNotNull(managerbusy.getBusById(bus.getId()));
		
		managerbusy.deletebus(bus);
		assertNull(managerbusy.getBusById(bus.getId()));
		
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoDodaniu = listaBusow2.size();
		
		assertEquals(liczbaBusowprzedDodaniem, liczbaBusowpoDodaniu);
		managerbusy.deleteAllbusy();
	}
	
	@Test
	public void findAllbusyCheck() {
	
		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);
	
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedDodaniem = listaBusow.size();
	
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
	
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
	
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoDodaniu = listaBusow2.size();
		
	
		assertNotNull(listaBusow);
	
		
	
		assertEquals(liczbaBusowpoDodaniu, liczbaBusowprzedDodaniem+2);
		managerbusy.deleteAllbusy();
	}

	
	@Test
	public void editbusCheck() {
		
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedDodaniem = listaBusow.size();
		
		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);
		
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
		
		Bus bus3 = new Bus();
		bus3.setMarka(marka2);
		bus3.setnrlini(nrlini2);
		
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
		managerbusy.addBus(bus3);
		
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoDodaniu = listaBusow2.size();
		assertEquals(liczbaBusowprzedDodaniem+3, liczbaBusowpoDodaniu);
			
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini1);
		managerbusy.updatebus(bus2);
		
		ObjectId idbusy = bus2.getId();
		
		
		Bus pl = managerbusy.getBusById(idbusy);
		
		assertEquals(marka2, pl.getMarka());
		assertEquals(nrlini1, pl.getnrlini());
		
		assertEquals(marka2, bus1.getMarka());
		assertEquals(marka2, bus1.getnrlini());
		
		assertEquals(marka2, bus3.getMarka());
		assertEquals(nrlini2, bus3.getnrlini());
		
		managerbusy.deleteAllbusy();
	}
	
	
	@Test
	public void findbusBynrliniCheck() {
		
		List<Bus> listaBusow = managerbusy.getAllbusy();
		int liczbaBusowprzedDodaniem = listaBusow.size();
		
		Bus bus = new Bus();
		bus.setMarka(marka1);
		bus.setnrlini(nrliniUnique);
		
		managerbusy.addBus(bus);
		assertNotNull(managerbusy.getBusById(bus.getId()));
		
		List<Bus> listaBusow2 = managerbusy.getAllbusy();
		int liczbaBusowpoDodaniu = listaBusow2.size();
		
		assertEquals(liczbaBusowpoDodaniu, liczbaBusowprzedDodaniem+1);
		
		assertNotNull(managerbusy.getBusyByNrlini(bus.getnrlini()));
		List<Bus> pl = managerbusy.getBusyByNrlini(bus.getnrlini());
		Bus pl2 = pl.get(0);
		assertEquals(nrliniUnique, pl2.getnrlini());
		
		managerbusy.deleteAllbusy();
	}
	
	
	
}
