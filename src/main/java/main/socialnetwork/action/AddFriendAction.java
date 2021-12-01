package main.socialnetwork.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import java.util.Set;
import main.socialnetwork.dao.HibernateUserDAO;
import main.socialnetwork.dao.UserDAO;
import main.socialnetwork.model.User;
import org.apache.struts2.interceptor.SessionAware;

public class AddFriendAction extends ActionSupport implements SessionAware{

    //povezano sa key="name" u login_success.jsp
    private String name;

    private Map<String, Object> userSession;

    @Override
    public void validate() {
        UserDAO dao = new HibernateUserDAO();

        List<User> users = dao.getUserByName(name);

        User currentLoggedUser = (User) userSession.get("user");

        if (name.isEmpty()) {
            addFieldError("name", "Name cannot be empty!");
            return;
        }

        if (users.isEmpty()) {
            addFieldError("name", "User does not exist!");
            return;
        }

        //if we try to add ourselves
        if (currentLoggedUser.getName().equals(users.get(0).getName())) {
            addFieldError("name", "You cannot add yourself!");
            return;
        }

        //if we try to add person that is already our friend
        for (User user : currentLoggedUser.getFriends()) {
            if (user.getName().equals(users.get(0).getName())) {
                addFieldError("name", "Already your friend!");
                return;
            }
        }
    }

    @Override
    public String execute() {
        UserDAO dao = new HibernateUserDAO();

        List<User> users = dao.getUserByName(name);

        User currentLoggedUser = (User) userSession.get("user");

        //getting friends and adding new one
        Set<User> friends = currentLoggedUser.getFriends();
        friends.add(users.get(0));
        currentLoggedUser.setFriends(friends);

        dao.updateUser(currentLoggedUser);
        
        return SUCCESS;
    }

    public Map<String, Object> getUserSession() {
        return userSession;
    }

    public void setUserSession(Map<String, Object> userSession) {
        this.userSession = userSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.userSession = session;
    }

}
