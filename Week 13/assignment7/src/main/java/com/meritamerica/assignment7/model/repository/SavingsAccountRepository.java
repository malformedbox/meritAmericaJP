package com.meritamerica.assignment7.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment7.model.AccountHolder;
import com.meritamerica.assignment7.model.CheckingAccount;
import com.meritamerica.assignment7.model.SavingsAccount;

public interface SavingsAccountRepository extends CrudRepository<SavingsAccount, Long>{
	List<SavingsAccount> findAll();
	List<SavingsAccount> findAllByaccHolder(AccountHolder ah);
}
