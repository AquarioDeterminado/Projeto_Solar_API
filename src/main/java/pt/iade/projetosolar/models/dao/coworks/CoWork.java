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

    @OneToMany(mappedBy = "coworkSpace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStationsSpace> coworkSpaces = new ArrayList<>();

    @OneToMany(mappedBy = "coWork", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    @ManyToMany(mappedBy = "coworkSpaces", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cowork_spaces_facilities",
            joinColumns = @JoinColumn(name = "cof_spc_id"),
            inverseJoinColumns = @JoinColumn(name = "cof_fac_id"))
    private ArrayList<Facility> facilities = new ArrayList<>();

    public WorkStationsSpace getSpace(int spaceId) {
        for (WorkStationsSpace coworkSpace : coworkSpaces)
            if (coworkSpace.getId() == spaceId)
                return coworkSpace;
        return null;
    }

    public List<Event> getEvents() { return events; }

    public List<Facility> getFacilities() {return facilities;}
}
