package pt.iade.projetosolar.models.exportInfo;

import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;

import java.util.ArrayList;

public class WorkStationsSpaceInfo {
    private final int  id;
    private final String name;
    private final String address;

    private final double occupationRate;

    public WorkStationsSpaceInfo(WorkStationsSpace space) {
        this.id = space.getId();
        this.name = space.getCoWork().getName();
        this.address = space.getCoWork().getLocation();
        this.occupationRate = space.getOccupancyRate();
    }

    public static ArrayList<WorkStationsSpaceInfo> getInfo(ArrayList<WorkStationsSpace> spaces) {
        ArrayList<WorkStationsSpaceInfo> spacesInfo = new ArrayList<>();
        for (WorkStationsSpace space : spaces) {
            spacesInfo.add( new WorkStationsSpaceInfo(space));
        }
        return spacesInfo;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getAddress() {return address;}
    public double getOccupationRate() {return occupationRate;}


}
