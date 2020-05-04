package com.rest.api.service;

import java.util.List;

import com.rest.api.model.Sensor;
import com.rest.api.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {
   
	@Autowired
	private SensorRepository sensorRepository;
	
	//Create operation
	public Sensor create(int floorNum, int roomNum, int sid,int smokeLevel,int co2Level) {
		return sensorRepository.save(new Sensor(floorNum, roomNum, sid,smokeLevel,co2Level));
	}

	//Retrieve operation
	public List<Sensor> getAll(){
		return sensorRepository.findAll();
	}

	public Sensor getBySid(int sid) {
		return sensorRepository.findBySid(sid);
	}

	//Update operation
	public Sensor update(int floorNum, int roomNum, int sid) {
		Sensor p = sensorRepository.findBySid(sid);
		p.setFloorNum(floorNum);
		p.setRoomNum(roomNum);
		return sensorRepository.save(p);
	}

	//Update operation
	public Sensor updateData(int sid,int smokeLevel,int co2Level) {
		Sensor p = sensorRepository.findBySid(sid);
		p.setSmokeLevel(smokeLevel);
		p.setCo2Level(co2Level);
		return sensorRepository.save(p);
	}

	//Delete operation
	public void deleteAll() {
		sensorRepository.deleteAll();
	}

	public void delete(int sid) {
		Sensor p = sensorRepository.findBySid(sid);
		sensorRepository.delete(p);
	}
}
