package com.meritamerica.assignment7.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.meritamerica.assignment7.model.AccountHolder;

public interface AccountHolderRepository extends CrudRepository<AccountHolder, Long> {
	List<AccountHolder> findAll();
	//List<AccountHolder> findAllById(AccountHolder ah);
	Optional<AccountHolder> findByUsername(String username);
}
