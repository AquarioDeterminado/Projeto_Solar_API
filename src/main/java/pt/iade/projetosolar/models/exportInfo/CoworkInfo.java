package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.coworks.CoWork;

import java.awt.*;
import java.util.ArrayList;

public class CoworkInfo {

    private final int id;
    private String name;
    private static Image logo;

    public CoworkInfo(CoWork coWork)
    {
        this.id = coWork.getId();
        this.name = coWork.getName();
        //this.logo = coWork.getLogo(); TODO
    }

    public static Image getLogo() {
        return logo;
    }

    public static void setLogo(Image logo) {
        CoworkInfo.logo = logo;
    }

    public static ArrayList<CoworkInfo> getCoWorkInfo(ArrayList<CoWork> nearCoWorks) {
        ArrayList<CoworkInfo> coworks = new ArrayList<>();
        for (CoWork cowork : nearCoWorks) {
            coworks.add(new CoworkInfo(cowork));
        }
        return coworks;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
