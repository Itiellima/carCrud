package com.crud.service;

import java.util.List;
import java.util.Optional;

import com.crud.model.Car;

public interface CarService {

	List <Car> getAllCars();
	Optional<Car> getCarById(Integer id);
	void deleteCarById(Integer id);
	void updateCar(Car car);
	void insertCar(Car car);
	
}
