package com.meritamerica.assignment6.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment6.model.AccountHolder;
import com.meritamerica.assignment6.model.CheckingAccount;

public interface CheckingAccountRepository extends CrudRepository<CheckingAccount, Long>{
	List<CheckingAccount> findAll();
	List<CheckingAccount> findAllByaccHolder(AccountHolder ah); 
	//findAllBy is a keyword, 
	//after that "accHolder" is the variable name is located in the type, CheckingAccount
	//where the parameter AccountHolder is instantiated
}
