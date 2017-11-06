package com.plenumsoft.vuzee.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.plenumsoft.vuzee.mysql.entities.User;
import com.plenumsoft.vuzee.mysql.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return (List<User>)repository.findAll();
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public Long creater(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
		return user.getId();
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		repository.save(user);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

}
