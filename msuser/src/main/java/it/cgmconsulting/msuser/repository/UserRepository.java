package it.cgmconsulting.msuser.repository;


import it.cgmconsulting.msuser.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.username = ?1")
    Optional<Users> findByUsername(String username);
}
