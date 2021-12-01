package main.socialnetwork.action;

import com.opensymphony.xwork2.ActionSupport;
import main.socialnetwork.dao.HibernateUserDAO;
import main.socialnetwork.model.User;

public class RegisterAction extends ActionSupport {

    private User user;

    @Override
    public void validate() {
        if (user.getName().isEmpty()) {
            addFieldError("user.name", "Name cannot be empty!");
            return;
        }
        if (user.getName().length() > 45) {
            addFieldError("user.name", "Name too long!");
            return;
        }

        HibernateUserDAO dao = new HibernateUserDAO();

        if (!dao.getUserByName(user.getName()).isEmpty()) {
            addFieldError("user.name", "Name already exists!");
            return;
        }

        if (user.getPassword().length() > 45) {
            addFieldError("user.password", "Password too long!");
        }

    }

    @Override
    public String execute() {
        HibernateUserDAO dao = new HibernateUserDAO();
        dao.addUser(user);
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
