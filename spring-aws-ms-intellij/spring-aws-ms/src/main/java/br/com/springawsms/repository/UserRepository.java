package br.com.springawsms.repository;

import br.com.springawsms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u from User u where u.userName =: user")
    User findByUsername(@Param("userName" ) String userName);
}
