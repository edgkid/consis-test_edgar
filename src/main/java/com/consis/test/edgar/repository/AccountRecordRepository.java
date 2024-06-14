package com.consis.test.edgar.repository;

import com.consis.test.edgar.domain.Account;
import com.consis.test.edgar.domain.AccountRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRecordRepository extends JpaRepository<AccountRecord, Long> {

    @Query("SELECT ar FROM AccountRecord ar WHERE ar.account.id = :id")
    List<AccountRecord> findAccountRecordsByAccountId(@Param("id") Long id);

}
