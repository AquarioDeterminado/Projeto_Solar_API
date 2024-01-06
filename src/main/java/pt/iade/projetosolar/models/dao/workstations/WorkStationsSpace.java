package pt.iade.projetosolar.models.dao.workstations;

import jakarta.persistence.*;
import pt.iade.projetosolar.models.dao.coworks.CoWork;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workstations_space")
public class WorkStationsSpace {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "wsp_id") private int id;

    @ManyToOne
    @JoinColumn(name = "wsp_spc_id")
    private CoWork coworkSpace;

    @OneToMany(mappedBy = "workStationsSpace", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WorkStationsGroup> groups = new ArrayList<>();

    public int getId() { return id; }

    public List<WorkStationsGroup> getTableGroups() { return groups; }

    public ArrayList<WorkStation> getTables() {
        ArrayList<WorkStation> tables = new ArrayList<>();

        for (WorkStationsGroup group : groups) {
            tables.addAll(group.getTables());
        }
        return tables;
    }
}
