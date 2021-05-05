package com.meritamerica.assignment6.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment6.model.AccountHoldersContactDetails;

public interface AccountHoldersContactDetailsRepository extends CrudRepository<AccountHoldersContactDetails, Long>{
	List<AccountHoldersContactDetails> findAll();
	//List<AccountHoldersContactDetails> findAllByAccountHoldersContactDetails(AccountHoldersContactDetails ahDetails);
}
