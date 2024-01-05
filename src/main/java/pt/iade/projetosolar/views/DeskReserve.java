package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import pt.iade.projetosolar.controllers.DeskReserveController;
import pt.iade.projetosolar.controllers.repositories.WorkStationsSpaceRepository;


@RestController
@RequestMapping(path = "/Solar/deskReserve")
public class DeskReserve {

    private final WorkStationsSpaceRepository spacesRepository;
    private final DeskReserveController deskReserveController;


    @Autowired
    public DeskReserve(WorkStationsSpaceRepository spacesRepository) {
        this.spacesRepository = spacesRepository;
        this.deskReserveController = new DeskReserveController(spacesRepository);
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
