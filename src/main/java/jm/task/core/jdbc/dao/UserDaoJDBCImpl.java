package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = new Util().getConnection().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users ("
                    + "  id int NOT NULL AUTO_INCREMENT,"
                    + "  name varchar(45) NOT NULL,"
                    + "  lastName varchar(45) NOT NULL,"
                    + "  age int NOT NULL,"
                    + "  PRIMARY KEY (id),"
                    + "  UNIQUE KEY id_UNIQUE (id)"
                    + ");");

        } catch (Throwable sqlException) {
            System.out.println("Ошибка создания таблицы");
            sqlException.printStackTrace();
        }

    }

    public void dropUsersTable()  {
        try (Statement statement = new Util().getConnection().createStatement()){
            statement.execute("DROP TABLE IF EXISTS users;");

        } catch (SQLException sqlException) {
            System.out.println("Ошибка удаления таблицы");
          //  sqlException.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES(?,  ?, ?);";
        try (PreparedStatement preparedStatement = new Util().getConnection().prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0)
                System.out.printf("User с имененм %s добавлен в базу данных \n", name);
            else
                System.out.println("User не добавлен в таблицу users");

        } catch (SQLException sqlException) {
            System.out.println("Ошибка вставки в таблицу");
          //  sqlException.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = new Util().getConnection().createStatement()){
            statement.executeUpdate("DELETE FROM users WHERE id = " + id + ";");
        } catch (SQLException sqlException) {
            System.out.println("Ошибка удаления из таблицы");
            sqlException.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = new Util().getConnection().createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users;");
            while (resultSet.next()) {
                    list.add(new User(
                            resultSet.getString(2),
                            resultSet.getString(3),
                            (byte) resultSet.getInt(4)));
           }
           return list;


        } catch (SQLException sqlException) {
            System.out.println("Ошибка получения списка Users");
          sqlException.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = new Util().getConnection().createStatement()){

            if (statement.execute("TRUNCATE TABLE users;"))
                System.out.println("Таблица очищена");
            else
                System.out.println("Записи таблицы очищены не были");

        } catch (SQLException e) {
            System.out.println("Ошибка очистки таблицы");
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
