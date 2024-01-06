package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.users.User;

import java.sql.Date;
import java.util.ArrayList;

public class LogInController {
    private final ArrayList<LogInError> error = new ArrayList<>();
    private final UserRepository userRepository;

    LogInController (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public int getUserId(String email) {
        User user = userRepository.findByEmail(email);
        return user.getId();
    }

    public boolean checkCredentials(int id, String password) {
        User user = userRepository.findByIdAndPassword(id, password);
        return user != null;
    }

    public User createNewUser(String userName, String email, String phone, Date bDate, String password) {
        User user = null;
        if (checkInfo(email, password)) {
            user = new User(userName, email, password, phone, bDate);
            userRepository.save(user);
        }
        return user;
    }

    public boolean checkInfo(String email, String password) {
        if (userExists(email))
            error.add(LogInError.USER_ALREADY_EXISTS);
        if (verifyPassword(password))
            error.add(LogInError.INVALID_PASSWORD);
        if (verifyEmail(email))
            error.add(LogInError.INVALID_EMAIL);

        return error.isEmpty();
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public static boolean verifyPassword(String password) {
        CharSequence specialChars = "!@#$%^&*()_+";
        return  !passwordContainsSpecialChars(password) || password.length() < 6;
    }

    private static boolean passwordContainsSpecialChars(String password) {
        return password.contains("!")
                || password.contains("@")
                || password.contains("#")
                || password.contains("$")
                || password.contains("%")
                || password.contains("^")
                || password.contains("&")
                || password.contains("*")
                || password.contains("(")
                || password.contains(")")
                || password.contains("_")
                || password.contains("+");
    }

    public boolean verifyEmail(String email) {
        return !email.contains("@") || !email.contains(".");
    }

    public boolean changePassword(int userId, String newPassword, String oldPassword) {
        if(checkCredentials(userId, oldPassword)) {
            if (!verifyPassword(newPassword)) {
                error.add(LogInError.INVALID_PASSWORD);
            } else {
                userRepository.findById(userId)
                        .map(user -> {
                            user.setPassword(newPassword);
                            return userRepository.save(user);
                        });
                return true;
            }
        }
        return false;
    }

    public ArrayList<LogInError> getError() {
        ArrayList<LogInError> error = this.error;
        this.error.clear();
        return error;
    }
}
