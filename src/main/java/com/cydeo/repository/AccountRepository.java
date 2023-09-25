package com.cydeo.repository;

import com.cydeo.entity.Account;
import com.cydeo.enums.AccountStatus;
import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

        List<Account> findAllByAccountStatus(AccountStatus accountStatus);

}