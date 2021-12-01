package main.socialnetwork.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import main.socialnetwork.dao.HibernateUserDAO;
import main.socialnetwork.model.User;
import org.apache.struts2.interceptor.SessionAware;

public class LoginAction extends ActionSupport implements SessionAware {

    //key will access this user variable from struts form
    private User user;

    private Map<String, Object> userSession;

    //this method will be executed first and validate data and then execute() method will be then executed.
    @Override
    public void validate() {
        System.out.println("validate");
        HibernateUserDAO dao = new HibernateUserDAO();

        if (user.getName().isEmpty()) {
            addFieldError("user.name", "Name cannot be blank!");
            return;
        }

        List<User> users = dao.getUserByName(user.getName());

        if (users.isEmpty()) {
            addFieldError("user.name", "User not found!");
            return;
        }

        if (!users.get(0).getPassword().equals(user.getPassword())) {
            addFieldError("user.password", "Incorrect Password!");
            return;
        }

        this.user = users.get(0);
        //puts user into session so that we can later get it from the view
        userSession.put("user", this.user);

    }

    //struts by default executes this method
    @Override
    public String execute() {
        System.out.println("execute");
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Object> getUserSession() {
        return userSession;
    }

    public void setUserSession(Map<String, Object> userSession) {
        this.userSession = userSession;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.userSession = session;
    }

}
