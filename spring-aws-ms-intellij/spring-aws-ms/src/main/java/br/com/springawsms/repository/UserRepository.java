package br.com.springawsms.repository;

import br.com.springawsms.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users,Long> {
    @Query("SELECT u from Users u where u.userName =: user")
    Users findByUsername(@Param("userName" ) String userName);
}
