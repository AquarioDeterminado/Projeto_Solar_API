package pt.iade.projetosolar.views;

import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.repositories.CoWorkRepository;

@RequestMapping(path = "/Solar/coworkspaces")
@RestController
public class CoWorkSpaceInfo {

    private final CoWorkRepository coWorkSpaceRepository;

    public CoWorkSpaceInfo(CoWorkRepository coWorkSpaceRepository) {
        this.coWorkSpaceRepository = coWorkSpaceRepository;
    }

    public static class getFloorPlan {
        public String coWorkId;
        public String spaceId;
    }
}
