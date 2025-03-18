package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.model.Car;
import com.crud.repository.CarRepository;

@Service("CarService")
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	@Override
	public List<Car> getAllCars() {
		return carRepository.findAll();
	}

	@Override
	public Optional<Car> getCarById(Integer id) {
		return carRepository.findById(id);
	}

	@Override
	public void deleteCarById(Integer id) {
		carRepository.deleteById(id);
	}

	@Override
	public void insertCar(Car car) {
		if (car.getId() == null) {
			carRepository.save(car);
		}
	}

	@Override
	public void updateCar(Car car) {
		if (car.getId() != null) {
			carRepository.save(car);
		}
	}

}
