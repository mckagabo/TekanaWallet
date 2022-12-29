package com.tekanawallet.tekanawallet.registration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tekanawallet.tekanawallet.registration.model.Balance;
import com.tekanawallet.tekanawallet.registration.model.User;

public interface BalanceRepository extends JpaRepository<Balance,UUID>{
	Balance findByUser(User user);

}
