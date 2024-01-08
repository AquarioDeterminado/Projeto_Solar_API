package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.exportInfo.UserInfo;
import pt.iade.projetosolar.models.importInfo.Id;


@RequestMapping(path = "/users")
@RestController
public class UsersInfo {

    private final UserRepository userRepository;

    public UsersInfo(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    private UserInfo getUser(@RequestBody Id userId) {
        User user = userRepository.findById(userId.id()).get();
        return new UserInfo(user);
    }
}

