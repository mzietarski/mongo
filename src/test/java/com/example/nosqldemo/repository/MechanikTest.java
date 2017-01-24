package com.example.nosqldemo.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.nosqldemo.service.BusManager;
import com.example.nosqldemo.service.MechanikManager;
import com.example.nosqldemo.domain.Bus;
import com.example.nosqldemo.domain.Mechanik;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
public class MechanikTest {
	
	@Autowired
	BusManager managerbusy;
	@Autowired
	MechanikManager managerMechanik;
	
	//bus
	private final String marka1 = "Solaris";
	private final String marka2 = "Daewoo";
	
	private final int nrlini1 = 5;
	private final int nrlini2 = 7;
	
	
	private int nrliniUnique = 9;
	
	private final int nrliniUnique2 =2;
	private final int kryterium = 1;
	
	//Mechanik
	private final String imieU1 = "Bartek";
	private final String imieU2 = "Antek";
	
	private final int liczbaObiektow1 = 2;
	private final int liczbaObiektow2 = 4; 
	private final int liczbaObiektowUnique = 6;
	
	private final String ImieUnique = "Roman";

	
	
	@Test
	public void checkAdding(){

		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);
		managerbusy.addBus(bus1);
		List<Bus> busyTab = new ArrayList<Bus>();
		busyTab.add(bus1);
		
		Mechanik mechanik = new Mechanik();
		mechanik.setImie(imieU1);
		mechanik.setLiczbaObiektow(liczbaObiektow1);
		mechanik.setbusy(busyTab);
		
		List<Mechanik> listaMechanik = managerMechanik.getAllMechanik();
		int liczbaMechanikprzedDodaniem = listaMechanik.size();
		
		
		managerMechanik.addNewMechanik(mechanik);
		
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikpoDodaniu = listaMechanik2.size();
		//System.out.println(liczbaKoszowpoDodaniu);
		
		ObjectId id = mechanik.getId();
		Mechanik uk = managerMechanik.getMechanikById(id);
		
		assertEquals(uk.getImie(), mechanik.getImie());
		assertEquals(uk.getLiczbaObiektow(), mechanik.getLiczbaObiektow());
		assertEquals(uk.getbusy().size(), mechanik.getbusy().size());
		
		assertEquals(uk.getbusy().get(0).getMarka(), mechanik.getbusy().get(0).getMarka());
		assertEquals(uk.getbusy().get(0).getnrlini(), mechanik.getbusy().get(0).getnrlini());
		
		assertEquals(liczbaMechanikprzedDodaniem+1, liczbaMechanikpoDodaniu);
		
