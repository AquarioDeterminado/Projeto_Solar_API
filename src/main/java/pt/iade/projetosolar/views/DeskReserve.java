package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import pt.iade.projetosolar.controllers.DeskReserveController;
import pt.iade.projetosolar.controllers.repositories.UserRepository;
import pt.iade.projetosolar.controllers.repositories.WorkStationRepository;
import pt.iade.projetosolar.controllers.repositories.WorkStationsSpaceRepository;
import pt.iade.projetosolar.models.dao.users.User;
import pt.iade.projetosolar.models.dao.workstations.WorkStation;
import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;
import pt.iade.projetosolar.models.exportInfo.WorkStationsSpaceInfo;
import pt.iade.projetosolar.models.importInfo.Id;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "/deskReserve")
public class DeskReserve {

    private final WorkStationsSpaceRepository spacesRepository;
    private final WorkStationRepository workStationRepository;
    private final DeskReserveController deskReserveController;
    private final UserRepository userRepository;


    @Autowired
    public DeskReserve(WorkStationsSpaceRepository spacesRepository, WorkStationRepository workStationRepository, UserRepository userRepository) {
        this.spacesRepository = spacesRepository;
        this.deskReserveController = new DeskReserveController(spacesRepository);
        this.workStationRepository = workStationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/spaceslist", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<WorkStationsSpaceInfo> getSpaces(@RequestBody Id id) {
        User user = userRepository.findById(id.id()).get();

        ArrayList<WorkStationsSpace> spaces = user.getAcessibleSpaces();
        ArrayList<WorkStationsSpaceInfo> response = WorkStationsSpaceInfo.getInfo(spaces);

        return response;
    }

    @PostMapping(path = "/showSpaceLayout/{spaceId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getFloorPlan(@PathVariable int spaceId, @RequestBody Id id) {
        return deskReserveController.getFloorPlanHTML(spaceId, id.id()).html();
    }

    @GetMapping(path = "/getOccupancyRate/{spaceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getOccupancyRate(@PathVariable int spaceId) {
        return deskReserveController.getOccupancyRate(spaceId);
    }

    @PostMapping(path = "/reserveTable/{tableId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public int reserveTable(@PathVariable int tableId, @RequestBody Id userId) {
        WorkStation table = workStationRepository.findById(tableId).get();
        User user = userRepository.findById(userId.id()).get();

        int id;
        if(table.isReserved()){
            id = -1;
        } else {
            id = tableId;
            table.reserve(user);
            workStationRepository.save(table);
        }

        return id;
    }

}
