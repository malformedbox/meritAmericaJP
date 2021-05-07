package com.meritamerica.assignment7.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment7.model.CDOffering;

public interface CDOfferingRepository extends CrudRepository<CDOffering, Long>{
	List<CDOffering> findAll();
}
