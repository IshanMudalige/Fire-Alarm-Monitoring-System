package com.rest.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Sensor {
	@Id
	String id;
	int floorNum;
	int roomNum;
	int    sid;
	int smokeLevel;
	int co2Level;
	
	public Sensor(int floorNum, int roomNum, int sid,int smokeLevel,int co2Level) {
		this.floorNum = floorNum;
		this.roomNum  = roomNum;
		this.sid       = sid;
		this.smokeLevel = smokeLevel;
		this.co2Level = co2Level;
		
	}

	public int getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(int floorNum) {
		this.floorNum = floorNum;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getSmokeLevel() {
		return smokeLevel;
	}

	public void setSmokeLevel(int smokeLevel) {
		this.smokeLevel = smokeLevel;
	}

	public int getCo2Level() {
		return co2Level;
	}

	public void setCo2Level(int co2Level) {
		this.co2Level = co2Level;
	}

	public String toString() {
		return "Sensor Floor Number:"+floorNum+" Room Number:"+roomNum+" Sensor ID:"+sid;
	}
}
