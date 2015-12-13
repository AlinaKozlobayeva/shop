package dao;

import entity.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alina on 03.11.2015.
 */
public interface UserDao extends GenericDao<String, User> {

    User findUserByLogin(String login);
    boolean insertUser(User user);

    @Override
    User findById(int id);

    @Override
    List<User> findAll();

    @Override
    boolean create(User user);

    @Override
    boolean update(User user);

    @Override
    boolean delete(String value);
}
