package pt.iade.projetosolar.models.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;
import pt.iade.projetosolar.controllers.repositories.WorkStationsSpaceRepository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cowork_space")
public class CoWork {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "spc_id") private int id;

    @Column (name = "spc_name") private String name;

    @Column (name = "spc_locations") private String location;

    @Column (name = "spc_max_capacity") private int maxCapacity;

    @Column (name = "spc_site") private String webSite;

    @Column (name = "spc_phone") private String phone;

    @Column (name = "spc_email") private String email;

    @Column (name = "spc_active") private boolean isActive;

    @OneToMany(mappedBy = "coworkSpace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStationsSpace> coworkSpaces = new ArrayList<>();

    public WorkStationsSpace getSpace(int spaceId) {
        for (WorkStationsSpace coworkSpace : coworkSpaces)
            if (coworkSpace.getId() == spaceId)
                return coworkSpace;
        return null;
    }
}
