package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {



    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("First", "User", ((byte) 10));
        userService.saveUser("Second", "User", ((byte) 15));
        userService.saveUser("Third", "User", ((byte) 20));
        userService.saveUser("Fourth", "User", ((byte) 25));
        for (User user: userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
