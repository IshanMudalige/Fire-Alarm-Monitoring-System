package com.rest.api.repository;

import com.rest.api.model.Sensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository<Sensor, String>{
   Sensor findBySid(int sid);
}
