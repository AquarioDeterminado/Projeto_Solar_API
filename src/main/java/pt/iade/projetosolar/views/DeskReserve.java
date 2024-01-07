package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import pt.iade.projetosolar.controllers.DeskReserveController;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.controllers.repositories.WorkStationsSpaceRepository;
import pt.iade.projetosolar.models.dao.coworks.CoWork;
import pt.iade.projetosolar.models.dao.subscriptions.Subscription;
import pt.iade.projetosolar.models.dao.subscriptions.SubscriptionRecord;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;
import pt.iade.projetosolar.models.importInfo.UserId;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/deskReserve")
public class DeskReserve {

    private final WorkStationsSpaceRepository spacesRepository;
    private final DeskReserveController deskReserveController;
    private final UserRepository userRepository;


    @Autowired
    public DeskReserve(WorkStationsSpaceRepository spacesRepository, UserRepository userRepository) {
        this.spacesRepository = spacesRepository;
        this.deskReserveController = new DeskReserveController(spacesRepository);
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/showSpaceLayout/spaceslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSpaces(@PathVariable UserId userId) {
        User user = userRepository.findById(userId.id()).get();

        ArrayList<WorkStationsSpace> spaces = user.getAcessibleSpaces();

        return deskReserveController.getFloorPlanJSON(spaceId);
    }

    @GetMapping(path = "/showSpaceLayout/{spaceId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getFloorPlan(@PathVariable int spaceId) {
        return deskReserveController.getFloorPlanHTML(spaceId).html();
    }

    @GetMapping(path = "/getOccupancyRate/{spaceId}", produces = MediaType.TEXT_HTML_VALUE)
    public int getOccupancyRate(@PathVariable int spaceId) {
        return deskReserveController.getOccupancyRate(spaceId);
    }

}
