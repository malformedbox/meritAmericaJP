package com.meritamerica.assignment7.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findAll();
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findById(Long id);
	
	Boolean existsByUsername(String username);
}
