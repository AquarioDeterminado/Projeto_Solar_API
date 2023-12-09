package pt.iade.projetosolar.views;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.LogInController;

public class UserInfo {

    @RequestMapping(path = "/Solar/user")
    @RestController
    public class getUserController{

        @PostMapping(path = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
        private void getUser (@RequestBody LogInController logIn) {

            if (logIn.checkCredentials()) {

            } else {

            }
        }
    }
}
