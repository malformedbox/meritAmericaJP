package com.meritamerica.assignment7.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.model.Role;
import com.meritamerica.assignment7.model.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleEnum name);
}
