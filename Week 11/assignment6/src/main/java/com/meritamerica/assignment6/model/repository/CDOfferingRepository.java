package com.meritamerica.assignment6.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment6.model.CDOffering;

public interface CDOfferingRepository extends CrudRepository<CDOffering, Long>{
	List<CDOffering> findAll();
}
