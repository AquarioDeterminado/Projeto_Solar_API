package pt.iade.projetosolar.controllers;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pt.iade.projetosolar.controllers.repositories.WorkStationsSpaceRepository;
import pt.iade.projetosolar.models.dao.WorkStation;
import pt.iade.projetosolar.models.dao.WorkStationsGroup;
import pt.iade.projetosolar.models.dao.WorkStationsSpace;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeskReserveController {

    private final WorkStationsSpaceRepository spaces;

    public DeskReserveController(WorkStationsSpaceRepository spaces) {
        this.spaces = spaces;
    }

    public Document getFloorPlanHTML(int spaceId) {
        WorkStationsSpace space = spaces.findById(spaceId).get();
        Document dom = getDOM(spaceId);
        Document finalFloorPlan = CheckReservedTables(dom);
        return finalFloorPlan;
    }



    private Document CheckReservedTables(Document dom) {
        Iterable<WorkStationsSpace> spaceList = spaces.findAll();
        for (WorkStationsSpace space : spaceList) {
            List<WorkStationsGroup> tableGroups = space.getTableGroups();
            for (WorkStationsGroup group : tableGroups) {
                List<WorkStation> tables = group.getTables();
                for (WorkStation table : tables) {
                    String tableId = "table" + table.getId();

                    String tableClass = "freeTable";
                    if (table.isReserved())
                        tableClass = "reservedTable";

                    Element tableElement = dom.getElementById(tableId);
                    assert tableElement != null;
                    tableElement.removeClass("notReservable");
                    tableElement.addClass(tableClass);
                }
            }
        }
        return dom;
    }

    private Document getDOM(int spaceId) {
        File html = new File("src/main/resources/static/floorplans/" + spaceId + ".html");

        Document doc = null;
        try {
            doc = Jsoup.parse(html, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Document cssDoc = getStyleSheet();
        doc.head().append("<style>" + cssDoc.body().html() + "</style>");

        return doc;
    }

    private Document getStyleSheet() {
        File css = new File("src/main/resources/static/floorplans/floorPlanStyle.css");

        Document doc = null;
        try {
            doc = Jsoup.parse(css, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return doc;
    }

    public int getOccupancyRate(int spaceId) {
        WorkStationsSpace space = spaces.findById(spaceId).get();
        ArrayList<WorkStation> tables = space.getTables();

        int totalTables = tables.size();
        int reservedTables = 0;
        for (WorkStation table : tables) {
            if (table.isReserved())
                reservedTables++;
        }

        return reservedTables / totalTables;
    }


}
