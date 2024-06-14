package com.consis.test.edgar.repository;

import com.consis.test.edgar.domain.AccountRecordType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRecordTypeRepository extends JpaRepository<AccountRecordType, Long> {
}
