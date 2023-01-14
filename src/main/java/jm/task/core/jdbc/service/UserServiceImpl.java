package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
   // UserDaoJDBCImpl userDaoJDBSorHibernate = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoJDBSorHibernate = new UserDaoHibernateImpl();
    public void createUsersTable() {
        userDaoJDBSorHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBSorHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoJDBSorHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoJDBSorHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoJDBSorHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBSorHibernate.cleanUsersTable();
    }
}
