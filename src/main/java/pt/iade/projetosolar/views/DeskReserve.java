package pt.iade.projetosolar.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.jsoup.nodes.Document;
import pt.iade.projetosolar.controllers.DeskReserveMap;
import pt.iade.projetosolar.controllers.repositories.WorkStationsSpaceRepository;
import pt.iade.projetosolar.models.dao.WorkStationsSpace;

@RestController
@RequestMapping(path = "/Solar/deskReserve")
public class DeskReserve {

    private final WorkStationsSpaceRepository spacesRepository;
    private final DeskReserveMap deskReserveMap;


    @Autowired
    public DeskReserve(WorkStationsSpaceRepository spacesRepository) {
        this.spacesRepository = spacesRepository;
        deskReserveMap = new DeskReserveMap(spacesRepository);
    }

    @GetMapping(path = "/showSpaceLayout/{spaceId}", produces = MediaType.TEXT_HTML_VALUE)
    public String getFloorPlan(@PathVariable int spaceId) {
        WorkStationsSpace space = spacesRepository.findById(spaceId).get();
        Document dom = deskReserveMap.getDOM(spaceId);
        Document finalFloorPlan = deskReserveMap.CheckReservedTables(dom);
        return finalFloorPlan.html();
    }

    @GetMapping(path = "/showSpaceLayout/style", produces = MediaType.TEXT_HTML_VALUE)
    public String getHtmlStyle() {
        Document style = deskReserveMap.getStyleSheet();
        return style.html();
    }

}
