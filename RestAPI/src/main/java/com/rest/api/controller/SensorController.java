package com.rest.api.controller;

import java.util.List;

import com.rest.api.model.Sensor;
import com.rest.api.model.User;
import com.rest.api.service.SensorService;
import com.rest.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorController {

	@Autowired
	private SensorService sensorService;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/create")
	public String create(@RequestParam int floorNum, @RequestParam int roomNum, @RequestParam int sid,@RequestParam int smokeLevel,@RequestParam int co2Level) {
		Sensor p = sensorService.create(floorNum, roomNum, sid,smokeLevel,co2Level);
		return p.toString();
	}
	
	@RequestMapping("/get")
	public Sensor getSensor(@RequestParam int sid) {
		return sensorService.getBySid(sid);
	}

	@RequestMapping("/getAll")
	public List<Sensor> getAll(){
		return sensorService.getAll();
	}

	@RequestMapping("/update")
	public String update(@RequestParam int floorNum, @RequestParam int roomNum, @RequestParam int sid) {
		Sensor p = sensorService.update(floorNum, roomNum, sid);
		return p.toString();
	}

	@RequestMapping("/updateData")
	public String updateData(@RequestParam int sid,@RequestParam int smokeLevel,@RequestParam int co2Level) {
		Sensor p = sensorService.updateData(sid,smokeLevel,co2Level);
		return p.toString();
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam int sid) {
		sensorService.delete(sid);
		return "Deleted "+sid;
	}

	@RequestMapping ("/deleteAll")
	public String deleteAll() {
		sensorService.deleteAll();
		return "Deleted all records";
	}

	@RequestMapping("/createUser")
	public String createUser(@RequestParam String username, @RequestParam String password) {
		User p = userService.createUser(username,password);
		return p.toString();
	}

	@RequestMapping("/login")
	public boolean login(@RequestParam String username, String password) {
		return userService.getByUsernameAndPassword(username,password);
	}
	
}
