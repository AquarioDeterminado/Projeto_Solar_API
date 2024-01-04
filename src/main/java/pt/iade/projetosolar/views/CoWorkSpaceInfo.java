package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.projetosolar.controllers.repositories.CoWorkRepository;
import pt.iade.projetosolar.models.dao.CoWork;
import pt.iade.projetosolar.models.dao.WorkStationsSpace;

import java.util.Optional;

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
