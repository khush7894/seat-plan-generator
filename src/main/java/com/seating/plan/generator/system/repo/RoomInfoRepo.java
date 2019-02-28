package com.seating.plan.generator.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seating.plan.generator.system.entity.RoomInfo;

public interface RoomInfoRepo extends JpaRepository<RoomInfo, Integer> {


	
}