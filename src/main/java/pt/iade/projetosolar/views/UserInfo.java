package pt.iade.projetosolar.views;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.LogInController;


@RequestMapping(path = "/Solar/users")
@RestController
public class UserInfo{

    @PostMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    private void getUser (@RequestBody LogInController logIn) {

        if (logIn.checkCredentials()) {

        } else {

        }
    }
}

