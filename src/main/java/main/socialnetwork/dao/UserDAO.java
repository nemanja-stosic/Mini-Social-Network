package main.socialnetwork.dao;

import java.util.List;
import main.socialnetwork.model.User;

public interface UserDAO {

    void addUser(User user);
    void updateUser(User user);

    List<User> getUserByName(String name);
}
