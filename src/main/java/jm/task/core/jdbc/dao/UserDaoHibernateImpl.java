package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import org.hibernate.query.NativeQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users ("
                    + "  id int NOT NULL AUTO_INCREMENT,"
                    + "  name varchar(45) NOT NULL,"
                    + "  lastName varchar(45) NOT NULL,"
                    + "  age int NOT NULL,"
                    + "  PRIMARY KEY (id),"
                    + "  UNIQUE KEY id_UNIQUE (id)"
                    + ");").executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            System.out.println("Ошибк создания таблицы");
            e.printStackTrace();
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users;").executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            System.out.println("Ошибк удаления таблицы");
            e.printStackTrace();
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (SessionException e) {
            System.out.println("Ошибка сохранения User ");
        }
    }
    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (SessionException e) {
            System.out.println("Ошибка удаления User id = " + id);
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            list = new ArrayList<>(criteria.list());
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Ошибка получения списка User");
        }
        return list;
    }
    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users;").executeUpdate();
            transaction.commit();
        } catch (SessionException e) {
            System.out.println("Ошибка очистки таблицы");
        }
    }
}
