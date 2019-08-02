package com.football.manager.service;

import com.football.manager.dao.UserDao;
import com.football.manager.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    @Transactional
    public void addUser(User user){
        dao.save(user);
    }

    @Transactional
    public User findUserByLogin(String login){return dao.findByLogin(login);}
}