		managerMechanik.deleteAllMechanik();
	}
	
	
	@Test
	public void deleteMechanikCheck() {
		
		Bus bus1 = new Bus();
		bus1.setMarka(marka1);
		bus1.setnrlini(nrlini1);
		managerbusy.addBus(bus1);
		
		Bus bus2 = new Bus();
		bus2.setMarka(marka1);
		bus2.setnrlini(nrlini1);
		managerbusy.addBus(bus2);
		
		List<Bus> busyTab = new ArrayList<Bus>();
		busyTab.add(bus1);
		busyTab.add(bus2);
		
		Mechanik mechanik = new Mechanik();
		mechanik.setImie(imieU1);
		mechanik.setLiczbaObiektow(liczbaObiektow1);
		mechanik.setbusy(busyTab);
		managerMechanik.addNewMechanik(mechanik);
		
		List<Mechanik> listaMechanik = managerMechanik.getAllMechanik();
		int liczbaMechanikprzedUsunieciem = listaMechanik.size();
		
		ObjectId idMechanik = mechanik.getId();
		managerMechanik.deleteMechanik(mechanik);
		
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikpoUsunieciu = listaMechanik2.size();
		
		assertEquals(liczbaMechanikprzedUsunieciem, liczbaMechanikpoUsunieciu+1);
		assertEquals(null, managerMechanik.getMechanikById(idMechanik));
		
		assertEquals(null, managerbusy.getBusById(bus1.getId()));
		assertEquals(null, managerbusy.getBusById(bus2.getId()));
		
		managerMechanik.deleteAllMechanik();
	}
	
	
	@Test
	public void deleteAllMechanikCheck() {
		List<Mechanik> listaMechanik = managerMechanik.getAllMechanik();
		int liczbaMechanikPrzedDodaniem = listaMechanik.size();
		
		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);
	
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
	
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
		
		List<Bus> busyTab1 = new ArrayList<Bus>();
		busyTab1.add(bus1);
		
		List<Bus> busyTab2 = new ArrayList<Bus>();
		busyTab1.add(bus2);
		
		Mechanik mechanik1 = new Mechanik();
		mechanik1.setImie(imieU1);
		mechanik1.setLiczbaObiektow(liczbaObiektow1);
		mechanik1.setbusy(busyTab1);
		managerMechanik.addNewMechanik(mechanik1);
		
		Mechanik mechanik2 = new Mechanik();
		mechanik2.setImie(imieU1);
		mechanik2.setLiczbaObiektow(liczbaObiektow1);
		mechanik2.setbusy(busyTab2);
		managerMechanik.addNewMechanik(mechanik2);
	
		assertNotNull(listaMechanik);
		
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikPoDodaniu = listaMechanik2.size();
	
		assertEquals(liczbaMechanikPoDodaniu, liczbaMechanikPrzedDodaniem+2);
		
		managerMechanik.deleteAllMechanik();
		
		List<Mechanik> listaMechanik3 = managerMechanik.getAllMechanik();
		int liczbaMechanikpoUsunieciu = listaMechanik3.size();
		assertEquals(0, liczbaMechanikpoUsunieciu);
	}
	
	
	@Test
	public void findKoszCheck() {
		
		List<Mechanik> listaMechanik = managerMechanik.getAllMechanik();
		int liczbaMechanikprzedDodaniem = listaMechanik.size();
		
		Bus bus = new Bus();
		bus.setMarka(marka1);
		bus.setnrlini(nrlini1);
		managerbusy.addBus(bus);
		
		List<Bus> busyTab = new ArrayList<Bus>();
		busyTab.add(bus);
		
		Mechanik mechanik = new Mechanik();
		mechanik.setImie(imieU1);
		mechanik.setLiczbaObiektow(liczbaObiektow1);
		mechanik.setbusy(busyTab);
		managerMechanik.addNewMechanik(mechanik);
		
		assertNotNull(managerMechanik.getMechanikById(mechanik.getId()));
		assertNotNull(managerbusy.getBusById(bus.getId()));
		
		managerMechanik.deleteMechanik(mechanik);
		assertNull(managerbusy.getBusById(bus.getId()));
		assertNull(managerMechanik.getMechanikById(mechanik.getId()));
		
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikPoUsunieciu = listaMechanik2.size();
		
		assertEquals(liczbaMechanikprzedDodaniem, liczbaMechanikPoUsunieciu);
		
		managerMechanik.deleteAllMechanik();
	}
	
	
	@Test
	public void findAllMechanikCheck() {
	
		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);
	
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
	
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
	
		List<Bus> busyTab = new ArrayList<Bus>();
		busyTab.add(bus1);
		busyTab.add(bus2);
	
		Mechanik mechanik = new Mechanik();
		mechanik.setImie(imieU2);
		mechanik.setLiczbaObiektow(liczbaObiektow2);
		mechanik.setbusy(busyTab);

		List<Mechanik> listaMechanik = managerMechanik.getAllMechanik();
		int liczbaMechanikprzedDodaniem = listaMechanik.size();
	
		managerMechanik.addNewMechanik(mechanik);
	
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikpoDodaniu = listaMechanik2.size();
		
	
		assertNotNull(listaMechanik);
	
		
	
		assertEquals(liczbaMechanikpoDodaniu, liczbaMechanikprzedDodaniem+1);
		
		managerMechanik.deleteAllMechanik();
	}
	
	@Test
	public void editMechanikCheck() {
		
		List<Mechanik> listaMechanik1 = managerMechanik.getAllMechanik();
		int liczbaMechanikprzedDodaniem = listaMechanik1.size();
		
		Bus bus1 = new Bus();
		bus1.setMarka(marka2);
		bus1.setnrlini(nrlini2);

		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
				
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
		
		List<Bus> busyTab1 = new ArrayList<Bus>();
		busyTab1.add(bus1);
		
		Mechanik mechanik1 = new Mechanik();
		mechanik1.setImie(imieU1);
		mechanik1.setLiczbaObiektow(liczbaObiektow1);
		mechanik1.setbusy(busyTab1);
		managerMechanik.addNewMechanik(mechanik1);
		
		List<Bus> busyTab2 = new ArrayList<Bus>();
		busyTab2.add(bus2);
		
		Mechanik mechanik2 = new Mechanik();
		mechanik2.setImie(imieU2);
		mechanik2.setLiczbaObiektow(liczbaObiektow2);
		mechanik2.setbusy(busyTab2);
		managerMechanik.addNewMechanik(mechanik2);

		
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikpoDodaniu = listaMechanik2.size();
		assertEquals(liczbaMechanikprzedDodaniem+2, liczbaMechanikpoDodaniu);
		
		
		mechanik2.setImie(marka1);
		mechanik2.setLiczbaObiektow(liczbaObiektow1);
		
		managerMechanik.updateMechanik(mechanik2);
		
		ObjectId idMechanik = mechanik2.getId();
		
		Mechanik uk = managerMechanik.getMechanikById(idMechanik);
		
		assertEquals(imieU1, uk.getImie());
		assertEquals(liczbaObiektow1, uk.getLiczbaObiektow());
		
		managerMechanik.deleteAllMechanik();
		
	}
	
	
	@Test
	public void findMechanikByLiczbaObiektow() {
		
		List<Mechanik> listaMechanik1 = managerMechanik.getAllMechanik();
		int liczbaMechanikprzedDodaniem = listaMechanik1.size();
		
		Bus bus = new Bus();
		bus.setMarka(marka1);
		bus.setnrlini(nrliniUnique);	
		managerbusy.addBus(bus);
		
		List<Bus> busyTab1 = new ArrayList<Bus>();
		busyTab1.add(bus);
		
		Mechanik mechanik1 = new Mechanik();
		mechanik1.setImie(imieU1);
		mechanik1.setLiczbaObiektow(liczbaObiektowUnique);
		mechanik1.setbusy(busyTab1);
		managerMechanik.addNewMechanik(mechanik1);
		
		
		assertNotNull(managerbusy.getBusById(bus.getId()));
		assertNotNull(managerMechanik.getMechanikById(mechanik1.getId()));
		
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikpoDodaniu = listaMechanik2.size();
		
		assertEquals(liczbaMechanikpoDodaniu, liczbaMechanikprzedDodaniem+1);
		
		assertNotNull(managerMechanik.getMechanikByLiczbaObiektow(mechanik1.getLiczbaObiektow()));
		List<Mechanik> uk = managerMechanik.getMechanikByLiczbaObiektow(mechanik1.getLiczbaObiektow());
		Mechanik uk2 = uk.get(0);
		assertEquals(liczbaObiektowUnique, uk2.getLiczbaObiektow());
		
		managerMechanik.deleteAllMechanik();
	}
	
	@Test
	public void checkPobranieWszystkichObiektowZMechanik() {
		Bus bus1 = new Bus();
		bus1.setMarka(marka1);
		bus1.setnrlini(nrlini1);
		
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
				
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
		
		List<Bus> busyTab1 = new ArrayList<Bus>();
		busyTab1.add(bus1);
		busyTab1.add(bus2);
		
		Mechanik mechanik1 = new Mechanik();
		mechanik1.setImie(imieU1);
		mechanik1.setLiczbaObiektow(liczbaObiektow1);
		mechanik1.setbusy(busyTab1);
		managerMechanik.addNewMechanik(mechanik1);
		
		List<Bus> listaBusowWMechanik = managerMechanik.getbusyInMechanik(mechanik1);
		assertNotNull(listaBusowWMechanik);
		
		assertEquals(listaBusowWMechanik.size(), 2);
		
		Bus pl1 = listaBusowWMechanik.get(0);
		Bus pl2 = listaBusowWMechanik.get(1);
		
		assertEquals(pl1.getMarka(), marka1);
		assertEquals(pl1.getnrlini(), nrlini1);
		
		assertEquals(pl2.getMarka(), marka2);
		assertEquals(pl2.getnrlini(), nrlini2);
		
		managerMechanik.deleteAllMechanik();
	}

	
	@Test
	public void getMechanikByNazwaLiczbaObiektowCheck() {
		List<Mechanik> listaMechanik = managerMechanik.getAllMechanik();
		int liczbaMechanikPrzedDodaniem = listaMechanik.size();
		
		Bus bus1 = new Bus();
		bus1.setMarka(marka1);
		bus1.setnrlini(nrlini1);
	
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrlini2);
	
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
		
		List<Bus> busyTab1 = new ArrayList<Bus>();
		busyTab1.add(bus1);
		
		List<Bus> busyTab2 = new ArrayList<Bus>();
		busyTab1.add(bus2);
		
		Mechanik mechanik1 = new Mechanik();
		mechanik1.setImie(imieU1);
		mechanik1.setLiczbaObiektow(liczbaObiektow1);
		mechanik1.setbusy(busyTab1);
		managerMechanik.addNewMechanik(mechanik1);
		
		Mechanik mechanik2 = new Mechanik();
		mechanik2.setImie(ImieUnique);
		mechanik2.setLiczbaObiektow(liczbaObiektowUnique);
		mechanik2.setbusy(busyTab2);
		managerMechanik.addNewMechanik(mechanik2);
	
		List<Mechanik> listaMechanik2 = managerMechanik.getAllMechanik();
		int liczbaMechanikPoDodaniu = listaMechanik2.size();
	
		assertEquals(liczbaMechanikPoDodaniu, liczbaMechanikPrzedDodaniem+2);
		
		assertNotNull(managerMechanik.getMechanikByImieLiczbaObiektow(ImieUnique, liczbaObiektowUnique));
		List<Mechanik> pl1 = managerMechanik.getMechanikByImieLiczbaObiektow(ImieUnique, liczbaObiektowUnique);
		int length1 = pl1.size();
		assertEquals(1, length1);
		
		Mechanik mechanik3 = new Mechanik();
		mechanik3.setImie(ImieUnique);
		mechanik3.setLiczbaObiektow(liczbaObiektowUnique);
		mechanik3.setbusy(busyTab2);
		managerMechanik.addNewMechanik(mechanik3);
		
		assertNotNull(managerMechanik.getMechanikByImieLiczbaObiektow(ImieUnique, liczbaObiektowUnique));
		List<Mechanik> pl2 = managerMechanik.getMechanikByImieLiczbaObiektow(ImieUnique, liczbaObiektowUnique);
		int length2 = pl2.size();
		assertEquals(2, length2);
		
		List<Mechanik> pl3 = managerMechanik.getMechanikByImieLiczbaObiektow(imieU1, liczbaObiektowUnique);
		int length3 = pl3.size();
		assertEquals(0, length3);
		
		managerMechanik.deleteAllMechanik();
	}
	
	@Test
	public void deleteBusyWMechanikuByNrliniCheck() {
		Bus bus1 = new Bus();
		bus1.setMarka(marka1);
		bus1.setnrlini(nrlini1);
		
		Bus bus2 = new Bus();
		bus2.setMarka(marka2);
		bus2.setnrlini(nrliniUnique2);
		
		Bus bus3 = new Bus();
		bus3.setMarka(marka1);
		bus3.setnrlini(nrliniUnique2);
				
		managerbusy.addBus(bus1);
		managerbusy.addBus(bus2);
		managerbusy.addBus(bus3);
		
		List<Bus> busyTab1 = new ArrayList<Bus>();
		busyTab1.add(bus1);
		busyTab1.add(bus2);
		busyTab1.add(bus3);
		
		Mechanik mechanik1 = new Mechanik();
		mechanik1.setImie(imieU1);
		mechanik1.setLiczbaObiektow(liczbaObiektow1);
		mechanik1.setbusy(busyTab1);
		managerMechanik.addNewMechanik(mechanik1);
		
		List<Bus> listaBusowMechanik = managerMechanik.getbusyInMechanik(mechanik1);
		assertNotNull(listaBusowMechanik );
		
		managerMechanik.deleteBusyWMechanikByNrlini(mechanik1, kryterium);
		
		assertEquals(null,managerbusy.getBusById(bus1.getId()));
		assertEquals(null, managerbusy.getBusById(bus2.getId()));
		assertEquals(null, managerbusy.getBusById(bus3.getId()));
		
	}
	
	
}
