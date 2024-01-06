package pt.iade.projetosolar.models.dao.workstations;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workstations_group")
public class WorkStationsGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "wgr_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wgr_wsp_id")
    private WorkStationsSpace workStationsSpace;

    @OneToMany(mappedBy = "workStationsGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStation> workstations = new ArrayList<>();

    public List<WorkStation> getTables() { return workstations; }
}
