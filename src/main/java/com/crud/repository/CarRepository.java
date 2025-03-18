package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.model.Car;

@Repository("CarRepository")
public interface CarRepository extends JpaRepository<Car, Integer> {

}
