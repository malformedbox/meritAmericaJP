package com.meritamerica.assignment7.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment7.model.AccountHolder;
import com.meritamerica.assignment7.model.CheckingAccount;

public interface CheckingAccountRepository extends CrudRepository<CheckingAccount, Long>{
	List<CheckingAccount> findAll();
	
	//@Query("select ca from CheckingAccount ca where ca.accHolder =:ah")
	List<CheckingAccount> findAllByaccHolder(AccountHolder ah); 
	//find_By is a keyword 
	//after that "accHolder" is the variable name is located in the type, CheckingAccount
	//where the parameter AccountHolder is instantiated
}
