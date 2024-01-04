package pt.iade.projetosolar.models.dao;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workstation")
public class WorkStation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "wks_id") private int id;

    @ManyToOne
    @JoinColumn(name = "wks_wrg_id")
    private WorkStationsGroup workStationsGroup;

    @OneToMany(mappedBy = "workStation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStationUse> uses = new ArrayList<>();

    public int getId() { return id; }

    public boolean isReserved() {
        for (WorkStationUse use : uses)
            if (use.getEndTime() == null)
                return true;

        return false;
    }
}
