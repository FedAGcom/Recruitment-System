package com.fedag.recruitmentSystem.repository;

import com.fedag.recruitmentSystem.model.EmailCode;
import com.fedag.recruitmentSystem.model.EmailCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmailCodeRepository extends JpaRepository<EmailCode, EmailCodeId> {
    @Query(value = "select email, code, type " +
            "from email_code v " +
            "where v.code = :code " +
            "and v.type = 'ACTIVATION' ",
            nativeQuery = true)
    Optional<EmailCode> findActivationByCode(@Param("code") String code);

    @Query(value = "select email, code, type " +
            "from email_code v " +
            "where v.email = :email " +
            "and v.type = 'ACTIVATION' ",
            nativeQuery = true)
    Optional<EmailCode> findActivationByEmail(@Param("email") String email);

    @Query(value = "select email, code, type " +
            "from email_code v " +
            "where v.code = :code " +
            "and v.type = 'PASS_CHANGE' ",
            nativeQuery = true)
    Optional<EmailCode> findPassChangeByCode(@Param("code") String code);

    @Query(value = "select email, code, type " +
            "from email_code v " +
            "where v.email = :email " +
            "and v.type = 'PASS_CHANGE' ",
            nativeQuery = true)
    Optional<EmailCode> findPassChangeByEmail(@Param("email") String email);

}
