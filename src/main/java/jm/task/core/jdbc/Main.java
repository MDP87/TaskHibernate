package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
      // userService.dropUsersTable();
   //    userService.cleanUsersTable();
        User user = new User();
        user.setName("NAME1");
        user.setLastName("LASTNAME1");
        user.setAge((byte) 11);
        User user2 = new User();
        user2.setName("NAME2");
        user2.setLastName("LASTNAME2");
        user2.setAge((byte) 22);

        User user3 = new User();
        user3.setName("NAME3");
        user3.setLastName("LASTNAME3");
        user3.setAge((byte) 33);
        User user4 = new User();
        user4.setName("NAME4");
        user4.setLastName("LASTNAME4");
        user4.setAge((byte) 44);
//
        userService.createUsersTable();
        userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        List<User> list = userService.getAllUsers();
        list.stream().forEach(x -> System.out.println(x));

        //userService.removeUserById(1L);
//        list = userService.getAllUsers();
//        System.out.println(list.get(0).getName() + " " + list.get(0).getLastName() + " " + list.get(0).getAge());
//        userService.cleanUsersTable();
   //     userService.dropUsersTable();

    }
}
