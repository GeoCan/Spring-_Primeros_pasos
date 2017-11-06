package com.plenumsoft.vuzee.mysql.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.plenumsoft.vuzee.mysql.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsername(String name);
}
