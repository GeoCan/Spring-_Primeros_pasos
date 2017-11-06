package com.plenumsoft.vuzee.mysql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.plenumsoft.vuzee.mysql.entities.User;

@Service
public interface UserService {
		List<User> getAll();
		User getUser(Long id);
		Long creater (User user);
		void update(User user);
		void delete(Long id);

}
