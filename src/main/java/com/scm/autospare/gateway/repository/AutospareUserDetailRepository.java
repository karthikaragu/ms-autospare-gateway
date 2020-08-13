package com.scm.autospare.gateway.repository;

import com.scm.autospare.gateway.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutospareUserDetailRepository extends JpaRepository<UserDetail,Integer> {

    Optional<UserDetail> findByUserName(String name);

}
