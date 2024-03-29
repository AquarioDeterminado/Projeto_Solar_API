package pt.iade.projetosolar.controllers;

import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.users.User;

public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String changeName(String name, int userId) {
        userRepository.findById(userId).get().setName(name);
        return name;
    }

}
