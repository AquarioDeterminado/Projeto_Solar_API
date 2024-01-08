package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.exportInfo.IdAndErrorResponse;
import pt.iade.projetosolar.models.exportInfo.UserInfo;
import pt.iade.projetosolar.models.importInfo.AuthChangeUserString;
import pt.iade.projetosolar.models.importInfo.ChangeUserString;
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

    @PostMapping(path = "/change/password", produces = MediaType.APPLICATION_JSON_VALUE)
    private IdAndErrorResponse changePassword(@RequestBody AuthChangeUserString changeUserString) {
        User user = userRepository.findById(changeUserString.getUserID()).get();
        IdAndErrorResponse response = user.setPassword(changeUserString);
        if (response.getError().isEmpty())
            userRepository.save(user);
        return response;
    }

    @PostMapping(path = "/change/name", produces = MediaType.APPLICATION_JSON_VALUE)
    private IdAndErrorResponse changeName(@RequestBody ChangeUserString changeUserString) {
        User user = userRepository.findById(changeUserString.getUserId()).get();
        user.setName(changeUserString.getValue());
        userRepository.save(user);
        return new IdAndErrorResponse(user.getId());
    }

}

