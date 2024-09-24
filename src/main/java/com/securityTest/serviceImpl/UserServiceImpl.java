package com.securityTest.serviceImpl;

import com.securityTest.entity.User;
import com.securityTest.repository.UserRepo;
import com.securityTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User saveUser(User user) {
       return  userRepo.save(user);
    }
}
