package pt.iade.projetosolar.models.dao.coworks;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.events.Event;
import pt.iade.projetosolar.models.dao.utils.Facility;
import pt.iade.projetosolar.models.dao.workstations.WorkStationsSpace;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cowork_space")
public class CoWork {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "spc_id") private int id;

    @Column (name = "spc_name") private String name;

    @Column (name = "spc_location") private String location;

    @Column (name = "spc_max_capacity") private int maxCapacity;

    @Column (name = "spc_site") private String webSite;

    @Column (name = "spc_phone") private String phone;

    @Column (name = "spc_email") private String email;

    @Column (name = "spc_active") private boolean isActive;

    @OneToMany(mappedBy = "coWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStationsSpace> coworkSpaces = new ArrayList<>();

    @OneToMany(mappedBy = "coWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "coWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CoWorksFacilities> facilities = new ArrayList<>();

    public WorkStationsSpace getSpace(int spaceId) {
        for (WorkStationsSpace coworkSpace : coworkSpaces)
            if (coworkSpace.getId() == spaceId)
                return coworkSpace;
        return null;
    }

    public List<Event> getEvents() { return events; }

    public List<CoWorksFacilities> getFacilities() {return facilities;}

    public String getLocation() {
        return location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getWebSite() {
        return webSite;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<WorkStationsSpace> getCoworkSpaces() {
        return coworkSpaces;
    }

    public int getId() {return id;}

    public String getName() {return name;}
}
