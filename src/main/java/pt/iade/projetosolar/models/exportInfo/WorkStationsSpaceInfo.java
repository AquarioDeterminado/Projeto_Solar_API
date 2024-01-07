package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;

import java.util.ArrayList;

public class WorkStationsSpaceInfo {
    private final int  id;
    private final String name;
    private final String address;

    public WorkStationsSpaceInfo(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static ArrayList<WorkStationsSpaceInfo> getInfo(ArrayList<WorkStationsSpace> spaces) {
        ArrayList<WorkStationsSpaceInfo> spacesInfo = new ArrayList<>();
        for (WorkStationsSpace space : spaces) {
            spacesInfo.add(new WorkStationsSpaceInfo(
                space.getId(),
                space.getCoWork().getName(),
                space.getCoWork().getLocation()
            ));
        }
        return spacesInfo;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}


}
