package com.seating.plan.generator.system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.seating.plan.generator.system.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	
}
