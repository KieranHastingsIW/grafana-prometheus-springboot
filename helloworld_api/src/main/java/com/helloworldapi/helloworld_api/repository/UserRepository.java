package com.helloworldapi.helloworld_api.repository;



import org.springframework.data.jpa.repository.JpaRepository;


import com.helloworldapi.helloworld_api.Entity.User;



public interface UserRepository extends JpaRepository<User, Long>{

}
