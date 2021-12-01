package main.socialnetwork.dao;

import java.util.List;
import main.socialnetwork.model.User;
import main.socialnetwork.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class HibernateUserDAO implements UserDAO {

    @Override
    public void addUser(User user) {
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }
    }

    @Override
    public List<User> getUserByName(String name) {
        List<User> userList = null;
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String q = "from User where name like :inputName";

            Query<User> query = session.createQuery(q);

            query.setParameter("inputName", name);

            userList = query.list();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.close();
        }

        return userList;

    }

}
