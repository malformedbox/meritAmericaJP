package com.meritamerica.assignment7.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.meritamerica.assignment7.model.AccountHolder;
import com.meritamerica.assignment7.model.CDAccount;
import com.meritamerica.assignment7.model.CheckingAccount;

public interface CDAccountRepository extends CrudRepository<CDAccount, Long>{
	List<CDAccount> findAll();
	List<CDAccount> findAllByaccHolder(AccountHolder ah);
}
