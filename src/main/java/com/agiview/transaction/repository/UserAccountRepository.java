package com.agiview.transaction.repository;


import com.agiview.transaction.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}
