package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crud.model.Car;
import com.crud.service.CarService;

public class CarDbRestController {

	@Autowired
	CarService carService;
	
	@RequestMapping(value = "/cars/{id}", method = RequestMethod.GET) 
	public List<Car> getAllCars(){
		return carService.getAllCars();
	}
	
	@RequestMapping(value = "/cars", method = RequestMethod.POST)
	public void addCar(@RequestBody Car car) {
		carService.insertCar(car);
	}
	
	@RequestMapping(value = "/cars", method = RequestMethod.PUT)
	public void updateCar(@RequestBody Car car) {
		carService.updateCar(car);
	}
	
	@RequestMapping(value = "/cars/{id}", method = RequestMethod.DELETE)
	public void deleteCar(@PathVariable Integer id) {
		carService.deleteCarById(id);
	}
	
	
}
