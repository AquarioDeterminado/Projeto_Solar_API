package pt.iade.projetosolar.views;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.exportInfo.IdAndErrorResponse;
import pt.iade.projetosolar.models.importInfo.LogInInfo;

@RestController
@RequestMapping(path = "/login")
public class LogIn {

    private final UserRepository userRepository;

    public LogIn(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/checkCredentials", produces = "application/json")
    public IdAndErrorResponse checkCredentials(@RequestBody LogInInfo logInInfo) {
        String email = logInInfo.getEmail();
        String password = logInInfo.getPassword();

        IdAndErrorResponse idAndErrorResponse = null;
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user == null) {
            idAndErrorResponse = new IdAndErrorResponse("User does not exist");
        } else if (!user.isActive()) {
            idAndErrorResponse = new IdAndErrorResponse("User is not active");
        } else {
            idAndErrorResponse = new IdAndErrorResponse(user.getId());
        }

        return idAndErrorResponse;
    }
}
