package com.consis.test.edgar.repository;

import com.consis.test.edgar.domain.Person;
import com.consis.test.edgar.domain.TypeAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeAccountRepository extends JpaRepository<TypeAccount, Long> {
}
