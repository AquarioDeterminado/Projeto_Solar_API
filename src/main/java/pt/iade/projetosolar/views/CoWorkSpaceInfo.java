package pt.iade.projetosolar.views;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.CoworkController;
import pt.iade.projetosolar.controllers.repositories.CoWorkRepository;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.exportInfo.CoworkInfo;
import pt.iade.projetosolar.models.importInfo.Id;

import java.util.ArrayList;

@RequestMapping(path = "/coworkspaces")
@RestController
public class CoWorkSpaceInfo {

    private final CoWorkRepository coWorkSpaceRepository;
    private final UserRepository userRepository;

    public CoWorkSpaceInfo(CoWorkRepository coWorkSpaceRepository, UserRepository userRepository) {
        this.coWorkSpaceRepository = coWorkSpaceRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CoworkInfo> getCoworkSpaces(@RequestBody Id id) {
        ArrayList<CoworkInfo> coWorks = new ArrayList<>();
        CoworkController coworkController = new CoworkController(coWorkSpaceRepository, userRepository);

        ArrayList<CoWork> nearCoWorks = coworkController.getNearCoWorks(id.id());
        coWorks = CoworkInfo.getCoWorkInfo(nearCoWorks);

        return coWorks;
    }

}
